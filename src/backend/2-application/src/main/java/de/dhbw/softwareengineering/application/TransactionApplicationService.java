package de.dhbw.softwareengineering.application;

import de.dhbw.softwareengineering.adapters.transaction.mapper.CreateDTOToTransactionMapper;
import de.dhbw.softwareengineering.adapters.transaction.TransactionBaseDTO;
import de.dhbw.softwareengineering.adapters.transaction.TransactionCreateDTO;
import de.dhbw.softwareengineering.adapters.transaction.TransactionUpdateDTO;
import de.dhbw.softwareengineering.validation.annotations.ValidAccountName;
import de.dhbw.softwareengineering.validation.annotations.ValidInstitutionName;
import de.dhbw.softwareengineering.constants.Constants;
import de.dhbw.softwareengineering.domain.account.Account;
import de.dhbw.softwareengineering.domain.institution.Institution;
import de.dhbw.softwareengineering.domain.institution.InstitutionRepository;
import de.dhbw.softwareengineering.domain.services.CompatibilityService;
import de.dhbw.softwareengineering.domain.transaction.Transaction;
import de.dhbw.softwareengineering.domain.transaction.TransactionRepository;
import de.dhbw.softwareengineering.enums.InstitutionType;
import de.dhbw.softwareengineering.enums.TransactionType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Validated
public class TransactionApplicationService {
    private final TransactionRepository transactionRepository;

    private final InstitutionRepository institutionRepository;

    private final CreateDTOToTransactionMapper createMapper;

    private final CompatibilityService domainService;

    @Autowired
    public TransactionApplicationService(TransactionRepository transactionRepository, InstitutionRepository institutionRepository, CreateDTOToTransactionMapper createMapper, CompatibilityService domainService) {
        this.transactionRepository = transactionRepository;
        this.institutionRepository = institutionRepository;
        this.createMapper = createMapper;
        this.domainService = domainService;
    }

    public List<Transaction> getAllTransactions(@ValidInstitutionName String institutionName,
                                                @ValidAccountName String accountName){
        return this.transactionRepository.findByInstitutionAndAccount(institutionName, accountName);
    }

    public Optional<Transaction> getTransactionById(@ValidInstitutionName String institutionName,
                                                    @ValidAccountName String accountName,
                                                    @NotNull UUID transactionId){
        return this.transactionRepository.findByInstitutionAndAccountAndId(institutionName, accountName, transactionId);
    }

    public void deleteTransaction(@ValidInstitutionName String institutionName,
                                  @ValidAccountName String accountName,
                                  @NotNull UUID transactionId) throws Exception{
        Institution institution = this.institutionRepository.findByName(institutionName).orElseThrow(IllegalArgumentException::new);
        List<Account> accounts = institution.getAccounts();
        Account account = accounts.stream().filter(a -> a.getAccountName().equals(accountName)).findFirst().orElseThrow(IllegalArgumentException::new);
        List<Transaction> transactions = account.getTransactions();

        if(containsId(transactions, transactionId)){
            accounts.removeIf(a -> Objects.equals(a.getAccountName(), accountName));
            transactions.removeIf(t -> t.getId().equals(transactionId));
            account.updateTransactions(transactions);
            accounts.add(account);
            institution.updateAccounts(accounts);

            this.institutionRepository.save(institution);
            return;
        }
        throw new IllegalArgumentException("Transaction does not exist!");
    }


    public Transaction createTransaction(@Valid TransactionCreateDTO transaction) throws Exception{

        Institution institution = this.institutionRepository.findByName(transaction.getInstitutionName()).orElseThrow(IllegalArgumentException::new);
        List<Account> institutionAccounts = institution.getAccounts();
        Account account = institutionAccounts.stream().filter(a -> a.getAccountName().equals(transaction.getAccountName())).findFirst().orElseThrow(IllegalArgumentException::new);
        List<Transaction> accountTransactions = account.getTransactions();

        Transaction toCreate = this.createMapper.apply(transaction, account);

        if(isInputInvalid(toCreate, institution.getInstitutionType())){
            throw new IllegalArgumentException("Wrong transaction input!");
        }

        institutionAccounts.removeIf(a -> Objects.equals(a.getAccountName(), transaction.getAccountName()));
        accountTransactions.add(toCreate);
        account.updateTransactions(accountTransactions);
        institutionAccounts.add(account);
        institution.updateAccounts(institutionAccounts);
        this.institutionRepository.save(institution);
        return toCreate;
    }

    public Transaction updateTransaction(@Valid TransactionUpdateDTO transaction) throws Exception{

        if(isInputInvalid(transaction)){
            throw new IllegalArgumentException("Wrong transaction input!");
        }

        //Get institution -> accounts -> account -> transactions -> transaction
        Institution institution = this.institutionRepository.findByName(transaction.getInstitutionName()).orElseThrow(IllegalArgumentException::new);
        List<Account> institutionAccounts = institution.getAccounts();
        Account account = institutionAccounts.stream().filter(a -> a.getAccountName().equals(transaction.getAccountName())).findFirst().orElseThrow(IllegalArgumentException::new);
        List<Transaction> accountTransactions = account.getTransactions();

        //Transaction toUpdate = accountTransactions.stream().filter(t -> t.getId() == transaction.getTransactionId()).findFirst().orElseThrow(IllegalArgumentException::new);

        if(containsId(accountTransactions, transaction.getTransactionId())){
            Transaction toUpdate = this.transactionRepository.findByAccountAndId(account.getId(), transaction.getTransactionId()).orElseThrow(IllegalArgumentException::new);
            //Check input
            if(!domainService.isInstitutionTypeCompatibleWithTransactionType(institution.getInstitutionType(), transaction.getTransaction().getTransactionType())){
                throw new IllegalArgumentException("Types not compatible!");
            }

            // Remove account from institution
            institutionAccounts.removeIf(a -> Objects.equals(a.getAccountName(), transaction.getAccountName()));
            //remove transaction
            accountTransactions.removeIf(t -> t.getId() == transaction.getTransactionId());
            //edit/create new transaction -> add to account
            Transaction newTransaction = updateTransactionProperties(toUpdate, transaction);
            //add account to institution
            accountTransactions.add(newTransaction);
            account.updateTransactions(accountTransactions);
            institutionAccounts.add(account);
            institution.updateAccounts(institutionAccounts);
            //save institution
            this.institutionRepository.save(institution);

            return newTransaction;
        }
        throw new IllegalArgumentException("Transaction does not exist!");
    }


    private Transaction updateTransactionProperties(Transaction toUpdate, TransactionUpdateDTO dto){
        TransactionBaseDTO parameters = dto.getTransaction();
        if(parameters.getDescription() != null){
            toUpdate.setDescription(parameters.getDescription());
        }
        if(parameters.getAmount() != null){
            toUpdate.setAmount(parameters.getAmount());
        }
        if(parameters.getUnit() != null){
            toUpdate.setUnit(parameters.getUnit());
        }
        if(parameters.getTimestamp() != null){
            toUpdate.setTimestamp(parameters.getTimestamp());
        }
        if(parameters.getTransactionType() != null){
            toUpdate.setTransactionType(parameters.getTransactionType());
        }

        return toUpdate;
    }


    private boolean containsId(List<Transaction> transactions, UUID id){
        return transactions.stream().anyMatch(transaction -> transaction.getId().equals(id));
    }

    private boolean isInputInvalid(Transaction transaction, InstitutionType institutionType){
        UUID id = transaction.getId();
        String accountName = transaction.getAccount().getAccountName();
        String institutionName = transaction.getAccount().getInstitution().getName();
        String description = transaction.getDescription();
        Double amount = transaction.getAmount();
        String unit = transaction.getUnit();
        Timestamp time = transaction.getTimestamp();
        TransactionType type = transaction.getTransactionType();

        if(id == null || amount.isNaN() || amount.isInfinite() || time == null){
            return true;
        }
        if(!domainService.isInstitutionTypeCompatibleWithTransactionType(institutionType, type)){
            return true;
        }
        if(accountName == null || accountName.isEmpty() || accountName.isBlank()){
            return true;
        }
        if(institutionName == null || institutionName.isEmpty() || institutionName.isBlank()){
            return true;
        }
        if(description.length() > Constants.DESCRIPTION_MAX_LENGTH){
            return true;
        }
        if(unit == null || unit.isBlank() || unit.isEmpty() || unit.length() > Constants.UNIT_MAX_LENGTH){
            return true;
        }
        return false;
    }

    private boolean isInputInvalid(TransactionUpdateDTO dto){
        UUID id = dto.getTransactionId();
        String institutionName = dto.getInstitutionName();
        String accountName = dto.getAccountName();
        if(id == null || institutionName == null || accountName == null || institutionName.isEmpty() || institutionName.isBlank() || accountName.isBlank() || accountName.isEmpty()){
            return true;
        }
        if(dto.getTransaction().getDescription().length() > Constants.DESCRIPTION_MAX_LENGTH){
            return true;
        }
        Double amount = dto.getTransaction().getAmount();
        if(amount != null && (amount.isInfinite() || amount.isNaN())){
            return true;
        }
        String unit = dto.getTransaction().getUnit();
        if(unit != null && (unit.isEmpty() || unit.isBlank())){
            return true;
        }

        return false;
    }

}

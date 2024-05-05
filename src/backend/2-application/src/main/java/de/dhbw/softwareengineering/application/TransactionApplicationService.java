package de.dhbw.softwareengineering.application;

import de.dhbw.softwareengineering.adapters.account.Mapper.CreateDTOToAccountMapper;
import de.dhbw.softwareengineering.adapters.transaction.Mapper.CreateDTOToTransactionMapper;
import de.dhbw.softwareengineering.adapters.transaction.TransactionBaseDTO;
import de.dhbw.softwareengineering.adapters.transaction.TransactionCreateDTO;
import de.dhbw.softwareengineering.adapters.transaction.TransactionUpdateDTO;
import de.dhbw.softwareengineering.domain.account.Account;
import de.dhbw.softwareengineering.domain.account.AccountRepository;
import de.dhbw.softwareengineering.domain.institution.Institution;
import de.dhbw.softwareengineering.domain.institution.InstitutionRepository;
import de.dhbw.softwareengineering.domain.transaction.Transaction;
import de.dhbw.softwareengineering.domain.transaction.TransactionRepository;
import de.dhbw.softwareengineering.enums.InstitutionType;
import de.dhbw.softwareengineering.enums.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionApplicationService {
    private final TransactionRepository transactionRepository;

    private final InstitutionRepository institutionRepository;

    private final CreateDTOToTransactionMapper createMapper;

    @Autowired
    public TransactionApplicationService(TransactionRepository transactionRepository, InstitutionRepository institutionRepository, CreateDTOToTransactionMapper createMapper) {
        this.transactionRepository = transactionRepository;
        this.institutionRepository = institutionRepository;
        this.createMapper = createMapper;
    }

    public List<Transaction> getAllTransactions(String institutionName, String accountName){
        return this.transactionRepository.findByInstitutionAndAccount(institutionName, accountName);
    }

    public Optional<Transaction> getTransactionById(String institutionName, String accountName, UUID transactionId){
        return this.transactionRepository.findByInstitutionAndAccountAndId(institutionName, accountName, transactionId);
    }

    public void deleteTransaction(String institutionName, String accountName, UUID transactionId) throws Exception{
        Institution institution = this.institutionRepository.findByName(institutionName).orElseThrow(IllegalArgumentException::new);
        List<Account> accounts = institution.getAccounts();
        Account account = accounts.stream().filter(a -> a.getAccountName().equals(accountName)).findFirst().orElseThrow(IllegalArgumentException::new);
        List<Transaction> transactions = account.getTransactions();

        if(containsId(transactions, transactionId)){
            accounts.removeIf(a -> Objects.equals(a.getAccountName(), accountName));
            transactions.removeIf(t -> t.getId().equals(transactionId));
            account.setTransactions(transactions);
            accounts.add(account);
            institution.setAccounts(accounts);
            this.institutionRepository.save(institution);
            return;
        }
        throw new IllegalArgumentException("Transaction does not exist!");
    }


    public Transaction createTransaction(TransactionCreateDTO transaction) throws Exception{

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
        account.setTransactions(accountTransactions);
        institutionAccounts.add(account);
        institution.setAccounts(institutionAccounts);
        this.institutionRepository.save(institution);
        return toCreate;
    }

    public Transaction updateTransaction(TransactionUpdateDTO transaction) throws Exception{

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
            if(!isTransactionTypeCompatible(transaction.getTransaction().getTransactionType(), institution.getInstitutionType())){
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
            account.setTransactions(accountTransactions);
            institutionAccounts.add(account);
            institution.setAccounts(institutionAccounts);
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
        if(!isTransactionTypeCompatible(type, institutionType)){
            return true;
        }
        if(accountName == null || accountName.isEmpty() || accountName.isBlank()){
            return true;
        }
        if(institutionName == null || institutionName.isEmpty() || institutionName.isBlank()){
            return true;
        }
        if(description.length() > 255){
            return true;
        }
        if(unit == null || unit.isBlank() || unit.isEmpty() || unit.length() > 10){
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
        if(dto.getTransaction().getDescription().length() > 255){
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

    private boolean isTransactionTypeCompatible(TransactionType transactionType, InstitutionType institutionType){
        if(institutionType == InstitutionType.BANK){
            return transactionType != TransactionType.BUY && transactionType != TransactionType.SELL;
        }
        else{
            return transactionType != TransactionType.INCOME && transactionType != TransactionType.EXPENSE;
        }
    }
}

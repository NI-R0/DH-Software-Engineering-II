package de.dhbw.softwareengineering.application;

import de.dhbw.softwareengineering.adapters.transaction.mapper.CreateDTOToTransactionMapper;
import de.dhbw.softwareengineering.adapters.transaction.TransactionCreateDTO;
import de.dhbw.softwareengineering.adapters.transaction.TransactionUpdateDTO;
import de.dhbw.softwareengineering.adapters.transaction.mapper.UpdateDTOToTransactionMapper;
import de.dhbw.softwareengineering.domain.services.ValidationService;
import de.dhbw.softwareengineering.exceptions.ObjectNotFoundException;
import de.dhbw.softwareengineering.validation.annotations.ValidAccountName;
import de.dhbw.softwareengineering.validation.annotations.ValidInstitutionName;
import de.dhbw.softwareengineering.domain.account.Account;
import de.dhbw.softwareengineering.domain.institution.Institution;
import de.dhbw.softwareengineering.domain.institution.InstitutionRepository;
import de.dhbw.softwareengineering.domain.services.CompatibilityService;
import de.dhbw.softwareengineering.domain.transaction.Transaction;
import de.dhbw.softwareengineering.domain.transaction.TransactionRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Validated
public class TransactionApplicationService {
    private final TransactionRepository transactionRepository;
    private final InstitutionRepository institutionRepository;
    private final CreateDTOToTransactionMapper createMapper;
    private final UpdateDTOToTransactionMapper updateMapper;
    private final CompatibilityService compatibilityService;
    private final ValidationService validationService;

    @Autowired
    public TransactionApplicationService(TransactionRepository transactionRepository, InstitutionRepository institutionRepository, CreateDTOToTransactionMapper createMapper, UpdateDTOToTransactionMapper updateMapper, CompatibilityService compatibilityService, ValidationService validationService) {
        this.transactionRepository = transactionRepository;
        this.institutionRepository = institutionRepository;
        this.createMapper = createMapper;
        this.updateMapper = updateMapper;
        this.compatibilityService = compatibilityService;
        this.validationService = validationService;
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

        Institution institution = this.institutionRepository.findByName(institutionName)
                .orElseThrow(() -> new ObjectNotFoundException("Institution with name " + institutionName + " does not exist."));
        List<Account> accounts = institution.getAccounts();

        Account account = accounts.stream().filter(a -> a.getAccountName().equals(accountName)).findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("Account with name " + accountName + " does not exist."));
        List<Transaction> transactions = account.getTransactions();

        if(!containsId(transactions, transactionId)){
            throw new IllegalArgumentException("Transaction does not exist.");
        }

        accounts.remove(account);
        transactions.removeIf(t -> t.getId().equals(transactionId));
        account.updateTransactions(transactions);
        accounts.add(account);
        institution.updateAccounts(accounts);

        this.institutionRepository.save(institution);
        return;
    }


    public Transaction createTransaction(@Valid TransactionCreateDTO dto) throws Exception{

        Institution institution = this.institutionRepository.findByName(dto.getInstitutionName())
                .orElseThrow(() -> new ObjectNotFoundException("Institution with name " + dto.getInstitutionName() + " does not exist."));
        List<Account> accounts = institution.getAccounts();

        if(!this.compatibilityService.isInstitutionTypeCompatibleWithTransactionType(institution.getInstitutionType(), dto.getTransaction().getTransactionType())){
            throw new IllegalArgumentException("Types are not compatible.");
        }

        if(dto.getTransaction().getAmount() == 0.0){
            throw new IllegalArgumentException("Amount must not be 0.");
        }

        Account account = accounts.stream().filter(a -> a.getAccountName().equals(dto.getAccountName())).findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("Account with name " + dto.getAccountName() + " does not exist."));
        List<Transaction> transactions = account.getTransactions();

        Transaction toCreate = this.createMapper.apply(dto, account);
        this.validationService.validate(toCreate);

        accounts.remove(account);
        transactions.add(toCreate);
        account.updateTransactions(transactions);
        accounts.add(account);
        institution.updateAccounts(accounts);
        this.institutionRepository.save(institution);
        return toCreate;
    }

    public Transaction updateTransaction(@Valid TransactionUpdateDTO dto) throws Exception{

        //Get institution -> accounts -> account -> transactions -> transaction
        Institution institution = this.institutionRepository.findByName(dto.getInstitutionName())
                .orElseThrow(() -> new ObjectNotFoundException("Institution with name " + dto.getInstitutionName() + " does not exist."));
        List<Account> accounts = institution.getAccounts();

        if(dto.getTransaction().getAmount() == 0.0){
            throw new IllegalArgumentException("Amount must not be 0.");
        }

        //Check if new transaction type is compatible with institution
        if(dto.getTransaction().getTransactionType() != null){
            if(!compatibilityService.isInstitutionTypeCompatibleWithTransactionType(institution.getInstitutionType(), dto.getTransaction().getTransactionType())){
                throw new IllegalArgumentException("Types not compatible!");
            }
        }

        Account account = accounts.stream().filter(a -> a.getAccountName().equals(dto.getAccountName())).findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("Account with name " + dto.getAccountName() + " does not exist."));
        List<Transaction> transactions = account.getTransactions();

        if(!containsId(transactions, dto.getTransactionId())){
            throw new ObjectNotFoundException("Transaction with ID " + dto.getTransactionId() + " does not exist.");
        }

        Transaction toUpdate = this.transactionRepository.findByAccountAndId(account.getId(), dto.getTransactionId()).get();

        //accounts.removeIf(a -> Objects.equals(a.getAccountName(), dto.getAccountName()));
        //transactions.removeIf(t -> t.getId() == dto.getTransactionId());
        accounts.remove(account);
        transactions.remove(toUpdate);
        Transaction newTransaction = this.updateMapper.apply(toUpdate, dto);
        this.validationService.validate(newTransaction);
        transactions.add(newTransaction);
        account.updateTransactions(transactions);
        accounts.add(account);
        institution.updateAccounts(accounts);
        this.institutionRepository.save(institution);

        return newTransaction;
    }


    private boolean containsId(List<Transaction> transactions, UUID id){
        return transactions.stream().anyMatch(transaction -> transaction.getId().equals(id));
    }

}

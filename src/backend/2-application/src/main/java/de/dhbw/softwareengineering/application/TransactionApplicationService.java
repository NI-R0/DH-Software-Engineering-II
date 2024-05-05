package de.dhbw.softwareengineering.application;

import de.dhbw.softwareengineering.adapters.account.Mapper.CreateDTOToAccountMapper;
import de.dhbw.softwareengineering.adapters.transaction.Mapper.CreateDTOToTransactionMapper;
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
            transactions.removeIf(t -> t.getId() == transactionId);
            account.setTransactions(transactions);
            accounts.add(account);
            institution.setAccounts(accounts);
            this.institutionRepository.save(institution);
        }
    }


    public Transaction createTransaction(TransactionCreateDTO transaction) throws Exception{
        //check if input is correct

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
        return null;
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
        if(institutionType == InstitutionType.BANK){
            if(type == TransactionType.BUY || type == TransactionType.SELL){
                return true;
            }
        }
        else{
            if(type == TransactionType.INCOME || type == TransactionType.EXPENSE){
                return true;
            }
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


}

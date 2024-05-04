package de.dhbw.softwareengineering.domain.account;

import de.dhbw.softwareengineering.domain.transaction.TransactionEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {

    public List<AccountAggregate> findAllByInstitution(String institutionName);

    public Optional<AccountAggregate> findById(UUID id);

    public Optional<AccountAggregate> findByIdAndInstitution(String institutionName, UUID accountId);

    public Optional<AccountAggregate> findByNameAndInstitution(String institutionName, String accountName);




    /////////////////////////////////////////////////////






    /*public Optional<AccountAggregate> createAccount(String institutionName, AccountAggregate account);

    public Optional<AccountAggregate> editAccount(AccountAggregate account);

    public void deleteAccount(AccountAggregate account);

    public List<TransactionEntity> findAllTransactions(UUID accountId);
    public List<TransactionEntity> createTransaction(UUID accountId, TransactionEntity transaction);
    public List<TransactionEntity> updateTransaction(UUID accountId, TransactionEntity transaction);
    public List<TransactionEntity> deleteTransaction(UUID accountId, TransactionEntity transaction);
    public List<TransactionEntity> deleteAllTransactions(AccountAggregate account);

    public Optional<TransactionEntity> findTransactionById(UUID accountId, UUID id);*/
}

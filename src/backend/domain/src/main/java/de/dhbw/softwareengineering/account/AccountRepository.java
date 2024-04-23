package de.dhbw.softwareengineering.account;

import de.dhbw.softwareengineering.transaction.TransactionEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {
    public Optional<AccountAggregate> findById(UUID id);

    public Optional<AccountAggregate> findByIdAndInstitution(UUID institutionId, UUID accountId);

    public Optional<AccountAggregate> createAccount(UUID institutionId, AccountAggregate account);

    public Optional<AccountAggregate> editAccount(AccountAggregate account);

    public void deleteAccount(UUID id);

    public List<TransactionEntity> findAllTransactions(UUID accountId);
    public List<TransactionEntity> createTransaction(UUID accountId, TransactionEntity transaction);
    public List<TransactionEntity> updateTransaction(UUID accountId, TransactionEntity transaction);
    public List<TransactionEntity> deleteTransaction(UUID accountId, UUID id);

    public Optional<TransactionEntity> findTransactionById(UUID accountId, UUID id);
}

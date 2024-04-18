package de.dhbw.softwareengineering.account;

import de.dhbw.softwareengineering.transaction.TransactionEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {
    public Optional<AccountAggregate> findById(UUID id);

    public List<TransactionEntity> findAllTransactions();
    public List<TransactionEntity> createTransaction(TransactionEntity transaction);
    public List<TransactionEntity> updateTransaction(TransactionEntity transaction);
    public List<TransactionEntity> deleteTransaction(TransactionEntity transaction);
}

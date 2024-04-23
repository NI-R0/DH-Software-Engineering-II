package de.dhbw.softwareengineering.transaction;

import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository {
    public Optional<TransactionEntity> findById(UUID id);

    public Optional<TransactionEntity> findByIdAndAccount(UUID accountId, UUID transactionId);

    public void deleteTransaction(TransactionEntity entity);

    public Optional<TransactionEntity> editTransaction(TransactionEntity entity);

    public Optional<TransactionEntity> createTransaction(UUID accountId, TransactionEntity entity);
}

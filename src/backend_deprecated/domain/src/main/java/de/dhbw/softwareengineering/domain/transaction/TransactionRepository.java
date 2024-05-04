package de.dhbw.softwareengineering.domain.transaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository {
    public Optional<TransactionEntity> findById(UUID id);

    public Optional<TransactionEntity> findByIdAndAccount(UUID accountId, UUID transactionId);

    public List<TransactionEntity> findAllByAccountName(String accountName);

    public void deleteTransaction(TransactionEntity entity);

    public Optional<TransactionEntity> editTransaction(TransactionEntity entity);

    public Optional<TransactionEntity> createTransaction(UUID accountId, TransactionEntity entity);
}

package de.dhbw.softwareengineering.domain.transaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository {

    List<Transaction> findByAccount(UUID accountId);

    Optional<Transaction> findByAccountAndId(UUID accountId, UUID transactionId);

}

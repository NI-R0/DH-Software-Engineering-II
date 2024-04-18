package de.dhbw.softwareengineering.transaction;

import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository {
    public Optional<TransactionEntity> findById(UUID id);
}

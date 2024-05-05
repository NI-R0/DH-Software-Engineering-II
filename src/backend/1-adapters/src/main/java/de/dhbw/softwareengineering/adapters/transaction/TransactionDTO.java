package de.dhbw.softwareengineering.adapters.transaction;

import de.dhbw.softwareengineering.enums.TransactionType;

import java.sql.Timestamp;
import java.util.UUID;

public class TransactionDTO {

    private final UUID id;

    private final UUID accountId;

    private final String description;

    private final Double amount;

    private final String unit;

    private final Timestamp timestamp;

    private final TransactionType transactionType;

    public TransactionDTO(final UUID id, final UUID accountId, final String description, final Double amount, final String unit, final Timestamp timestamp, final TransactionType transactionType) {
        this.id = id;
        this.accountId = accountId;
        this.description = description;
        this.amount = amount;
        this.unit = unit;
        this.timestamp = timestamp;
        this.transactionType = transactionType;
    }

    public UUID getId() {
        return id;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public String getDescription() {
        return description;
    }

    public Double getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }
}

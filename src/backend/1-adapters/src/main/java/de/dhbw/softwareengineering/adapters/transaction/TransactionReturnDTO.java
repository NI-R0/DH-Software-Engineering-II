package de.dhbw.softwareengineering.adapters.transaction;

import de.dhbw.softwareengineering.enums.TransactionType;

import java.sql.Timestamp;
import java.util.UUID;

public class TransactionReturnDTO {

    private final UUID id;

    private final String accountName;

    private final String institutionName;

    private final String description;

    private final Double amount;

    private final String unit;

    private final Timestamp timestamp;

    private final TransactionType transactionType;

    @SuppressWarnings("unused")
    public TransactionReturnDTO(UUID id, String accountName, String institutionName, String description, Double amount, String unit, Timestamp timestamp, TransactionType transactionType) {
        this.id = id;
        this.accountName = accountName;
        this.institutionName = institutionName;
        this.description = description;
        this.amount = amount;
        this.unit = unit;
        this.timestamp = timestamp;
        this.transactionType = transactionType;
    }

    public UUID getId() {
        return id;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getInstitutionName() {
        return institutionName;
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

package de.dhbw.softwareengineering.adapters.transaction;

import de.dhbw.softwareengineering.enums.TransactionType;

import java.sql.Timestamp;

public class TransactionBaseDTO {

    private final String description;

    private final Double amount;

    private final String unit;

    private final Timestamp timestamp;

    private final TransactionType transactionType;

    public TransactionBaseDTO(String description, Double amount, String unit, Timestamp timestamp, TransactionType transactionType) {
        this.description = description;
        this.amount = amount;
        this.unit = unit;
        this.timestamp = timestamp;
        this.transactionType = transactionType;
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

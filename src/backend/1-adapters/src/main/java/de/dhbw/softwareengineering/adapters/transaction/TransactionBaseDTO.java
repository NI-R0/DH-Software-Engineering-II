package de.dhbw.softwareengineering.adapters.transaction;

import de.dhbw.softwareengineering.validation.annotations.ValidTransactionDescription;
import de.dhbw.softwareengineering.validation.annotations.ValidTransactionTimestamp;
import de.dhbw.softwareengineering.validation.annotations.ValidTransactionType;
import de.dhbw.softwareengineering.validation.annotations.ValidTransactionUnit;
import de.dhbw.softwareengineering.enums.TransactionType;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public class TransactionBaseDTO {

    @ValidTransactionDescription
    private final String description;
    @NotNull
    private final Double amount;
    @ValidTransactionUnit
    private final String unit;
    @ValidTransactionTimestamp
    private final Timestamp timestamp;
    @ValidTransactionType
    private final TransactionType transactionType;

    @SuppressWarnings("unused")
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

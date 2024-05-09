package de.dhbw.softwareengineering.adapters.transaction;

import de.dhbw.softwareengineering.annotations.ValidTransactionDescription;
import de.dhbw.softwareengineering.annotations.ValidTransactionType;
import de.dhbw.softwareengineering.annotations.ValidTransactionUnit;
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
    @NotNull
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

package de.dhbw.softwareengineering.transaction;

import de.dhbw.softwareengineering.enums.TransactionType;
import de.dhbw.softwareengineering.values.DescriptionValue;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public class TransactionEntity {
    private UUID transactionId;
    private DescriptionValue description; //ALS VALUE
    private Double amount;
    private String unit;
    private Timestamp timestamp;
    private TransactionType type;

    //GETTER AND SETTER

    public UUID getTransactionId(){
        return transactionId;
    }

    public DescriptionValue getDescription() {
        return description;
    }

    public void setDescription(DescriptionValue description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
}

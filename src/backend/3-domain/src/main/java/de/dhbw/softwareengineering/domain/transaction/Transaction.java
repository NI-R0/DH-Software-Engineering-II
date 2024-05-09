package de.dhbw.softwareengineering.domain.transaction;

import de.dhbw.softwareengineering.constants.Constants;
import de.dhbw.softwareengineering.domain.account.Account;
import de.dhbw.softwareengineering.enums.TransactionType;
import de.dhbw.softwareengineering.validation.annotations.*;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.Validate;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @ValidId
    @Column(name = "id", nullable = false)
    private UUID id;

    @Valid
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ValidTransactionDescription
    @Column(name = "description", length = Constants.DESCRIPTION_MAX_LENGTH)
    private String description;

    @NotNull
    @Column(name = "amount", nullable = false)
    private Double amount;

    @ValidTransactionUnit
    @Column(name = "unit", nullable = false, length = Constants.UNIT_MAX_LENGTH)
    private String unit;

    @ValidTransactionTimestamp
    @Column(name = "timestamp")
    private Timestamp timestamp;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    @ValidTransactionType
    private TransactionType transactionType;

    @SuppressWarnings("unused")
    protected Transaction(){}

    @SuppressWarnings("unused")
    public Transaction(final UUID id,
                       final Account account,
                       final String description,
                       final Double amount,
                       final String unit,
                       final Timestamp timestamp,
                       final TransactionType transactionType){
        Validate.notNull(id);
        Validate.notNull(account);
        //Description can be null or empty
        Validate.notNaN(amount);
        Validate.notBlank(unit);
        Validate.notNull(timestamp);
        Validate.notNull(transactionType);

        this.id = id;
        this.account = account;
        this.description = description;
        this.amount = amount;
        this.unit = unit;
        this.timestamp = timestamp;
        this.transactionType = transactionType;
    }

    public UUID getId() {
        return id;
    }

    public Account getAccount() {
        return account;
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

    public void setId(@ValidId UUID id) {
        this.id = id;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setDescription(@ValidTransactionDescription String description) {
        this.description = description;
    }

    public void setAmount(@NotNull Double amount) {
        this.amount = amount;
    }

    public void setUnit(@ValidTransactionUnit String unit) {
        this.unit = unit;
    }

    public void setTimestamp(@ValidTransactionTimestamp Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setTransactionType(@ValidInstitutionType TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}

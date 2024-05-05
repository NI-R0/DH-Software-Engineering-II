package de.dhbw.softwareengineering.domain.transaction;

import de.dhbw.softwareengineering.domain.account.Account;
import de.dhbw.softwareengineering.enums.TransactionType;
import jakarta.persistence.*;
import org.apache.commons.lang3.Validate;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "unit")
    private String unit;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    protected Transaction(){}

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
}

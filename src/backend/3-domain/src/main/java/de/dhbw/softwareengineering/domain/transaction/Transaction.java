package de.dhbw.softwareengineering.domain.transaction;

import de.dhbw.softwareengineering.domain.account.Account;
import de.dhbw.softwareengineering.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.apache.commons.lang3.Validate;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "amount", nullable = false)
    private Double amount;

    @NotBlank
    @NotEmpty
    @Size(max = 10, min = 2)
    @Column(name = "unit", nullable = false, length = 10)
    private String unit;

    @NotNull
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

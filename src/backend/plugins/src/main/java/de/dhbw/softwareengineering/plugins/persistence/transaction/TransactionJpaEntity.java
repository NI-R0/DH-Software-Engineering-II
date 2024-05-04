package de.dhbw.softwareengineering.plugins.persistence.transaction;

import de.dhbw.softwareengineering.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "TRANSACTION")
public class TransactionJpaEntity {
    @Id
    @Column(name = "TRANSACTIONID", nullable = false)
    private UUID id;

    @OnDelete(action = OnDeleteAction.RESTRICT)
    @Column(name = "ACCOUNT", nullable = true)
    private UUID account;

    @Size(max = 255)
    @Column(name = "DESCRIPTION")
    private String description;

    @NotNull
    @Column(name = "AMOUNT", nullable = false)
    private Double amount;

    @Size(max = 10)
    @NotNull
    @Column(name = "UNIT", nullable = false, length = 10)
    private String unit;
    @NotNull
    @Column(name = "TIMESTAMP", nullable = false)
    private Timestamp timestamp;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false)
    private TransactionType transactionType;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAccountid() {
        return account;
    }

    public void setAccountid(UUID accountid) {
        this.account = accountid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
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

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}







/*
@Entity
@Table(name = "TRANSACTION")
public class TransactionJpaEntity {
    @Id
    @Column(name = "TRANSACTIONID", nullable = false)
    private UUID id;

    @NotNull
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @Column(name = "ACCOUNTID", nullable = false)
    private UUID accountId;

    @Size(max = 255)
    @Column(name = "DESCRIPTION")
    private String description;

    @NotNull
    @Column(name = "AMOUNT", nullable = false)
    private Double amount;

    @Size(max = 10)
    @NotNull
    @Column(name = "UNIT", nullable = false, length = 10)
    private String unit;


    @NotNull
    @Column(name = "TIMESTAMP", nullable = false)
    private Timestamp timestamp;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false)
    private TransactionType transactionType;

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
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


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        TransactionJpaEntity that = (TransactionJpaEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}*/

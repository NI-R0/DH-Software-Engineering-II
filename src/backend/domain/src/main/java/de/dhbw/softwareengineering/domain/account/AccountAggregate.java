package de.dhbw.softwareengineering.domain.account;

import de.dhbw.softwareengineering.domain.transaction.TransactionEntity;
import de.dhbw.softwareengineering.domain.values.AccountOwnerNameValue;
import de.dhbw.softwareengineering.domain.values.NameValue;

import java.util.List;
import java.util.UUID;

public class AccountAggregate {
    private UUID accountId;
    private NameValue accountName;
    private Double balance;

    private AccountOwnerNameValue owner;

    public AccountOwnerNameValue getOwner() {
        return owner;
    }

    public void setOwner(AccountOwnerNameValue owner) {
        this.owner = owner;
    }

    private List<TransactionEntity> transactions;

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public NameValue getAccountName() {
        return accountName;
    }

    public void setAccountName(NameValue name) {
        this.accountName = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<TransactionEntity> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionEntity> transactions) {
        this.transactions = transactions;
    }
}

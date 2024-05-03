package de.dhbw.softwareengineering.domain.account;

import de.dhbw.softwareengineering.domain.transaction.TransactionEntity;
import de.dhbw.softwareengineering.domain.values.AccountOwnerNameValue;

import java.util.List;
import java.util.UUID;

public class AccountAggregate {
    private UUID accountId;
    private String accountName;
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

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String name) {
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

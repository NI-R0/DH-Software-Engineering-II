package de.dhbw.softwareengineering.account;

import de.dhbw.softwareengineering.transaction.TransactionEntity;
import de.dhbw.softwareengineering.values.NameValue;

import java.util.List;
import java.util.UUID;

public class AccountAggregate {
    private UUID accountId;
    private NameValue name;
    private Double balance;

    private List<TransactionEntity> transactions;

    public NameValue getName() {
        return name;
    }

    public void setName(NameValue name) {
        this.name = name;
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

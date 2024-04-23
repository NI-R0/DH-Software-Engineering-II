package de.dhbw.softwareengineering.account;

import de.dhbw.softwareengineering.transaction.TransactionDto;
import de.dhbw.softwareengineering.values.NameDto;

import java.util.List;
import java.util.UUID;

public class AccountDto {
    private UUID accountId;
    private NameDto name;
    private Double balance;

    private List<TransactionDto> transactions;

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public NameDto getName() {
        return name;
    }

    public void setName(NameDto name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<TransactionDto> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDto> transactions) {
        this.transactions = transactions;
    }
}

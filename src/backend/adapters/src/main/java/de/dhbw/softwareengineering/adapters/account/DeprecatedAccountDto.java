package de.dhbw.softwareengineering.adapters.account;

import de.dhbw.softwareengineering.adapters.transaction.TransactionDto;
import de.dhbw.softwareengineering.adapters.values.AccountOwnerNameDto;

import java.util.List;
import java.util.UUID;

public class DeprecatedAccountDto {
    private UUID accountId;
    private String accountName;

    private AccountOwnerNameDto owner;
    private Double balance;

    private List<TransactionDto> transactions;

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public AccountOwnerNameDto getOwner() {
        return owner;
    }

    public void setOwner(AccountOwnerNameDto owner) {
        this.owner = owner;
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

    public List<TransactionDto> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDto> transactions) {
        this.transactions = transactions;
    }
}

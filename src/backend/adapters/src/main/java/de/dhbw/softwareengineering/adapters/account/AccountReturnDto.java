package de.dhbw.softwareengineering.adapters.account;

import de.dhbw.softwareengineering.adapters.values.AccountOwnerNameDto;

import java.util.List;
import java.util.UUID;

public class AccountReturnDto {
    private String accountName;
    private AccountOwnerNameDto owner;
    private Double balance;
    private List<UUID> transactions;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public AccountOwnerNameDto getOwner() {
        return owner;
    }

    public void setOwner(AccountOwnerNameDto owner) {
        this.owner = owner;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<UUID> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<UUID> transactions) {
        this.transactions = transactions;
    }
}

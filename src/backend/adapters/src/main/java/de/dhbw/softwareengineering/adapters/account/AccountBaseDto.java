package de.dhbw.softwareengineering.adapters.account;

import de.dhbw.softwareengineering.adapters.values.AccountOwnerNameDto;

public class AccountBaseDto {
    private String accountName;
    private AccountOwnerNameDto owner;
    private Double balance;

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
}

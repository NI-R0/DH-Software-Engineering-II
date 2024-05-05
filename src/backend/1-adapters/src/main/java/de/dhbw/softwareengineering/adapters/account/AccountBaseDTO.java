package de.dhbw.softwareengineering.adapters.account;

import de.dhbw.softwareengineering.adapters.values.AccountOwnerNameDTO;

public class AccountBaseDTO {

    private final String accountName;

    private final AccountOwnerNameDTO owner;

    private final Double Balance;

    public AccountBaseDTO(String accountName, AccountOwnerNameDTO owner, Double balance) {
        this.accountName = accountName;
        this.owner = owner;
        Balance = balance;
    }

    public String getAccountName() {
        return accountName;
    }

    public AccountOwnerNameDTO getOwner() {
        return owner;
    }

    public Double getBalance() {
        return Balance;
    }
}

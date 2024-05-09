package de.dhbw.softwareengineering.adapters.account;

import de.dhbw.softwareengineering.adapters.values.AccountOwnerNameDTO;
import de.dhbw.softwareengineering.validation.annotations.ValidAccountName;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;

public class AccountBaseDTO {

    @ValidAccountName
    private final String accountName;

    @Valid
    private final AccountOwnerNameDTO owner;

    @PositiveOrZero
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

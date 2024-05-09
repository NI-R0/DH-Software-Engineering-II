package de.dhbw.softwareengineering.adapters.account;

import de.dhbw.softwareengineering.adapters.values.AccountOwnerNameDTO;
import de.dhbw.softwareengineering.annotations.ValidAccountName;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public class AccountInformationDTO {

    private final UUID id;

    private final String accountName;

    private final AccountOwnerNameDTO accountOwner;

    private final Double balance;

    private final List<UUID> transactions;

    public AccountInformationDTO(UUID id, String accountName, AccountOwnerNameDTO accountOwner, Double balance, List<UUID> transactions) {
        this.id = id;
        this.accountName = accountName;
        this.accountOwner = accountOwner;
        this.balance = balance;
        this.transactions = transactions;
    }

    public UUID getId() {
        return id;
    }

    public String getAccountName() {
        return accountName;
    }

    public AccountOwnerNameDTO getAccountOwner() {
        return accountOwner;
    }

    public Double getBalance() {
        return balance;
    }

    public List<UUID> getTransactions() {
        return transactions;
    }
}

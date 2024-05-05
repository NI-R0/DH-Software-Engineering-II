package de.dhbw.softwareengineering.adapters.account;

import de.dhbw.softwareengineering.adapters.transaction.TransactionDTO;
import de.dhbw.softwareengineering.adapters.values.AccountOwnerNameDTO;

import java.util.List;
import java.util.UUID;

public class AccountDTO {

    private final UUID id;

    private final String institutionName;

    private final String accountName;

    private final AccountOwnerNameDTO accountOwner;

    private final Double balance;

    private final List<TransactionDTO> transactions;

    public AccountDTO(final UUID id, final String institutionName, final String accountName, final AccountOwnerNameDTO accountOwner, final Double balance, final List<TransactionDTO> transactions) {
        this.id = id;
        this.institutionName = institutionName;
        this.accountName = accountName;
        this.accountOwner = accountOwner;
        this.balance = balance;
        this.transactions = transactions;
    }

    public UUID getId() {
        return id;
    }

    public String getInstitutionName() {
        return institutionName;
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

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }
}

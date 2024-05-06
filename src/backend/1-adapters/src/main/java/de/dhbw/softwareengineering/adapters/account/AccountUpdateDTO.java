package de.dhbw.softwareengineering.adapters.account;

import java.util.UUID;

public class AccountUpdateDTO {

    private final String institutionName;

    private final UUID accountId;

    private final AccountBaseDTO accountInfo;

    @SuppressWarnings("unused")
    public AccountUpdateDTO(String institutionName, UUID accountId, AccountBaseDTO accountInfo) {
        this.institutionName = institutionName;
        this.accountId = accountId;
        this.accountInfo = accountInfo;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public AccountBaseDTO getAccountInfo() {
        return accountInfo;
    }
}

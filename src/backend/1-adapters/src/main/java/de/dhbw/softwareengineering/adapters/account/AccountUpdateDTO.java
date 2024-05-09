package de.dhbw.softwareengineering.adapters.account;

import de.dhbw.softwareengineering.annotations.ValidId;
import de.dhbw.softwareengineering.annotations.ValidInstitutionName;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class AccountUpdateDTO {

    @ValidInstitutionName
    private final String institutionName;

    @ValidId
    private final UUID accountId;

    //DO NOT VALIDATE ON UPDATE
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

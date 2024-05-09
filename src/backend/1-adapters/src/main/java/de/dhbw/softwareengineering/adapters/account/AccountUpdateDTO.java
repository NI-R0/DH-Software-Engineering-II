package de.dhbw.softwareengineering.adapters.account;

import de.dhbw.softwareengineering.validation.annotations.ValidId;
import de.dhbw.softwareengineering.validation.annotations.ValidInstitutionName;
import jakarta.validation.Valid;

import java.util.UUID;

public class AccountUpdateDTO {

    @ValidInstitutionName
    private final String institutionName;

    @ValidId
    private final UUID accountId;

    private final AccountBaseDTO accountInfo;

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

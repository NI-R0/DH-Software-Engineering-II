package de.dhbw.softwareengineering.adapters.account;

import de.dhbw.softwareengineering.annotations.ValidInstitutionName;
import jakarta.validation.Valid;

public class AccountCreateDTO {

    @ValidInstitutionName
    private final String institutionName;
    @Valid
    private final AccountBaseDTO account;

    @SuppressWarnings("unused")
    public AccountCreateDTO(String institutionName, AccountBaseDTO account) {
        this.institutionName = institutionName;
        this.account = account;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public AccountBaseDTO getAccount() {
        return account;
    }
}

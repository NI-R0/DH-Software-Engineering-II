package de.dhbw.softwareengineering.adapters.account;

import de.dhbw.softwareengineering.validation.annotations.ValidInstitutionName;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

public class AccountCreateDTO {

    @ValidInstitutionName
    private final String institutionName;
    @Valid
    private final AccountBaseDTO account;

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

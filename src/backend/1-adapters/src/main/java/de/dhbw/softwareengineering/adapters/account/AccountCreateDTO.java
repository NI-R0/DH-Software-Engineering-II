package de.dhbw.softwareengineering.adapters.account;

public class AccountCreateDTO {

    private final String institutionName;

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

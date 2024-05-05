package de.dhbw.softwareengineering.adapters.account;


public class AccountReturnDTO {

    private final String institutionName;

    private final AccountInformationDTO account;

    public AccountReturnDTO(String institutionName, AccountInformationDTO account) {
        this.institutionName = institutionName;
        this.account = account;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public AccountInformationDTO getAccount() {
        return account;
    }
}

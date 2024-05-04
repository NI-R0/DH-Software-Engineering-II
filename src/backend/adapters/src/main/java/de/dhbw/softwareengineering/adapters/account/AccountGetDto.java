package de.dhbw.softwareengineering.adapters.account;

public class AccountGetDto {

    private String institutionName;

    private AccountReturnDto account;

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public AccountReturnDto getAccount() {
        return account;
    }

    public void setAccount(AccountReturnDto account) {
        this.account = account;
    }
}

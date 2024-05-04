package de.dhbw.softwareengineering.adapters.account;

import de.dhbw.softwareengineering.adapters.values.AccountOwnerNameDto;

public class AccountCreateDto {

    private String institutionName;

    private AccountBaseDto account;


    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public AccountBaseDto getAccount() {
        return account;
    }

    public void setAccount(AccountBaseDto account) {
        this.account = account;
    }
}

package de.dhbw.softwareengineering.institution;

import java.util.List;
import java.util.UUID;

import de.dhbw.softwareengineering.account.AccountAggregate;
import de.dhbw.softwareengineering.enums.InstitutionType;

public class InstitutionAggregate {
    private UUID institutionId;
    private String name;
    private InstitutionType type;
    private List<AccountAggregate> accounts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InstitutionType getType() {
        return type;
    }

    public void setType(InstitutionType type) {
        this.type = type;
    }

    public List<AccountAggregate> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountAggregate> accounts) {
        this.accounts = accounts;
    }
}

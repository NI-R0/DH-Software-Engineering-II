package de.dhbw.softwareengineering.domain.institution;

import java.util.List;
import java.util.UUID;

import de.dhbw.softwareengineering.domain.account.AccountAggregate;
import de.dhbw.softwareengineering.domain.values.NameValue;
import de.dhbw.softwareengineering.enums.InstitutionType;

public class InstitutionAggregate {
    private UUID institutionId;
    private NameValue name;
    private InstitutionType type;
    private List<AccountAggregate> accounts;

    public UUID getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(UUID institutionId) {
        this.institutionId = institutionId;
    }

    public NameValue getName() {
        return name;
    }

    public void setName(NameValue name) {
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

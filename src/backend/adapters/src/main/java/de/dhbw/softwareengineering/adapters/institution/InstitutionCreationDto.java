package de.dhbw.softwareengineering.adapters.institution;

import de.dhbw.softwareengineering.adapters.account.AccountDto;
import de.dhbw.softwareengineering.adapters.values.NameDto;
import de.dhbw.softwareengineering.enums.InstitutionType;

import java.util.List;
import java.util.UUID;

public class InstitutionCreationDto {
    private UUID institutionId;
    private NameDto name;
    private InstitutionType type;

    public UUID getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(UUID institutionId) {
        this.institutionId = institutionId;
    }

    public NameDto getName() {
        return name;
    }

    public void setName(NameDto name) {
        this.name = name;
    }

    public InstitutionType getType() {
        return type;
    }

    public void setType(InstitutionType type) {
        this.type = type;
    }
}

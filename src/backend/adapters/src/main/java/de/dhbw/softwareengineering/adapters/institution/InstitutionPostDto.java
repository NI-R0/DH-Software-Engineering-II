package de.dhbw.softwareengineering.adapters.institution;

import de.dhbw.softwareengineering.enums.InstitutionType;

import java.util.UUID;

public class InstitutionPostDto {
    private UUID institutionId;
    private String name;
    private InstitutionType type;

    public UUID getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(UUID institutionId) {
        this.institutionId = institutionId;
    }

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
}

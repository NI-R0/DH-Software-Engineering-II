package de.dhbw.softwareengineering.adapters.institution;

import de.dhbw.softwareengineering.enums.InstitutionType;

import java.util.UUID;

public class InstitutionCreateDto {
    private String name;
    private InstitutionType type;

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

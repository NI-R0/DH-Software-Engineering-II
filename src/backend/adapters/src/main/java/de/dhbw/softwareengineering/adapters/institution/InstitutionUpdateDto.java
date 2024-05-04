package de.dhbw.softwareengineering.adapters.institution;

import de.dhbw.softwareengineering.enums.InstitutionType;

import java.util.UUID;

public class InstitutionUpdateDto {
    private String name;
    private String newName;
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

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
}

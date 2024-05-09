package de.dhbw.softwareengineering.adapters.institution;

import de.dhbw.softwareengineering.annotations.ValidInstitutionName;
import de.dhbw.softwareengineering.annotations.ValidInstitutionType;
import de.dhbw.softwareengineering.enums.InstitutionType;

public class InstitutionUpdateDTO {

    @ValidInstitutionName
    private final String name;

    @ValidInstitutionName
    private final String newName;

    //DO NOT VALIDATE ON UPDATE
    private final InstitutionType type;

    @SuppressWarnings("unused")
    public InstitutionUpdateDTO(String name, String newName, InstitutionType type) {
        this.name = name;
        this.newName = newName;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getNewName() {
        return newName;
    }

    public InstitutionType getType() {
        return type;
    }
}

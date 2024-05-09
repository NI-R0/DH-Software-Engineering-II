package de.dhbw.softwareengineering.adapters.institution;

import de.dhbw.softwareengineering.validation.annotations.ValidInstitutionName;
import de.dhbw.softwareengineering.enums.InstitutionType;
import jakarta.validation.constraints.Null;

public class InstitutionUpdateDTO {

    @ValidInstitutionName
    private final String name;

    private final String newName;

    private final InstitutionType type;


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

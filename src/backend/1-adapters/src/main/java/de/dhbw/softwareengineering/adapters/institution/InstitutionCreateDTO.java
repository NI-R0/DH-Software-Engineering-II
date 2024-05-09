package de.dhbw.softwareengineering.adapters.institution;

import de.dhbw.softwareengineering.validation.annotations.ValidInstitutionName;
import de.dhbw.softwareengineering.validation.annotations.ValidInstitutionType;
import de.dhbw.softwareengineering.enums.InstitutionType;

public class InstitutionCreateDTO {

    @ValidInstitutionName
    private final String name;
    @ValidInstitutionType
    private final InstitutionType type;

    public InstitutionCreateDTO(String name, InstitutionType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public InstitutionType getType() {
        return type;
    }
}

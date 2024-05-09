package de.dhbw.softwareengineering.adapters.institution;

import de.dhbw.softwareengineering.annotations.ValidInstitutionName;
import de.dhbw.softwareengineering.annotations.ValidInstitutionType;
import de.dhbw.softwareengineering.enums.InstitutionType;

public class InstitutionCreateDTO {

    @ValidInstitutionName
    private final String name;
    @ValidInstitutionType
    private final InstitutionType type;

    @SuppressWarnings("unused")
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

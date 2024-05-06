package de.dhbw.softwareengineering.adapters.institution;

import de.dhbw.softwareengineering.enums.InstitutionType;

public class InstitutionCreateDTO {

    private final String name;
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

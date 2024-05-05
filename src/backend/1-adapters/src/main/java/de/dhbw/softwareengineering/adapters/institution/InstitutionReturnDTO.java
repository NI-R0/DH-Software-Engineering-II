package de.dhbw.softwareengineering.adapters.institution;

import de.dhbw.softwareengineering.enums.InstitutionType;

import java.util.List;

public class InstitutionReturnDTO {

    private final String institutionName;

    private final InstitutionType institutionType;

    private final List<String> accountNames;

    public InstitutionReturnDTO(String institutionName, InstitutionType institutionType, List<String> accountNames) {
        this.institutionName = institutionName;
        this.institutionType = institutionType;
        this.accountNames = accountNames;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public InstitutionType getInstitutionType() {
        return institutionType;
    }

    public List<String> getAccountNames() {
        return accountNames;
    }
}

package de.dhbw.softwareengineering.adapters.institution;


import de.dhbw.softwareengineering.enums.InstitutionType;

import java.util.List;
import java.util.UUID;

public class InstitutionGetDto {
    private String name;
    private InstitutionType type;
    private List<String> accountNames;

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

    public List<String> getAccountNames() {
        return accountNames;
    }

    public void setAccountNames(List<String> accountNames) {
        this.accountNames = accountNames;
    }
}

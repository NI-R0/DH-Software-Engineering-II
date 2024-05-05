package de.dhbw.softwareengineering.adapters.institution;

import de.dhbw.softwareengineering.adapters.account.AccountDTO;
import de.dhbw.softwareengineering.enums.InstitutionType;

import java.util.List;

public class InstitutionDTO {

    private final String institutionName;

    private final InstitutionType institutionType;

    private final List<AccountDTO> accountList;

    public InstitutionDTO(final String institutionName, final InstitutionType institutionType, final List<AccountDTO> accountList) {
        this.institutionName = institutionName;
        this.institutionType = institutionType;
        this.accountList = accountList;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public InstitutionType getInstitutionType() {
        return institutionType;
    }

    public List<AccountDTO> getAccountList() {
        return accountList;
    }
}

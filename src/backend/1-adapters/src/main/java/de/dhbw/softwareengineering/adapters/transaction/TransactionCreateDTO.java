package de.dhbw.softwareengineering.adapters.transaction;

import de.dhbw.softwareengineering.annotations.ValidAccountName;
import de.dhbw.softwareengineering.annotations.ValidInstitutionName;
import jakarta.validation.Valid;

public class TransactionCreateDTO {

    @ValidInstitutionName
    private final String institutionName;
    @ValidAccountName
    private final String accountName;
    @Valid
    private final TransactionBaseDTO transaction;

    @SuppressWarnings("unused")
    public TransactionCreateDTO(String institutionName, String accountName, TransactionBaseDTO transaction) {
        this.institutionName = institutionName;
        this.accountName = accountName;
        this.transaction = transaction;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public String getAccountName() {
        return accountName;
    }

    public TransactionBaseDTO getTransaction() {
        return transaction;
    }
}

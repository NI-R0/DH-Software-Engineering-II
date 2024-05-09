package de.dhbw.softwareengineering.adapters.transaction;

import de.dhbw.softwareengineering.validation.annotations.ValidAccountName;
import de.dhbw.softwareengineering.validation.annotations.ValidId;
import de.dhbw.softwareengineering.validation.annotations.ValidInstitutionName;

import java.util.UUID;

public class TransactionUpdateDTO {

    @ValidInstitutionName
    private final String institutionName;
    @ValidAccountName
    private final String accountName;
    @ValidId
    private final UUID transactionId;
    //DO NOT VALIDATE ON UPDATE
    private final TransactionBaseDTO transaction;

    @SuppressWarnings("unused")
    public TransactionUpdateDTO(String institutionName, String accountName, UUID transactionId, TransactionBaseDTO transaction) {
        this.institutionName = institutionName;
        this.accountName = accountName;
        this.transactionId = transactionId;
        this.transaction = transaction;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public String getAccountName() {
        return accountName;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public TransactionBaseDTO getTransaction() {
        return transaction;
    }
}

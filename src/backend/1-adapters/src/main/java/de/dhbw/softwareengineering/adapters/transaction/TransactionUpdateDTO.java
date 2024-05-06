package de.dhbw.softwareengineering.adapters.transaction;

import java.util.UUID;

public class TransactionUpdateDTO {

    private final String institutionName;

    private final String accountName;

    private final UUID transactionId;

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

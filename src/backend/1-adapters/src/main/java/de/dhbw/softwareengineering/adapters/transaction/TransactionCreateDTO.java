package de.dhbw.softwareengineering.adapters.transaction;

public class TransactionCreateDTO {

    private final String institutionName;

    private final String accountName;

    private final TransactionBaseDTO transaction;

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

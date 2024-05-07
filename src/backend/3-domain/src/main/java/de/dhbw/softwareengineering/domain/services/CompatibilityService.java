package de.dhbw.softwareengineering.domain.services;

import de.dhbw.softwareengineering.domain.transaction.Transaction;
import de.dhbw.softwareengineering.enums.InstitutionType;
import de.dhbw.softwareengineering.enums.TransactionType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompatibilityService {

    public boolean isInstitutionTypeCompatibleWithTransactionList
            (InstitutionType institutionType, List<Transaction> transactionList){

        if(institutionType == InstitutionType.BANK){
            return !transactionList.stream().anyMatch(
                    t -> t.getTransactionType() == TransactionType.BUY ||
                         t.getTransactionType() == TransactionType.SELL);
        }
        return !transactionList.stream().anyMatch(
                t -> t.getTransactionType() == TransactionType.EXPENSE ||
                     t.getTransactionType() == TransactionType.INCOME);
    }

    public boolean isInstitutionTypeCompatibleWithTransactionType
            (InstitutionType institutionType, TransactionType transactionType){

        if(institutionType == InstitutionType.BANK){
            return (transactionType != TransactionType.BUY && transactionType != TransactionType.SELL);
        }
        return (transactionType != TransactionType.INCOME && transactionType != TransactionType.EXPENSE);
    }
}

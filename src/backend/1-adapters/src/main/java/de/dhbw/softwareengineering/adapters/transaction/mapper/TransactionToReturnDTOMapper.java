package de.dhbw.softwareengineering.adapters.transaction.mapper;

import de.dhbw.softwareengineering.adapters.transaction.TransactionReturnDTO;
import de.dhbw.softwareengineering.domain.transaction.Transaction;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class TransactionToReturnDTOMapper implements Function<Transaction, TransactionReturnDTO> {

    @Override
    public TransactionReturnDTO apply(Transaction transaction){
        return map(transaction);
    }

    private TransactionReturnDTO map(Transaction transaction){
        return new TransactionReturnDTO(transaction.getId(),
                transaction.getAccount().getAccountName(),
                transaction.getAccount().getInstitution().getName(),
                transaction.getDescription(),
                transaction.getAmount(),
                transaction.getUnit(),
                transaction.getTimestamp(),
                transaction.getTransactionType());
    }

}

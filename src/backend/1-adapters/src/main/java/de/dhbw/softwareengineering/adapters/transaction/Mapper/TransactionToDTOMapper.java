package de.dhbw.softwareengineering.adapters.transaction.Mapper;

import de.dhbw.softwareengineering.adapters.transaction.TransactionDTO;
import de.dhbw.softwareengineering.domain.transaction.Transaction;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class TransactionToDTOMapper implements Function<Transaction, TransactionDTO> {

    @Override
    public TransactionDTO apply(Transaction transaction) {
        return map(transaction);
    }

    private TransactionDTO map(final Transaction transaction) {
        return new TransactionDTO(transaction.getId(),
                transaction.getAccount().getId(),
                transaction.getDescription(),
                transaction.getAmount(),
                transaction.getUnit(),
                transaction.getTimestamp(),
                transaction.getTransactionType());
    }

}

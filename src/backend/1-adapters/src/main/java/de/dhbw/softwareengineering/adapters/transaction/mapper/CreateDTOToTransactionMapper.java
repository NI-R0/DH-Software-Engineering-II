package de.dhbw.softwareengineering.adapters.transaction.mapper;

import de.dhbw.softwareengineering.adapters.transaction.TransactionCreateDTO;
import de.dhbw.softwareengineering.domain.account.Account;
import de.dhbw.softwareengineering.domain.transaction.Transaction;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.Function;

@Component
public class CreateDTOToTransactionMapper implements Function<TransactionCreateDTO, Transaction> {

    @Override
    public Transaction apply(TransactionCreateDTO dto){
        return map(dto, null);
    }

    public Transaction apply(TransactionCreateDTO dto, Account account){
        return map(dto, account);
    }

    private Transaction map(TransactionCreateDTO dto, Account account){
        return new Transaction(UUID.randomUUID(),
                account,
                dto.getTransaction().getDescription(),
                dto.getTransaction().getAmount(),
                dto.getTransaction().getUnit(),
                dto.getTransaction().getTimestamp(),
                dto.getTransaction().getTransactionType());
    }

}

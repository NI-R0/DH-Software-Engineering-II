package de.dhbw.softwareengineering.adapters.transaction.mapper;

import de.dhbw.softwareengineering.adapters.transaction.TransactionBaseDTO;
import de.dhbw.softwareengineering.adapters.transaction.TransactionCreateDTO;
import de.dhbw.softwareengineering.adapters.transaction.TransactionUpdateDTO;
import de.dhbw.softwareengineering.domain.account.Account;
import de.dhbw.softwareengineering.domain.transaction.Transaction;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.Function;

@Component
public class UpdateDTOToTransactionMapper implements Function<TransactionUpdateDTO, Transaction> {

    @Override
    public Transaction apply(TransactionUpdateDTO dto){
        return null;
    }

    public Transaction apply(Transaction transaction, TransactionUpdateDTO dto){
        return mapToExisting(transaction, dto);
    }

    private Transaction mapToExisting(Transaction transaction, TransactionUpdateDTO dto){

        TransactionBaseDTO properties = dto.getTransaction();
        if(properties.getTransactionType() != null){
            transaction.updateTransactionType(properties.getTransactionType());
        }
        if(properties.getDescription() != null){
            transaction.updateDescription(properties.getDescription());
        }
        if(properties.getAmount() != null){
            transaction.updateAmount(properties.getAmount());
        }
        if(properties.getUnit() != null){
            transaction.updateUnit(properties.getUnit());
        }
        if(properties.getTimestamp() != null){
            transaction.updateTimestamp(properties.getTimestamp());
        }
        return transaction;
    }


}

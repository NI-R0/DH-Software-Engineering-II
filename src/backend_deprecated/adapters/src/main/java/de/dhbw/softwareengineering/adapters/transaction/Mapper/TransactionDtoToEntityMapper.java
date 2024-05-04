package de.dhbw.softwareengineering.adapters.transaction.Mapper;

import de.dhbw.softwareengineering.adapters.transaction.TransactionDto;
import de.dhbw.softwareengineering.domain.transaction.TransactionEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionDtoToEntityMapper {
    public TransactionEntity mapDtoToEntity(TransactionDto dto){
        TransactionEntity entity = new TransactionEntity();
        entity.setTransactionId(dto.getTransactionId());
        entity.setType(dto.getType());
        entity.setUnit(dto.getUnit());
        entity.setTimestamp(dto.getTimestamp());
        entity.setAmount(dto.getAmount());
        entity.setAmount(dto.getAmount());
        return entity;
    }
}

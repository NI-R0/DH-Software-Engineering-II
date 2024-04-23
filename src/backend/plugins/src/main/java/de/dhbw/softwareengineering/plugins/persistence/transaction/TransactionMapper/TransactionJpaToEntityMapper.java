package de.dhbw.softwareengineering.plugins.persistence.transaction.TransactionMapper;

import de.dhbw.softwareengineering.enums.TransactionType;
import de.dhbw.softwareengineering.plugins.persistence.transaction.TransactionJpaEntity;
import de.dhbw.softwareengineering.transaction.TransactionEntity;
import de.dhbw.softwareengineering.values.DescriptionValue;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Component
public class TransactionJpaToEntityMapper {
    //Transaction
    public TransactionEntity mapJpaToEntity(TransactionJpaEntity jpa) throws Exception{

        if(!isJpaInputValid(jpa)){
            throw new IllegalArgumentException("JPA properties could not be matched to entity!");
        }

        TransactionEntity entity = new TransactionEntity();
        entity.setTransactionId(jpa.getId());
        entity.setAmount(jpa.getAmount());
        entity.setDescription(new DescriptionValue(jpa.getDescription()));
        entity.setTimestamp(jpa.getTimestamp());
        entity.setUnit(jpa.getUnit());
        entity.setType(jpa.getTransactionType());

        return entity;
    }

    private boolean isJpaInputValid(TransactionJpaEntity jpa){
        UUID id = jpa.getId();
        String description = jpa.getDescription();
        Timestamp time = jpa.getTimestamp();
        String unit = jpa.getUnit();
        Double amount = jpa.getAmount();
        TransactionType type = jpa.getTransactionType();

        if(id == null || description == null || time == null || unit.isEmpty() || amount == null || type == null){
            return false;
        }

        return true;
    }
}
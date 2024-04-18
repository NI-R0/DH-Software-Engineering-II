package de.dhbw.softwareengineering.plugins.persistence.transaction.Mapper;

import de.dhbw.softwareengineering.plugins.persistence.transaction.TransactionJpaEntity;
import de.dhbw.softwareengineering.transaction.TransactionEntity;
import de.dhbw.softwareengineering.values.DescriptionValue;
import org.springframework.stereotype.Component;

@Component
public class JpaToEntityMapper {
    //Transaction
    public TransactionEntity mapJpaToEntity(TransactionJpaEntity jpa){
        TransactionEntity entity = new TransactionEntity();
        entity.setAmount(jpa.getAmount());
        entity.setDescription(new DescriptionValue(jpa.getDescription()));
        entity.setTimestamp(jpa.getTimestamp());
        entity.setUnit(jpa.getUnit());
        entity.setType(jpa.getTransactionType());

        return entity;
    }
}

package de.dhbw.softwareengineering.plugins.persistence.transaction.Mapper;

import de.dhbw.softwareengineering.enums.TransactionType;
import de.dhbw.softwareengineering.plugins.persistence.transaction.TransactionJpaEntity;
import de.dhbw.softwareengineering.plugins.persistence.transaction.TransactionJpaRepository;
import de.dhbw.softwareengineering.transaction.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.UUID;

@Component
public class EntityToJpaMapper {
    //Transaction
    @Autowired
    TransactionJpaRepository jpaRepository;

    public TransactionJpaEntity mapEntityToJpa(TransactionEntity entity) throws Exception{
        if(!isInputLegit(entity)){
            throw new IllegalArgumentException("Wrong Input!");
        }

        TransactionJpaEntity jpa = mapEntityIdToJpa(entity);
    }

    private TransactionJpaEntity mapEntityIdToJpa(TransactionEntity entity) throws Exception{
        if(jpaRepository.existsById(entity.getTransactionId())){
            throw new IllegalArgumentException("Transaction with ID " + entity.getTransactionId().toString() + " already exists!");
        }

    }



    private boolean isInputLegit(TransactionEntity entity){
        UUID id = entity.getTransactionId();
        Double amount = entity.getAmount();
        String description = entity.getDescription().getText();
        String unit = entity.getUnit();
        Timestamp timestamp = entity.getTimestamp();
        TransactionType type = entity.getType();

        if(id == null || amount == null || description.isEmpty() || unit.isEmpty() || type == null || timestamp == null){
            return false;
        }
        else if(description.length() > 255 || unit.length() > 10){
            return false;
        }
        return true;
    }
}

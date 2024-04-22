package de.dhbw.softwareengineering.plugins.persistence.transaction.TransactionMapper;

import de.dhbw.softwareengineering.enums.TransactionType;
import de.dhbw.softwareengineering.plugins.persistence.transaction.TransactionJpaEntity;
import de.dhbw.softwareengineering.plugins.persistence.transaction.TransactionJpaRepository;
import de.dhbw.softwareengineering.transaction.TransactionEntity;
import de.dhbw.softwareengineering.values.DescriptionValue;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.function.Function;

@Component
public class TransactionEntityToJpaMapper {
    @Autowired
    TransactionJpaRepository jpaRepository;

    /*public Optional<TransactionJpaEntity> mapEntityToJpa(TransactionEntity entity, boolean alreadyExists) throws Exception{


        if(!isTransactionValid(entity,alreadyExists)){
            return Optional.empty();
        }

        if(alreadyExists){

            Optional<TransactionJpaEntity> newJpa = mapToExistingJpa(entity);
            return null;
        }

        //TransactionJpaEntity newJpa = mapNonExistingEntity(entity);

        return Optional.empty();
    }*/

    public TransactionJpaEntity mapEntityToExistingJpa(TransactionEntity entity, TransactionJpaEntity jpa) throws Exception{

        if(!isInputValid(entity)){
            throw new IllegalArgumentException("Illegal transaction parameter!");
        }

        if(entity.getDescription() != null){
            jpa.setDescription(entity.getDescription().getText());
        }
        if(entity.getType() != null){
            jpa.setTransactionType(entity.getType());
        }
        if(entity.getUnit() != null){
            jpa.setUnit(entity.getUnit());
        }
        if(entity.getAmount() != null){
            jpa.setAmount(entity.getAmount());
        }
        if(entity.getTimestamp() != null){
            jpa.setTimestamp(entity.getTimestamp());
        }
        return jpa;
    }

    private boolean isInputValid(TransactionEntity entity){
        String description = entity.getDescription().getText();
        String unit = entity.getUnit();
        if(description.length() > 255 || Objects.equals(unit, "")){
            return false;
        }
        return true;
    }

    /*public TransactionJpaEntity mapNonExistingEntity(TransactionEntity entity){
        TransactionJpaEntity newJpa = new TransactionJpaEntity();
        newJpa.set

        return null;
    }*/

    /*private boolean isTransactionValid(TransactionEntity entity, boolean alreadyExisting){
        UUID id = entity.getTransactionId();
        String description = entity.getDescription().getText();
        Timestamp time = entity.getTimestamp();
        String unit = entity.getUnit();
        Double amount = entity.getAmount();
        TransactionType type = entity.getType();

        if(id == null || description.isEmpty() || time == null || unit.isEmpty() || amount == null || type == null){
            return false;
        }

        return true;*/

        /*Optional<TransactionJpaEntity> jpaEntity = jpaRepository.findById(id);

        return jpaEntity.isPresent();*/

        /*if(alreadyExisting && jpaEntity.isPresent()){
            return true;
        }

        else if(!alreadyExisting && jpaEntity.isEmpty()){
            return true;
        }

        return false;

        //existing & found -> true
        //not existing & found -> false
        //existing & not found -> false
        //not existing & not found -> true
    }*/

}

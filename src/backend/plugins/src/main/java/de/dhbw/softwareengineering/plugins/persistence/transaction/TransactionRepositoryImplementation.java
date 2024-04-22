package de.dhbw.softwareengineering.plugins.persistence.transaction;

import de.dhbw.softwareengineering.plugins.persistence.transaction.TransactionMapper.TransactionEntityToJpaMapper;
import de.dhbw.softwareengineering.plugins.persistence.transaction.TransactionMapper.TransactionJpaToEntityMapper;
import de.dhbw.softwareengineering.transaction.TransactionEntity;
import de.dhbw.softwareengineering.transaction.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.InvalidPropertiesFormatException;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TransactionRepositoryImplementation implements TransactionRepository {
    @Autowired
    TransactionJpaRepository jpaRepository;
    @Autowired
    TransactionEntityToJpaMapper entityToJpa;
    @Autowired
    TransactionJpaToEntityMapper jpaToEntity;

    @Override
    public Optional<TransactionEntity> findById(UUID id){
        try {
            Optional<TransactionJpaEntity> jpaOptional = jpaRepository.findById(id);
            TransactionJpaEntity jpaEntity = jpaOptional.orElseThrow(IllegalArgumentException::new);
            return Optional.of(jpaToEntity.mapJpaToEntity(jpaEntity));
        }
        catch (Exception e){
            System.out.println(e.toString());
            return Optional.empty();
        }
    }

    @Override
    public void deleteTransaction(UUID id){
        Optional<TransactionJpaEntity> transactionJpa = jpaRepository.findById(id);
        transactionJpa.ifPresent(jpa -> jpaRepository.delete(jpa));
    }

    @Override
    public Optional<TransactionEntity> editTransaction(TransactionEntity entity){

        try{
            Optional<TransactionJpaEntity> jpaOptional = jpaRepository.findById(entity.getTransactionId());
            TransactionJpaEntity jpaEntity = jpaOptional.orElseThrow(IllegalArgumentException::new);

            jpaEntity = entityToJpa.mapEntityToExistingJpa(entity, jpaEntity);

            jpaRepository.save(jpaEntity);

            return Optional.of(jpaToEntity.mapJpaToEntity(jpaEntity));

        }catch(Exception e){
            System.out.println(e.toString());
            return Optional.empty();
        }
    }
}

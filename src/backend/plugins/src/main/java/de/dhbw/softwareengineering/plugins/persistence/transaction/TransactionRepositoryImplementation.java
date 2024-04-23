package de.dhbw.softwareengineering.plugins.persistence.transaction;

import de.dhbw.softwareengineering.plugins.persistence.transaction.TransactionMapper.TransactionEntityToJpaMapper;
import de.dhbw.softwareengineering.plugins.persistence.transaction.TransactionMapper.TransactionJpaToEntityMapper;
import de.dhbw.softwareengineering.domain.transaction.TransactionEntity;
import de.dhbw.softwareengineering.domain.transaction.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public Optional<TransactionEntity> findByIdAndAccount(UUID accountId, UUID transactionId){
        try {
            Optional<TransactionJpaEntity> jpaOptional = jpaRepository.findById(transactionId);
            TransactionJpaEntity jpaEntity = jpaOptional.orElseThrow(IllegalArgumentException::new);
            if(jpaEntity.getAccountId() != accountId){
                throw new IllegalArgumentException("ID not found");
            }
            return Optional.of(jpaToEntity.mapJpaToEntity(jpaEntity));
        }
        catch (Exception e){
            System.out.println(e.toString());
            return Optional.empty();
        }
    }

    @Override
    public void deleteTransaction(TransactionEntity transaction){
        Optional<TransactionJpaEntity> transactionJpa = jpaRepository.findById(transaction.getTransactionId());
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

    @Override
    public Optional<TransactionEntity> createTransaction(UUID accountId, TransactionEntity entity){
        try{
            Optional<TransactionJpaEntity> jpaOptional = jpaRepository.findById(entity.getTransactionId());

            if(jpaOptional.isPresent()){
                throw new IllegalArgumentException("Transaction with ID " + entity.getTransactionId().toString() + " already exists!");
            }

            TransactionJpaEntity jpaEntity = entityToJpa.mapEntityToNewJpa(accountId, entity);

            jpaRepository.save(jpaEntity);

            return Optional.of(jpaToEntity.mapJpaToEntity(jpaEntity));

        }catch(Exception e){
            System.out.println(e.toString());
            return Optional.empty();
        }
    }
}

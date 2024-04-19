package de.dhbw.softwareengineering.plugins.persistence.transaction;

import de.dhbw.softwareengineering.plugins.persistence.transaction.TransactionMapper.EntityToJpaMapper;
import de.dhbw.softwareengineering.plugins.persistence.transaction.TransactionMapper.JpaToEntityMapper;
import de.dhbw.softwareengineering.transaction.TransactionEntity;
import de.dhbw.softwareengineering.transaction.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class TransactionRepositoryImplementation implements TransactionRepository {
    @Autowired
    TransactionJpaRepository jpaRepository;
    @Autowired
    EntityToJpaMapper entityToJpa;
    @Autowired
    JpaToEntityMapper jpaToEntity;

    @Override
    public Optional<TransactionEntity> findById(UUID id){
        Optional<TransactionJpaEntity> jpaOptional = jpaRepository.findById(id);
        return jpaOptional.map(jpa -> jpaToEntity.mapJpaToEntity(jpa));
    }

    @Override
    public void deleteTransaction(UUID id){
    }

    @Override
    public TransactionEntity editTransaction(TransactionEntity entity){
        return null;
    }
}

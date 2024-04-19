package de.dhbw.softwareengineering.plugins.persistence.account;

import de.dhbw.softwareengineering.account.AccountAggregate;
import de.dhbw.softwareengineering.account.AccountRepository;
import de.dhbw.softwareengineering.plugins.persistence.account.AccountMapper.EntityToJpaMapper;
import de.dhbw.softwareengineering.plugins.persistence.account.AccountMapper.JpaToEntityMapper;
import de.dhbw.softwareengineering.transaction.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AccountRepositoryImplementation implements AccountRepository {
    @Autowired
    AccountJpaRepository jpaRepository;
    @Autowired
    EntityToJpaMapper entityToJpa;
    @Autowired
    JpaToEntityMapper jpaToEntity;

    @Override
    public Optional<AccountAggregate> findById(UUID id){return null;}
    @Override
    public List<TransactionEntity> findAllTransactions(){return null;}
    @Override
    public List<TransactionEntity> createTransaction(TransactionEntity transaction){return null;}
    @Override
    public List<TransactionEntity> updateTransaction(TransactionEntity transaction){return null;}
    @Override
    public List<TransactionEntity> deleteTransaction(UUID id){return null;}
}

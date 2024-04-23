package de.dhbw.softwareengineering.plugins.persistence.account;

import de.dhbw.softwareengineering.account.AccountAggregate;
import de.dhbw.softwareengineering.account.AccountRepository;
import de.dhbw.softwareengineering.plugins.persistence.account.AccountMapper.AccountAggregateToJpaMapper;
import de.dhbw.softwareengineering.plugins.persistence.account.AccountMapper.AccountJpaToAggregateMapper;
import de.dhbw.softwareengineering.plugins.persistence.transaction.TransactionJpaEntity;
import de.dhbw.softwareengineering.plugins.persistence.transaction.TransactionJpaRepository;
import de.dhbw.softwareengineering.plugins.persistence.transaction.TransactionMapper.TransactionJpaToEntityMapper;
import de.dhbw.softwareengineering.plugins.persistence.transaction.TransactionRepositoryImplementation;
import de.dhbw.softwareengineering.transaction.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AccountRepositoryImplementation implements AccountRepository {
    @Autowired
    AccountJpaRepository accountJpaRepository;
    @Autowired
    TransactionJpaRepository transactionJpaRepository;
    @Autowired
    AccountAggregateToJpaMapper accountEntityToJpa;
    @Autowired
    AccountJpaToAggregateMapper accountJpaToEntity;
    @Autowired
    TransactionJpaToEntityMapper transactionJpaToEntity;
    @Autowired
    TransactionRepositoryImplementation transactionRepositoryImpl;

    @Override
    public Optional<AccountAggregate> findById(UUID id) {
        try{
            Optional<AccountJpaEntity> jpaOptional = accountJpaRepository.findById(id);
            AccountJpaEntity jpaEntity = jpaOptional.orElseThrow(IllegalArgumentException::new);
            return accountJpaToEntity.mapJpaToAggregate(jpaEntity);
        }
        catch(Exception e){
            System.out.println(e.toString());
            return Optional.empty();
        }
    }

    @Override
    public List<TransactionEntity> findAllTransactions(UUID accountId)
    {
        List<TransactionEntity> transactionEntities = new ArrayList<>();
        List<TransactionJpaEntity> transactionJpas = transactionJpaRepository.findByAccount(accountId);

        transactionJpas.forEach(jpaEntity -> {
            try {
                transactionEntities.add(transactionJpaToEntity.mapJpaToEntity(jpaEntity));
            } catch (Exception e) {
                //TODO
                System.out.println(e.toString());
            }
        });

        return transactionEntities;
    }
    @Override
    public List<TransactionEntity> createTransaction(UUID accountId, TransactionEntity transaction)
    {
        transactionRepositoryImpl.createTransaction(accountId, transaction);
        return findAllTransactions(accountId);
    }
    @Override
    public List<TransactionEntity> updateTransaction(UUID accountId, TransactionEntity transaction)
    {
        transactionRepositoryImpl.editTransaction(transaction);
        return findAllTransactions(accountId);
    }
    @Override
    public List<TransactionEntity> deleteTransaction(UUID accountId, UUID id)
    {
        transactionRepositoryImpl.deleteTransaction(id);
        return findAllTransactions(accountId);
    }

    @Override
    public Optional<TransactionEntity> findTransactionById(UUID accountId, UUID id)
    {
        return transactionRepositoryImpl.findByIdAndAccount(accountId, id);
    }
}

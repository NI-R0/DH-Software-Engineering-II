package de.dhbw.softwareengineering.plugins.persistence.account;

import de.dhbw.softwareengineering.domain.account.AccountAggregate;
import de.dhbw.softwareengineering.domain.account.AccountRepository;
import de.dhbw.softwareengineering.plugins.persistence.account.AccountMapper.AccountAggregateToJpaMapper;
import de.dhbw.softwareengineering.plugins.persistence.account.AccountMapper.AccountJpaToAggregateMapper;
import de.dhbw.softwareengineering.plugins.persistence.transaction.TransactionJpaRepository;
import de.dhbw.softwareengineering.plugins.persistence.transaction.TransactionMapper.TransactionJpaToEntityMapper;
import de.dhbw.softwareengineering.plugins.persistence.transaction.TransactionRepositoryImplementation;
import de.dhbw.softwareengineering.domain.transaction.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AccountRepositoryBridge implements AccountRepository {
    @Autowired
    AccountJpaRepository accountJpaRepository;
    @Autowired
    AccountAggregateToJpaMapper aggregateToJpa;
    @Autowired
    AccountJpaToAggregateMapper jpaToAggregate;


    @Autowired
    TransactionJpaRepository transactionJpaRepository;
    @Autowired
    TransactionJpaToEntityMapper transactionJpaToEntity;
    @Autowired
    TransactionRepositoryImplementation transactionRepositoryImpl;


    @Override
    public List<AccountAggregate> findAllByInstitution(String institutionName){
        List<AccountAggregate> aggregates = new ArrayList<>();
        accountJpaRepository.findAllByInstitutionName(institutionName).forEach(jpa -> {
            try{
                aggregates.add(jpaToAggregate.mapJpaToAggregate(jpa));
            }
            catch(Exception e){
                System.out.println(e.toString());
            }
        });
        return aggregates;
    }


    @Override
    public Optional<AccountAggregate> findById(UUID id) {
        try{
            Optional<AccountJpaEntity> jpaOptional = accountJpaRepository.findById(id);
            AccountJpaEntity jpaEntity = jpaOptional.orElseThrow(IllegalArgumentException::new);
            return Optional.of(jpaToAggregate.mapJpaToAggregate(jpaEntity));
        }
        catch(Exception e){
            System.out.println(e.toString());
            return Optional.empty();
        }
    }

    @Override
    public Optional<AccountAggregate> findByIdAndInstitution(String institutionName, UUID accountId){
        try{
            Optional<AccountJpaEntity> jpaOptional = accountJpaRepository.findByIdAndInstitution(institutionName, accountId);
            AccountJpaEntity jpaEntity = jpaOptional.orElseThrow(IllegalArgumentException::new);
            return Optional.of(jpaToAggregate.mapJpaToAggregate(jpaEntity));
        }
        catch(Exception e){
            System.out.println(e.toString());
            return Optional.empty();
        }
    }

    @Override
    public Optional<AccountAggregate> findByNameAndInstitution(String institutionName, String accountName){
        try{
            Optional<AccountJpaEntity> jpaOptional = accountJpaRepository.findByNameAndInstitution(institutionName, accountName);
            AccountJpaEntity jpaEntity = jpaOptional.orElseThrow(IllegalArgumentException::new);
            return Optional.of(jpaToAggregate.mapJpaToAggregate(jpaEntity));
        }
        catch(Exception e){
            System.out.println(e.toString());
            return Optional.empty();
        }
    }



    /////////////////////////////////////////////





    /*@Override
    public Optional<AccountAggregate> createAccount(String institutionName , AccountAggregate account){
        try{
            Optional<AccountJpaEntity> jpaOptional = accountJpaRepository.findById(account.getAccountId());

            if(jpaOptional.isPresent()){
                throw new IllegalArgumentException("Transaction with ID " + account.getAccountId().toString() + " already exists!");
            }

            AccountJpaEntity jpaEntity = aggregateToJpa.mapAggregateToNewJpa(institutionName, account);

            accountJpaRepository.save(jpaEntity);

            return Optional.of(jpaToAggregate.mapJpaToAggregate(jpaEntity));
        }
        catch(Exception e){
            System.out.println(e.toString());
            return Optional.empty();
        }
    }
    @Override
    public Optional<AccountAggregate> editAccount(AccountAggregate account){
        try{
            Optional<AccountJpaEntity> jpaOptional = accountJpaRepository.findById(account.getAccountId());
            AccountJpaEntity jpaEntity = jpaOptional.orElseThrow(IllegalArgumentException::new);

            jpaEntity = aggregateToJpa.mapAggregateToExistingJpa(account, jpaEntity);

            accountJpaRepository.save(jpaEntity);

            return Optional.of(jpaToAggregate.mapJpaToAggregate(jpaEntity));
        }
        catch(Exception e){
            System.out.println(e.toString());
            return Optional.empty();
        }
    }
    @Override
    public void deleteAccount(AccountAggregate account){
        deleteAllTransactions(account);
        Optional<AccountJpaEntity> accountJpa = accountJpaRepository.findById(account.getAccountId());
        accountJpa.ifPresent(jpa -> accountJpaRepository.delete(jpa));

        //Alternative: Durch Transaktionsliste loopen und einzeln löschen (func. deleteAllTransactions)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public List<TransactionEntity> findAllTransactions(UUID accountId) {
        return jpaToAggregate.findAllTransactions(accountId);
    }
    @Override
    public List<TransactionEntity> createTransaction(UUID accountId, TransactionEntity transaction) {
        transactionRepositoryImpl.createTransaction(accountId, transaction);
        return findAllTransactions(accountId);
    }

    @Override
    public List<TransactionEntity> updateTransaction(UUID accountId, TransactionEntity transaction) {
        transactionRepositoryImpl.editTransaction(transaction);
        return findAllTransactions(accountId);
    }

    @Override
    public List<TransactionEntity> deleteTransaction(UUID accountId, TransactionEntity transaction) {
        transactionRepositoryImpl.deleteTransaction(transaction);
        return findAllTransactions(accountId);
    }

    @Override
    public List<TransactionEntity> deleteAllTransactions(AccountAggregate account){
        transactionJpaRepository.deleteAllByAccount(account.getAccountId());
        return findAllTransactions(account.getAccountId());
    }

    @Override
    public Optional<TransactionEntity> findTransactionById(UUID accountId, UUID id) {
        return transactionRepositoryImpl.findByIdAndAccount(accountId, id);
    }*/
}
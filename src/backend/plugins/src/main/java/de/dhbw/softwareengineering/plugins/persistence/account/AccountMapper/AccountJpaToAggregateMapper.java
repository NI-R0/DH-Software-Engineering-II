package de.dhbw.softwareengineering.plugins.persistence.account.AccountMapper;

import de.dhbw.softwareengineering.domain.account.AccountAggregate;
import de.dhbw.softwareengineering.domain.values.AccountOwnerNameValue;
import de.dhbw.softwareengineering.plugins.persistence.account.AccountJpaEntity;
import de.dhbw.softwareengineering.plugins.persistence.transaction.TransactionJpaEntity;
import de.dhbw.softwareengineering.plugins.persistence.transaction.TransactionJpaRepository;
import de.dhbw.softwareengineering.plugins.persistence.transaction.TransactionMapper.TransactionJpaToEntityMapper;
import de.dhbw.softwareengineering.domain.transaction.TransactionEntity;
import de.dhbw.softwareengineering.domain.values.NameValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class AccountJpaToAggregateMapper {
    @Autowired
    TransactionJpaRepository transactionJpaRepository;
    @Autowired
    TransactionJpaToEntityMapper transactionJpaToEntity;

    public AccountAggregate mapJpaToAggregate(AccountJpaEntity jpa) throws Exception{

        if(isJpaInputInvalid(jpa)){
            throw new IllegalArgumentException("JPA properties are illegal!");
        }

        AccountAggregate account = new AccountAggregate();
        account.setAccountId(jpa.getAccountId());
        account.setAccountName(new NameValue(jpa.getName()));
        account.setBalance(jpa.getBalance());
        account.setOwner(new AccountOwnerNameValue(jpa.getOwnerfirstname(), jpa.getOwnerlastname()));
        account.setTransactions(findAllTransactions(jpa.getAccountId()));

        return account;
    }

    public List<TransactionEntity> findAllTransactions(UUID accountId)
    {
        List<TransactionEntity> transactionEntities = new ArrayList<>();
        List<TransactionJpaEntity> transactionJpas = transactionJpaRepository.findAllByAccountId(accountId);

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

    private boolean isJpaInputInvalid(AccountJpaEntity jpa){
        UUID id = jpa.getAccountId();
        String name = jpa.getName();
        Double balance = jpa.getBalance();

        if(id == null || name.isEmpty() || balance == null){
            return true;
        }
        if(balance.isInfinite()|| balance.isNaN()){
            return true;
        }

        return false;
    }
}

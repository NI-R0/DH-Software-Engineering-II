package de.dhbw.softwareengineering.plugins.persistence.account.AccountMapper;

import de.dhbw.softwareengineering.domain.account.AccountAggregate;
import de.dhbw.softwareengineering.plugins.persistence.account.AccountJpaEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AccountAggregateToJpaMapper {
    public AccountJpaEntity mapAggregateToNewJpa(UUID institutionId, AccountAggregate account) throws Exception{
        if(isAggregateInputInvalid(account)){
            throw new IllegalArgumentException("Illegal account parameter!");
        }
        AccountJpaEntity jpaEntity = new AccountJpaEntity();
        jpaEntity.setAccountId(account.getAccountId());
        jpaEntity.setName(account.getName().getName());
        jpaEntity.setBalance(account.getBalance());
        jpaEntity.setInstitutionId(institutionId);

        return jpaEntity;
    }

    public AccountJpaEntity mapAggregateToExistingJpa(AccountAggregate account, AccountJpaEntity jpa) throws Exception{

        if(isAggregateInputInvalid(account)){
            throw new IllegalArgumentException("Illegal account parameter!");
        }
        if(account.getBalance() != null){
            jpa.setBalance(account.getBalance());
        }
        if(account.getName() != null && account.getName().getName() != null){
            jpa.setName(account.getName().getName());
        }

        return jpa;
    }

    private boolean isAggregateInputInvalid(AccountAggregate account){
        String name = account.getName().getName();
        if(name.isEmpty() || name.length() > 10){
            return true;
        }
        return false;
    }
}

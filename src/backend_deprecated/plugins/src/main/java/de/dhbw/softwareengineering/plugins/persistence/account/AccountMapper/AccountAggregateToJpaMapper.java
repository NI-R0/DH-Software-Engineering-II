package de.dhbw.softwareengineering.plugins.persistence.account.AccountMapper;

import de.dhbw.softwareengineering.domain.account.AccountAggregate;
import de.dhbw.softwareengineering.plugins.persistence.account.AccountJpaEntity;
import de.dhbw.softwareengineering.plugins.persistence.account.AccountJpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AccountAggregateToJpaMapper {
    /*public AccountJpaEntity mapAggregateToNewJpa(String institutionName, AccountAggregate account) throws Exception{
        if(isAggregateInputInvalid(account)){
            throw new IllegalArgumentException("Illegal account parameter!");
        }
        AccountJpaEntity jpaEntity = new AccountJpaEntity();
        jpaEntity.setId(account.getAccountId());
        jpaEntity.setAccountname(account.getAccountName());
        jpaEntity.setBalance(account.getBalance());
        jpaEntity.setInstitution(institutionName);
        jpaEntity.setOwnerfirstname(account.getOwner().getFirstName());
        jpaEntity.setOwnerlastname(account.getOwner().getLastName());

        return jpaEntity;
    }

    public AccountJpaEntity mapAggregateToExistingJpa(AccountAggregate account, AccountJpaEntity jpa) throws Exception{

        if(isAggregateInputInvalid(account)){
            throw new IllegalArgumentException("Illegal account parameter!");
        }
        if(account.getBalance() != null){
            jpa.setBalance(account.getBalance());
        }
        if(account.getAccountName() != null && account.getAccountName() != null){
            jpa.setAccountname(account.getAccountName());
        }
        if(account.getOwner() != null && !account.getOwner().getFullName().isEmpty()){
            jpa.setOwnerlastname(account.getOwner().getLastName());
            jpa.setOwnerfirstname(account.getOwner().getFirstName());
        }

        return jpa;
    }

    private boolean isAggregateInputInvalid(AccountAggregate account){
        String accountName = account.getAccountName();
        String firstName = account.getOwner().getFirstName();
        String lastName = account.getOwner().getLastName();
        if(accountName.isEmpty() || accountName.length() > 10){
            return true;
        }
        if(firstName.isEmpty() || firstName.length() > 15){
            return true;
        }
        if(lastName.isEmpty() || lastName.length() > 25){
            return true;
        }

        return false;
    }*/
    public AccountJpaEntity mapAggregateToJpa(AccountAggregate aggregate){
        AccountJpaEntity jpa = new AccountJpaEntity();
        jpa.setId(aggregate.getAccountId());
        jpa.setAccountname(aggregate.getAccountName());
        jpa.setOwnerfirstname(aggregate.getOwner().getFirstName());
        jpa.setOwnerlastname(aggregate.getOwner().getLastName());
        jpa.setBalance(aggregate.getBalance());
        jpa.setInstitution(aggregate.getInstitutionName());
        //jpa.setTransactions(aggregate.getTransactions());

        return jpa;
    }
}

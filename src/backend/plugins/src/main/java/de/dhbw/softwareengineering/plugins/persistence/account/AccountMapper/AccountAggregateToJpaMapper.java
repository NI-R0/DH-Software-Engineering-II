package de.dhbw.softwareengineering.plugins.persistence.account.AccountMapper;

import de.dhbw.softwareengineering.account.AccountAggregate;
import de.dhbw.softwareengineering.plugins.persistence.account.AccountJpaEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AccountAggregateToJpaMapper {
    public AccountJpaEntity mapAggregateToNewJpa(UUID institutionId, AccountAggregate account) throws Exception{
        return null;
    }

    public AccountJpaEntity mapAggregateToExistingJpa(AccountAggregate account, AccountJpaEntity jpa) throws Exception{
        return null;
    }

    private boolean isAggregateInputValid(AccountAggregate account){
        return false;
    }
}

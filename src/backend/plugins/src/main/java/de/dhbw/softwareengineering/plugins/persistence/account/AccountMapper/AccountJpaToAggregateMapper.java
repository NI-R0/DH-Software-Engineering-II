package de.dhbw.softwareengineering.plugins.persistence.account.AccountMapper;

import de.dhbw.softwareengineering.account.AccountAggregate;
import de.dhbw.softwareengineering.plugins.persistence.account.AccountJpaEntity;
import de.dhbw.softwareengineering.plugins.persistence.account.AccountJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountJpaToAggregateMapper {

    public AccountAggregate mapJpaToAggregate(AccountJpaEntity jpa) throws Exception{
        return null;
    }

    private boolean isJpaInputValid(AccountJpaEntity jpa){
        return false;
    }
}

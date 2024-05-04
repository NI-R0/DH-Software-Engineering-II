package de.dhbw.softwareengineering.plugins.persistence.institution.InstitutionMapper;

import de.dhbw.softwareengineering.domain.institution.InstitutionAggregate;
import de.dhbw.softwareengineering.plugins.persistence.account.AccountJpaEntity;
import de.dhbw.softwareengineering.plugins.persistence.account.AccountMapper.AccountAggregateToJpaMapper;
import de.dhbw.softwareengineering.plugins.persistence.institution.InstitutionJpaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InstitutionAggregateToJpaMapper {

    @Autowired
    AccountAggregateToJpaMapper accountAggregateToJpa;

    public InstitutionJpaEntity mapAggregateToJpa(InstitutionAggregate institution){
        InstitutionJpaEntity jpaEntity = new InstitutionJpaEntity();
        jpaEntity.setName(institution.getName());
        jpaEntity.setInstitutionType(institution.getType());
        List<AccountJpaEntity> accounts = new ArrayList<>();
        institution.getAccounts().forEach(account -> {
            accounts.add(accountAggregateToJpa.mapAggregateToJpa(account));
        });
        jpaEntity.setAccounts(accounts);
        return jpaEntity;
    }
}

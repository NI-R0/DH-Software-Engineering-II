package de.dhbw.softwareengineering.plugins.persistence.institution.InstitutionMapper;

import de.dhbw.softwareengineering.domain.account.AccountRepository;
import de.dhbw.softwareengineering.domain.institution.InstitutionAggregate;
import de.dhbw.softwareengineering.plugins.persistence.account.AccountMapper.AccountJpaToAggregateMapper;
import de.dhbw.softwareengineering.plugins.persistence.institution.InstitutionJpaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class InstitutionJpaToAggregateMapper {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountJpaToAggregateMapper accountJpaToAggregate;

    public InstitutionAggregate mapJpaToAggregate(InstitutionJpaEntity jpa) throws Exception{
        /*if(isJpaInputInvalid(jpa)){
            throw new IllegalArgumentException("JPA properties are illegal!");
        }*/
        InstitutionAggregate institution = new InstitutionAggregate();
        institution.setType(jpa.getInstitutionType());
        institution.setName(jpa.getName());

        institution.setAccounts(accountRepository.findAllByInstitution(jpa.getName()));

        return institution;
    }

    /*private boolean isJpaInputInvalid(InstitutionJpaEntity jpa){
        UUID id = jpa.getId();
        String name = jpa.getName();
        InstitutionType type = jpa.getInstitutionType();
        if(id == null || name.isEmpty() || type == null){
            return true;
        }
        return false;
    }*/
}

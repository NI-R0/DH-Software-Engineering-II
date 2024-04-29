package de.dhbw.softwareengineering.plugins.persistence.institution.InstitutionMapper;

import de.dhbw.softwareengineering.domain.account.AccountAggregate;
import de.dhbw.softwareengineering.enums.InstitutionType;
import de.dhbw.softwareengineering.domain.institution.InstitutionAggregate;
import de.dhbw.softwareengineering.plugins.persistence.account.AccountJpaEntity;
import de.dhbw.softwareengineering.plugins.persistence.account.AccountJpaRepository;
import de.dhbw.softwareengineering.plugins.persistence.account.AccountMapper.AccountJpaToAggregateMapper;
import de.dhbw.softwareengineering.plugins.persistence.institution.InstitutionJpaEntity;
import de.dhbw.softwareengineering.domain.values.NameValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class InstitutionJpaToAggregateMapper {
    @Autowired
    AccountJpaRepository accountJpaRepository;
    @Autowired
    AccountJpaToAggregateMapper accountJpaToAggregate;

    public InstitutionAggregate mapJpaToAggregate(InstitutionJpaEntity jpa) throws Exception{
        if(isJpaInputInvalid(jpa)){
            throw new IllegalArgumentException("JPA properties are illegal!");
        }
        InstitutionAggregate institution = new InstitutionAggregate();
        institution.setInstitutionId(jpa.getId());
        institution.setType(jpa.getInstitutionType());
        institution.setName(new NameValue(jpa.getName()));

        institution.setAccounts(findAllAccounts(jpa.getName()));

        return institution;
    }

    public List<AccountAggregate> findAllAccounts(String institutionName){
        List<AccountAggregate> accountAggregates = new ArrayList<>();
        List<AccountJpaEntity> jpaOptionals = accountJpaRepository.findAllByInstitutionName(institutionName);

        jpaOptionals.forEach(jpaEntity -> {
            try{
                accountAggregates.add(accountJpaToAggregate.mapJpaToAggregate(jpaEntity));
            }
            catch(Exception e){
                System.out.println(e.toString());
            }
        });
        return accountAggregates;
    }

    private boolean isJpaInputInvalid(InstitutionJpaEntity jpa){
        UUID id = jpa.getId();
        String name = jpa.getName();
        InstitutionType type = jpa.getInstitutionType();
        if(id == null || name.isEmpty() || type == null){
            return true;
        }
        return false;
    }
}

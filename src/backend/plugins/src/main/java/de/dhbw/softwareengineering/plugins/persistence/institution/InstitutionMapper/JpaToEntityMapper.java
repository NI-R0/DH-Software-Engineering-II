package de.dhbw.softwareengineering.plugins.persistence.institution.InstitutionMapper;

import de.dhbw.softwareengineering.institution.InstitutionAggregate;
import de.dhbw.softwareengineering.plugins.persistence.institution.InstitutionJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class JpaToEntityMapper {
    //Institution
    public InstitutionAggregate mapJpaToAggregate(InstitutionJpaEntity jpa){
        /*InstitutionAggregate institution = new InstitutionAggregate();
        NameValue name = new NameValue(jpa.getName());
        institution.setName(name);
        institution.setType(jpa.getInstitutionType());
        List<AccountJpaEntity> accounts = new ArrayList<>(jpa.getAccounts());
        institution.setAccounts();*/
        return null;
    }
}

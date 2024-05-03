package de.dhbw.softwareengineering.plugins.persistence.institution.InstitutionMapper;

import de.dhbw.softwareengineering.domain.institution.InstitutionAggregate;
import de.dhbw.softwareengineering.plugins.persistence.institution.InstitutionJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class InstitutionAggregateToJpaMapper {

    public InstitutionJpaEntity mapAggregateToJpa(InstitutionAggregate institution){
        InstitutionJpaEntity jpaEntity = new InstitutionJpaEntity();
        jpaEntity.setId(institution.getInstitutionId());
        jpaEntity.setName(institution.getName());
        jpaEntity.setInstitutionType(institution.getType());
        return jpaEntity;
    }
}

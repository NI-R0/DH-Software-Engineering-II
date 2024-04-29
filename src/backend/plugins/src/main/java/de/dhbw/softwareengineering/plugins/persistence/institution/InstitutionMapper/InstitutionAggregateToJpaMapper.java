package de.dhbw.softwareengineering.plugins.persistence.institution.InstitutionMapper;

import de.dhbw.softwareengineering.domain.institution.InstitutionAggregate;
import de.dhbw.softwareengineering.plugins.persistence.institution.InstitutionJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class InstitutionAggregateToJpaMapper {

    public InstitutionJpaEntity mapAggregateToNewJpa(InstitutionAggregate institution) throws Exception{
        if(isAggregateInputInvalid(institution)){
            throw new IllegalArgumentException("Illegal account parameter!");
        }
        InstitutionJpaEntity jpaEntity = new InstitutionJpaEntity();
        jpaEntity.setId(institution.getInstitutionId());
        jpaEntity.setName(institution.getName().getName());
        jpaEntity.setInstitutionType(institution.getType());

        return jpaEntity;
    }

    public InstitutionJpaEntity mapAggregateToExistingJpa(InstitutionAggregate institution, InstitutionJpaEntity jpa) throws Exception{
        if(isAggregateInputInvalid(institution)){
            throw new IllegalArgumentException("Illegal account parameter!");
        }
        if(institution.getName() != null && institution.getName().getName() != null){
            jpa.setName(institution.getName().getName());
        }
        if(institution.getType() != null){
            jpa.setInstitutionType(institution.getType());
        }
        return jpa;
    }

    private boolean isAggregateInputInvalid(InstitutionAggregate institution){
        String name = institution.getName().getName();
        if(name.isEmpty() || name.length() > 20){
            return true;
        }
        return false;
    }
}

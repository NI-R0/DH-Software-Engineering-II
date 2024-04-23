package de.dhbw.softwareengineering.plugins.persistence.institution;

import de.dhbw.softwareengineering.account.AccountAggregate;
import de.dhbw.softwareengineering.institution.InstitutionAggregate;
import de.dhbw.softwareengineering.institution.InstitutionRepository;
import de.dhbw.softwareengineering.plugins.persistence.institution.InstitutionMapper.EntityToJpaMapper;
import de.dhbw.softwareengineering.plugins.persistence.institution.InstitutionMapper.JpaToEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class InstitutionRepositoryImplementation implements InstitutionRepository {
    @Autowired
    InstitutionJpaRepository jpaRepository;
    @Autowired
    EntityToJpaMapper entityToJpa;
    @Autowired
    JpaToEntityMapper jpaToEntity;

    @Override
    public List<InstitutionAggregate> findAllInstitutions(){
        /*List<InstitutionAggregate> institutions = new ArrayList<>();
        List<InstitutionJpaEntity> JpaInstitutions = jpaRepository.findAll();
        JpaInstitutions.forEach(jpa ->{
            institutions.add(jpaToEntity.)
        });*/
        return null;
    }
    @Override
    public Optional<InstitutionAggregate> findById(UUID id){
        return null;
    }
    @Override
    public Optional<InstitutionAggregate> findByName(String name){
        return null;
    }
    @Override
    public InstitutionAggregate create(InstitutionAggregate institution) throws Exception{
        return null;
    }
    @Override
    public InstitutionAggregate update(InstitutionAggregate institution) throws Exception{
        return null;
    }
    @Override
    public void deleteById(UUID id){

    }
    @Override
    public void deleteByName(String name){

    }
    @Override
    public List<AccountAggregate> findAllAccounts(){
        return null;
    }
    @Override
    public List<AccountAggregate> createAccount(AccountAggregate account){
        return null;
    }
    @Override
    public List<AccountAggregate> updateAccount(AccountAggregate account){
        return null;
    }
    @Override
    public List<AccountAggregate> deleteAccount(AccountAggregate account){
        return null;
        //Warum nicht void
    }
}






















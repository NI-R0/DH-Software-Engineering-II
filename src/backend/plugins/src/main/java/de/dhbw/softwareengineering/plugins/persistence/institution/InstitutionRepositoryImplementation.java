package de.dhbw.softwareengineering.plugins.persistence.institution;

import de.dhbw.softwareengineering.domain.account.AccountAggregate;
import de.dhbw.softwareengineering.domain.institution.InstitutionAggregate;
import de.dhbw.softwareengineering.domain.institution.InstitutionRepository;
import de.dhbw.softwareengineering.plugins.persistence.account.AccountJpaRepository;
import de.dhbw.softwareengineering.plugins.persistence.account.AccountMapper.AccountJpaToAggregateMapper;
import de.dhbw.softwareengineering.plugins.persistence.account.AccountRepositoryImplementation;
import de.dhbw.softwareengineering.plugins.persistence.institution.InstitutionMapper.InstitutionAggregateToJpaMapper;
import de.dhbw.softwareengineering.plugins.persistence.institution.InstitutionMapper.InstitutionJpaToAggregateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class InstitutionRepositoryImplementation implements InstitutionRepository {
    @Autowired
    InstitutionJpaRepository institutionJpaRepository;
    @Autowired
    AccountJpaRepository accountJpaRepository;
    @Autowired
    InstitutionAggregateToJpaMapper aggregateToJpa;
    @Autowired
    InstitutionJpaToAggregateMapper jpaToAggregate;
    @Autowired
    AccountRepositoryImplementation accountRepositoryImpl;
    @Autowired
    AccountJpaToAggregateMapper accountJpaToAggregate;

    @Override
    public List<InstitutionAggregate> findAllInstitutions(){
        List<InstitutionAggregate> institutionAggregates = new ArrayList<>();
        List<InstitutionJpaEntity> jpaOptionals = institutionJpaRepository.findAll();
        jpaOptionals.forEach(jpaEntity -> {
            try{
                institutionAggregates.add(jpaToAggregate.mapJpaToAggregate(jpaEntity));
            }
            catch(Exception e){
                System.out.println(e.toString());
            }
        });
        return institutionAggregates;
    }
    @Override
    public Optional<InstitutionAggregate> findById(UUID id){
        try{
            Optional<InstitutionJpaEntity> jpaOptional = institutionJpaRepository.findById(id);
            InstitutionJpaEntity jpaEntity = jpaOptional.orElseThrow(IllegalArgumentException::new);
            return Optional.of(jpaToAggregate.mapJpaToAggregate(jpaEntity));
        }
        catch(Exception e){
            System.out.println(e.toString());
            return Optional.empty();
        }
    }
    @Override
    public Optional<InstitutionAggregate> findByName(String name){
        try{
            Optional<InstitutionJpaEntity> jpaOptional = institutionJpaRepository.findByName(name);
            InstitutionJpaEntity jpaEntity = jpaOptional.orElseThrow(IllegalArgumentException::new);
            return Optional.of(jpaToAggregate.mapJpaToAggregate(jpaEntity));
        }
        catch(Exception e){
            System.out.println(e.toString());
            return Optional.empty();
        }
    }
    @Override
    public Optional<InstitutionAggregate> createInstitution(InstitutionAggregate institution) throws Exception{
        try{
            Optional<InstitutionJpaEntity> jpaOptional = institutionJpaRepository.findById(institution.getInstitutionId());

            if(jpaOptional.isPresent()){
                throw new IllegalArgumentException("Transaction with ID " + institution.getInstitutionId().toString() + " already exists!");
            }

            InstitutionJpaEntity jpaEntity = aggregateToJpa.mapAggregateToNewJpa(institution);

            institutionJpaRepository.save(jpaEntity);

            return Optional.of(jpaToAggregate.mapJpaToAggregate(jpaEntity));
        }
        catch(Exception e){
            System.out.println(e.toString());
            return Optional.empty();
        }
    }
    @Override
    public Optional<InstitutionAggregate> updateInstitution(InstitutionAggregate institution) throws Exception{
        try{
            Optional<InstitutionJpaEntity> jpaOptional = institutionJpaRepository.findById(institution.getInstitutionId());

            InstitutionJpaEntity jpaEntity = jpaOptional.orElseThrow(IllegalArgumentException::new);

            jpaEntity = aggregateToJpa.mapAggregateToExistingJpa(institution, jpaEntity);

            institutionJpaRepository.save(jpaEntity);

            return Optional.of(jpaToAggregate.mapJpaToAggregate(jpaEntity));
        }
        catch(Exception e){
            System.out.println(e.toString());
            return Optional.empty();
        }
    }
    @Override
    public void deleteInstitution(InstitutionAggregate institution){
        deleteAllAccounts(institution);
        Optional<InstitutionJpaEntity> jpaOptional = institutionJpaRepository.findById(institution.getInstitutionId());
        jpaOptional.ifPresent(jpa -> {
            institutionJpaRepository.delete(jpa);
        });
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public List<AccountAggregate> findAllAccounts(InstitutionAggregate institution){
        return accountRepositoryImpl.findAllByInstitution(institution.getName());
    }
    @Override
    public List<AccountAggregate> createAccount(InstitutionAggregate institution, AccountAggregate account){
        accountRepositoryImpl.createAccount(institution.getName(), account);
        return findAllAccounts(institution);
    }
    @Override
    public List<AccountAggregate> updateAccount(InstitutionAggregate institution, AccountAggregate account){
        accountRepositoryImpl.editAccount(account);
        return findAllAccounts(institution);
    }
    @Override
    public List<AccountAggregate> deleteAccount(InstitutionAggregate institution, AccountAggregate account){
        accountRepositoryImpl.deleteAccount(account);
        return findAllAccounts(institution);
    }

    @Override
    public List<AccountAggregate> deleteAllAccounts(InstitutionAggregate institution){
        institution.getAccounts().forEach(account -> {
            accountRepositoryImpl.deleteAccount(account);
        });
        return findAllAccounts(institution);
    }
}























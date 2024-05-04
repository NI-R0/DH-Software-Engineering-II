package de.dhbw.softwareengineering.plugins.persistence.institution;

import de.dhbw.softwareengineering.domain.institution.InstitutionAggregate;
import de.dhbw.softwareengineering.domain.institution.InstitutionRepository;
import de.dhbw.softwareengineering.plugins.persistence.account.AccountJpaRepository;
import de.dhbw.softwareengineering.plugins.persistence.account.AccountMapper.AccountAggregateToJpaMapper;
import de.dhbw.softwareengineering.plugins.persistence.institution.InstitutionMapper.InstitutionAggregateToJpaMapper;
import de.dhbw.softwareengineering.plugins.persistence.institution.InstitutionMapper.InstitutionJpaToAggregateMapper;
import de.dhbw.softwareengineering.plugins.persistence.transaction.TransactionJpaRepository;
import de.dhbw.softwareengineering.plugins.persistence.transaction.TransactionMapper.TransactionEntityToJpaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class InstitutionRepositoryBridge implements InstitutionRepository {
    @Autowired
    InstitutionJpaRepository institutionJpaRepository;
    @Autowired
    AccountJpaRepository accountJpaRepository;
    @Autowired
    TransactionJpaRepository transactionJpaRepository;


    @Autowired
    InstitutionAggregateToJpaMapper aggregateToJpa;
    @Autowired
    InstitutionJpaToAggregateMapper jpaToAggregate;

    @Autowired
    AccountAggregateToJpaMapper accountAggregateToJpa;
    @Autowired
    TransactionEntityToJpaMapper transactionEntityToJpa;



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
    public Optional<InstitutionAggregate> findByName(String name){
        try{
            Optional<InstitutionJpaEntity> jpaOptional = institutionJpaRepository.findById(name);
            InstitutionJpaEntity jpaEntity = jpaOptional.orElseThrow(IllegalArgumentException::new);
            return Optional.of(jpaToAggregate.mapJpaToAggregate(jpaEntity));
        }
        catch(Exception e){
            System.out.println(e.toString());
            return Optional.empty();
        }
    }

    @Override
    public InstitutionAggregate save(InstitutionAggregate institution){

        /*institution.getAccounts().forEach(account -> {
            account.getTransactions().forEach(transaction -> {
                try{
                    transactionJpaRepository.save(transactionEntityToJpa.mapEntityToJpa(transaction));
                }
                catch(Exception e){
                    System.out.println("Transaction already saved!");
                }
            });
            try{
                accountJpaRepository.save(accountAggregateToJpa.mapAggregateToJpa(account));
            }
            catch(Exception e){
                System.out.println("Account already saved!");
            }

        });*/

        InstitutionJpaEntity jpaEntity = aggregateToJpa.mapAggregateToJpa(institution);
        institutionJpaRepository.save(jpaEntity);

        return institution;
    }

    @Modifying
    @Override
    public void delete(InstitutionAggregate institution){

        institution.getAccounts().forEach(account -> {
            transactionJpaRepository.deleteAllByAccount(account.getAccountId());
            accountJpaRepository.deleteById(account.getAccountId());
        });
        //accountJpaRepository.deleteAllByInstitution(institution.getName());
        InstitutionJpaEntity jpaEntity = aggregateToJpa.mapAggregateToJpa(institution);

        institutionJpaRepository.delete(jpaEntity);

    }























    /*@Override
    public void deleteInstitution(InstitutionAggregate institution){
        deleteAllAccounts(institution);
        Optional<InstitutionJpaEntity> jpaOptional = institutionJpaRepository.findById(institution.getInstitutionId());
        jpaOptional.ifPresent(jpa -> {
            institutionJpaRepository.delete(jpa);
        });
    }*/

    /////////////////////////////////////////////////////////////////////////////////////////////////////

    /*@Override
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
    }*/
}























package de.dhbw.softwareengineering.plugins.persistence.account;

import de.dhbw.softwareengineering.domain.account.Account;
import de.dhbw.softwareengineering.domain.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AccountRepositoryBridge implements AccountRepository {

    private final AccountSpringDataRepository springDataRepository;

    @Autowired
    public AccountRepositoryBridge(final AccountSpringDataRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public List<Account> findByInstitution(String institutionName){
        return springDataRepository.findAccountsByInstitutionName(institutionName);
    }

    @Override
    public Optional<Account> findByInstitutionAndId(String institutionName, UUID accountId){
        return springDataRepository.findAccountByInstitutionAndId(institutionName, accountId);
    }

    @Override
    public Optional<Account> findByInstitutionAndName(String institutionName, String accountName){
        return springDataRepository.findAccountByInstitutionAndName(institutionName, accountName);
    }

}

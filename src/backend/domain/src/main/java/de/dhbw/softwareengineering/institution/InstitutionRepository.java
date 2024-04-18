package de.dhbw.softwareengineering.institution;

import de.dhbw.softwareengineering.account.AccountAggregate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InstitutionRepository {

    public List<InstitutionAggregate> findAllInstitutions();
    public Optional<InstitutionAggregate> findById(UUID id);
    public Optional<InstitutionAggregate> findByName(String name);

    public InstitutionAggregate create(InstitutionAggregate institution) throws Exception;
    public InstitutionAggregate update(InstitutionAggregate institution) throws Exception;

    public void deleteById(UUID id);
    public void deleteByName(String name);

    public List<AccountAggregate> findAllAccounts();
    public List<AccountAggregate> createAccount(AccountAggregate account);
    public List<AccountAggregate> updateAccount(AccountAggregate account);
    public List<AccountAggregate> deleteAccount(AccountAggregate account);

}

package de.dhbw.softwareengineering.domain.institution;

import de.dhbw.softwareengineering.domain.account.AccountAggregate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InstitutionRepository {

    public List<InstitutionAggregate> findAllInstitutions();
    public Optional<InstitutionAggregate> findById(UUID id);
    public Optional<InstitutionAggregate> findByName(String name);


    /*public Optional<InstitutionAggregate> createInstitution(InstitutionAggregate institution) throws Exception;
    public Optional<InstitutionAggregate> updateInstitution(InstitutionAggregate institution) throws Exception;
    public void deleteInstitution(InstitutionAggregate institution);

    public List<AccountAggregate> findAllAccounts(InstitutionAggregate institution);
    public List<AccountAggregate> createAccount(InstitutionAggregate institution, AccountAggregate account);
    public List<AccountAggregate> updateAccount(InstitutionAggregate institution, AccountAggregate account);
    public List<AccountAggregate> deleteAccount(InstitutionAggregate institution, AccountAggregate account);
    public List<AccountAggregate> deleteAllAccounts(InstitutionAggregate institution);*/



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public InstitutionAggregate save(InstitutionAggregate institution);
    public void delete(InstitutionAggregate institution);

}

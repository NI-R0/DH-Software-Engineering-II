package de.dhbw.softwareengineering.domain.account;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {

    List<Account> findByInstitution(String institutionName);

    Optional<Account> findByInstitutionAndId(String institutionName, UUID accountId);

    Optional<Account> findByInstitutionAndName(String institutionName, String accountName);

}

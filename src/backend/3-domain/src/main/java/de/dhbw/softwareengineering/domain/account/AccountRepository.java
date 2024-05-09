package de.dhbw.softwareengineering.domain.account;

import de.dhbw.softwareengineering.validation.annotations.ValidAccountName;
import de.dhbw.softwareengineering.validation.annotations.ValidId;
import de.dhbw.softwareengineering.validation.annotations.ValidInstitutionName;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {

    List<Account> findByInstitution(@ValidInstitutionName String institutionName);

    Optional<Account> findByInstitutionAndId(@ValidInstitutionName String institutionName,
                                             @ValidId UUID accountId);

    Optional<Account> findByInstitutionAndName(@ValidInstitutionName String institutionName,
                                               @ValidAccountName String accountName);

}

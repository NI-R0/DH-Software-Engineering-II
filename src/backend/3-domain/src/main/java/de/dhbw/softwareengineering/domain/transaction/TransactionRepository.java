package de.dhbw.softwareengineering.domain.transaction;

import de.dhbw.softwareengineering.validation.annotations.ValidAccountName;
import de.dhbw.softwareengineering.validation.annotations.ValidId;
import de.dhbw.softwareengineering.validation.annotations.ValidInstitutionName;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository {

    Optional<Transaction> findByAccountAndId(@ValidId UUID accountId,
                                             @ValidId UUID transactionId);

    List<Transaction> findByInstitutionAndAccount(@ValidInstitutionName String institutionName,
                                                  @ValidAccountName String accountName);

    Optional<Transaction> findByInstitutionAndAccountAndId(@ValidInstitutionName String institutionName,
                                                           @ValidAccountName String accountName,
                                                           @ValidId UUID transactionId);

}

package de.dhbw.softwareengineering.plugins.persistence.account;

import de.dhbw.softwareengineering.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountSpringDataRepository extends JpaRepository<Account, UUID> {

    @Query(value = "SELECT * FROM ACCOUNT WHERE institution_name = ?1", nativeQuery = true)
    List<Account> findAccountsByInstitutionName(String institutionName);

    @Query(value = "SELECT * FROM ACCOUNT WHERE institution_name = ?1 AND id = ?2", nativeQuery = true)
    Optional<Account> findAccountByInstitutionAndId(String institution, UUID account);

    @Query(value = "SELECT * FROM ACCOUNT WHERE institution_name = ?1 AND account_name = ?2", nativeQuery = true)
    Optional<Account> findAccountByInstitutionAndName(String institution, String account);

}

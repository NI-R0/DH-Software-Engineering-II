package de.dhbw.softwareengineering.plugins.persistence.transaction;

import de.dhbw.softwareengineering.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionSpringDataRepository extends JpaRepository<Transaction, UUID> {

    @Query(value = "SELECT * FROM TRANSACTION WHERE account_id = ?1", nativeQuery = true)
    List<Transaction> findAllByAccount(UUID accountId);

    @Query(value = "SELECT * FROM TRANSACTION WHERE account_id = ?1 AND id = ?2", nativeQuery = true)
    Optional<Transaction> findByAccountAndId(UUID accountId, UUID transactionId);


    @Query(value = "SELECT TRANSACTION.* FROM INSTITUTION JOIN ACCOUNT ON INSTITUTION.name = ACCOUNT.institution_name JOIN TRANSACTION ON ACCOUNT.id = TRANSACTION.account_id WHERE INSTITUTION.name = ?1 AND ACCOUNT.account_name = ?2", nativeQuery = true)
    List<Transaction> findAllByInstitutionAndAccount(String institutionName, String accountName);

    @Query(value = "SELECT TRANSACTION.* FROM INSTITUTION JOIN ACCOUNT ON INSTITUTION.name = ACCOUNT.institution_name JOIN TRANSACTION ON ACCOUNT.id = TRANSACTION.account_id WHERE INSTITUTION.name = ?1 AND ACCOUNT.account_name = ?2 AND TRANSACTION.id = ?3", nativeQuery = true)
    Optional<Transaction> findByInstitutionAndAccountAndId(String institutionName, String accountName, UUID transactionId);

}

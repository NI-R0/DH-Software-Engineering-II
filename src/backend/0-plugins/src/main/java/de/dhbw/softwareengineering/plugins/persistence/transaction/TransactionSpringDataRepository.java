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

}

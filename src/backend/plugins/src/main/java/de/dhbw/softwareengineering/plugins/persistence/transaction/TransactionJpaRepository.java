package de.dhbw.softwareengineering.plugins.persistence.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionJpaRepository extends JpaRepository<TransactionJpaEntity, UUID> {

    @Query(value="SELECT * FROM FINANCE-MANAGER-DB.TRANSACTION WHERE accountid = ?1", nativeQuery = true)
    List<TransactionJpaEntity> findByAccount(UUID accountId);

}

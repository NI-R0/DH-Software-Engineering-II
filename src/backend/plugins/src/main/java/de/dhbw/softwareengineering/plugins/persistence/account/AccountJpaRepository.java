package de.dhbw.softwareengineering.plugins.persistence.account;

import de.dhbw.softwareengineering.plugins.persistence.transaction.TransactionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountJpaRepository extends JpaRepository<AccountJpaEntity, UUID> {

    @Query(value="SELECT * FROM ACCOUNT WHERE institution = ?1", nativeQuery = true)
    List<AccountJpaEntity> findAllByInstitutionName(String institutionName);

    @Query(value = "SELECT * FROM ACCOUNT WHERE institution = ?1 AND accountId = ?2", nativeQuery = true)
    Optional<AccountJpaEntity> findByIdAndInstitution(String institutionName, UUID accountId);

    @Query(value = "SELECT * FROM ACCOUNT WHERE institution = ?1 AND accountName = ?2", nativeQuery = true)
    Optional<AccountJpaEntity> findByNameAndInstitution(String institutionName, String accountName);

    @Modifying
    @Query(value="DELETE FROM ACCOUNT WHERE institution = ?1",nativeQuery = true)
    void deleteAllByInstitution(String institutionName);
}

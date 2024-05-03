package de.dhbw.softwareengineering.plugins.persistence.account;

import de.dhbw.softwareengineering.plugins.persistence.transaction.TransactionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountJpaRepository extends JpaRepository<AccountJpaEntity, UUID> {

    @Query(value="SELECT * FROM ACCOUNT WHERE institutionid = ?1", nativeQuery = true)
    List<AccountJpaEntity> findAllByInstitutionId(UUID institutionId);

    @Query(value="SELECT * FROM ACCOUNT WHERE institution = ?1", nativeQuery = true)
    List<AccountJpaEntity> findAllByInstitutionName(String institutionName);

    @Query(value="DELETE FROM FINANCE-MANAGER-DB.ACCOUNT WHERE institutionid = ?1",nativeQuery = true)
    @Modifying
    void deleteAllByInstitution(UUID institutionId);
}

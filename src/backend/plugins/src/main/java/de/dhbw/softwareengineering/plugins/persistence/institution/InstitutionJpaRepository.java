package de.dhbw.softwareengineering.plugins.persistence.institution;

import de.dhbw.softwareengineering.plugins.persistence.account.AccountJpaEntity;
import de.dhbw.softwareengineering.plugins.persistence.account.AccountJpaRepository;
import de.dhbw.softwareengineering.plugins.persistence.transaction.TransactionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InstitutionJpaRepository extends JpaRepository<InstitutionJpaEntity, UUID> {
    @Query(value="SELECT * FROM FINANCE-MANAGER-DB.INSTITUTION WHERE name=?1",nativeQuery = true)
    Optional<InstitutionJpaEntity> findByName(String institutionName);
}

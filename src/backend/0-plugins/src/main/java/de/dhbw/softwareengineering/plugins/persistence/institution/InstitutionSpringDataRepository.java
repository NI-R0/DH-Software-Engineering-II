package de.dhbw.softwareengineering.plugins.persistence.institution;

import de.dhbw.softwareengineering.domain.institution.Institution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutionSpringDataRepository extends JpaRepository<Institution, String> {
}

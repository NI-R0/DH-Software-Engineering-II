package de.dhbw.softwareengineering.domain.institution;

import java.util.List;
import java.util.Optional;

public interface InstitutionRepository {

    List<Institution> findAllInstitutions();

    Optional<Institution> findByName(String institutionName);

    Institution save(Institution institution);

    void delete(Institution institution);


}

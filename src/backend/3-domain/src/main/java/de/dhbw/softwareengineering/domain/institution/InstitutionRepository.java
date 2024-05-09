package de.dhbw.softwareengineering.domain.institution;

import de.dhbw.softwareengineering.validation.annotations.ValidInstitutionName;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Validated
public interface InstitutionRepository {

    List<Institution> findAllInstitutions();

    Optional<Institution> findByName(@ValidInstitutionName String institutionName);

    Institution save(@Valid Institution institution);

    void delete(@Valid Institution institution);


}

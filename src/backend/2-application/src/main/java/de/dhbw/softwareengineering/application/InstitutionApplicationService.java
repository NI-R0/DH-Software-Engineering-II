package de.dhbw.softwareengineering.application;

import de.dhbw.softwareengineering.domain.institution.Institution;
import de.dhbw.softwareengineering.domain.institution.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstitutionApplicationService {

    private final InstitutionRepository institutionRepository;

    @Autowired
    public InstitutionApplicationService(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    public List<Institution> findAllInstitutions(){
        return this.institutionRepository.findAllInstitutions();
    }
}

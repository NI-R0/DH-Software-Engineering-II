package de.dhbw.softwareengineering.plugins.persistence.institution;

import de.dhbw.softwareengineering.domain.institution.Institution;
import de.dhbw.softwareengineering.domain.institution.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InstitutionRepositoryBridge implements InstitutionRepository {

    private final InstitutionSpringDataRepository springDataRepository;

    @Autowired
    public InstitutionRepositoryBridge(final InstitutionSpringDataRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public List<Institution> findAllInstitutions(){
        return this.springDataRepository.findAll();
    }

    @Override
    public Optional<Institution> findByName(String institutionName){
        return this.springDataRepository.findById(institutionName);
    }

    @Override
    public Institution save(Institution newInstitution) {
        return this.springDataRepository.save(newInstitution);
    }

    @Override
    public void delete(Institution institution) {
        this.springDataRepository.delete(institution);
    }

}

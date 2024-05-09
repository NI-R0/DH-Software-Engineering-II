package de.dhbw.softwareengineering.application;

import de.dhbw.softwareengineering.adapters.institution.InstitutionCreateDTO;
import de.dhbw.softwareengineering.adapters.institution.InstitutionUpdateDTO;
import de.dhbw.softwareengineering.adapters.institution.mapper.CreateDTOToInstitutionMapper;
import de.dhbw.softwareengineering.adapters.institution.mapper.UpdateDTOToInstitutionMapper;
import de.dhbw.softwareengineering.exceptions.ObjectNotFoundException;
import de.dhbw.softwareengineering.validation.annotations.ValidInstitutionName;
import de.dhbw.softwareengineering.domain.institution.Institution;
import de.dhbw.softwareengineering.domain.institution.InstitutionRepository;
import de.dhbw.softwareengineering.domain.services.CompatibilityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated
public class InstitutionApplicationService {

    private final InstitutionRepository institutionRepository;

    private final CreateDTOToInstitutionMapper createDTOMapper;

    private final UpdateDTOToInstitutionMapper updateMapper;

    private final CompatibilityService compatibility;

    @Autowired
    public InstitutionApplicationService(InstitutionRepository institutionRepository, CreateDTOToInstitutionMapper createDTOMapper, UpdateDTOToInstitutionMapper updateMapper, CompatibilityService compatibility) {
        this.institutionRepository = institutionRepository;
        this.createDTOMapper = createDTOMapper;
        this.updateMapper = updateMapper;
        this.compatibility = compatibility;
    }

    public List<Institution> getAllInstitutions(){
        return this.institutionRepository.findAllInstitutions();
    }

    public Optional<Institution> getInstitution(@ValidInstitutionName String name){
        return this.institutionRepository.findByName(name);
    }

    public Institution createInstitution(@Valid InstitutionCreateDTO dto) throws Exception{

        this.institutionRepository.findByName(dto.getName()).ifPresent(i -> {
            throw new IllegalArgumentException("Institution already exists!");
        });

        Institution toCreate = this.createDTOMapper.apply(dto);

        return this.institutionRepository.save(toCreate);
    }
    public Institution updateInstitution(@Valid InstitutionUpdateDTO dto) throws Exception{

        //Check if institution to update does exist
        Institution toUpdate = this.institutionRepository.findByName(dto.getName())
                .orElseThrow(() -> new ObjectNotFoundException("Institution with name " + dto.getName() + " does not exist."));


        //Check if institution with new name does not exist (works if newName is empty)
        if(dto.getNewName() != null && dto.getNewName() != dto.getName()){
            this.institutionRepository.findByName(dto.getNewName()).ifPresent(i -> {
                throw new IllegalArgumentException("Institution with name " + dto.getNewName() + " already exists!");
            });
        }

        //Check if updated institution type is compatible with all transactions
        if(dto.getType() != null){
            toUpdate.getAccounts().forEach(account -> {
                if(!compatibility.isInstitutionTypeCompatibleWithTransactionList(dto.getType(), account.getTransactions())){
                    throw new IllegalArgumentException("Types not comptatible!");
                }
            });
        }

        this.institutionRepository.delete(toUpdate);

        Institution newInstitution = this.updateMapper.apply(toUpdate, dto);
        //Manuelle Validierung an dieser Stelle möglich
        return this.institutionRepository.save(newInstitution);
    }

    public void deleteInstitution(@ValidInstitutionName String name) throws Exception{
        Institution institution = this.institutionRepository.findByName(name)
                .orElseThrow(() -> new ObjectNotFoundException("Institution with name " + name + " does not exist."));
        this.institutionRepository.delete(institution);
    }

}

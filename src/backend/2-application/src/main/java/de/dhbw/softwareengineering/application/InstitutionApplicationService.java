package de.dhbw.softwareengineering.application;

import de.dhbw.softwareengineering.adapters.institution.InstitutionCreateDTO;
import de.dhbw.softwareengineering.adapters.institution.InstitutionUpdateDTO;
import de.dhbw.softwareengineering.adapters.institution.mapper.CreateDTOToInstitutionMapper;
import de.dhbw.softwareengineering.annotations.ValidInstitutionName;
import de.dhbw.softwareengineering.constants.Constants;
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

    private final CompatibilityService compatibility;

    @Autowired
    public InstitutionApplicationService(InstitutionRepository institutionRepository, CreateDTOToInstitutionMapper createDTOMapper, CompatibilityService compatibility) {
        this.institutionRepository = institutionRepository;
        this.createDTOMapper = createDTOMapper;
        this.compatibility = compatibility;
    }

    public List<Institution> getAllInstitutions(){
        return this.institutionRepository.findAllInstitutions();
    }

    public Optional<Institution> getInstitution(@ValidInstitutionName String name){
        return this.institutionRepository.findByName(name);
    }

    public Institution createInstitution(@Valid InstitutionCreateDTO institution) throws Exception{
        Institution toCreate = this.createDTOMapper.apply(institution);

        if(isInputInvalid(toCreate)){
            throw new IllegalArgumentException("Wrong input!");
        }

        this.institutionRepository.findByName(toCreate.getName()).ifPresent(i -> {
            throw new IllegalArgumentException("Institution already exists!");
        });

        return this.institutionRepository.save(toCreate);
    }

    public Institution updateInstitution(@Valid InstitutionUpdateDTO dto) throws Exception{

        if(isInputInvalid(dto)){
            throw new IllegalArgumentException("Institution name is illegal!");
        }

        //Check if institution to update does exist
        Institution toUpdate = this.institutionRepository.findByName(dto.getName())
                .orElseThrow(IllegalArgumentException::new);


        //Check if institution with new name does not exist (works even if newName is empty)
        if(dto.getNewName() != null){
            this.institutionRepository.findByName(dto.getNewName()).ifPresent(i -> {
                throw new IllegalArgumentException("Institution already exists!");
            });
        }

        if(dto.getType() != null){
            toUpdate.getAccounts().forEach(account -> {
                if(!compatibility.isInstitutionTypeCompatibleWithTransactionList(dto.getType(), account.getTransactions())){
                    throw new IllegalArgumentException("Types not comptatible!");
                }
            });
        }

        this.institutionRepository.delete(toUpdate);

        if(dto.getNewName() != null && !dto.getNewName().isBlank() && !dto.getNewName().isEmpty()){
            toUpdate.updateName(dto.getNewName());
        }

        if(dto.getType() != null){
            toUpdate.updateInstitutionType(dto.getType());
        }
        //Accounts should still be stored in toUpdate

        return this.institutionRepository.save(toUpdate);
    }


    public void deleteInstitution(@ValidInstitutionName String name) throws Exception{
        Institution institution = this.institutionRepository.findByName(name)
                .orElseThrow(IllegalArgumentException::new);
        this.institutionRepository.delete(institution);

        //ONRESTRICT: ZUERST ACCOUNTS UND TRANSAKTIONEN LÖSCHEN
    }


    public boolean isInputInvalid(Institution institution){
        String institutionName = institution.getName();
        if(institutionName.isBlank() || institutionName.isEmpty()){
            return true;
        }
        if(institution.getInstitutionType() == null){
            return true;
        }
        return false;
    }

    private boolean isInputInvalid(InstitutionUpdateDTO dto){
        String oldName = dto.getName();
        String newName = dto.getNewName();

        if(oldName.isBlank() || oldName.isEmpty()){
            return true;
        }
        if(dto.getNewName() != null){
            if(newName.isBlank() || newName.length() > Constants.INSTITUTION_NAME_MAX_LENGTH || newName.length() < Constants.INSTITUTION_NAME_MIN_LENGTH){
                return true;
            }
            return false;
        }
        //Old name correct

        return false;
    }
}

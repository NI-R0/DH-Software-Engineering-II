package de.dhbw.softwareengineering.application;

import de.dhbw.softwareengineering.adapters.institution.InstitutionCreateDTO;
import de.dhbw.softwareengineering.adapters.institution.InstitutionUpdateDTO;
import de.dhbw.softwareengineering.adapters.institution.Mapper.CreateDTOToInstitutionMapper;
import de.dhbw.softwareengineering.domain.institution.Institution;
import de.dhbw.softwareengineering.domain.institution.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstitutionApplicationService {

    private final InstitutionRepository institutionRepository;

    private final CreateDTOToInstitutionMapper createDTOMapper;

    @Autowired
    public InstitutionApplicationService(InstitutionRepository institutionRepository, CreateDTOToInstitutionMapper createDTOMapper) {
        this.institutionRepository = institutionRepository;
        this.createDTOMapper = createDTOMapper;
    }

    public List<Institution> getAllInstitutions(){
        return this.institutionRepository.findAllInstitutions();
    }

    public Optional<Institution> getInstitution(String name){
        return this.institutionRepository.findByName(name);
    }

    public Institution createInstitution(InstitutionCreateDTO institution) throws Exception{
        Institution toCreate = this.createDTOMapper.apply(institution);

        if(isInputInvalid(toCreate)){
            throw new IllegalAccessException("Wrong input!");
        }

        this.institutionRepository.findByName(toCreate.getName()).ifPresent(i -> {
            throw new IllegalArgumentException("Institution already exists!");
        });

        return this.institutionRepository.save(toCreate);
    }

    public Institution updateInstitution(InstitutionUpdateDTO dto) throws Exception{

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


    public void deleteInstitution(String name) throws Exception{
        Institution institution = this.institutionRepository.findByName(name)
                .orElseThrow(IllegalArgumentException::new);
        this.institutionRepository.delete(institution);
    }


    private boolean isInputInvalid(Institution institution){
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

        if(oldName.isBlank() || oldName.isEmpty()){
            return true;
        }
        //Old name correct

        return false;
    }
}

package de.dhbw.softwareengineering.application;

import de.dhbw.softwareengineering.adapters.institution.InstitutionCreateDto;
import de.dhbw.softwareengineering.adapters.institution.InstitutionGetDto;
import de.dhbw.softwareengineering.adapters.institution.InstitutionUpdateDto;
import de.dhbw.softwareengineering.adapters.institution.Mapper.InstitutionAggregateToDtoMapper;
import de.dhbw.softwareengineering.adapters.institution.Mapper.InstitutionDtoToAggregateMapper;
import de.dhbw.softwareengineering.domain.institution.InstitutionAggregate;
import de.dhbw.softwareengineering.domain.institution.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InstitutionService {
    @Autowired
    InstitutionRepository repository;
    @Autowired
    InstitutionAggregateToDtoMapper aggregateToDtoMapper;
    @Autowired
    InstitutionDtoToAggregateMapper dtoToAggregateMapper;

    public List<InstitutionGetDto> getAllInstitutions(){
        List<InstitutionGetDto> dtos = new ArrayList<>();
        repository.findAllInstitutions().forEach(aggregate -> {
            dtos.add(aggregateToDtoMapper.mapAggregateToGetDto(aggregate));
        });
        return dtos;
    }

    public Optional<InstitutionGetDto> getInstitution(String name){
        Optional<InstitutionAggregate> optional = repository.findByName(name);
        return optional.map(institution -> aggregateToDtoMapper.mapAggregateToGetDto(institution));
    }

    public InstitutionGetDto createInstitution(InstitutionCreateDto dto) throws Exception{
        //Logik zum schauen ob es noch nicht existiert
        /*Optional<InstitutionAggregate> institution = repository.createInstitution(dtoToAggregateMapper.mapCreateDtoToAggregate(dto));
        return institution.map(aggregate -> aggregateToDtoMapper.mapAggregateToGetDto(aggregate));*/

        if(isInstitutionInputInvalid(dtoToAggregateMapper.mapCreateDtoToAggregate(dto), true)){
            throw new IllegalArgumentException("Wrong input!");
        }

        List<InstitutionAggregate> institutions = repository.findAllInstitutions();
        if(containsName(institutions, dto.getName())){
            throw new IllegalArgumentException("Institution with this name already exists!");
        }
        InstitutionAggregate institution = dtoToAggregateMapper.mapCreateDtoToAggregate(dto);
        InstitutionAggregate saved = repository.save(institution);
        return aggregateToDtoMapper.mapAggregateToGetDto(saved);
    }

    public InstitutionGetDto updateInstitution(InstitutionUpdateDto dto) throws Exception{
        /*Optional<InstitutionAggregate> institution = repository.updateInstitution(dtoToAggregateMapper.mapUpdateDtoToAggregate(dto));
        return institution.map(aggregate -> aggregateToDtoMapper.mapAggregateToGetDto(aggregate));*/

        Optional<InstitutionAggregate> optional = repository.findByName(dto.getName());
        InstitutionAggregate institution = optional.orElseThrow(IllegalArgumentException::new);

        Optional<InstitutionAggregate> newInstitution = repository.findByName(dto.getNewName());
        newInstitution.ifPresent(i -> {throw new IllegalArgumentException("New name already exists!");});

        repository.delete(institution);

        if(isInstitutionInputInvalid(dtoToAggregateMapper.mapUpdateDtoToAggregate(dto), false)){
            throw new IllegalArgumentException("Wrong input!");
        }

        if(dto.getNewName() != null){
            institution.setName(dto.getNewName());
        }
        if(dto.getType() != null){
            institution.setType(dto.getType());
        }
        InstitutionAggregate saved = repository.save(institution);
        return aggregateToDtoMapper.mapAggregateToGetDto(saved);
    }

    public void deleteInstitution(String name) throws Exception{
        InstitutionAggregate institution = repository.findByName(name).orElseThrow(IllegalArgumentException::new);
        repository.delete(institution);
    }

    private boolean containsName(List<InstitutionAggregate> institutions, String name){
        return institutions.stream().anyMatch(institution -> institution.getName().equals(name));
    }

    private boolean isInstitutionInputInvalid(InstitutionAggregate institution, boolean newInstitution){
        String name = institution.getName();
        if(name.isBlank() || name.length() > 20){
            return true;
        }
        if(newInstitution && institution.getType() == null){
            return true;
        }
        return false;
    }
}

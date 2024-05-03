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

    public Optional<InstitutionGetDto> getInstitutionById(UUID id){
        Optional<InstitutionAggregate> optional = repository.findById(id);
        return optional.map(institution -> aggregateToDtoMapper.mapAggregateToGetDto(institution));
    }

    public Optional<InstitutionGetDto> getInstitutionByName(String name){
        Optional<InstitutionAggregate> optional = repository.findByName(name);
        return optional.map(institution -> aggregateToDtoMapper.mapAggregateToGetDto(institution));
    }

    public InstitutionGetDto createInstitution(InstitutionCreateDto dto) throws Exception{
        //Logik zum schauen ob es noch nicht existiert
        /*Optional<InstitutionAggregate> institution = repository.createInstitution(dtoToAggregateMapper.mapCreateDtoToAggregate(dto));
        return institution.map(aggregate -> aggregateToDtoMapper.mapAggregateToGetDto(aggregate));*/

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

        Optional<InstitutionAggregate> optional = repository.findById(dto.getInstitutionId());
        InstitutionAggregate institution = optional.orElseThrow(IllegalArgumentException::new);
        if(isInstitutionInputInvalid(institution)){
            throw new IllegalArgumentException("ID does not exist!");
        }

        if(dto.getName() != null){
            institution.setName(dto.getName());
        }
        if(dto.getType() != null){
            institution.setType(dto.getType());
        }
        InstitutionAggregate saved = repository.save(institution);
        return aggregateToDtoMapper.mapAggregateToGetDto(saved);
    }

    public void deleteInstitutionById(UUID id) throws Exception{
        InstitutionAggregate institution = repository.findById(id).orElseThrow(IllegalArgumentException::new);
        repository.deleteInstitution(institution);
    }

    public void deleteInstitutionByName(String name) throws Exception{
        InstitutionAggregate institution = repository.findByName(name).orElseThrow(IllegalArgumentException::new);
        repository.deleteInstitution(institution);
    }



    private boolean containsName(List<InstitutionAggregate> institutions, String name){
        return institutions.stream().anyMatch(institution -> institution.getName().equals(name));
    }

    private boolean isInstitutionInputInvalid(InstitutionAggregate institution){
        String name = institution.getName();
        if(name.isEmpty() || name.length() > 20){
            return true;
        }
        return false;
    }
}

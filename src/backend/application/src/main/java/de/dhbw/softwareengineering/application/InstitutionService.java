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
    InstitutionAggregateToDtoMapper aggregateToDto;
    @Autowired
    InstitutionDtoToAggregateMapper dtoToAggregateMapper;

    public List<InstitutionGetDto> getAllInstitutions(){
        List<InstitutionGetDto> dtos = new ArrayList<>();
        repository.findAllInstitutions().forEach(aggregate -> {
            dtos.add(aggregateToDto.mapAggregateToGetDto(aggregate));
        });
        return dtos;
    }

    public Optional<InstitutionGetDto> getInstitutionById(UUID id){
        Optional<InstitutionAggregate> optional = repository.findById(id);
        return optional.map(institution -> aggregateToDto.mapAggregateToGetDto(institution));
    }

    public Optional<InstitutionGetDto> getInstitutionByName(String name){
        Optional<InstitutionAggregate> optional = repository.findByName(name);
        return optional.map(institution -> aggregateToDto.mapAggregateToGetDto(institution));
    }

    public Optional<InstitutionGetDto> createInstitution(InstitutionCreateDto dto) throws Exception{
        Optional<InstitutionAggregate> institution = repository.createInstitution(dtoToAggregateMapper.mapCreateDtoToAggregate(dto));
        return institution.map(aggregate -> aggregateToDto.mapAggregateToGetDto(aggregate));
    }

    public Optional<InstitutionGetDto> updateInstitution(InstitutionUpdateDto dto) throws Exception{
        Optional<InstitutionAggregate> institution = repository.updateInstitution(dtoToAggregateMapper.mapUpdateDtoToAggregate(dto));
        return institution.map(aggregate -> aggregateToDto.mapAggregateToGetDto(aggregate));
    }

    public void deleteInstitutionById(UUID id) throws Exception{

    }

    public void deleteInstitutionByName(String name) throws Exception{

    }
}

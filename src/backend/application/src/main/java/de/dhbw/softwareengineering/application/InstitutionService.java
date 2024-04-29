package de.dhbw.softwareengineering.application;

import de.dhbw.softwareengineering.adapters.institution.InstitutionAggregateToDtoMapper;
import de.dhbw.softwareengineering.adapters.institution.InstitutionDto;
import de.dhbw.softwareengineering.adapters.institution.InstitutionDtoToAggregateMapper;
import de.dhbw.softwareengineering.domain.institution.InstitutionAggregate;
import de.dhbw.softwareengineering.domain.institution.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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

    public List<InstitutionDto> getAllInstitutions(){
        List<InstitutionDto> dtos = new ArrayList<>();
        repository.findAllInstitutions().forEach(aggregate -> {
            dtos.add(aggregateToDto.mapAggregateToDto(aggregate));
        });
        return dtos;
    }

    public Optional<InstitutionDto> getInstitutionById(UUID id){
        Optional<InstitutionAggregate> optional = repository.findById(id);
        return optional.map(institution -> aggregateToDto.mapAggregateToDto(institution));
    }

    public Optional<InstitutionDto> getInstitutionByName(String name){
        Optional<InstitutionAggregate> optional = repository.findByName(name);
        return optional.map(institution -> aggregateToDto.mapAggregateToDto(institution));
    }

    public Optional<InstitutionDto> createInstitution(InstitutionDto dto) throws Exception{
        Optional<InstitutionAggregate> institution = repository.createInstitution(dtoToAggregateMapper.mapDtoToAggregate(dto));
        return institution.map(aggregate -> aggregateToDto.mapAggregateToDto(aggregate));
    }

    public Optional<InstitutionDto> updateInstitution(InstitutionDto dto) throws Exception{
        Optional<InstitutionAggregate> institution = repository.updateInstitution(dtoToAggregateMapper.mapDtoToAggregate(dto));
        return institution.map(aggregate -> aggregateToDto.mapAggregateToDto(aggregate));
    }
}

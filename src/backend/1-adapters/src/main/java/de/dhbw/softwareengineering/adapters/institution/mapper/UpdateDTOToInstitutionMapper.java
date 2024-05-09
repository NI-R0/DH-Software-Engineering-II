package de.dhbw.softwareengineering.adapters.institution.mapper;

import de.dhbw.softwareengineering.adapters.institution.InstitutionUpdateDTO;
import de.dhbw.softwareengineering.domain.institution.Institution;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.function.Function;

@Component
public class UpdateDTOToInstitutionMapper implements Function<InstitutionUpdateDTO, Institution> {

    @Override
    public Institution apply(InstitutionUpdateDTO dto){
        return null;
    }

    public Institution apply(Institution institution, InstitutionUpdateDTO update){
        return mapToExisting(institution, update);
    }

    private Institution mapToExisting(Institution institution, InstitutionUpdateDTO dto){

        if(dto.getNewName() != null){
            institution.updateName(dto.getNewName());
        }
        else if(dto.getType() != null){
            institution.updateInstitutionType(dto.getType());
        }

        return institution;
    }

}

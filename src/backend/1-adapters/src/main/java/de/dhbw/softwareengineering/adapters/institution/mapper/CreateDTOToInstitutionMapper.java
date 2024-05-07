package de.dhbw.softwareengineering.adapters.institution.mapper;

import de.dhbw.softwareengineering.adapters.institution.InstitutionCreateDTO;
import de.dhbw.softwareengineering.domain.account.Account;
import de.dhbw.softwareengineering.domain.institution.Institution;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
public class CreateDTOToInstitutionMapper implements Function<InstitutionCreateDTO, Institution> {

    @Override
    public Institution apply(InstitutionCreateDTO dto){
        return map(dto);
    }

    private Institution map(InstitutionCreateDTO dto){
        List<Account> accounts = new ArrayList<>();
        return new Institution(dto.getName(), dto.getType(), accounts);
    }


}

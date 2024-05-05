package de.dhbw.softwareengineering.adapters.institution.Mapper;

import de.dhbw.softwareengineering.adapters.institution.InstitutionCreateDTO;
import de.dhbw.softwareengineering.adapters.institution.InstitutionUpdateDTO;
import de.dhbw.softwareengineering.domain.account.Account;
import de.dhbw.softwareengineering.domain.institution.Institution;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
public class UpdateDTOToInstitutionMapper implements Function<InstitutionUpdateDTO, Institution> {

    @Override
    public Institution apply(InstitutionUpdateDTO dto){
        return map(dto, new ArrayList<>());
    }

    public Institution applyWithAccounts(InstitutionUpdateDTO dto, List<Account> accounts){
        return map(dto, accounts);
    }

    private Institution map(InstitutionUpdateDTO dto, List<Account> accounts){
        return new Institution(dto.getNewName(), dto.getType(), accounts);
    }
}

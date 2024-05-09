package de.dhbw.softwareengineering.adapters.institution.mapper;

import de.dhbw.softwareengineering.adapters.institution.InstitutionReturnDTO;
import de.dhbw.softwareengineering.domain.account.Account;
import de.dhbw.softwareengineering.domain.institution.Institution;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
public class InstitutionToReturnDTOMapper implements Function<Institution, InstitutionReturnDTO> {

    @Override
    public InstitutionReturnDTO apply(final Institution institution){
        return map(institution);
    }

    private InstitutionReturnDTO map(final Institution institution){
        List<Account> accountList = institution.getAccounts();
        List<String> accountNames = new ArrayList<>();
        accountList.forEach(a -> {
            accountNames.add(a.getAccountName());
        });
        return new InstitutionReturnDTO(institution.getName(), institution.getInstitutionType(), accountNames);
    }

}

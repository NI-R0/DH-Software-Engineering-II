package de.dhbw.softwareengineering.adapters.institution.Mapper;

import de.dhbw.softwareengineering.adapters.account.AccountDTO;
import de.dhbw.softwareengineering.adapters.account.AccountToDTOMapper;
import de.dhbw.softwareengineering.adapters.institution.InstitutionDTO;
import de.dhbw.softwareengineering.domain.account.Account;
import de.dhbw.softwareengineering.domain.institution.Institution;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
public class InstitutionToDTOMapper implements Function<Institution, InstitutionDTO> {

    private final AccountToDTOMapper accountMapper;

    public InstitutionToDTOMapper(AccountToDTOMapper accountMapper) {
        this.accountMapper = accountMapper;
    }


    @Override
    public InstitutionDTO apply(final Institution institution) {
        return map(institution);
    }

    private InstitutionDTO map(final Institution institution) {
        List<Account> accounts = institution.getAccounts();
        List<AccountDTO> accountDTOS = new ArrayList<>();
        accounts.forEach(a -> {
            accountDTOS.add(accountMapper.apply(a));
        });
        return new InstitutionDTO(institution.getName(), institution.getInstitutionType(), accountDTOS);
    }
}

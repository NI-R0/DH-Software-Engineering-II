package de.dhbw.softwareengineering.adapters.institution;

import de.dhbw.softwareengineering.adapters.account.AccountDto;
import de.dhbw.softwareengineering.adapters.account.AccountDtoToAggregateMapper;
import de.dhbw.softwareengineering.domain.account.AccountAggregate;
import de.dhbw.softwareengineering.domain.institution.InstitutionAggregate;
import de.dhbw.softwareengineering.domain.values.NameValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InstitutionDtoToAggregateMapper {
    @Autowired
    AccountDtoToAggregateMapper accountMapper;

    public InstitutionAggregate mapDtoToAggregate(InstitutionDto dto){
        InstitutionAggregate institution = new InstitutionAggregate();
        institution.setInstitutionId(dto.getInstitutionId());
        institution.setType(dto.getType());
        institution.setName(new NameValue(dto.getName().getName()));

        List<AccountAggregate> accounts = new ArrayList<>();
        List<AccountDto> accountDtos = dto.getAccounts();
        accountDtos.forEach(accountDto -> {
            accounts.add(accountMapper.mapDtoToAggregate(accountDto));
        });
        institution.setAccounts(accounts);


        return institution;
    }
}

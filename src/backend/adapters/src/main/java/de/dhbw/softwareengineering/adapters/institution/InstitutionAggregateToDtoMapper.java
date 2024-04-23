package de.dhbw.softwareengineering.adapters.institution;

import de.dhbw.softwareengineering.adapters.account.AccountAggregateToDtoMapper;
import de.dhbw.softwareengineering.adapters.account.AccountDto;
import de.dhbw.softwareengineering.adapters.values.NameDto;
import de.dhbw.softwareengineering.domain.account.AccountAggregate;
import de.dhbw.softwareengineering.domain.institution.InstitutionAggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InstitutionAggregateToDtoMapper {
    @Autowired
    AccountAggregateToDtoMapper accountMapper;

    public InstitutionDto mapAggregateToDto(InstitutionAggregate aggregate){
        InstitutionDto dto = new InstitutionDto();
        dto.setInstitutionId(aggregate.getInstitutionId());
        dto.setType(aggregate.getType());

        NameDto name = new NameDto();
        name.setName(aggregate.getName().getName());
        dto.setName(name);

        List<AccountDto> accounts = new ArrayList<>();
        List<AccountAggregate> accountAggregates = aggregate.getAccounts();
        accountAggregates.forEach(accountAggregate -> {
           accounts.add(accountMapper.mapAggregateToDto(accountAggregate));
        });
        dto.setAccounts(accounts);

        return dto;
    }

}

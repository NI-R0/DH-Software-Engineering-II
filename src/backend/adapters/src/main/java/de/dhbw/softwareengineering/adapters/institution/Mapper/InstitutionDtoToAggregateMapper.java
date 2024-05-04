package de.dhbw.softwareengineering.adapters.institution.Mapper;

import de.dhbw.softwareengineering.adapters.account.Mapper.AccountDtoToAggregateMapper;
import de.dhbw.softwareengineering.adapters.institution.InstitutionCreateDto;
import de.dhbw.softwareengineering.adapters.institution.InstitutionUpdateDto;
import de.dhbw.softwareengineering.domain.account.AccountAggregate;
import de.dhbw.softwareengineering.domain.institution.InstitutionAggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class InstitutionDtoToAggregateMapper {
    @Autowired
    AccountDtoToAggregateMapper accountMapper;

    /*public InstitutionAggregate mapDtoToAggregate(InstitutionDto dto){
        InstitutionAggregate institution = new InstitutionAggregate();
        institution.setInstitutionId(dto.getInstitutionId());
        institution.setType(dto.getType());
        institution.setName(dto.getName());

        List<AccountAggregate> accounts = new ArrayList<>();
        List<AccountDto> accountDtos = dto.getAccounts();
        accountDtos.forEach(accountDto -> {
            accounts.add(accountMapper.mapDtoToAggregate(accountDto));
        });
        institution.setAccounts(accounts);


        return institution;
    }*/

    public InstitutionAggregate mapCreateDtoToAggregate(InstitutionCreateDto dto){
        InstitutionAggregate institution = new InstitutionAggregate();

        institution.setName(dto.getName());
        institution.setType(dto.getType());
        List<AccountAggregate> accounts = new ArrayList<>();
        institution.setAccounts(accounts);

        return institution;
    }

    public InstitutionAggregate mapUpdateDtoToAggregate(InstitutionUpdateDto dto){
        InstitutionAggregate institution = new InstitutionAggregate();

        institution.setName(dto.getNewName());
        institution.setType(dto.getType());
        List<AccountAggregate> accounts = new ArrayList<>();
        institution.setAccounts(accounts);

        return institution;
    }
}

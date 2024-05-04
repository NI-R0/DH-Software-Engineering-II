package de.dhbw.softwareengineering.application;

import de.dhbw.softwareengineering.adapters.account.AccountCreateDto;
import de.dhbw.softwareengineering.adapters.account.AccountGetDto;
import de.dhbw.softwareengineering.adapters.account.Mapper.AccountAggregateToDtoMapper;
import de.dhbw.softwareengineering.adapters.account.Mapper.AccountDtoToAggregateMapper;
import de.dhbw.softwareengineering.domain.account.AccountAggregate;
import de.dhbw.softwareengineering.domain.account.AccountRepository;
import de.dhbw.softwareengineering.domain.institution.InstitutionAggregate;
import de.dhbw.softwareengineering.domain.institution.InstitutionRepository;
import de.dhbw.softwareengineering.domain.values.AccountOwnerNameValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    InstitutionRepository institutionRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountAggregateToDtoMapper aggregateToDtoMapper;
    @Autowired
    AccountDtoToAggregateMapper dtoToAggregateMapper;

    //Create: Check if name already exists for institution

    public List<AccountGetDto> getAllAccounts(String institutionName){
        List<AccountGetDto> dtos = new ArrayList<>();
        accountRepository.findAllByInstitution(institutionName).forEach(account -> {
            dtos.add(aggregateToDtoMapper.mapAggregateToGetDto(account));
        });
        return dtos;
    }

    public Optional<AccountGetDto> getAccountById(String institutionName, UUID accountId){
        Optional<AccountAggregate> optional = accountRepository.findByIdAndInstitution(institutionName, accountId);
        return optional.map(account -> aggregateToDtoMapper.mapAggregateToGetDto((account)));
    }


    public Optional<AccountGetDto> getAccountByName(String institutionName, String accountName){
        Optional<AccountAggregate> optional = accountRepository.findByNameAndInstitution(institutionName,accountName);
        return optional.map(account -> aggregateToDtoMapper.mapAggregateToGetDto(account));
    }

    public AccountGetDto createAccount(AccountCreateDto dto) throws Exception{
        InstitutionAggregate institution = institutionRepository.findByName(dto.getInstitutionName()).orElseThrow(IllegalArgumentException::new);
        //Check if acc w/ name doesnt exist
        AccountAggregate account = dtoToAggregateMapper.mapCreateDtoToAggregate(dto);

        if(isAccountInputInvalid(account, true)){
            throw new IllegalArgumentException("Wrong input!");
        }

        List<AccountAggregate> institutionAccounts = institution.getAccounts();
        institutionAccounts.add(account);
        institution.setAccounts(institutionAccounts);

        institutionRepository.save(institution);
        return aggregateToDtoMapper.mapAggregateToGetDto(account);
    }



    private boolean isAccountInputInvalid(AccountAggregate account, boolean newAccount){
        UUID accountId = account.getAccountId();
        String accountName = account.getAccountName();
        Double balance = account.getBalance();
        AccountOwnerNameValue owner = account.getOwner();
        if(accountId == null || balance.isNaN() || balance.isInfinite()){
            return true;
        }
        if(accountName.isEmpty() || accountName.length() > 20){
            return true;
        }
        if(owner.getFirstName().length() > 15 || owner.getLastName().length() > 25){
            return true;
        }
        if(owner.getFirstName().isBlank() || owner.getLastName().isBlank()){
            return newAccount;
        }
        return false;
    }
}

package de.dhbw.softwareengineering.application;

import de.dhbw.softwareengineering.adapters.account.AccountDto;
import de.dhbw.softwareengineering.adapters.account.Mapper.AccountAggregateToDtoMapper;
import de.dhbw.softwareengineering.adapters.account.Mapper.AccountDtoToAggregateMapper;
import de.dhbw.softwareengineering.domain.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountRepository repository;
    @Autowired
    AccountAggregateToDtoMapper aggregateToDtoMapper;
    @Autowired
    AccountDtoToAggregateMapper dtoToAggregateMapper;

    //Create: Check if name already exists for institution

    public List<AccountDto> getAllAccounts(){
        List<AccountDto> dtos = new ArrayList<>();

        return dtos;
    }
}

package de.dhbw.softwareengineering.adapters.account.Mapper;

import de.dhbw.softwareengineering.adapters.account.AccountBaseDto;
import de.dhbw.softwareengineering.adapters.account.AccountCreateDto;
import de.dhbw.softwareengineering.domain.account.AccountAggregate;
import de.dhbw.softwareengineering.adapters.transaction.TransactionDto;
import de.dhbw.softwareengineering.adapters.transaction.Mapper.TransactionDtoToEntityMapper;
import de.dhbw.softwareengineering.domain.transaction.TransactionEntity;
import de.dhbw.softwareengineering.domain.values.AccountOwnerNameValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class AccountDtoToAggregateMapper {
    @Autowired
    TransactionDtoToEntityMapper dtoToEntityMapper;

    /*public AccountAggregate mapDtoToAggregate(AccountDto dto){
        AccountAggregate account = new AccountAggregate();
        account.setAccountId(dto.getAccountId());
        account.setBalance(dto.getBalance());
        account.setAccountName(dto.getAccountName());
        account.setOwner(new AccountOwnerNameValue(dto.getOwner().getFirstName(), dto.getOwner().getLastName()));
        *//*account.setTransactions(dto.getTransactions());*//*

        List<TransactionEntity> transactions = new ArrayList<>();
        List<TransactionDto> transactionDtos = dto.getTransactions();
        transactionDtos.forEach(transactionDto ->{
            transactions.add(dtoToEntityMapper.mapDtoToEntity(transactionDto));
        });
        account.setTransactions(transactions);

        return account;
    }*/

    public AccountAggregate mapCreateDtoToAggregate(AccountCreateDto dto){
        AccountAggregate aggregate = new AccountAggregate();
        AccountBaseDto account = dto.getAccount();
        aggregate.setAccountId(UUID.randomUUID());
        aggregate.setAccountName(account.getAccountName());
        aggregate.setOwner(new AccountOwnerNameValue(account.getOwner().getFirstName(), account.getOwner().getLastName()));
        aggregate.setBalance(account.getBalance());
        aggregate.setInstitutionName(dto.getInstitutionName());
        aggregate.setTransactions(new ArrayList<>());

        return aggregate;
    }

}

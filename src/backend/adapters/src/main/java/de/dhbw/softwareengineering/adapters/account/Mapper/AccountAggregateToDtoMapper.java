package de.dhbw.softwareengineering.adapters.account.Mapper;

import de.dhbw.softwareengineering.adapters.account.AccountDto;
import de.dhbw.softwareengineering.adapters.transaction.TransactionDto;
import de.dhbw.softwareengineering.adapters.transaction.Mapper.TransactionEntityToDtoMapper;
import de.dhbw.softwareengineering.adapters.values.AccountOwnerNameDto;
import de.dhbw.softwareengineering.domain.account.AccountAggregate;
import de.dhbw.softwareengineering.domain.transaction.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountAggregateToDtoMapper {

    @Autowired
    TransactionEntityToDtoMapper entityToDtoMapper;

    public AccountDto mapAggregateToDto(AccountAggregate aggregate){
        AccountDto dto = new AccountDto();
        dto.setAccountId(aggregate.getAccountId());

        dto.setAccountName(aggregate.getAccountName());

        AccountOwnerNameDto owner = new AccountOwnerNameDto();
        owner.setFirstName(aggregate.getOwner().getFirstName());
        owner.setLastName(aggregate.getOwner().getLastName());
        dto.setOwner(owner);

        dto.setBalance(aggregate.getBalance());

        List<TransactionDto> transactions = new ArrayList<>();
        List<TransactionEntity> transactionEntities = aggregate.getTransactions();
        transactionEntities.forEach(entity ->{
            transactions.add(entityToDtoMapper.mapEntityToDto(entity));
        });
        dto.setTransactions(transactions);

        return dto;
    }

}

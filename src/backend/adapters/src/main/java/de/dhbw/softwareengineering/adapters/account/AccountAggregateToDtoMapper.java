package de.dhbw.softwareengineering.adapters.account;

import de.dhbw.softwareengineering.adapters.transaction.TransactionDto;
import de.dhbw.softwareengineering.adapters.transaction.TransactionEntityToDtoMapper;
import de.dhbw.softwareengineering.adapters.values.AccountOwnerNameDto;
import de.dhbw.softwareengineering.adapters.values.NameDto;
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

        NameDto name = new NameDto();
        name.setName(aggregate.getAccountName().getName());
        dto.setAccountName(name);

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

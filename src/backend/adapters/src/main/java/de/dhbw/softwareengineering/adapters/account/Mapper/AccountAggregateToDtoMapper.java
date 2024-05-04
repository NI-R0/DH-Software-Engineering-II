package de.dhbw.softwareengineering.adapters.account.Mapper;

import de.dhbw.softwareengineering.adapters.account.AccountGetDto;
import de.dhbw.softwareengineering.adapters.account.AccountReturnDto;
import de.dhbw.softwareengineering.adapters.transaction.Mapper.TransactionEntityToDtoMapper;
import de.dhbw.softwareengineering.adapters.values.AccountOwnerNameDto;
import de.dhbw.softwareengineering.domain.account.AccountAggregate;
import de.dhbw.softwareengineering.domain.transaction.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class AccountAggregateToDtoMapper {

    @Autowired
    TransactionEntityToDtoMapper entityToDtoMapper;

    /*public AccountDto mapAggregateToDto(AccountAggregate aggregate){
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
    }*/

    public AccountGetDto mapAggregateToGetDto(AccountAggregate aggregate){
        AccountGetDto dto = new AccountGetDto();
        AccountReturnDto account = new AccountReturnDto();

        account.setAccountName(aggregate.getAccountName());
        dto.setInstitutionName(aggregate.getInstitutionName());
        account.setBalance(aggregate.getBalance());
        AccountOwnerNameDto owner = new AccountOwnerNameDto();
        owner.setLastName(aggregate.getOwner().getLastName());
        owner.setFirstName(aggregate.getOwner().getFirstName());
        account.setOwner(owner);

        List<UUID> uuids = new ArrayList<>();
        List<TransactionEntity> transactions = aggregate.getTransactions();
        transactions.forEach(transaction -> {
            uuids.add(transaction.getTransactionId());
        });
        account.setTransactions(uuids);

        dto.setAccount(account);

        return dto;
    }

}

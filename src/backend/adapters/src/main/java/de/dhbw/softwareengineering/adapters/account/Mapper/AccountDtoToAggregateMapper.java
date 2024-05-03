package de.dhbw.softwareengineering.adapters.account.Mapper;

import de.dhbw.softwareengineering.adapters.account.AccountDto;
import de.dhbw.softwareengineering.domain.account.AccountAggregate;
import de.dhbw.softwareengineering.adapters.transaction.TransactionDto;
import de.dhbw.softwareengineering.adapters.transaction.Mapper.TransactionDtoToEntityMapper;
import de.dhbw.softwareengineering.domain.transaction.TransactionEntity;
import de.dhbw.softwareengineering.domain.values.AccountOwnerNameValue;
import de.dhbw.softwareengineering.domain.values.NameValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountDtoToAggregateMapper {
    @Autowired
    TransactionDtoToEntityMapper dtoToEntityMapper;

    public AccountAggregate mapDtoToAggregate(AccountDto dto){
        AccountAggregate account = new AccountAggregate();
        account.setAccountId(dto.getAccountId());
        account.setBalance(dto.getBalance());
        account.setAccountName(new NameValue(dto.getAccountName().getName()));
        account.setOwner(new AccountOwnerNameValue(dto.getOwner().getFirstName(), dto.getOwner().getLastName()));
        /*account.setTransactions(dto.getTransactions());*/

        List<TransactionEntity> transactions = new ArrayList<>();
        List<TransactionDto> transactionDtos = dto.getTransactions();
        transactionDtos.forEach(transactionDto ->{
            transactions.add(dtoToEntityMapper.mapDtoToEntity(transactionDto));
        });
        account.setTransactions(transactions);

        return account;
    }

}

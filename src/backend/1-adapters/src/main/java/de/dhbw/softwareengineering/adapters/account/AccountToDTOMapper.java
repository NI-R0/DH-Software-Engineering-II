package de.dhbw.softwareengineering.adapters.account;

import de.dhbw.softwareengineering.adapters.transaction.TransactionDTO;
import de.dhbw.softwareengineering.adapters.transaction.TransactionToDTOMapper;
import de.dhbw.softwareengineering.adapters.values.AccountOwnerNameDTO;
import de.dhbw.softwareengineering.adapters.values.AccountOwnerNameValueToDTOMapper;
import de.dhbw.softwareengineering.domain.account.Account;
import de.dhbw.softwareengineering.domain.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
public class AccountToDTOMapper implements Function<Account, AccountDTO> {


    private final TransactionToDTOMapper transactionMapper;

    private final AccountOwnerNameValueToDTOMapper ownerMapper;

    @Autowired
    public AccountToDTOMapper(final TransactionToDTOMapper transactionMapper, final AccountOwnerNameValueToDTOMapper ownerMapper) {
        this.transactionMapper = transactionMapper;
        this.ownerMapper = ownerMapper;
    }

    @Override
    public AccountDTO apply(Account account) {
        return map(account);
    }

    private AccountDTO map(Account account) {
        List<Transaction> transactions = account.getTransactions();
        List<TransactionDTO> transactionDTOS = new ArrayList<>();
        transactions.forEach(t -> {
            transactionDTOS.add(transactionMapper.apply(t));
        });

        AccountOwnerNameDTO ownerDTO = ownerMapper.apply(account.getOwner());

        return new AccountDTO(account.getId(),
                account.getInstitution().getName(),
                account.getAccountName(),
                ownerDTO,
                account.getBalance(),
                transactionDTOS);
    }

}

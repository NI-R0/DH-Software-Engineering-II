package de.dhbw.softwareengineering.adapters.account.Mapper;

import de.dhbw.softwareengineering.adapters.account.AccountInformationDTO;
import de.dhbw.softwareengineering.adapters.account.AccountReturnDTO;
import de.dhbw.softwareengineering.adapters.values.AccountOwnerNameDTO;
import de.dhbw.softwareengineering.domain.account.Account;
import de.dhbw.softwareengineering.domain.transaction.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

@Component
public class AccountToReturnDTOMapper implements Function<Account, AccountReturnDTO> {

    @Override
    public AccountReturnDTO apply(final Account account){
        return map(account);
    }

    private AccountReturnDTO map(final Account account){
        AccountOwnerNameDTO owner = new AccountOwnerNameDTO(account.getOwner().getFirstName(), account.getOwner().getLastName());
        List<Transaction> transactions = account.getTransactions();
        List<UUID> transactionIds = new ArrayList<>();
        transactions.forEach(t -> {
            transactionIds.add(t.getId());
        });
        AccountInformationDTO accountInfo = new AccountInformationDTO(account.getId(), account.getAccountName(), owner, account.getBalance(), transactionIds);
        return new AccountReturnDTO(account.getInstitution().getName(), accountInfo);
    }



}

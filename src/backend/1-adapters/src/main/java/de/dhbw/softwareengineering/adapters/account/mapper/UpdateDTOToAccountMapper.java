package de.dhbw.softwareengineering.adapters.account.mapper;

import de.dhbw.softwareengineering.adapters.account.AccountBaseDTO;
import de.dhbw.softwareengineering.adapters.account.AccountUpdateDTO;
import de.dhbw.softwareengineering.domain.account.Account;
import de.dhbw.softwareengineering.domain.institution.Institution;
import de.dhbw.softwareengineering.domain.values.AccountOwnerNameValue;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.function.Function;

@Component
public class UpdateDTOToAccountMapper implements Function<AccountUpdateDTO, Account> {

    @Override
    public Account apply(final AccountUpdateDTO dto){
        return null;
    }

    public Account apply(Account account, AccountUpdateDTO dto){
        return mapToExisting(account, dto);
    }

    private Account mapToExisting(Account account, AccountUpdateDTO dto){
        AccountBaseDTO properties = dto.getAccountInfo();
        if(properties.getAccountName() != null){
            account.updateAccountName(properties.getAccountName());
        }
        else if (properties.getOwner() != null) {
            AccountOwnerNameValue owner = new AccountOwnerNameValue();
            owner.setFirstName(properties.getOwner().getFirstName());
            owner.setLastName(properties.getOwner().getLastName());
            account.updateAccountOwner(owner);
        }
        else if(properties.getBalance() != null){
            account.updateBalance(properties.getBalance());
        }
        return account;
    }
}

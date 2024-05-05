package de.dhbw.softwareengineering.adapters.account.Mapper;

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

    public Account apply(final AccountUpdateDTO dto, Institution institution){
        return map(dto, institution);
    }

    private Account map(final AccountUpdateDTO dto, final Institution institution){
        AccountBaseDTO account = dto.getAccountInfo();
        AccountOwnerNameValue owner = new AccountOwnerNameValue();
        owner.setFirstName(account.getOwner().getFirstName());
        owner.setLastName(account.getOwner().getLastName());
        String accountName = account.getAccountName();
        return new Account(dto.getAccountId(), institution, accountName, owner, account.getBalance(), new ArrayList<>());
    }
}

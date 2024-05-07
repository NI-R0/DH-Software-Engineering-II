package de.dhbw.softwareengineering.adapters.account.mapper;

import de.dhbw.softwareengineering.adapters.account.AccountBaseDTO;
import de.dhbw.softwareengineering.adapters.account.AccountCreateDTO;
import de.dhbw.softwareengineering.domain.account.Account;
import de.dhbw.softwareengineering.domain.institution.Institution;
import de.dhbw.softwareengineering.domain.values.AccountOwnerNameValue;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;
import java.util.function.Function;

@Component
public class CreateDTOToAccountMapper implements Function<AccountCreateDTO, Account> {

    @Override
    public Account apply(final AccountCreateDTO dto){
        return null;
    }

    public Account apply(final AccountCreateDTO dto, Institution institution){
        return map(dto, institution);
    }

    private Account map(final AccountCreateDTO dto, final Institution institution){
        AccountBaseDTO account = dto.getAccount();
        AccountOwnerNameValue owner = new AccountOwnerNameValue();
        owner.setFirstName(account.getOwner().getFirstName());
        owner.setLastName(account.getOwner().getLastName());
        String accountName = account.getAccountName();
        return new Account(UUID.randomUUID(), institution, accountName, owner, account.getBalance(), new ArrayList<>());
    }

}

package de.dhbw.softwareengineering.adapters.values.mapper;

import de.dhbw.softwareengineering.adapters.values.AccountOwnerNameDTO;
import de.dhbw.softwareengineering.domain.values.AccountOwnerNameValue;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AccountOwnerNameValueToDTOMapper implements Function<AccountOwnerNameValue, AccountOwnerNameDTO> {

    @Override
    public AccountOwnerNameDTO apply(AccountOwnerNameValue accountOwnerNameValue) {
        return map(accountOwnerNameValue);
    }

    private AccountOwnerNameDTO map(AccountOwnerNameValue nameValue) {
        return new AccountOwnerNameDTO(nameValue.getFirstName(), nameValue.getLastName());
    }
}

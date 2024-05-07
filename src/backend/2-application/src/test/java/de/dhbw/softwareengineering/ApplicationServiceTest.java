package de.dhbw.softwareengineering;

import de.dhbw.softwareengineering.adapters.account.AccountBaseDTO;
import de.dhbw.softwareengineering.adapters.account.AccountCreateDTO;
import de.dhbw.softwareengineering.adapters.account.mapper.CreateDTOToAccountMapper;
import de.dhbw.softwareengineering.adapters.values.AccountOwnerNameDTO;
import de.dhbw.softwareengineering.application.AccountApplicationService;
import de.dhbw.softwareengineering.domain.account.Account;
import de.dhbw.softwareengineering.domain.institution.Institution;
import de.dhbw.softwareengineering.domain.institution.InstitutionRepository;
import de.dhbw.softwareengineering.domain.values.AccountOwnerNameValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ApplicationServiceTest {

    @InjectMocks
    private AccountApplicationService accountService;
    @Mock
    private InstitutionRepository institutionRepository;
    @Mock
    private AccountCreateDTO accountCreateDTO;
    @Mock
    private AccountBaseDTO accountBaseDTO;
    @Mock
    private CreateDTOToAccountMapper createDTOMapper;
    @Mock
    private AccountOwnerNameDTO ownerNameDTO;
    @Mock
    private AccountOwnerNameValue ownerNameValue;

    @Mock
    private Institution institution;
    @Mock
    private Account account1;
    @Mock
    private Account account2;
    @Mock
    private Account newAccount;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_CreateAccount_NameAlreadyExists() throws Exception {
        when(accountCreateDTO.getInstitutionName()).thenReturn("Test Institution");
        when(accountCreateDTO.getAccount()).thenReturn(accountBaseDTO);

        when(accountBaseDTO.getAccountName()).thenReturn("Test Account 1");
        when(accountBaseDTO.getBalance()).thenReturn(0.0);
        when(accountBaseDTO.getOwner()).thenReturn(ownerNameDTO);

        when(ownerNameDTO.getFirstName()).thenReturn("Vorname");
        when(ownerNameDTO.getLastName()).thenReturn("Nachname");

        when(institutionRepository.findByName(anyString())).thenReturn(Optional.of(institution));

        List<Account> accounts = Arrays.asList(account1, account2);
        when(account1.getAccountName()).thenReturn("Test Account 1");
        when(account2.getAccountName()).thenReturn("Test Account 2");

        when(institution.getAccounts()).thenReturn(accounts);

        assertThrows(IllegalArgumentException.class, () -> {
            accountService.createAccount(accountCreateDTO);
        });
    }

    @Test
    public void test_CreateAccount_InstitutionNotFound() {
        when(accountCreateDTO.getInstitutionName()).thenReturn("Test Institution");
        when(institutionRepository.findByName(anyString())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> {
            accountService.createAccount(accountCreateDTO);
        });
    }

    @Test
    public void test_CreateAccount_InvalidInput() {
        when(accountCreateDTO.getInstitutionName()).thenReturn("Test Institution");
        when(accountCreateDTO.getAccount()).thenReturn(accountBaseDTO);

        when(accountBaseDTO.getAccountName()).thenReturn("New Account");
        when(accountBaseDTO.getBalance()).thenReturn(0.0);
        when(accountBaseDTO.getOwner()).thenReturn(ownerNameDTO);

        when(ownerNameDTO.getFirstName()).thenReturn("Vorname");
        when(ownerNameDTO.getLastName()).thenReturn("Nachname");

        when(institutionRepository.findByName(anyString())).thenReturn(Optional.of(institution));

        List<Account> accounts = new ArrayList<>();
        accounts.add(account1);
        accounts.add(account2);
        when(account1.getAccountName()).thenReturn("Test Account 1");
        when(account2.getAccountName()).thenReturn("Test Account 2");

        when(institution.getAccounts()).thenReturn(accounts);

        when(createDTOMapper.apply(any(), any())).thenReturn(newAccount);

        when(newAccount.getId()).thenReturn(null);
        when(newAccount.getAccountName()).thenReturn("New Account");
        when(newAccount.getBalance()).thenReturn(0.0);
        when(newAccount.getOwner()).thenReturn(ownerNameValue);
        when(ownerNameValue.getFirstName()).thenReturn("Vorname");
        when(ownerNameValue.getLastName()).thenReturn("Nachname");
        //Should check for other input parameters as well

        assertThrows(IllegalArgumentException.class, () -> {
            accountService.createAccount(accountCreateDTO);
        });
    }

    @Test
    public void test_CreateAccount_Success() throws Exception{
        when(accountCreateDTO.getInstitutionName()).thenReturn("Test Institution");
        when(accountCreateDTO.getAccount()).thenReturn(accountBaseDTO);

        when(accountBaseDTO.getAccountName()).thenReturn("New Account");
        when(accountBaseDTO.getBalance()).thenReturn(0.0);
        when(accountBaseDTO.getOwner()).thenReturn(ownerNameDTO);

        when(ownerNameDTO.getFirstName()).thenReturn("Vorname");
        when(ownerNameDTO.getLastName()).thenReturn("Nachname");

        when(institutionRepository.findByName(anyString())).thenReturn(Optional.of(institution));

        List<Account> accounts = new ArrayList<>();
        accounts.add(account1);
        accounts.add(account2);
        when(account1.getAccountName()).thenReturn("Test Account");
        when(account2.getAccountName()).thenReturn("Test Account 2");

        when(institution.getAccounts()).thenReturn(accounts);

        when(createDTOMapper.apply(any(), any())).thenReturn(newAccount);

        when(newAccount.getId()).thenReturn(UUID.fromString("e58ed763-928c-4155-bee9-fdbaaadc15f3"));
        when(newAccount.getAccountName()).thenReturn("New Account");
        when(newAccount.getBalance()).thenReturn(0.0);
        when(newAccount.getOwner()).thenReturn(ownerNameValue);
        when(ownerNameValue.getFirstName()).thenReturn("Vorname");
        when(ownerNameValue.getLastName()).thenReturn("Nachname");

        Account result = accountService.createAccount(accountCreateDTO);
        assertEquals(newAccount, result);
    }


}

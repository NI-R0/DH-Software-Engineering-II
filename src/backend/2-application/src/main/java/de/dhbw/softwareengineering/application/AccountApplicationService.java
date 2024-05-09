package de.dhbw.softwareengineering.application;

import de.dhbw.softwareengineering.adapters.account.AccountCreateDTO;
import de.dhbw.softwareengineering.adapters.account.AccountUpdateDTO;
import de.dhbw.softwareengineering.adapters.account.mapper.CreateDTOToAccountMapper;
import de.dhbw.softwareengineering.domain.services.ValidationService;
import de.dhbw.softwareengineering.validation.annotations.ValidAccountName;
import de.dhbw.softwareengineering.validation.annotations.ValidInstitutionName;
import de.dhbw.softwareengineering.constants.Constants;
import de.dhbw.softwareengineering.domain.account.Account;
import de.dhbw.softwareengineering.domain.account.AccountRepository;
import de.dhbw.softwareengineering.domain.institution.Institution;
import de.dhbw.softwareengineering.domain.institution.InstitutionRepository;
import de.dhbw.softwareengineering.domain.values.AccountOwnerNameValue;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;

@Service
@Validated
public class AccountApplicationService {

    private final AccountRepository accountRepository;

    private final InstitutionRepository institutionRepository;

    private final CreateDTOToAccountMapper createMapper;

    private final ValidationService validationService;

    @Autowired
    public AccountApplicationService(AccountRepository accountRepository, InstitutionRepository institutionRepository, CreateDTOToAccountMapper createMapper, ValidationService validationService) {
        this.accountRepository = accountRepository;
        this.institutionRepository = institutionRepository;
        this.createMapper = createMapper;
        this.validationService = validationService;
    }

    public List<Account> getAllAccounts(@ValidInstitutionName String institutionName){
        return this.accountRepository.findByInstitution(institutionName);
    }

    public Optional<Account> getAccountByName(@ValidInstitutionName String institutionName,
                                              @ValidAccountName String accountName){
        return this.accountRepository.findByInstitutionAndName(institutionName, accountName);
    }

    public Optional<Account> getAccountById(@ValidInstitutionName String institutionName,
                                            @NotNull UUID accountId){
        return this.accountRepository.findByInstitutionAndId(institutionName, accountId);
    }

    public Account createAccount(@Valid AccountCreateDTO dto) throws Exception{
        Institution institution = this.institutionRepository.findByName(dto.getInstitutionName()).orElseThrow(IllegalArgumentException::new);

        //Check if acc w/ name already exists
        if(containsName(institution.getAccounts(), dto.getAccount().getAccountName())){
            throw new IllegalArgumentException("Account with this name does already exist!");
        }

        Account toCreate = this.createMapper.apply(dto, institution);

        if(isInputInvalid(toCreate)){
            throw new IllegalArgumentException("Wrong account input!");
        }

        List<Account> institutionAccounts = institution.getAccounts();
        //List<Account> institutionAccounts = new ArrayList<>(institution.getAccounts());
        institutionAccounts.add(toCreate);
        institution.setAccounts(institutionAccounts);

        this.institutionRepository.save(institution);
        return toCreate;
    }

    public Account updateAccount(@Valid AccountUpdateDTO dto) throws Exception{

        if(isInputInvalid(dto)){
            throw new IllegalArgumentException("Wrong input!");
        }

        Institution institution = this.institutionRepository.findByName(dto.getInstitutionName()).orElseThrow(IllegalArgumentException::new);
        List<Account> accounts = institution.getAccounts();

        if(containsId(accounts, dto.getAccountId())){

            Account toUpdate = this.accountRepository.findByInstitutionAndId(dto.getInstitutionName(), dto.getAccountId()).orElseThrow(IllegalArgumentException::new);

            String newAccountName = dto.getAccountInfo().getAccountName();
            if(newAccountName != null && containsName(accounts, newAccountName) && !newAccountName.equals(toUpdate.getAccountName())){
                throw new IllegalArgumentException("Account with new name already exists");
            }

            accounts.removeIf(a -> a.getId() == toUpdate.getId());

            Account newAccount = updateAccountProperties(toUpdate, dto);

            accounts.add(newAccount);
            institution.setAccounts(accounts);
            this.institutionRepository.save(institution);

            return newAccount;
        }

        throw new IllegalArgumentException("Account with new name already exists!");
    }

    public void deleteAccountByName(@ValidInstitutionName String institutionName,
                                    @ValidAccountName String accountName) throws Exception{
        Institution institution = this.institutionRepository.findByName(institutionName).orElseThrow(IllegalArgumentException::new);
        List<Account> accounts = institution.getAccounts();

        if(containsName(accounts, accountName)){
            accounts.removeIf(a -> Objects.equals(a.getAccountName(), accountName));
            institution.setAccounts(accounts);
            this.institutionRepository.save(institution);
            return;
        }
        throw new IllegalArgumentException("Account does not exists!");
    }

    public void deleteAccountById(@ValidInstitutionName String institutionName,
                                  @NotNull UUID accountId) throws Exception{
        Institution institution = this.institutionRepository.findByName(institutionName).orElseThrow(IllegalArgumentException::new);
        List<Account> accounts = institution.getAccounts();

        if(containsId(accounts, accountId)){
            accounts.removeIf(a -> a.getId() == accountId);
            institution.setAccounts(accounts);
            this.institutionRepository.save(institution);
        }
    }



    private Account updateAccountProperties(Account toUpdate, AccountUpdateDTO dto){

        if(dto.getAccountInfo().getBalance() != null){
            toUpdate.setBalance(dto.getAccountInfo().getBalance());
        }
        AccountOwnerNameValue owner = new AccountOwnerNameValue();
        if(dto.getAccountInfo().getOwner().getFirstName() != null){
            owner.setFirstName(dto.getAccountInfo().getOwner().getFirstName());
        }
        if(dto.getAccountInfo().getOwner().getLastName() != null){
            owner.setLastName(dto.getAccountInfo().getOwner().getLastName());
        }
        if(dto.getAccountInfo().getOwner() != null){
            toUpdate.setAccountOwner(owner);
        }
        if(dto.getAccountInfo().getAccountName() != null){
            toUpdate.setAccountName(dto.getAccountInfo().getAccountName());
        }

        return toUpdate;
    }

    private boolean isInputInvalid(Account account){
        UUID accountId = account.getId();
        String accountName = account.getAccountName();
        Double balance = account.getBalance();
        AccountOwnerNameValue owner = account.getOwner();
        String firstName = owner.getFirstName();
        String lastName = owner.getLastName();
        if(accountId == null || balance.isNaN() || balance.isInfinite()){
            return true;
        }
        if(accountName.isEmpty() || accountName.length() > Constants.ACCOUNT_NAME_MAX_LENGTH){
            return true;
        }
        if(firstName.length() > Constants.OWNER_FIRST_NAME_MAX_LENGTH || firstName.length() < Constants.OWNER_FIRST_NAME_MIN_LENGTH){
            return true;
        }
        if(lastName != "" && (lastName.length() > Constants.OWNER_LAST_NAME_MAX_LENGTH)){
            return true;
        }
        if(owner.getFirstName().isBlank()){
            return true;
        }
        return false;
    }
    private boolean isInputInvalid(AccountUpdateDTO dto){
        String name = dto.getAccountInfo().getAccountName();
        Double balance = dto.getAccountInfo().getBalance();
        String ownerFirstName = dto.getAccountInfo().getOwner().getFirstName();
        String ownerLastName = dto.getAccountInfo().getOwner().getLastName();

        if(dto.getAccountInfo()==null){
            return true;
        }
        if(dto.getInstitutionName() == null){
            return true;
        }
        if(dto.getAccountInfo().getAccountName() != null){
            if(name.isBlank() || name.length() > Constants.ACCOUNT_NAME_MAX_LENGTH || name.length() < Constants.ACCOUNT_NAME_MIN_LENGTH){
                return true;
            }
        }
        if(balance != null){
            if(balance.isNaN() || balance.isInfinite()){
                return true;
            }
        }
        if(dto.getAccountInfo().getOwner() != null){
            if(ownerFirstName.isEmpty() || ownerFirstName.isBlank() || ownerFirstName.length() > Constants.OWNER_FIRST_NAME_MIN_LENGTH || ownerFirstName.length() < Constants.OWNER_FIRST_NAME_MIN_LENGTH){
                return true;
            }
            if(ownerLastName.isEmpty() || ownerLastName.isBlank() || ownerLastName.length() > Constants.OWNER_LAST_NAME_MAX_LENGTH){
                return true;
            }
        }
        return false;
    }

    public boolean containsName(List<Account> accounts, String name){
        return accounts.stream().anyMatch(account -> account.getAccountName().equals(name));
    }

    public boolean containsId(List<Account> accounts, UUID id){
        return accounts.stream().anyMatch(account -> account.getId().equals(id));
    }
}

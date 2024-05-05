package de.dhbw.softwareengineering.application;

import de.dhbw.softwareengineering.adapters.account.AccountCreateDTO;
import de.dhbw.softwareengineering.adapters.account.Mapper.CreateDTOToAccountMapper;
import de.dhbw.softwareengineering.domain.account.Account;
import de.dhbw.softwareengineering.domain.account.AccountRepository;
import de.dhbw.softwareengineering.domain.institution.Institution;
import de.dhbw.softwareengineering.domain.institution.InstitutionRepository;
import de.dhbw.softwareengineering.domain.values.AccountOwnerNameValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountApplicationService {

    private final AccountRepository accountRepository;

    private final InstitutionRepository institutionRepository;

    private final CreateDTOToAccountMapper createMapper;

    @Autowired
    public AccountApplicationService(AccountRepository accountRepository, InstitutionRepository institutionRepository, CreateDTOToAccountMapper createMapper) {
        this.accountRepository = accountRepository;
        this.institutionRepository = institutionRepository;
        this.createMapper = createMapper;
    }

    public List<Account> getAllAccounts(String institutionName){
        return this.accountRepository.findByInstitution(institutionName);
    }

    public Optional<Account> getAccountByName(String institutionName, String accountName){
        return this.accountRepository.findByInstitutionAndName(institutionName, accountName);
    }

    public Optional<Account> getAccountById(String institutionName, UUID accountId){
        return this.accountRepository.findByInstitutionAndId(institutionName, accountId);
    }

    public Account createAccount(AccountCreateDTO dto) throws Exception{
        Institution institution = this.institutionRepository.findByName(dto.getInstitutionName()).orElseThrow(IllegalArgumentException::new);

        //Check if acc w/ name already exists
        if(containsName(institution.getAccounts(), dto.getInstitutionName())){
            throw new IllegalArgumentException("Account with this name does already exist!");
        }

        Account toCreate = createMapper.apply(dto, institution);

        if(isInputValid(toCreate)){
            throw new IllegalArgumentException("Wrong account input!");
        }

        List<Account> institutionAccounts = institution.getAccounts();
        institutionAccounts.add(toCreate);
        institution.setAccounts(institutionAccounts);

        this.institutionRepository.save(institution);
        return toCreate;
    }

    public Account updateAccount(AccountCreateDTO dto) throws Exception{
        return null;
    }

    public void deleteAccountByName(String institutionName, String accountName) throws Exception{
        Institution institution = this.institutionRepository.findByName(institutionName).orElseThrow(IllegalArgumentException::new);
        List<Account> accounts = institution.getAccounts();

        if(containsName(accounts, accountName)){
            accounts.removeIf(a -> Objects.equals(a.getAccountName(), accountName));
            institution.setAccounts(accounts);
            this.institutionRepository.save(institution);
        }
    }

    public void deleteAccountById(String institutionName, UUID accountId) throws Exception{
        Institution institution = this.institutionRepository.findByName(institutionName).orElseThrow(IllegalArgumentException::new);
        List<Account> accounts = institution.getAccounts();

        if(containsId(accounts, accountId)){
            accounts.removeIf(a -> a.getId() == accountId);
            institution.setAccounts(accounts);
            this.institutionRepository.save(institution);
        }
    }



    private boolean isInputValid(Account account){
        UUID accountId = account.getId();
        String accountName = account.getAccountName();
        Double balance = account.getBalance();
        AccountOwnerNameValue owner = account.getOwner();
        if(accountId == null || balance.isNaN() || balance.isInfinite()){
            return true;
        }
        if(accountName.isEmpty() || accountName.length() > 20){
            return true;
        }
        if(owner.getFirstName().length() > 15 || owner.getLastName().length() > 25){
            return true;
        }
        if(owner.getFirstName().isBlank() || owner.getLastName().isBlank()){
            return true;
        }
        return false;
    }

    private boolean containsName(List<Account> accounts, String name){
        return accounts.stream().anyMatch(account -> account.getAccountName().equals(name));
    }

    private boolean containsId(List<Account> accounts, UUID id){
        return accounts.stream().anyMatch(account -> account.getId().equals(id));
    }
}

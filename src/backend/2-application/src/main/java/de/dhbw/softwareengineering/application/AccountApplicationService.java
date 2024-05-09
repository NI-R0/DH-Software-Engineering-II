package de.dhbw.softwareengineering.application;

import de.dhbw.softwareengineering.adapters.account.AccountCreateDTO;
import de.dhbw.softwareengineering.adapters.account.AccountUpdateDTO;
import de.dhbw.softwareengineering.adapters.account.mapper.CreateDTOToAccountMapper;
import de.dhbw.softwareengineering.adapters.account.mapper.UpdateDTOToAccountMapper;
import de.dhbw.softwareengineering.domain.services.ValidationService;
import de.dhbw.softwareengineering.exceptions.ObjectNotFoundException;
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
    private final UpdateDTOToAccountMapper updateMapper;
    private final ValidationService validationService;

    @Autowired
    public AccountApplicationService(AccountRepository accountRepository, InstitutionRepository institutionRepository, CreateDTOToAccountMapper createMapper, UpdateDTOToAccountMapper updateMapper, ValidationService validationService) {
        this.accountRepository = accountRepository;
        this.institutionRepository = institutionRepository;
        this.createMapper = createMapper;
        this.updateMapper = updateMapper;
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
        Institution institution = this.institutionRepository.findByName(dto.getInstitutionName())
                .orElseThrow(() -> new ObjectNotFoundException("Institution with name " + dto.getInstitutionName() + " does not exist."));
        List<Account> accounts = institution.getAccounts();

        //Check if acc with new name already exists
        if(containsName(accounts, dto.getAccount().getAccountName())){
            throw new IllegalArgumentException("Account with new name does already exist!");
        }

        Account toCreate = this.createMapper.apply(dto, institution);
        this.validationService.validate(toCreate);
        accounts.add(toCreate);
        institution.updateAccounts(accounts);

        this.institutionRepository.save(institution);
        return toCreate;
    }

    public Account updateAccount(@Valid AccountUpdateDTO dto) throws Exception{

        //Check if institution does exist
        Institution institution = this.institutionRepository.findByName(dto.getInstitutionName())
                .orElseThrow(() -> new ObjectNotFoundException("Institution with name " + dto.getInstitutionName() + " does not exist."));
        List<Account> accounts = institution.getAccounts();

        //Check if account to update does exist
        if(!containsId(accounts, dto.getAccountId())){
            throw new ObjectNotFoundException("Account with ID " + dto.getAccountId() + " does not exist.");
        }

        Account toUpdate = this.accountRepository.findByInstitutionAndId(dto.getInstitutionName(), dto.getAccountId()).get();

        //Check if account with new name already exists
        String newName = dto.getAccountInfo().getAccountName();
        if(newName != null && !Objects.equals(newName, toUpdate.getAccountName())){
            if(containsName(accounts, dto.getAccountInfo().getAccountName())){
                throw new IllegalArgumentException("Account with new name does already exist.");
            }
        }

        //accounts.removeIf(a -> a.getId() == toUpdate.getId());
        accounts.remove(toUpdate);
        Account newAccount = this.updateMapper.apply(toUpdate, dto);
        this.validationService.validate(newAccount);
        accounts.add(newAccount);
        institution.updateAccounts(accounts);
        this.institutionRepository.save(institution);

        return newAccount;
    }

    public void deleteAccountByName(@ValidInstitutionName String institutionName,
                                    @ValidAccountName String accountName) throws Exception{
        Institution institution = this.institutionRepository.findByName(institutionName)
                .orElseThrow(() -> new ObjectNotFoundException("Institution with name " + institutionName + " does not exist."));
        List<Account> accounts = institution.getAccounts();

        if(!containsName(accounts, accountName)){
            throw new IllegalArgumentException("Account does not exists!");
        }

        accounts.removeIf(a -> Objects.equals(a.getAccountName(), accountName));
        institution.updateAccounts(accounts);
        this.institutionRepository.save(institution);
        return;
    }

    public void deleteAccountById(@ValidInstitutionName String institutionName,
                                  @NotNull UUID accountId) throws Exception{
        Institution institution = this.institutionRepository.findByName(institutionName)
                .orElseThrow(() -> new ObjectNotFoundException("Institution with name " + institutionName + " does not exist."));
        List<Account> accounts = institution.getAccounts();

        if(!containsId(accounts, accountId)){
            throw new IllegalArgumentException("Account does not exists!");
        }

        accounts.removeIf(a -> a.getId().equals(accountId));
        institution.updateAccounts(accounts);
        this.institutionRepository.save(institution);
        return;
    }



    public boolean containsName(List<Account> accounts, String name){
        return accounts.stream().anyMatch(account -> account.getAccountName().equals(name));
    }

    public boolean containsId(List<Account> accounts, UUID id){
        return accounts.stream().anyMatch(account -> account.getId().equals(id));
    }
}

package de.dhbw.softwareengineering.plugins.REST;

import de.dhbw.softwareengineering.adapters.account.AccountCreateDTO;
import de.dhbw.softwareengineering.adapters.account.AccountReturnDTO;
import de.dhbw.softwareengineering.adapters.account.AccountUpdateDTO;
import de.dhbw.softwareengineering.adapters.account.mapper.AccountToReturnDTOMapper;
import de.dhbw.softwareengineering.application.AccountApplicationService;
import de.dhbw.softwareengineering.domain.account.Account;
import de.dhbw.softwareengineering.exceptions.ObjectNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/accounts")
public class AccountController {

    private final AccountApplicationService accountService;

    private final AccountToReturnDTOMapper accountMapper;

    @Autowired
    public AccountController(final AccountApplicationService accountService, final AccountToReturnDTOMapper accountMapper) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    @GetMapping("/getAll/{institutionName}")
    @Operation(
            summary = "Retrieve all accounts",
            description = "Retrieve all accounts of an institution.",
            tags = {"Account Controller"}
    )
    @Parameters({
            @Parameter(name = "institutionName", description = "Name of institution.")
    })
    public ResponseEntity<List<AccountReturnDTO>> getAllAccounts(@PathVariable String institutionName){
        List<AccountReturnDTO> accounts = this.accountService.getAllAccounts(institutionName)
                .stream()
                .map(accountMapper)
                .collect(Collectors.toList());
        if(!accounts.isEmpty()){
            return ResponseEntity.ok(accounts);
        }
        throw new ObjectNotFoundException("No accounts could be found for Institution " + institutionName);
    }

    @GetMapping("/get/{institutionName}/name={accountName}")
    @Operation(
            summary = "Retrieve account by name",
            description = "Retrieve an account by its name.",
            tags = {"Account Controller"}
    )
    @Parameters({
            @Parameter(name = "institutionName", description = "Name of institution."),
            @Parameter(name = "accountName", description = "Name of account to retrieve")
    })
    public ResponseEntity<AccountReturnDTO> getAccountByName(@PathVariable String institutionName,
                                                             @PathVariable String accountName){
        Account account = this.accountService.getAccountByName(institutionName, accountName).orElseThrow(() -> new ObjectNotFoundException("Account with name " + accountName + " could not be found."));
        return ResponseEntity.ok(this.accountMapper.apply(account));
    }

    @GetMapping("/get/{institutionName}/id={accountId}")
    @Operation(
            summary = "Retrieve account by ID",
            description = "Retrieve an account by its ID.",
            tags = {"Account Controller"}
    )
    @Parameters({
            @Parameter(name = "institutionName", description = "Name of institution."),
            @Parameter(name = "accountId", description = "ID of account to retrieve.")
    })
    public ResponseEntity<AccountReturnDTO> getAccountById(@PathVariable String institutionName,
                                                           @PathVariable UUID accountId) throws Exception{
        Account account = this.accountService.getAccountById(institutionName, accountId).orElseThrow(() -> new ObjectNotFoundException("Account with ID " + accountId + " could not be found."));
        return ResponseEntity.ok(this.accountMapper.apply(account));

    }

    @PostMapping("/create")
    @Operation(
            summary = "Create new account",
            description = "Create a new account.",
            tags = {"Account Controller"}
    )
    public ResponseEntity<AccountReturnDTO> createAccount(@RequestBody AccountCreateDTO account) throws Exception{
        Account body = this.accountService.createAccount(account);
        return ResponseEntity.ok(this.accountMapper.apply(body));
    }

    @PutMapping("/update")
    @Operation(
            summary = "Update account",
            description = "Update an account. Name or ID must match an existing account.",
            tags = {"Account Controller"}
    )
    public ResponseEntity<AccountReturnDTO> updateAccount(@RequestBody AccountUpdateDTO account) throws Exception{
        Account body = this.accountService.updateAccount(account);
        return ResponseEntity.ok(this.accountMapper.apply(body));
    }

    @DeleteMapping("/delete/{institutionName}/name={name}")
    @Operation(
            summary = "Delete account by name",
            description = "Delete an account by its name.",
            tags = {"Account Controller"}
    )
    @Parameters({
            @Parameter(name = "institutionName", description = "Name of institution"),
            @Parameter(name = "name" ,description = "Name of account to delete")
    })
    public ResponseEntity<Void> deleteByName(@PathVariable String institutionName, @PathVariable("name") String accountName) throws Exception{
        this.accountService.deleteAccountByName(institutionName, accountName);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("delete/{institutionName}/id={id}")
    @Operation(
            summary = "Delete account by ID",
            description = "Delete an account by its ID.",
            tags = {"Account Controller"}
    )
    @Parameters({
            @Parameter(name = "institutionName", description = "Name of institution"),
            @Parameter(name = "id" ,description = "ID of account to delete")
    })
    public ResponseEntity<Void> deleteById(@PathVariable String institutionName, @PathVariable("id") UUID accountId) throws Exception{
        this.accountService.deleteAccountById(institutionName, accountId);
        return ResponseEntity.ok().build();
    }

}

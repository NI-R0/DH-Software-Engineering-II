package de.dhbw.softwareengineering.plugins.REST;

import de.dhbw.softwareengineering.adapters.account.AccountCreateDTO;
import de.dhbw.softwareengineering.adapters.account.AccountReturnDTO;
import de.dhbw.softwareengineering.adapters.account.AccountUpdateDTO;
import de.dhbw.softwareengineering.adapters.account.Mapper.AccountToReturnDTOMapper;
import de.dhbw.softwareengineering.application.AccountApplicationService;
import de.dhbw.softwareengineering.domain.account.Account;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
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
        return ResponseEntity.ok(this.accountService.getAllAccounts(institutionName)
                .stream()
                .map(accountMapper)
                .collect(Collectors.toList()));
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
        try{
            Account account = this.accountService.getAccountByName(institutionName, accountName).orElseThrow(IllegalArgumentException::new);
            return ResponseEntity.ok(this.accountMapper.apply(account));
        }
        catch(Exception e){
            System.out.println("AccountController: " + e.toString());
            return ResponseEntity.notFound().build();
        }
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
                                                           @PathVariable UUID accountId){
        try{
            Account account = this.accountService.getAccountById(institutionName, accountId).orElseThrow(IllegalArgumentException::new);
            return ResponseEntity.ok(this.accountMapper.apply(account));
        }
        catch(Exception e){
            System.out.println("AccountController: " + e.toString());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    @Operation(
            summary = "Create new account",
            description = "Create a new account.",
            tags = {"Account Controller"}
    )
    public ResponseEntity<AccountReturnDTO> createAccount(@RequestBody AccountCreateDTO account){
        try{
            Account body = this.accountService.createAccount(account);
            return ResponseEntity.ok(this.accountMapper.apply(body));
        }
        catch(Exception e){
            System.out.println("AccountController: " + e.toString());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update")
    @Operation(
            summary = "Update account",
            description = "Update an account. Name or ID must match an existing account.",
            tags = {"Account Controller"}
    )
    public ResponseEntity<AccountReturnDTO> updateAccount(@RequestBody AccountUpdateDTO account){
        try{
            Account body = this.accountService.updateAccount(account);
            return ResponseEntity.ok(this.accountMapper.apply(body));
        }
        catch(Exception e){
            System.out.println("AccountController: " + e.toString());
            return ResponseEntity.badRequest().build();
        }
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
    public ResponseEntity<Void> deleteByName(@PathVariable String institutionName, @PathVariable("name") String accountName){
        try{
            this.accountService.deleteAccountByName(institutionName, accountName);
            return ResponseEntity.ok().build();
        }
        catch(Exception e){
            System.out.println("AccountController: " + e.toString());
            return ResponseEntity.badRequest().build();
        }
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
    public ResponseEntity<Void> deleteById(@PathVariable String institutionName, @PathVariable("id") UUID accountId){
        try{
            this.accountService.deleteAccountById(institutionName, accountId);
            return ResponseEntity.ok().build();
        }
        catch(Exception e){
            System.out.println("AccountController: " + e.toString());
            return ResponseEntity.badRequest().build();
        }
    }


}

package de.dhbw.softwareengineering.plugins.REST;

import de.dhbw.softwareengineering.adapters.account.AccountCreateDto;
import de.dhbw.softwareengineering.adapters.account.AccountGetDto;
import de.dhbw.softwareengineering.adapters.institution.InstitutionGetDto;
import de.dhbw.softwareengineering.adapters.institution.InstitutionNameDto;
import de.dhbw.softwareengineering.application.AccountService;
import de.dhbw.softwareengineering.domain.account.AccountAggregate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

////////////////////////////////////////////////////////////////////////////////////////////
    //Account methods

    /*@GetMapping("/api/institution={institution}/accounts/getAll")
    @Operation(
            summary = "Retrieve all accounts",
            description = "Retrieve all accounts of an institution.",
            tags = {"Account Controller"}
    )
    @Parameters({
            @Parameter(name = "institution", description = "Name of institution")
    })
    public ResponseEntity<List<AccountGetDto>> getAllAccounts(@PathVariable("institution") String institutionName)
    {
        return ResponseEntity.ok().build();
    }*/

    @GetMapping("/getAll/{institutionName}")
    @Operation(
            summary = "Retrieve all accounts",
            description = "Retrieve all accounts of an institution.",
            tags = {"Account Controller"}
    )
    public ResponseEntity<List<AccountGetDto>> getAllAccounts(@PathVariable String institutionName)
    {
        return ResponseEntity.ok(accountService.getAllAccounts(institutionName));
    }

    @GetMapping("/get/{institutionName}/{accountName}")
    @Operation(
            summary = "Retrieve account by name",
            description = "Retrieve an account by its name.",
            tags = {"Account Controller"}
    )
    @Parameters({
            @Parameter(name = "name", description = "Name of account to retrieve")
    })
    public ResponseEntity<AccountGetDto> getAccountByName(@PathVariable String institutionName,
                                                          @PathVariable String accountName)
    {
        Optional<AccountGetDto> account = accountService.getAccountByName(institutionName, accountName);
        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/get/{institutionName}/{accountId}")
    @Operation(
            summary = "Retrieve account by ID",
            description = "Retrieve an account by its ID.",
            tags = {"Account Controller"}
    )
    @Parameters({
            @Parameter(name = "id", description = "ID of account to retrieve.")
    })
    public ResponseEntity<AccountGetDto> getAccountById(@PathVariable String institutionName,
                                                        @PathVariable UUID accountId)
    {
        Optional<AccountGetDto> account = accountService.getAccountById(institutionName, accountId);
        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    @Operation(
            summary = "Create new account",
            description = "Create a new account.",
            tags = {"Account Controller"}
    )
    public ResponseEntity<AccountGetDto> createAccount(@RequestBody AccountCreateDto dto)
    {
        try{
            AccountGetDto body = accountService.createAccount(dto);
            return ResponseEntity.ok(body);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }









    @PutMapping("/accounts/update")
    @Operation(
            summary = "Update account",
            description = "Update an account. Name or ID must match an existing account.",
            tags = {"Account Controller"}
    )
    @Parameters({
            @Parameter(name = "institution", description = "Name of institution")
    })
    public ResponseEntity<InstitutionGetDto> updateAccount(@PathVariable("institution") String institutionName,
                                                           @RequestBody AccountCreateDto dto)
    {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/id={id}")
    @Operation(
            summary = "Delete account by ID",
            description = "Delete an account by its ID.",
            tags = {"Account Controller"}
    )
    @Parameters({
            @Parameter(name = "institution", description = "Name of institution"),
            @Parameter(name = "id" ,description = "ID of account to delete")
    })
    public ResponseEntity<Void> deleteAccountById(@PathVariable("institution") String institutionName,
                                                  @PathVariable("id") UUID accountId)
    {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{name}")
    @Operation(
            summary = "Delete account by name",
            description = "Delete an account by its name.",
            tags = {"Account Controller"}
    )
    @Parameters({
            @Parameter(name = "institution", description = "Name of institution"),
            @Parameter(name = "name" ,description = "Name of account to delete")
    })
    public ResponseEntity<Void> deleteAccountByName(@PathVariable("institution") String institutionName,
                                                    @PathVariable("name") String accountName)
    {
        return ResponseEntity.ok().build();
    }



}

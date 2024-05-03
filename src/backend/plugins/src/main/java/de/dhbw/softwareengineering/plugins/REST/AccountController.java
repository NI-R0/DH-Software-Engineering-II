package de.dhbw.softwareengineering.plugins.REST;

import de.dhbw.softwareengineering.adapters.account.AccountDto;
import de.dhbw.softwareengineering.adapters.institution.InstitutionGetDto;
import de.dhbw.softwareengineering.adapters.institution.InstitutionNameDto;
import de.dhbw.softwareengineering.application.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
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

    @GetMapping("/api/accounts/getAll")
    @Operation(
            summary = "Retrieve all accounts",
            description = "Retrieve all accounts of an institution.",
            tags = {"Account Controller"}
    )
    public ResponseEntity<List<AccountDto>> getAllAccounts(@RequestBody InstitutionNameDto dto)
    {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/accounts/get/name={name}")
    @Operation(
            summary = "Retrieve account by name",
            description = "Retrieve an account by its name.",
            tags = {"Account Controller"}
    )
    @Parameters({
            @Parameter(name = "name", description = "Name of account to retrieve")
    })
    public ResponseEntity<List<AccountDto>> getAccountByName(@PathVariable("name") String accountName,
                                                             @RequestBody InstitutionNameDto dto)
    {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/accounts/get/id={id}")
    @Operation(
            summary = "Retrieve account by ID",
            description = "Retrieve an account by its ID.",
            tags = {"Account Controller"}
    )
    @Parameters({
            @Parameter(name = "id", description = "ID of account to retrieve.")
    })
    public ResponseEntity<List<AccountDto>> getAccountById(@PathVariable("id") UUID accountId,
                                                           @RequestBody InstitutionNameDto dto)
    {
        return ResponseEntity.ok().build();
    }





    @PostMapping("/api/institution={institution}/accounts/create")
    @Operation(
            summary = "Create new account",
            description = "Create a new account.",
            tags = {"Account Controller"}
    )
    @Parameters({
            @Parameter(name = "institution", description = "Name of institution")
    })
    public ResponseEntity<InstitutionGetDto> createAccount(@PathVariable("institution") String institutionName,
                                                           @RequestBody AccountDto dto)
    {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/institution={institution}/accounts/update")
    @Operation(
            summary = "Update account",
            description = "Update an account. Name or ID must match an existing account.",
            tags = {"Account Controller"}
    )
    @Parameters({
            @Parameter(name = "institution", description = "Name of institution")
    })
    public ResponseEntity<InstitutionGetDto> updateAccount(@PathVariable("institution") String institutionName,
                                                           @RequestBody AccountDto dto)
    {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/institution={institution}/accounts/delete/id={id}")
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

    @DeleteMapping("/api/institution/{institution}/accounts/delete/{name}")
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

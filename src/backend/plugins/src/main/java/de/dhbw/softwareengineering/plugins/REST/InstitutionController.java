package de.dhbw.softwareengineering.plugins.REST;

import de.dhbw.softwareengineering.adapters.account.AccountDto;
import de.dhbw.softwareengineering.adapters.institution.InstitutionCreationDto;
import de.dhbw.softwareengineering.adapters.institution.InstitutionDto;
import de.dhbw.softwareengineering.application.AccountService;
import de.dhbw.softwareengineering.application.InstitutionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class InstitutionController {
    @Autowired
    InstitutionService institutionService;
    @Autowired
    AccountService accountService;

    @GetMapping("/api/institutions/getAll")
    @Operation(
            summary = "Retrieve all institutions",
            description = "Retrieve all institutions.",
            tags = {"Institution Controller"}
    )
    public ResponseEntity<List<InstitutionDto>> getAllInstitutions(){
        return ResponseEntity.ok(institutionService.getAllInstitutions());
    }

    @GetMapping("/api/institutions/get/id={id}")
    @Operation(
            summary = "Retrieve institution by ID",
            description = "Retrieve an institution by its ID.",
            tags = {"Institution Controller"}
    )
    @Parameters({
            @Parameter(name = "id", description = "ID of institution to retrieve")
    })
    public ResponseEntity<InstitutionDto> getInstitutionById(@PathVariable UUID id){
        Optional<InstitutionDto> dto = institutionService.getInstitutionById(id);
        return dto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/api/institutions/get/name={name}")
    @Operation(
            summary = "Retrieve institution by name",
            description = "Retrieve an institution by its name.",
            tags = {"Institution Controller"}
    )
    @Parameters({
            @Parameter(name = "name", description = "Name of institution to retrieve")
    })
    public ResponseEntity<InstitutionDto> getInstitutionByName(@PathVariable String name){
        Optional<InstitutionDto> dto = institutionService.getInstitutionByName(name);
        return dto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping("/api/institutions/create")
    @Operation(
            summary = "Create institution",
            description = "Create a new institution.",
            tags = {"Institution Controller"}
    )
    public ResponseEntity<InstitutionDto> createInstitution(@RequestBody InstitutionDto dto){
        try{
            Optional<InstitutionDto> body = institutionService.createInstitution(dto);
            return ResponseEntity.ok(body.orElseThrow(IllegalArgumentException::new));
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/api/institutions/update")
    @Operation(
            summary = "Update institution",
            description = "Update an institution. Name or ID must match an existing institution in the database.",
            tags = {"Institution Controller"}
    )
    public ResponseEntity<InstitutionDto> updateInstitution(@RequestBody InstitutionDto dto){
        try{
            Optional<InstitutionDto> body = institutionService.updateInstitution(dto);
            return ResponseEntity.ok(body.orElseThrow(IllegalArgumentException::new));
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/api/institutions/delete/id={id}")
    @Operation(
            summary = "Delete institution by ID",
            description = "Deletes an institution by its ID.",
            tags = {"Institution Controller"}
    )
    @Parameters({
            @Parameter(name = "id", description = "ID of institution to delete")
    })
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        try{
            institutionService.deleteInstitutionById(id);
            return ResponseEntity.ok().build();
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/api/institutions/delete/name={name}")
    @Operation(
            summary = "Delete institution by name",
            description = "Deletes an institution by its name.",
            tags = {"Institution Controller"}
    )
    @Parameters({
            @Parameter(name = "name", description = "Name of institution to delete")
    })
    public ResponseEntity<Void> deleteByName(@PathVariable String name){
        try{
            institutionService.deleteInstitutionByName(name);
            return ResponseEntity.ok().build();
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    //Account methods

    @GetMapping("/api/institution={institution}/accounts/getAll")
    @Operation(
            summary = "Retrieve all accounts",
            description = "Retrieve all accounts of an institution.",
            tags = {"Account Controller"}
    )
    @Parameters({
            @Parameter(name = "institution", description = "Name of institution")
    })
    public ResponseEntity<List<AccountDto>> getAllAccounts(@PathVariable("institution") String institutionName)
    {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/institution={institution}/accounts/get/name={name}")
    @Operation(
            summary = "Retrieve account by name",
            description = "Retrieve an account by its name.",
            tags = {"Account Controller"}
    )
    @Parameters({
            @Parameter(name = "institution", description = "Name of institution"),
            @Parameter(name = "name", description = "Name of account to retrieve")
    })
    public ResponseEntity<List<AccountDto>> getAccountByName(@PathVariable("institution") String institutionName,
                                                             @PathVariable("name") String accountName)
    {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/institution={institution}/accounts/get/id={id}")
    @Operation(
            summary = "Retrieve account by ID",
            description = "Retrieve an account by its ID.",
            tags = {"Account Controller"}
    )
    @Parameters({
            @Parameter(name = "institution", description = "Name of institution"),
            @Parameter(name = "id", description = "ID of account to retrieve.")
    })
    public ResponseEntity<List<AccountDto>> getAccountById(@PathVariable("institution") String institutionName,
                                                           @PathVariable("id") UUID accountId)
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
    public ResponseEntity<InstitutionDto> createAccount(@PathVariable("institution") String institutionName,
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
    public ResponseEntity<InstitutionDto> updateAccount(@PathVariable("institution") String institutionName,
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

    @DeleteMapping("/api/institution={institution}/accounts/delete/name={name}")
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



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Transaction Methods

    @GetMapping("/api/institution={institution}/account={account}/transactions/getAll")
    @Operation(
            summary = "Retrieve all transactions",
            description = "Retrieve all transactions of an account.",
            tags = {"Transaction Controller"}
    )
    @Parameters({
            @Parameter(name = "institution", description = "Name of institution"),
            @Parameter(name = "account" ,description = "Name of account")
    })
    public ResponseEntity<List<AccountDto>> getAllTransactions(@PathVariable("institution") String institutionName,
                                                               @PathVariable("account") String accountName)
    {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/institution={institution}/account={account}/transactions/get/id={id}")
    @Operation(
            summary = "Retrieve transaction by ID",
            description = "Retrieves a transaction from the database by its ID.",
            tags = {"Transaction Controller"}
    )
    @Parameters({
            @Parameter(name = "institution", description = "Name of institution"),
            @Parameter(name = "account" ,description = "Name of account"),
            @Parameter(name = "id", description = "ID of transaction to retrieve")
    })
    public ResponseEntity<List<AccountDto>> getTransactionById(@PathVariable("institution") String institutionName,
                                                               @PathVariable("account") String accountName,
                                                               @PathVariable("id") UUID transactionId)
    {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/institution={institution}/account={account}/transactions/create")
    @Operation(
            summary = "Create new transaction",
            description = "Create a new transaction.",
            tags = {"Transaction Controller"}
    )
    @Parameters({
            @Parameter(name = "institution", description = "Name of institution"),
            @Parameter(name = "account" ,description = "Name of account")
    })
    public ResponseEntity<InstitutionDto> createTransaction(@PathVariable("institution") String institutionName,
                                                            @PathVariable("account") String accountName,
                                                            @RequestBody AccountDto dto)
    {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/institution={institution}/account={account}/transactions/update")
    @Operation(
            summary = "Update transaction",
            description = "Updates a transaction. ID must match to an existing transaction.",
            tags = {"Transaction Controller"}
    )
    @Parameters({
            @Parameter(name = "institution", description = "Name of institution"),
            @Parameter(name = "account" ,description = "Name of account")
    })
    public ResponseEntity<InstitutionDto> updateTransaction(@PathVariable("institution") String institutionName,
                                                            @PathVariable("account") String accountName,
                                                            @RequestBody AccountDto dto)
    {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/institution={institution}/account={account}/transactions/delete/id={id}")
    @Operation(
            summary = "Delete transaction by ID",
            description = "Delete a transaction by its ID.",
            tags = {"Transaction Controller"}
    )
    @Parameters({
            @Parameter(name = "institution", description = "Name of institution"),
            @Parameter(name = "account" ,description = "Name of account"),
            @Parameter(name = "id", description = "ID of transaction to delete")
    })
    public ResponseEntity<Void> deleteTransaction(@PathVariable("institution") String institutionName,
                                                  @PathVariable("account") String accountName,
                                                  @PathVariable("id") UUID transactionId)
    {
        return ResponseEntity.ok().build();
    }







    /*@Operation(
            summary = "Retrieve a Tutorial by Id",
            description = "Get a Tutorial object by specifying its id. The response is Tutorial object with id, title, description and published status.",
            tags = { "tutorials", "get" })
            @Parameters({
    @Parameter(name = "title", description = "Search Tutorials by title"),
    @Parameter(name = "page", description = "Page number, starting from 0", required = true),
    @Parameter(name = "size", description = "Number of items per page", required = true)
  })
    @GetMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {

    }*/
}

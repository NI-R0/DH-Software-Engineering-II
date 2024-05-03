package de.dhbw.softwareengineering.plugins.REST;

import de.dhbw.softwareengineering.adapters.account.AccountDto;
import de.dhbw.softwareengineering.adapters.institution.InstitutionCreationDto;
import de.dhbw.softwareengineering.adapters.institution.InstitutionDto;
import de.dhbw.softwareengineering.application.AccountService;
import de.dhbw.softwareengineering.application.InstitutionService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Tag(name = "Institution")
    public ResponseEntity<List<InstitutionDto>> getAllInstitutions(){
        return ResponseEntity.ok(institutionService.getAllInstitutions());
    }

    @GetMapping("/api/institutions/getInstitution/id={id}")
    @Tag(name = "Institution")
    public ResponseEntity<InstitutionDto> getInstitutionById(@PathVariable UUID id){
        Optional<InstitutionDto> dto = institutionService.getInstitutionById(id);
        return dto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/api/institutions/getInstitution/name={name}")
    @Tag(name = "Institution")
    public ResponseEntity<InstitutionDto> getInstitutionByName(@PathVariable String name){
        Optional<InstitutionDto> dto = institutionService.getInstitutionByName(name);
        return dto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping("/api/institutions/createInstitution")
    @Tag(name = "Institution")
    public ResponseEntity<InstitutionDto> createInstitution(@RequestBody InstitutionDto dto){
        try{
            Optional<InstitutionDto> body = institutionService.createInstitution(dto);
            return ResponseEntity.ok(body.orElseThrow(IllegalArgumentException::new));
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/api/institutions/updateInstitution")
    @Tag(name = "Institution")
    public ResponseEntity<InstitutionDto> updateInstitution(@RequestBody InstitutionDto dto){
        try{
            Optional<InstitutionDto> body = institutionService.updateInstitution(dto);
            return ResponseEntity.ok(body.orElseThrow(IllegalArgumentException::new));
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/api/institutions/deleteInstitution/id={id}")
    @Tag(name = "Institution")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        try{
            institutionService.deleteInstitutionById(id);
            return ResponseEntity.ok().build();
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/api/institutions/deleteInstitution/name={name}")
    @Tag(name = "Institution")
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

    @GetMapping("/api/institution={institutionName}/accounts/getAll")
    @Tag(name = "Account")
    public ResponseEntity<List<AccountDto>> getAllAccounts(@PathVariable String institutionName){
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/institution={institutionName}/accounts/createAccount")
    @Tag(name = "Account")
    public ResponseEntity<InstitutionDto> createAccount(@PathVariable String institutionName,
                                                        @RequestBody AccountDto dto){
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/institution={institutionName}/accounts/updateAccount")
    @Tag(name = "Account")
    public ResponseEntity<InstitutionDto> updateAccount(@PathVariable String institutionName,
                                                        @RequestBody AccountDto dto){
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/institution={institutionName}/accounts/deleteAccount/id={accountId}")
    @Tag(name = "Account")
    public ResponseEntity<InstitutionDto> deleteAccountById(@PathVariable String institutionName,
                                                            @PathVariable UUID accountId,
                                                            @RequestBody AccountDto dto){
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/institution={institutionName}/accounts/deleteAccount/name={accountName}")
    @Tag(name = "Account")
    public ResponseEntity<InstitutionDto> deleteAccountByName(@PathVariable String institutionName,
                                                              @PathVariable String accountName,
                                                              @RequestBody AccountDto dto){
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


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Transaction Methods

    @GetMapping("/api/institution={institutionName}/account={accountName}/transactions/getAllTransactions")
    @Tag(name = "Transaction")
    public ResponseEntity<List<AccountDto>> getAllTransactions(@PathVariable String institutionName,
                                                               @PathVariable String accountName){
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/institution={institutionName}/account={accountName}/transactions/createTransaction")
    @Tag(name = "Transaction")
    public ResponseEntity<InstitutionDto> createTransaction(@PathVariable String institutionName,
                                                            @PathVariable String accountName,
                                                            @RequestBody AccountDto dto){
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/institution={institutionName}/account={accountName}/transactions/updateTransaction")
    @Tag(name = "Transaction")
    public ResponseEntity<InstitutionDto> updateTransaction(@PathVariable String institutionName,
                                                            @PathVariable String accountName,
                                                            @RequestBody AccountDto dto){
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/institution={institutionName}/account={accountName}/transactions/deleteTransaction/id={transactionId}")
    @Tag(name = "Transaction")
    public ResponseEntity<InstitutionDto> deleteTransaction(@PathVariable String institutionName,
                                                            @PathVariable String accountName,
                                                            @PathVariable UUID transactionId,
                                                            @RequestBody AccountDto dto){
        return ResponseEntity.ok().build();
    }
}

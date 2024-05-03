package de.dhbw.softwareengineering.plugins.REST;

import de.dhbw.softwareengineering.adapters.account.AccountDto;
import de.dhbw.softwareengineering.adapters.institution.InstitutionCreateDto;
import de.dhbw.softwareengineering.adapters.institution.InstitutionGetDto;
import de.dhbw.softwareengineering.adapters.institution.InstitutionNameDto;
import de.dhbw.softwareengineering.adapters.institution.InstitutionUpdateDto;
import de.dhbw.softwareengineering.adapters.transaction.TransactionDto;
import de.dhbw.softwareengineering.application.AccountService;
import de.dhbw.softwareengineering.application.InstitutionService;
import de.dhbw.softwareengineering.application.TransactionService;
import de.dhbw.softwareengineering.domain.institution.InstitutionAggregate;
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
public class InstitutionController {
    @Autowired
    InstitutionService institutionService;
    @Autowired
    TransactionService transactionService;

    @GetMapping("/api/institutions/getAll")
    @Operation(
            summary = "Retrieve all institutions",
            description = "Retrieve all institutions.",
            tags = {"Institution Controller"},
            deprecated = true
    )
    public ResponseEntity<List<InstitutionGetDto>> getAllInstitutions(){
        return ResponseEntity.ok(institutionService.getAllInstitutions());
    }

    @GetMapping("/api/institutions/get/id={id}")
    @Operation(
            summary = "Retrieve institution by ID",
            description = "Retrieve an institution by its ID.",
            tags = {"Institution Controller"},
            deprecated = true
    )
    @Parameters({
            @Parameter(name = "id", description = "ID of institution to retrieve")
    })
    public ResponseEntity<InstitutionGetDto> getInstitutionById(@PathVariable UUID id){
        Optional<InstitutionGetDto> dto = institutionService.getInstitutionById(id);
        return dto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/api/institutions/get/name={name}")
    @Operation(
            summary = "Retrieve institution by name",
            description = "Retrieve an institution by its name.",
            tags = {"Institution Controller"},
            deprecated = true
    )
    @Parameters({
            @Parameter(name = "name", description = "Name of institution to retrieve")
    })
    public ResponseEntity<InstitutionGetDto> getInstitutionByName(@PathVariable String name){
        Optional<InstitutionGetDto> dto = institutionService.getInstitutionByName(name);
        return dto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping("/api/institutions/create")
    @Operation(
            summary = "Create institution",
            description = "Create a new institution.",
            tags = {"Institution Controller"},
            deprecated = true
    )
    public ResponseEntity<InstitutionGetDto> createInstitution(@RequestBody InstitutionCreateDto dto){
        try{
            InstitutionGetDto body = institutionService.createInstitution(dto);
            return ResponseEntity.ok(body);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/api/institutions/update")
    @Operation(
            summary = "Update institution by ID",
            description = "Update an institution. ID must match an existing institution in the database.",
            tags = {"Institution Controller"},
            deprecated = true
    )
    public ResponseEntity<InstitutionGetDto> updateInstitution(@RequestBody InstitutionUpdateDto dto){
        /*try{
            Optional<InstitutionGetDto> body = institutionService.updateInstitution(dto);
            return ResponseEntity.ok(body.orElseThrow(IllegalArgumentException::new));
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }*/
        try{
            InstitutionGetDto body = institutionService.updateInstitution(dto);
            return ResponseEntity.ok(body);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/api/institutions/delete/id={id}")
    @Operation(
            summary = "Delete institution by ID",
            description = "Deletes an institution by its ID.",
            tags = {"Institution Controller"},
            deprecated = true
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
            tags = {"Institution Controller"},
            deprecated = true
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





    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}

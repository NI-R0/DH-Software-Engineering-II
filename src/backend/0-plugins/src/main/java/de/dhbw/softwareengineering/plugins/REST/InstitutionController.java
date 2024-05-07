package de.dhbw.softwareengineering.plugins.REST;

import de.dhbw.softwareengineering.adapters.institution.InstitutionCreateDTO;
import de.dhbw.softwareengineering.adapters.institution.InstitutionReturnDTO;
import de.dhbw.softwareengineering.adapters.institution.InstitutionUpdateDTO;
import de.dhbw.softwareengineering.adapters.institution.mapper.InstitutionToReturnDTOMapper;
import de.dhbw.softwareengineering.application.InstitutionApplicationService;
import de.dhbw.softwareengineering.domain.institution.Institution;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/institutions")
public class InstitutionController {

    private final InstitutionApplicationService institutionService;

    private final InstitutionToReturnDTOMapper institutionMapper;

    @Autowired
    public InstitutionController(InstitutionApplicationService institutionService, InstitutionToReturnDTOMapper institutionMapper) {
        this.institutionService = institutionService;
        this.institutionMapper = institutionMapper;
    }

    @GetMapping("/getAll")
    @Operation(
            summary = "Retrieve all institutions",
            description = "Retrieve all institutions.",
            tags = {"Institution Controller"},
            deprecated = false
    )
    public ResponseEntity<List<InstitutionReturnDTO>> getAllInstitutions(){
        return ResponseEntity.ok(this.institutionService.getAllInstitutions()
                .stream()
                .map(institutionMapper)
                .collect(Collectors.toList()));
    }

    @GetMapping("/get/name={name}")
    @Operation(
            summary = "Retrieve institution by name",
            description = "Retrieve an institution by its name.",
            tags = {"Institution Controller"}
    )
    @Parameters({
            @Parameter(name = "name", description = "Name of institution to retrieve")
    })
    public ResponseEntity<InstitutionReturnDTO> getInstitutionByName(@PathVariable String name){
        try{
            Institution institution = this.institutionService.getInstitution(name).orElseThrow(IllegalArgumentException::new);
            return ResponseEntity.ok(this.institutionMapper.apply(institution));
        }
        catch(Exception e){
            System.out.println("InstitutionController: " + e.toString());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    @Operation(
            summary = "Create institution",
            description = "Create a new institution.",
            tags = {"Institution Controller"},
            deprecated = false
    )
    public ResponseEntity<InstitutionReturnDTO> createInstitution(@RequestBody InstitutionCreateDTO institution){
        try{
            Institution created = this.institutionService.createInstitution(institution);
            return ResponseEntity.ok(this.institutionMapper.apply(created));
        }
        catch(Exception e){
            System.out.println("InstitutionController: " + e.toString());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update")
    @Operation(
            summary = "Update institution by ID",
            description = "Update an institution. ID must match an existing institution in the database.",
            tags = {"Institution Controller"},
            deprecated = false
    )
    public ResponseEntity<InstitutionReturnDTO> updateInstitution(@RequestBody InstitutionUpdateDTO institution){
        try{
            Institution updated = this.institutionService.updateInstitution(institution);
            return ResponseEntity.ok(this.institutionMapper.apply(updated));
        }
        catch(Exception e){
            System.out.println("InstitutionController: " + e.toString());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/name={name}")
    @Operation(
            summary = "Delete institution by name",
            description = "Deletes an institution by its name.",
            tags = {"Institution Controller"},
            deprecated = false
    )
    @Parameters({
            @Parameter(name = "name", description = "Name of institution to delete")
    })
    public ResponseEntity<Void> deleteByName(@PathVariable(value = "name") String institutionName){
        try{
            this.institutionService.deleteInstitution(institutionName);
            return ResponseEntity.ok().build();
        }
        catch(Exception e){
            System.out.println("InstitutionController: " + e.toString());
            return ResponseEntity.notFound().build();
        }
    }



}

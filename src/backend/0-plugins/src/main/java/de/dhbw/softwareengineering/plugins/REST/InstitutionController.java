package de.dhbw.softwareengineering.plugins.REST;

import de.dhbw.softwareengineering.adapters.institution.InstitutionCreateDTO;
import de.dhbw.softwareengineering.adapters.institution.InstitutionReturnDTO;
import de.dhbw.softwareengineering.adapters.institution.InstitutionUpdateDTO;
import de.dhbw.softwareengineering.adapters.institution.mapper.InstitutionToReturnDTOMapper;
import de.dhbw.softwareengineering.application.InstitutionApplicationService;
import de.dhbw.softwareengineering.domain.institution.Institution;
import de.dhbw.softwareengineering.exceptions.ObjectNotFoundException;
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
        List<InstitutionReturnDTO> institutions = this.institutionService.getAllInstitutions()
                .stream()
                .map(institutionMapper)
                .collect(Collectors.toList());
        if(!institutions.isEmpty()){
            return ResponseEntity.ok(institutions);
        }
        throw new ObjectNotFoundException("No institutions could be found.");
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
        Institution institution = this.institutionService.getInstitution(name).orElseThrow(() -> new ObjectNotFoundException("Institution with name " + name + "could not be found."));
        return ResponseEntity.ok(this.institutionMapper.apply(institution));
    }

    @PostMapping("/create")
    @Operation(
            summary = "Create institution",
            description = "Create a new institution.",
            tags = {"Institution Controller"},
            deprecated = false
    )
    public ResponseEntity<InstitutionReturnDTO> createInstitution(@RequestBody InstitutionCreateDTO institution) throws Exception{
        Institution created = this.institutionService.createInstitution(institution);
        return ResponseEntity.ok(this.institutionMapper.apply(created));
    }

    @PutMapping("/update")
    @Operation(
            summary = "Update institution by ID",
            description = "Update an institution. ID must match an existing institution in the database.",
            tags = {"Institution Controller"},
            deprecated = false
    )
    public ResponseEntity<InstitutionReturnDTO> updateInstitution(@RequestBody InstitutionUpdateDTO institution) throws Exception{
        Institution updated = this.institutionService.updateInstitution(institution);
        return ResponseEntity.ok(this.institutionMapper.apply(updated));
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
    public ResponseEntity<Void> deleteByName(@PathVariable(value = "name") String institutionName) throws Exception{
        this.institutionService.deleteInstitution(institutionName);
        return ResponseEntity.ok().build();
    }

}

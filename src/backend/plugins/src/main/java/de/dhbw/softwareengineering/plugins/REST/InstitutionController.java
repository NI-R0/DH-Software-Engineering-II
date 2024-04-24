package de.dhbw.softwareengineering.plugins.REST;

import de.dhbw.softwareengineering.adapters.institution.InstitutionDto;
import de.dhbw.softwareengineering.application.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class InstitutionController {
    @Autowired
    InstitutionService service;

    @GetMapping("/institutions/getAll")
    public ResponseEntity<List<InstitutionDto>> getAllInstitutions(){
        return ResponseEntity.ok(service.getAllInstitutions());
    }

    @GetMapping("/institutions/getBy/id/{id}")
    public ResponseEntity<InstitutionDto> getInstitutionById(@PathVariable UUID id){
        Optional<InstitutionDto> dto = service.getInstitutionById(id);
        return dto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/institutions/getBy/name/{name}")
    public ResponseEntity<InstitutionDto> getInstitutionByName(@PathVariable String name){
        Optional<InstitutionDto> dto = service.getInstitutionByName(name);
        return dto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping("/institutions/createInstitution")
    public ResponseEntity<InstitutionDto> createInstitution(@RequestBody InstitutionDto dto){
        try{
            Optional<InstitutionDto> body = service.createInstitution(dto);
            return ResponseEntity.ok(body.orElseThrow(IllegalArgumentException::new));
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}

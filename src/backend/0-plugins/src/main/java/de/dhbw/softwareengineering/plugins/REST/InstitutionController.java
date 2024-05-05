package de.dhbw.softwareengineering.plugins.REST;

import de.dhbw.softwareengineering.adapters.institution.InstitutionDTO;
import de.dhbw.softwareengineering.adapters.institution.InstitutionToDTOMapper;
import de.dhbw.softwareengineering.application.InstitutionApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/institutions")
public class InstitutionController {

    private final InstitutionApplicationService institutionService;

    private final InstitutionToDTOMapper institutionMapper;

    @Autowired
    public InstitutionController(InstitutionApplicationService institutionService, InstitutionToDTOMapper institutionMapper) {
        this.institutionService = institutionService;
        this.institutionMapper = institutionMapper;
    }

    @GetMapping("/getAll")
    public List<InstitutionDTO> getAllInstitutions(){
        return this.institutionService.findAllInstitutions()
                .stream()
                .map(institutionMapper)
                .collect(Collectors.toList());
    }

}

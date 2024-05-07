package de.dhbw.softwareengineering;

import de.dhbw.softwareengineering.adapters.institution.InstitutionCreateDTO;
import de.dhbw.softwareengineering.adapters.institution.mapper.CreateDTOToInstitutionMapper;
import de.dhbw.softwareengineering.application.InstitutionApplicationService;
import de.dhbw.softwareengineering.domain.institution.Institution;
import de.dhbw.softwareengineering.domain.institution.InstitutionRepository;
import de.dhbw.softwareengineering.enums.InstitutionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class InstitutionServiceTest {

    @InjectMocks
    private InstitutionApplicationService institutionService;
    @Mock
    private InstitutionRepository institutionRepository;
    @Mock
    private InstitutionCreateDTO createDTO;
    @Mock
    private Institution institution;
    @Mock
    private CreateDTOToInstitutionMapper createDTOMapper;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_CreateInstitution_NameAlreadyExists() throws Exception {
        when(createDTO.getName()).thenReturn("Test Institution");
        when(createDTOMapper.apply(any())).thenReturn(institution);
        when(institution.getName()).thenReturn("Test Institution");
        when(institution.getInstitutionType()).thenReturn(InstitutionType.BANK);
        //when(institutionService.isInputInvalid(institution)).thenReturn(true);
        when(institutionRepository.findByName(anyString())).thenReturn(Optional.of(institution));

        assertThrows(IllegalArgumentException.class, () -> {
            institutionService.createInstitution(createDTO);
        });
    }

    @Test
    public void test_CreateInstitution_InvalidInput() throws Exception{
        when(createDTO.getName()).thenReturn("Test Institution");
        when(createDTOMapper.apply(any())).thenReturn(institution);
        when(institution.getName()).thenReturn("Test Institution");
        when(institution.getInstitutionType()).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> {
            institutionService.createInstitution(createDTO);
        });
    }

    @Test
    public void test_CreateInstitution_Success() throws Exception{
        when(createDTO.getName()).thenReturn("Test Institution");
        when(createDTOMapper.apply(any())).thenReturn(institution);
        when(institution.getName()).thenReturn("Test Institution");
        when(institution.getInstitutionType()).thenReturn(InstitutionType.BANK);
        when(institutionRepository.findByName(anyString())).thenReturn(Optional.empty());
        when(institutionRepository.save(institution)).thenReturn(institution);

        Institution result = institutionService.createInstitution(createDTO);
        assertEquals(institution, result);
    }

}

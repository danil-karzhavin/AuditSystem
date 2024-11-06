package ru.CSApp.restdemo.service.contractor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.CSApp.restdemo.exception.contractor.ContractorNotFoundException;
import ru.CSApp.restdemo.model.contractor.Contractor;
import ru.CSApp.restdemo.model.contractor.ContractorDto;
import ru.CSApp.restdemo.repository.contractor.IContractorRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ContractorServiceTest {

    @Mock
    private IContractorRepository contractorRepository;

    @InjectMocks
    private ContractorService contractorService;

    private Contractor contractor;
    private ContractorDto contractorDto;

    @BeforeEach
    void setUp() {
        contractor = new Contractor();
        contractor.setId(1);
        contractor.setName("Test Contractor");
        contractor.setAddress("123 Test St");
        contractor.setInn("123456789111");

        contractorDto = new ContractorDto();
        contractorDto.setId(1);
        contractorDto.setName("Test Contractor");
        contractorDto.setAddress("123 Test St");
        contractorDto.setInn("123456789111");
    }

    @Test
    void testGetAllContractors() {
        when(contractorRepository.findAll()).thenReturn(List.of(contractor));

        List<Contractor> contractors = contractorService.getAllContractors();

        assertEquals(1, contractors.size());
        assertEquals(contractor.getName(), contractors.get(0).getName());
        verify(contractorRepository, times(1)).findAll();
    }

    @Test
    void testGetContractorById() {
        when(contractorRepository.findById(1)).thenReturn(Optional.of(contractor));

        Contractor found = contractorService.getContractorById(1);

        assertEquals(contractor.getName(), found.getName());
        verify(contractorRepository, times(1)).findById(1);
    }

    @Test
    void testGetContractorById_NotFound() {
        when(contractorRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ContractorNotFoundException.class, () -> {
            contractorService.getContractorById(1);
        });

        verify(contractorRepository, times(1)).findById(1);
    }

    @Test
    void testCreateContractor() {
        when(contractorRepository.save(any(Contractor.class))).thenReturn(contractor);

        Contractor saved = contractorService.createContractor(contractorDto);

        assertEquals(contractor.getName(), saved.getName());
        verify(contractorRepository, times(1)).save(any(Contractor.class));
    }

    @Test
    void testUpdateContractor() {
        when(contractorRepository.findById(1)).thenReturn(Optional.of(contractor));
        when(contractorRepository.save(any(Contractor.class))).thenReturn(contractor);

        Contractor updated = contractorService.updateContractor(contractorDto);

        assertEquals(contractor.getName(), updated.getName());
        verify(contractorRepository, times(1)).findById(1);
        verify(contractorRepository, times(1)).save(any(Contractor.class));
    }

    @Test
    void testDeleteContractorById() {
        when(contractorRepository.existsById(1)).thenReturn(true);
        doNothing().when(contractorRepository).deleteById(1);

        contractorService.deleteContractorById(1);

        verify(contractorRepository, times(1)).existsById(1);
        verify(contractorRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteContractorById_NotFound() {
        when(contractorRepository.existsById(2)).thenReturn(false);

        assertThrows(ContractorNotFoundException.class, () -> {
            contractorService.deleteContractorById(2);
        });

        verify(contractorRepository, times(1)).existsById(2);
    }

    @Test
    void testDeleteAllContractors() {
        doNothing().when(contractorRepository).deleteAll();

        contractorService.deleteAllContractors();

        verify(contractorRepository, times(1)).deleteAll();
    }

    @Test
    void testSaveContractor() {
        when(contractorRepository.save(any(Contractor.class))).thenReturn(contractor);

        contractorService.save(contractor);

        verify(contractorRepository, times(1)).save(contractor);
    }
}
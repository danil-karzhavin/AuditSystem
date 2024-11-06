package ru.CSApp.restdemo.service.contract.contractWithContractor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.CSApp.restdemo.exception.contract.contractWithContractor.ContractWithContractorNotFoundException;
import ru.CSApp.restdemo.model.contract.Contract;
import ru.CSApp.restdemo.model.contract.ContractType;
import ru.CSApp.restdemo.model.contract.contractWithContractor.ContractWithContractor;
import ru.CSApp.restdemo.model.contract.contractWithContractor.ContractWithContractorDto;
import ru.CSApp.restdemo.model.contractor.Contractor;
import ru.CSApp.restdemo.repository.contractor.IContractWithContractorRepository;
import ru.CSApp.restdemo.service.contract.IContractService;
import ru.CSApp.restdemo.service.contractor.IContractorService;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

class ContractWithContractorServiceTest {

    @Mock
    private IContractWithContractorRepository contractWithContractorRepository;

    @Mock
    private IContractService contractService;

    @Mock
    private IContractorService contractorService;

    @InjectMocks
    private ContractWithContractorService contractWithContractorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetContractWithContractorById_Success() {
        ContractWithContractor contractWithContractor = new ContractWithContractor();
        when(contractWithContractorRepository.findById(any(Integer.class))).thenReturn(Optional.of(contractWithContractor));

        ContractWithContractor result = contractWithContractorService.getContractWithContractorById(1);

        assertNotNull(result);
        verify(contractWithContractorRepository, times(1)).findById(1);
    }

    @Test
    void testGetContractWithContractorById_NotFound() {
        when(contractWithContractorRepository.findById(any(Integer.class))).thenReturn(Optional.empty());

        assertThrows(ContractWithContractorNotFoundException.class, () -> contractWithContractorService.getContractWithContractorById(1));
    }

    @Test
    void testCreateContractWithContractorForContract_Success() {
        ContractWithContractorDto dto = new ContractWithContractorDto();
        dto.setName("Test Contract");
        dto.setType(ContractType.supply);
        dto.setContractId(1);

        Contract contract = new Contract();
        when(contractService.getContractById(any(Integer.class))).thenReturn(contract);

        Contractor contractor = new Contractor();
        when(contractorService.getContractorById(any(Integer.class))).thenReturn(contractor);

        ContractWithContractor createdContractWithContractor = new ContractWithContractor();
        when(contractWithContractorRepository.save(any(ContractWithContractor.class))).thenReturn(createdContractWithContractor);

        ContractWithContractor result = contractWithContractorService.createContractWithContractorForContract(1, dto);

        assertNotNull(result);
        verify(contractWithContractorRepository, times(1)).save(any(ContractWithContractor.class));
    }

    @Test
    void testDeleteContractWithContractorById_Success() {
        when(contractWithContractorRepository.existsById(any(Integer.class))).thenReturn(true);

        contractWithContractorService.deleteContractWithContractorById(1);

        verify(contractWithContractorRepository, times(1)).existsById(1);
        verify(contractWithContractorRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteContractWithContractorById_NotFound() {
        when(contractWithContractorRepository.existsById(any(Integer.class))).thenReturn(false);

        assertThrows(ContractWithContractorNotFoundException.class, () -> contractWithContractorService.deleteContractWithContractorById(1));
        verify(contractWithContractorRepository, never()).deleteById(any(Integer.class));
    }

    @Test
    void testFindByPlanStartDateAfterAndPlanEndDateBefore() {
        LocalDate start = LocalDate.of(2023, 1, 1);
        LocalDate end = LocalDate.of(2023, 12, 31);
        List<ContractWithContractor> expectedList = List.of(new ContractWithContractor());
        when(contractWithContractorRepository.findByPlanStartDateAfterAndPlanEndDateBefore(start, end)).thenReturn(expectedList);

        List<ContractWithContractor> result = contractWithContractorService.findByPlanStartDateAfterAndPlanEndDateBefore(start, end);

        assertEquals(expectedList, result);
        verify(contractWithContractorRepository, times(1)).findByPlanStartDateAfterAndPlanEndDateBefore(start, end);
    }
}

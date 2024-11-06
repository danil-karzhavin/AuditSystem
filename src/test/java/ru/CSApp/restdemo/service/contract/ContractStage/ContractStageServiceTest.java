package ru.CSApp.restdemo.service.contract.ContractStage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.CSApp.restdemo.exception.contract.contractStage.ContractStageNotFoundException;
import ru.CSApp.restdemo.model.contract.Contract;
import ru.CSApp.restdemo.model.contract.contractStage.ContractStage;
import ru.CSApp.restdemo.model.contract.contractStage.ContractStageDto;
import ru.CSApp.restdemo.repository.contract.stage.IContractStageRepository;
import ru.CSApp.restdemo.service.contract.IContractService;
import ru.CSApp.restdemo.service.contract.contractStage.ContractStageService;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ContractStageServiceTest {

    @Mock
    private IContractStageRepository contractStageRepository;

    @Mock
    private IContractService contractService;

    @InjectMocks
    private ContractStageService contractStageService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetContractStageById_ExistingId() {
        // Arrange
        ContractStage stage = new ContractStage();
        stage.setId(1);
        when(contractStageRepository.findById(1)).thenReturn(Optional.of(stage));

        // Act
        ContractStage result = contractStageService.getContractStageById(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    public void testGetContractStageById_NonExistingId() {
        // Arrange
        when(contractStageRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ContractStageNotFoundException.class, () -> contractStageService.getContractStageById(1));
    }

//    @Test
//    public void testCreateContractStageForContract() {
//        // Arrange
//        ContractStageDto stageDto = new ContractStageDto();
//        stageDto.setName("Stage Name");
//
//        Contract contract = new Contract();
//        when(contractService.getContractById(1)).thenReturn(contract);
//
//        // Mock empty lists
//        when(contract.getStages()).thenReturn(new ArrayList<>());
//
//        // Act
//        ContractStage result = contractStageService.createContractStageForContract(1, stageDto);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals("Stage Name", result.getName());
//        verify(contractStageRepository, times(1)).save(any(ContractStage.class));
//        verify(contractService, times(1)).save(any(Contract.class));
//    }

    @Test
    public void testDeleteContractStageById_ExistingId() {
        // Arrange
        when(contractStageRepository.existsById(1)).thenReturn(true);

        // Act
        contractStageService.deleteContractStageById(1);

        // Assert
        verify(contractStageRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteContractStageById_NonExistingId() {
        // Arrange
        when(contractStageRepository.existsById(1)).thenReturn(false);

        // Act & Assert
        assertThrows(ContractStageNotFoundException.class, () -> contractStageService.deleteContractStageById(1));
    }
}
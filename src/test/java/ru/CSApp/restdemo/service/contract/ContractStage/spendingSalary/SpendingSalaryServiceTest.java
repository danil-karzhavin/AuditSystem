package ru.CSApp.restdemo.service.contract.ContractStage.spendingSalary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.CSApp.restdemo.exception.contract.contractStage.spendingSalary.SpendingSalaryNotFoundException;
import ru.CSApp.restdemo.model.contract.contractStage.ContractStage;
import ru.CSApp.restdemo.model.contract.contractStage.spendingSalary.SpendingSalary;
import ru.CSApp.restdemo.model.contract.contractStage.spendingSalary.SpendingSalaryDto;
import ru.CSApp.restdemo.repository.contract.stage.spendingSalary.ISpendingSalaryRepository;
import ru.CSApp.restdemo.service.contract.contractStage.IContractStageService;
import ru.CSApp.restdemo.service.contract.contractStage.spendingSalary.SpendingSalaryService;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SpendingSalaryServiceTest {

    @Mock
    private ISpendingSalaryRepository spendingSalaryRepository;

    @Mock
    private IContractStageService contractStageService;

    @InjectMocks
    private SpendingSalaryService spendingSalaryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetSpendingSalaryByIdShouldReturnSpendingSalary() {
        SpendingSalary mockSpendingSalary = new SpendingSalary();
        mockSpendingSalary.setId(1);

        when(spendingSalaryRepository.findById(1)).thenReturn(Optional.of(mockSpendingSalary));

        SpendingSalary spendingSalary = spendingSalaryService.getSpendingSalaryById(1);
        assertEquals(mockSpendingSalary, spendingSalary);
    }

    @Test
    void testGetSpendingSalaryByIdShouldThrowExceptionWhenNotFound() {
        when(spendingSalaryRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(SpendingSalaryNotFoundException.class, () -> {
            spendingSalaryService.getSpendingSalaryById(1);
        });
    }
    @Test
    void testGetSpendingSalariesByContractStageId() {
        when(spendingSalaryRepository.findByContractStageId(1)).thenReturn(Collections.emptyList());

        assertTrue(spendingSalaryService.getSpendingSalariesByContractStageId(1).isEmpty());
    }
    @Test
    void testCreateSpendingSalaryForContractStage() {
        SpendingSalaryDto dto = new SpendingSalaryDto();
        dto.setName("John");
        dto.setSurname("Doe");
        dto.setMonetaryValue(1000);

        ContractStage contractStage = new ContractStage();
        when(contractStageService.getContractStageById(1)).thenReturn(contractStage);

        SpendingSalary savedSpendingSalary = new SpendingSalary();
        savedSpendingSalary.setId(1);

        when(spendingSalaryRepository.save(any(SpendingSalary.class))).thenReturn(savedSpendingSalary);

        SpendingSalary result = spendingSalaryService.createSpendingSalaryForContractStage(1, dto);

        assertNotNull(result);
        assertEquals("John", result.getName());
    }
    @Test
    void testUpdateSpendingSalary() {
        SpendingSalary existingSpendingSalary = new SpendingSalary();
        existingSpendingSalary.setId(1);
        existingSpendingSalary.setName("John");

        SpendingSalaryDto dto = new SpendingSalaryDto();
        dto.setId(1);
        dto.setName("UpdatedName");

        when(spendingSalaryRepository.findById(1)).thenReturn(Optional.of(existingSpendingSalary));
        when(spendingSalaryRepository.save(any(SpendingSalary.class))).thenReturn(existingSpendingSalary);

        SpendingSalary updatedSpendingSalary = spendingSalaryService.updateSpendingSalary(dto);

        assertEquals("UpdatedName", updatedSpendingSalary.getName());
    }
    @Test
    void testDeleteSpendingSalaryById() {
        when(spendingSalaryRepository.existsById(1)).thenReturn(true);

        doNothing().when(spendingSalaryRepository).deleteById(1);

        assertDoesNotThrow(() -> {
            spendingSalaryService.deleteSpendingSalaryById(1);
        });
    }

    @Test
    void testDeleteSpendingSalaryByIdShouldThrowExceptionWhenNotFound() {
        when(spendingSalaryRepository.existsById(1)).thenReturn(false);

        assertThrows(SpendingSalaryNotFoundException.class, () -> {
            spendingSalaryService.deleteSpendingSalaryById(1);
        });
    }
}

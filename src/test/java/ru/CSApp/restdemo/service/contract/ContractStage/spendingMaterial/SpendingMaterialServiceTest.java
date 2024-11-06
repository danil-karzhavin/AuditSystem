package ru.CSApp.restdemo.service.contract.ContractStage.spendingMaterial;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.CSApp.restdemo.exception.contract.contractStage.spendingMaterial.SpendingMaterialNotFoundException;
import ru.CSApp.restdemo.model.contract.contractStage.ContractStage;
import ru.CSApp.restdemo.model.contract.contractStage.spendingMaterial.SpendingMaterial;
import ru.CSApp.restdemo.model.contract.contractStage.spendingMaterial.SpendingMaterialDto;
import ru.CSApp.restdemo.repository.contract.stage.spendingMaterial.ISpendingMaterialRepository;
import ru.CSApp.restdemo.service.contract.contractStage.IContractStageService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SpendingMaterialServiceTest {

    @Mock
    private ISpendingMaterialRepository spendingMaterialRepository;

    @Mock
    private IContractStageService contractStageService;

    @InjectMocks
    private ru.CSApp.restdemo.service.contract.contractStage.spendingMaterial.SpendingMaterialService spendingMaterialService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetSpendingMaterialById_Success() {
        SpendingMaterial spendingMaterial = new SpendingMaterial();
        spendingMaterial.setId(1);
        when(spendingMaterialRepository.findById(1)).thenReturn(Optional.of(spendingMaterial));

        SpendingMaterial found = spendingMaterialService.getSpendingMaterialById(1);

        assertNotNull(found);
        assertEquals(1, found.getId());
    }

    @Test
    void testGetSpendingMaterialById_NotFound() {
        when(spendingMaterialRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(SpendingMaterialNotFoundException.class, () -> {
            spendingMaterialService.getSpendingMaterialById(1);
        });
    }

    @Test
    void testCreateSpendingMaterialForContractStage_Success() {
        SpendingMaterialDto dto = new SpendingMaterialDto();
        dto.setName("Material A");
        dto.setMonetaryValue(100);

        ContractStage contractStage = new ContractStage();
        contractStage.setId(1);
        when(contractStageService.getContractStageById(1)).thenReturn(contractStage);

        SpendingMaterial created = spendingMaterialService.createSpendingMaterialForContractStage(1, dto);

        assertNotNull(created);
        assertEquals("Material A", created.getName());
        verify(spendingMaterialRepository, times(1)).save(any(SpendingMaterial.class));
        verify(contractStageService, times(1)).save(contractStage);
    }

    @Test
    void testUpdateSpendingMaterial_Success() {
        SpendingMaterialDto dto = new SpendingMaterialDto();
        dto.setId(1);
        dto.setName("Updated Material");

        SpendingMaterial existingMaterial = new SpendingMaterial();
        existingMaterial.setId(1);
        when(spendingMaterialRepository.findById(1)).thenReturn(Optional.of(existingMaterial));

        SpendingMaterial updated = spendingMaterialService.updateSpendingMaterial(dto);

        assertNotNull(updated);
        assertEquals("Updated Material", updated.getName());
        verify(spendingMaterialRepository, times(1)).save(existingMaterial);
    }

    @Test
    void testDeleteSpendingMaterialById_Success() {
        when(spendingMaterialRepository.existsById(1)).thenReturn(true);

        spendingMaterialService.deleteSpendingMaterialById(1);

        verify(spendingMaterialRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteSpendingMaterialById_NotFound() {
        when(spendingMaterialRepository.existsById(1)).thenReturn(false);

        assertThrows(SpendingMaterialNotFoundException.class, () -> {
            spendingMaterialService.deleteSpendingMaterialById(1);
        });
    }
}
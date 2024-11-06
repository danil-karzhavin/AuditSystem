package ru.CSApp.restdemo.service.contract;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.CSApp.restdemo.model.contract.Contract;
import ru.CSApp.restdemo.model.contract.ContractDto;
import ru.CSApp.restdemo.model.contract.ContractType;
import ru.CSApp.restdemo.repository.contract.IContractRepository;
import ru.CSApp.restdemo.exception.contract.ContractNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ContractServiceTest {

    @Mock
    private IContractRepository contractRepository;

    @InjectMocks
    private ContractService contractService;

    private ContractDto contractDto;
    private Contract contract;

    @BeforeEach
    void setUp() {
        contractDto = new ContractDto();
        contractDto.setId(1);
        contractDto.setName("Test Contract");
        contractDto.setMonetaryValue(1000);
        contractDto.setType(ContractType.purchase);
        contractDto.setPlanStartDate(LocalDate.of(2000, 06, 06));
        contractDto.setPlanEndDate(LocalDate.of(2030, 06, 06));
        contractDto.setActualStartDate(LocalDate.of(2000, 06, 06));
        contractDto.setActualEndDate(LocalDate.of(2030, 06, 06));

        contract = new Contract();
        contract.setId(1);
        contract.setName("Test Contract");
        contract.setMonetaryValue(1000);
        contract.setType("purchase");
        contract.setPlanStartDate(LocalDate.of(2000, 06, 06));
        contract.setPlanEndDate(LocalDate.of(2030, 06, 06));
        contract.setActualStartDate(LocalDate.of(2000, 06, 06));
        contract.setActualEndDate(LocalDate.of(2030, 06, 06));
    }

    @Test
    void testCreateContract() {
        when(contractRepository.save(any(Contract.class))).thenReturn(contract);

        Contract createdContract = contractService.createContract(contractDto);

        assertThat(createdContract.getName()).isEqualTo(contractDto.getName());
        assertThat(createdContract.getMonetaryValue()).isEqualTo(contractDto.getMonetaryValue());
        verify(contractRepository).save(any(Contract.class));
    }

    @Test
    void testUpdateContract_notFound() {
        when(contractRepository.findById(contractDto.getId())).thenReturn(Optional.empty());

        assertThrows(ContractNotFoundException.class, () -> contractService.updateContract(contractDto));
    }

    @Test
    void testGetContractById() {
        when(contractRepository.findById(anyInt())).thenReturn(Optional.of(contract));

        Contract foundContract = contractService.getContractById(1);

        assertThat(foundContract.getName()).isEqualTo("Test Contract");
    }

    @Test
    void testGetContractById_notFound() {
        when(contractRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(ContractNotFoundException.class, () -> contractService.getContractById(1));
    }

    @Test
    void testGetAllContracts() {
        when(contractRepository.findAll()).thenReturn(List.of(contract));

        List<Contract> contracts = contractService.getAllContracts();

        assertThat(contracts).hasSize(1);
    }

    @Test
    void testGetContractByName() {
        when(contractRepository.findByName("Test Contract")).thenReturn(List.of(contract));

        List<Contract> contracts = contractService.getContractByName(Map.of("name", "Test Contract"));

        assertThat(contracts).hasSize(1);
    }

    @Test
    void testDeleteContractById() {
        when(contractRepository.existsById(anyInt())).thenReturn(true);
        doNothing().when(contractRepository).deleteById(anyInt());

        contractService.deleteContractById(1);

        verify(contractRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteContractById_notFound() {
        when(contractRepository.existsById(anyInt())).thenReturn(false);

        assertThrows(ContractNotFoundException.class, () -> contractService.deleteContractById(1));
    }

    @Test
    void testFindByPlanStartDateAfterAndPlanEndDateBefore() {
        LocalDate start = LocalDate.now().minusDays(10);
        LocalDate end = LocalDate.now().plusDays(10);

        when(contractRepository.findByPlanStartDateAfterAndPlanEndDateBefore(start, end))
                .thenReturn(List.of(contract));

        List<Contract> contracts = contractService.findByPlanStartDateAfterAndPlanEndDateBefore(start, end);

        assertThat(contracts).hasSize(1);
    }
}
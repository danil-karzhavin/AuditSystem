package ru.CSApp.restdemo.repository.stage;

import ru.CSApp.restdemo.model.ContractStage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractStageRepository extends JpaRepository<ContractStage, Integer> {
    List<ContractStage> findByContractId(Integer contractId);
}


package ru.CSApp.restdemo.repository.contract.stage.spendingMaterial;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.CSApp.restdemo.model.contract.contractStage.spendingMaterial.SpendingMaterial;

import java.util.List;

public interface ISpendingMaterialRepository extends JpaRepository<SpendingMaterial, Integer> {
    List<SpendingMaterial> findByContractStageId(Integer contractStageId);
}

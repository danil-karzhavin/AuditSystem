package ru.CSApp.restdemo.repository.contract.stage.spendingMaterial;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.CSApp.restdemo.model.SpendingMaterial;

import java.util.List;

public interface SpendingMaterialRepository extends JpaRepository<SpendingMaterial, Integer> {
    List<SpendingMaterial> findByContractStageId(Integer contractStageId);
}

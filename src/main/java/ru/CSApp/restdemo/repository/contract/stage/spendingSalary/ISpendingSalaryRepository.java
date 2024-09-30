package ru.CSApp.restdemo.repository.contract.stage.spendingSalary;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.CSApp.restdemo.model.SpendingSalary;

import java.util.List;

public interface ISpendingSalaryRepository extends JpaRepository<SpendingSalary, Integer> {
    List<SpendingSalary> findByContractStageId(Integer contractStageId);
}

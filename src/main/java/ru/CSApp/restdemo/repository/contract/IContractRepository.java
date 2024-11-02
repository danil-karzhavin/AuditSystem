package ru.CSApp.restdemo.repository.contract;

import ru.CSApp.restdemo.model.contract.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface IContractRepository extends JpaRepository<Contract, Integer> {
    List<Contract> findByName(String name);
    List<Contract> findByPlanStartDateAfterAndPlanEndDateBefore(LocalDate startDate, LocalDate endDate);
}
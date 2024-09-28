package ru.CSApp.restdemo.repository.contract;

import ru.CSApp.restdemo.model.ContractWithContractor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractWithContractorRepository extends JpaRepository<ContractWithContractor, Integer> {

}
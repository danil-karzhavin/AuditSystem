package ru.CSApp.restdemo.repository.contractor;

import ru.CSApp.restdemo.model.ContractWithContractor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IContractWithContractorRepository extends JpaRepository<ContractWithContractor, Integer> {
    List<ContractWithContractor> findByContractId(Integer contractId);
}
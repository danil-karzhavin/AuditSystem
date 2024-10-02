package ru.CSApp.restdemo.repository.contract;

import ru.CSApp.restdemo.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContractRepository extends JpaRepository<Contract, Integer> {
    Contract findByName(String name);
}
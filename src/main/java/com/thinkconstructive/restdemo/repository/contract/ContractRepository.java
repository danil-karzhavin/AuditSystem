package com.thinkconstructive.restdemo.repository.contract;

import com.thinkconstructive.restdemo.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Integer> {
    Contract findByName(String name);
}
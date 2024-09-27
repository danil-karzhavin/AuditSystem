package com.thinkconstructive.restdemo.repository.contract;

import com.thinkconstructive.restdemo.model.ContractWithContractor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractWithContractorRepository extends JpaRepository<ContractWithContractor, Integer> {

}
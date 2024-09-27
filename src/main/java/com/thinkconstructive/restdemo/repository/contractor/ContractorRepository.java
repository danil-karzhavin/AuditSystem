package com.thinkconstructive.restdemo.repository.contractor;

import com.thinkconstructive.restdemo.model.Contractor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractorRepository extends JpaRepository<Contractor, Integer> {
}


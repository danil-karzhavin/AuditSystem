package com.thinkconstructive.restdemo.repository.stage;

import com.thinkconstructive.restdemo.model.SpendingSalary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpendingSalaryRepository extends JpaRepository<SpendingSalary, Integer> {
}

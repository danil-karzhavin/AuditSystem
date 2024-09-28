package ru.CSApp.restdemo.repository.stage;

import ru.CSApp.restdemo.model.SpendingSalary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpendingSalaryRepository extends JpaRepository<SpendingSalary, Integer> {
}

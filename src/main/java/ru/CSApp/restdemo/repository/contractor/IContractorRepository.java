package ru.CSApp.restdemo.repository.contractor;

import ru.CSApp.restdemo.model.Contractor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContractorRepository extends JpaRepository<Contractor, Integer> {
}


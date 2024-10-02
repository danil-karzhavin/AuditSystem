package ru.CSApp.restdemo.repository.contractor.inn;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.CSApp.restdemo.model.INN;

public interface IInnRepository extends JpaRepository<INN, String> {
}

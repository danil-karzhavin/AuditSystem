package ru.CSApp.restdemo.repository.stage;

import ru.CSApp.restdemo.model.SpendingMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpendingMaterialRepository extends JpaRepository<SpendingMaterial, Integer> {
}
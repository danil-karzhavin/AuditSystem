package com.thinkconstructive.restdemo.repository.stage;

import com.thinkconstructive.restdemo.model.SpendingMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpendingMaterialRepository extends JpaRepository<SpendingMaterial, Integer> {
}
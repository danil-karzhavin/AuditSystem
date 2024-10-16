package ru.CSApp.restdemo.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

public interface IContractable {
    public Integer getId();
    public String getName();
    public String getType();
    LocalDate getPlanStartDate();
    public LocalDate getPlanEndDate();
    public LocalDate getActualStartDate();
    public LocalDate getActualEndDate();
    public Integer getMonetaryValue();
}

package ru.CSApp.restdemo.model.contract;

import java.time.LocalDate;

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

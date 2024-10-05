package ru.CSApp.restdemo.service.contractor.inn;

import ru.CSApp.restdemo.model.Inn;

import java.util.List;

public interface IInnService {
    public List<Inn> getAllInns();
    public Inn getInnByContractId(Integer contractId);

    public Inn createInnForContractor(Integer contractorId, Inn inn);
    public Inn updateInn(Inn inn);

    public Integer deleteInnByContractorId(Integer contractorId);
}

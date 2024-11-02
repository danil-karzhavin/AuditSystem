//package ru.CSApp.restdemo.service.contractor.inn;
//
//import org.springframework.stereotype.Service;
//import ru.CSApp.restdemo.model.Contractor.Contractor;
//import ru.CSApp.restdemo.model.Inn;
//import ru.CSApp.restdemo.repository.contractor.IContractorRepository;
//import ru.CSApp.restdemo.repository.contractor.inn.IInnRepository;
//
//import java.util.List;
//@Service
//public class InnService implements IInnService{
//    IInnRepository innRepository;
//    IContractorRepository contractorRepository;
//
//    public InnService(IInnRepository innRepository) {
//        this.innRepository = innRepository;
//    }
//
//    @Override
//    public List<Inn> getAllInns() {
//        List<Inn> inns = innRepository.findAll();
//        return inns;
//    }
//
//    @Override
//    public Inn getInnByContractId(Integer contractorId) {
//        Inn inn = innRepository.findByContractorId(contractorId);
//        return inn;
//    }
//
//    @Override
//    public Inn createInnForContractor(Integer contractorId, Inn inn) {
//        Contractor contractor = contractorRepository.findById(contractorId).get();
//
//        inn.setContractor(contractor);
//        inn.setContractorId(contractorId);
//
//        contractor.setInn(inn);
//
//        contractorRepository.save(contractor);
//        return inn;
//    }
//
//    @Override
//    public Inn updateInn(Inn inn) {
//        innRepository.save(inn);
//        return inn;
//    }
//
//    @Override
//    public Integer deleteInnByContractorId(Integer contractorId) {
//        // здесь проблема
//        innRepository.deleteByContractorId(contractorId);
//        return contractorId;
//    }
//}

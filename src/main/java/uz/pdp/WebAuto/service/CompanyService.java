package uz.pdp.WebAuto.service;

import uz.pdp.WebAuto.dtos.company.CompanyDTO;
import uz.pdp.WebAuto.dtos.service.CompanyRequestDTO;
import uz.pdp.WebAuto.entity.Company;

import java.util.Optional;

public interface CompanyService {

    CompanyDTO save(CompanyRequestDTO dto);

    CompanyDTO save(Company dto);

    Optional<Company> findById(Long id);

    Company findByOwnerId(Long user);

}

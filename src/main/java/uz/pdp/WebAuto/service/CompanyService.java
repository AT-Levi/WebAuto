package uz.pdp.WebAuto.service;

import org.springframework.web.multipart.MultipartFile;
import uz.pdp.WebAuto.dtos.company.CompanyDataDTO;
import uz.pdp.WebAuto.dtos.company.CompanyResponseDTO;
import uz.pdp.WebAuto.dtos.company.CreateCompanyDTO;
import uz.pdp.WebAuto.dtos.image.ImageResponseDTO;
import uz.pdp.WebAuto.entity.Company;

import java.util.List;

public interface CompanyService {

    CompanyDataDTO save(CreateCompanyDTO dto);

    Company save(Company company);

    CompanyDataDTO update(CompanyDataDTO company);

    Company findById(Long id);

    Company update(Company company);

    Company findByName(String companyName);

//    CompanyDataDTO refreshCompanyLogo(Long companyId, MultipartFile logo);

    void deleteById(Long companyId);

    List<CompanyResponseDTO> findAll();

    CompanyResponseDTO saveLogo(MultipartFile logo);
}

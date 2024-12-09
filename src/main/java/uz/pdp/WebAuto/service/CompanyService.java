package uz.pdp.WebAuto.service;

import org.springframework.web.multipart.MultipartFile;
import uz.pdp.WebAuto.dtos.company.CompanyDataDTO;
import uz.pdp.WebAuto.dtos.company.CompanyRequestDTO;
import uz.pdp.WebAuto.dtos.company.CompanyResponseDTO;
import uz.pdp.WebAuto.entity.Company;

public interface CompanyService {

    CompanyDataDTO save(CompanyRequestDTO dto);

    CompanyDataDTO save(Company company);
    
    CompanyDataDTO update(CompanyDataDTO company);

    Company findById(Long id);

    Company findByOwnerId(Long ownerId);

    CompanyDataDTO refreshCompanyLogo(Long companyId, MultipartFile logo);
}

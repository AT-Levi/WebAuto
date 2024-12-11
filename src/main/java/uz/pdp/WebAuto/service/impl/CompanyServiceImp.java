package uz.pdp.WebAuto.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.WebAuto.config.service.CurrentUser;
import uz.pdp.WebAuto.config.service.StorageService;
import uz.pdp.WebAuto.dtos.address.AddressDTO;
import uz.pdp.WebAuto.dtos.company.CompanyDataDTO;
import uz.pdp.WebAuto.dtos.company.CompanyResponseDTO;
import uz.pdp.WebAuto.dtos.company.CreateCompanyDTO;
import uz.pdp.WebAuto.entity.Company;
import uz.pdp.WebAuto.entity.Image;
import uz.pdp.WebAuto.exception.NotFoundException;
import uz.pdp.WebAuto.mapper.AddressMapper;
import uz.pdp.WebAuto.mapper.CompanyMapper;
import uz.pdp.WebAuto.mapper.CompanyResMapper;
import uz.pdp.WebAuto.repository.CompanyRepository;
import uz.pdp.WebAuto.service.AddressService;
import uz.pdp.WebAuto.service.CompanyService;
import uz.pdp.WebAuto.service.ImageService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImp implements CompanyService {

    private final AddressService addressServiceImp;
    private final ImageService imageServiceImp;
    private final AddressMapper addressMapper;
    private final CurrentUser currentUser;
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final CompanyResMapper companyResMapper;

    @Override
    public CompanyDataDTO save(CreateCompanyDTO dto) {
        AddressDTO addressDTO = addressServiceImp.save(dto.getAddress());
        Image logo = imageServiceImp.save(dto.getLogo());

        Company company = Company.builder()
                .owner(currentUser.getCurrentUser())
                .email(dto.getEmail())
                .logo(logo)
                .name(dto.getName())
                .legalName(dto.getLegalName())
                .address(addressMapper.toEntity(addressDTO))
                .name(dto.getName())
                .phone(dto.getPhone())
                .description(dto.getDescription())
                .build();

        Company savedCompany = companyRepository.save(company);
        return companyMapper.toDto(savedCompany);
    }

    @Override
    public Company findById(Long id) {
        return companyRepository.findById(id).orElseThrow(() -> new NotFoundException("Company "));
    }

    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public CompanyDataDTO update(CompanyDataDTO companyDto) {
        Company company = findById(companyDto.getId());
        Company updatedCompany = companyRepository.save(company);
        return companyMapper.toDto(updatedCompany);
    }

    @Override
    public Company update(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company findByName(String companyName) {
        return companyRepository.findByName(companyName);
    }

    @Override
    public void deleteById(Long companyId) {
        companyRepository.deleteCompanyById(companyId);
    }

    @Override
    public List<CompanyResponseDTO> findAll() {
        List<Company> all = companyRepository.findAll();
        return companyResMapper.toDto(all);
    }

    @Override
    public CompanyResponseDTO saveLogo(MultipartFile logo) {
        Company company = companyRepository.findByOwnerId(currentUser.getCurrentUser().getId());
        Image savedLogo = imageServiceImp.save(logo);
        company.setLogo(savedLogo);
        return companyResMapper.toDto(save(company));
    }
}

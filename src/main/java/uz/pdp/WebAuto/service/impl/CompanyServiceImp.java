package uz.pdp.WebAuto.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.WebAuto.config.CurrentUser;
import uz.pdp.WebAuto.config.service.StorageService;
import uz.pdp.WebAuto.dtos.address.AddressDTO;
import uz.pdp.WebAuto.dtos.company.CompanyDataDTO;
import uz.pdp.WebAuto.dtos.company.CompanyRequestDTO;
import uz.pdp.WebAuto.dtos.company.CompanyResponseDTO;
import uz.pdp.WebAuto.entity.Company;
import uz.pdp.WebAuto.entity.Image;
import uz.pdp.WebAuto.exception.NotFoundException;
import uz.pdp.WebAuto.mapper.AddressMapper;
import uz.pdp.WebAuto.mapper.CompanyMapper;
import uz.pdp.WebAuto.repository.CompanyRepository;
import uz.pdp.WebAuto.service.AddressService;
import uz.pdp.WebAuto.service.CompanyService;
import uz.pdp.WebAuto.service.ImageService;

@Service
@RequiredArgsConstructor
public class CompanyServiceImp implements CompanyService {

    private final AddressService addressServiceImp;
    private final ImageService imageServiceImp;
    private final AddressMapper addressMapper;
    private final CurrentUser currentUser;
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final StorageService storageService;

    @Override
    public CompanyDataDTO save(CompanyRequestDTO dto) {

        AddressDTO addressDTO = addressServiceImp.save(dto.getAddress());
        Image image = saveImage(dto.getImage());

        Company company = Company.builder()
                .owner(currentUser.getCurrentUser())
                .email(dto.getEmail())
                .logo(image)
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

    @Override
    public Company findByOwnerId(Long ownerId) {
        return companyRepository.findByOwnerId(ownerId);
    }

    @Override
    public CompanyDataDTO refreshCompanyLogo(Long companyId, MultipartFile logo) {
        Company company = findById(companyId);
        Image image = imageServiceImp.findByCompanyId(companyId);
        storageService.deleteFile(image.getFileName());
        imageServiceImp.deleteById(image.getId());
        Image newImage = saveImage(logo);
        company.setLogo(newImage);
        Company update = update(company);
        return companyMapper.toDto(update);
    }

    private Image saveImage(MultipartFile logo) {
        return imageServiceImp.save(logo);
    }

    public CompanyDataDTO save(Company company) {
        return companyMapper.toDto(companyRepository.save(company));
    }

    @Override
    public CompanyDataDTO update(CompanyDataDTO companyDto) {
        Company company = findById(companyDto.getId());
        Company updatedCompany = companyRepository.save(company);
        return companyMapper.toDto(updatedCompany);
    }

    public Company update(Company company) {
        return null;
    }
}

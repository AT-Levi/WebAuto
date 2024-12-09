package uz.pdp.WebAuto.service.impl;

import lombok.var;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.WebAuto.config.service.StorageService;
import uz.pdp.WebAuto.dtos.company.CompanyDTO;
import uz.pdp.WebAuto.dtos.service.CompanyRequestDTO;
import uz.pdp.WebAuto.entity.Company;
import uz.pdp.WebAuto.entity.Image;
import uz.pdp.WebAuto.service.AddressService;
import uz.pdp.WebAuto.service.CompanyService;
import uz.pdp.WebAuto.service.ImageService;

import java.util.Optional;

@Service
public class CompanyServiceImp implements CompanyService {

    private static AddressService addressServiceImp;
    private static ImageService imageServiceImp;
    private final StorageService storageService;

    public CompanyServiceImp(StorageService storageService) {
        this.storageService = storageService;
    }

    @Override
    public CompanyDTO save(CompanyRequestDTO dto) {

        var address = addressServiceImp.save(dto.getAddress());

        Image image = imageServiceImp.save(dto.getImage());



        Company.builder()
                .email()
                .address()
                .name()
                .description()
                .owner()

                .logo(dto.)
                .
        return null;
    }

    @Override
    public CompanyDTO save(Company dto) {
        return null;
    }

    @Override
    public Optional<Company> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Company findByOwnerId(Long user) {
        return null;
    }
}

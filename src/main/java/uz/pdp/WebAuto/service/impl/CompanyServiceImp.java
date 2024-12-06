package uz.pdp.WebAuto.service.impl;

import org.springframework.stereotype.Service;
import uz.pdp.WebAuto.dtos.company.CompanyDTO;
import uz.pdp.WebAuto.dtos.service.CompanyRequestDTO;
import uz.pdp.WebAuto.entity.Company;
import uz.pdp.WebAuto.service.CompanyService;

import java.util.Optional;

@Service
public class CompanyServiceImp implements CompanyService {
    @Override
    public CompanyDTO save(CompanyRequestDTO dto) {
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

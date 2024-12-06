package uz.pdp.WebAuto.service.impl;

import uz.pdp.WebAuto.dtos.BrandDTO;
import uz.pdp.WebAuto.entity.Brand;
import uz.pdp.WebAuto.repository.BrandRepository;
import org.springframework.stereotype.Service;
import uz.pdp.WebAuto.service.BrandService;

import java.util.List;
import java.util.Optional;

import static uz.pdp.WebAuto.mapper.BrandMapper.BRAND_MAPPER;

@Service
public class BrandServiceImp implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImp(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public Brand getByID(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }


    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public Optional<Brand> getBrandById(Long id) {
        return brandRepository.findById(id);
    }

    @Override
    public void update(BrandDTO brandDTO) {
        Brand searchedBrand = findById(brandDTO.id())
                .map(brand -> {
                    brand.setName(brandDTO.name());
                    brand.setDescription(brandDTO.description());
                    brand.setLogoUrl(brandDTO.logoUrl());
                    brand.setWebsiteUrl(brandDTO.websiteUrl());
                    brand.setOwner(brandDTO.owner());
                    brand.setCountry(brandDTO.country());
                    brand.setCreatedDate(brandDTO.createdDate());
                    brand.setIcon(brandDTO.icon());
                    return brandRepository.save(brand);
                })
                .orElseThrow(() -> new RuntimeException("Brand not found"));
    }

    @Override
    public void delete(Long id) {
        brandRepository.deleteById(id);
    }

    public Brand save(Brand entity) {
        return brandRepository.save(entity);
    }

    @Override
    public Optional<Brand> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public BrandDTO save(BrandDTO dto) {
        Brand brand = BRAND_MAPPER.toEntity(dto);
        return BRAND_MAPPER.toDto(brandRepository.save(brand));
    }
}

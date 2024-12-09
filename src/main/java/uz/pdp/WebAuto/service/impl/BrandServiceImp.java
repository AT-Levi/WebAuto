package uz.pdp.WebAuto.service.impl;

import org.springframework.stereotype.Service;
import uz.pdp.WebAuto.dtos.BrandDTO;
import uz.pdp.WebAuto.entity.Brand;
import uz.pdp.WebAuto.repository.BrandRepository;
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

    @Override
    public Optional<Brand> findById(Long id) {
        return brandRepository.findById(id);
    }

    public Optional<Brand> save(Optional<Brand> brandDTO) {
        Brand brand = (Brand) BRAND_MAPPER.toEntity(brandDTO);
        Brand savedBrand = brandRepository.save(brand);
        return BRAND_MAPPER.toDto(savedBrand);
    }

    @Override
    public Brand save(Brand brand) {
        return null;
    }

    @Override
    public void update(BrandDTO brandDTO) {
        Brand brand = findById(brandDTO.id())
                .orElseThrow(() -> new RuntimeException("Brand not found with id: " + brandDTO.id()));

        brand.setName(brandDTO.name());
        brand.setDescription(brandDTO.description());
        brand.setLogoUrl(brandDTO.logoUrl());
        brand.setWebsiteUrl(brandDTO.websiteUrl());
        brand.setOwner(brandDTO.owner());
        brand.setCountry(brandDTO.country());
        brand.setCreatedDate(brandDTO.createdDate());
        brand.setIcon(brandDTO.icon());

        brandRepository.save(brand);
    }

    @Override
    public void delete(Long id) {
        if (!brandRepository.existsById(id)) {
            throw new RuntimeException("Brand not found with id: " + id);
        }
        brandRepository.deleteById(id);
    }


    public List<BrandDTO> getAllBrands() {
        return brandRepository.findAll().stream()
                .map(BRAND_MAPPER::toDto)
                .toList();
    }


    public Optional<Brand> getById(Long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found with id: " + id));
        return BRAND_MAPPER.toDto(brand);
    }


    public Optional<Brand> getByName(String name) {
        Object brand = brandRepository.findByName(name)
               .orElseThrow(() -> new RuntimeException("Brand not found with name: " + name));
        return BRAND_MAPPER.toDto((Brand) brand);
    }
}

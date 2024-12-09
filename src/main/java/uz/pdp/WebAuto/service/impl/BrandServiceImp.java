package uz.pdp.WebAuto.service.impl;

import lombok.RequiredArgsConstructor;
import uz.pdp.WebAuto.dtos.brand.BrandDTO;
import uz.pdp.WebAuto.dtos.brand.BrandRequestDTO;
import uz.pdp.WebAuto.entity.Brand;
import uz.pdp.WebAuto.entity.Image;
import uz.pdp.WebAuto.exception.NotFoundException;
import uz.pdp.WebAuto.mapper.BrandMapper;
import uz.pdp.WebAuto.repository.BrandRepository;
import org.springframework.stereotype.Service;
import uz.pdp.WebAuto.service.BrandService;
import uz.pdp.WebAuto.service.ImageService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandServiceImp implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;
    private final ImageService imageServiceImp;

    @Override
    public List<BrandDTO> getAllBrands() {
        return brandMapper.toDto(brandRepository.findAll());
    }

    public Optional<Brand> getBrandById(Long id) {
        return brandRepository.findById(id);
    }

    @Override
    public BrandDTO update(BrandDTO brandDTO) {
        return null;
    }

    @Override
    public void delete(Long id) {
        brandRepository.deleteByBrandId(id);
    }

    public Brand save(Brand entity) {
        return brandRepository.save(entity);
    }

    @Override
    public Brand findById(Long id) {
        return brandRepository.findById(id).orElseThrow(() -> new NotFoundException("Brand "));
    }

    @Override
    public BrandDTO save(BrandRequestDTO dto) {
        Image icon = imageServiceImp.save(dto.getIcon());

        Brand brand = brandRepository.save(Brand.builder()
                .name(dto.getName())
                .createdDate(dto.getCreatedDate())
                .icon(icon)
                .description(dto.getDescription())
                .websiteUrl(dto.getWebsiteUrl())
                .country(dto.getCountry())
                .build());

        return brandMapper.toDto(brand);
    }
}

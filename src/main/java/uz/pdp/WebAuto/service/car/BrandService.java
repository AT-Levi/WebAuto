package uz.pdp.WebAuto.service.car;

import uz.pdp.WebAuto.entity.car.Brand;
import uz.pdp.WebAuto.repository.car.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public Brand getByID(Long id){
        return brandRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }


    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public Optional<Brand> getBrandById(Long id) {
        return brandRepository.findById(id);
    }

    public Brand createBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    public Brand updateBrand(Long id, Brand brandDetails) {
        return brandRepository.findById(id)
                .map(brand -> {
                    brand.setName(brandDetails.getName());
                    brand.setDescription(brandDetails.getDescription());
                    brand.setLogoUrl(brandDetails.getLogoUrl());
                    brand.setWebsiteUrl(brandDetails.getWebsiteUrl());
                    brand.setOwner(brandDetails.getOwner());
                    brand.setCountry(brandDetails.getCountry());
                    brand.setCreatedDate(brandDetails.getCreatedDate());
                    brand.setIcon(brandDetails.getIcon());
                    return brandRepository.save(brand);
                })
                .orElseThrow(() -> new RuntimeException("Brand not found"));
    }

    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }

}

package uz.pdp.WebAuto.services;

import uz.pdp.WebAuto.entity.car.Brand;
import uz.pdp.WebAuto.repository.BrandRepository;
import org.springframework.stereotype.Service;

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
}

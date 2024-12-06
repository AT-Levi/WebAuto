package uz.pdp.WebAuto.service;

import uz.pdp.WebAuto.dtos.BrandDTO;
import uz.pdp.WebAuto.entity.Brand;

import java.util.Optional;

public interface BrandService {

    Optional<Brand> findById(Long id);

    void update(BrandDTO updatedBrand);

    BrandDTO save(BrandDTO brandDTO);

    Brand save(Brand brand);

    void delete(Long id);

}

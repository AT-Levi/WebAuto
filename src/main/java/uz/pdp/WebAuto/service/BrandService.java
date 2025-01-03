package uz.pdp.WebAuto.service;

import uz.pdp.WebAuto.dtos.brand.BrandDTO;
import uz.pdp.WebAuto.dtos.brand.BrandRequestDTO;
import uz.pdp.WebAuto.entity.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandService {

    Brand findById(Long id);

    BrandDTO update(BrandDTO updatedBrand);

    BrandDTO save(BrandRequestDTO brandRequestDTO);

    Brand save(Brand brand);

    void delete(Long id);

    List<BrandDTO> getAllBrands();

}

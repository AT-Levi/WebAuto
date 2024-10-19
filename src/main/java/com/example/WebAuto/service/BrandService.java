package com.example.WebAuto.service;

import com.example.WebAuto.entity.car.Brand;
import com.example.WebAuto.repository.BrandRepository;
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

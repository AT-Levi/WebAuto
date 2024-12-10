package uz.pdp.WebAuto.mapper;

import org.mapstruct.Mapper;
import uz.pdp.WebAuto.dtos.brand.BrandDTO;
import uz.pdp.WebAuto.entity.Brand;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface BrandMapper extends EntityMapper<BrandDTO, Brand> {

    @Override
    Brand toEntity(BrandDTO dto);

    @Override
    BrandDTO toDto(Brand entity);

    @Override
    List<Brand> toEntity(List<BrandDTO> list);

    @Override
    List<BrandDTO> toDto(List<Brand> list);


}


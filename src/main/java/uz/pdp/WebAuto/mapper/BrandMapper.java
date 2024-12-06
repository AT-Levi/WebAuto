package uz.pdp.WebAuto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uz.pdp.WebAuto.dtos.BrandDTO;
import uz.pdp.WebAuto.entity.Brand;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandMapper extends EntityMapper<BrandDTO, Brand> {

    BrandMapper BRAND_MAPPER = Mappers.getMapper(BrandMapper.class);

    @Override
    Brand toEntity(BrandDTO dto);

    @Override
    BrandDTO toDto(Brand entity);

    @Override
    List<Brand> toEntity(List<BrandDTO> list);

    @Override
    List<BrandDTO> toDto(List<Brand> list);
}

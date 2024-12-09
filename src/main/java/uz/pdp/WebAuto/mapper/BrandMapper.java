package uz.pdp.WebAuto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uz.pdp.WebAuto.dtos.BrandDTO;
import uz.pdp.WebAuto.entity.Brand;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface BrandMapper extends EntityMapper<Optional<Brand>, Brand> {

    BrandMapper BRAND_MAPPER = Mappers.getMapper(BrandMapper.class);

    @Override
    Brand toEntity(Optional<Brand> dto);

    @Override
    Optional<Brand> toDto(Brand entity);

    List<Brand> toEntity(BrandDTO list);

    @Override
    List<Optional<Brand>> toDto(List<Brand> list);



}

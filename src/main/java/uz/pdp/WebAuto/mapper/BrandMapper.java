package uz.pdp.WebAuto.mapper;

import org.mapstruct.Mapper;
import uz.pdp.WebAuto.dtos.brand.BrandDTO;
import uz.pdp.WebAuto.entity.Brand;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface BrandMapper extends EntityMapper<Optional<Brand>, Brand> {

    @Override
    Brand toEntity(Optional<Brand> dto);

    @Override
    Optional<Brand> toDto(Brand entity);

    List<Brand> toEntity(BrandDTO list);

    @Override
    List<Optional<Brand>> toDto(List<Brand> list);



}

package uz.pdp.WebAuto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uz.pdp.WebAuto.dtos.BrandDTO;
import uz.pdp.WebAuto.entity.Brand;

@Mapper
public interface BrandMapper {

    BrandMapper BRAND_MAPPER = Mappers.getMapper(BrandMapper.class);

    Brand toEntity(BrandDTO dto);
}

package uz.pdp.WebAuto.mapper;

import org.mapstruct.Mapper;
import uz.pdp.WebAuto.dtos.carType.CarTypeResponseDTO;
import uz.pdp.WebAuto.entity.CarType;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarTypeMapper extends EntityMapper<CarTypeResponseDTO, CarType> {

    @Override
    CarType toEntity(CarTypeResponseDTO dto);

    @Override
    CarTypeResponseDTO toDto(CarType carType);

    @Override
    List<CarType> toEntity(List<CarTypeResponseDTO> list);

    @Override
    List<CarTypeResponseDTO> toDto(List<CarType> list);
}

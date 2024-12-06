package uz.pdp.WebAuto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uz.pdp.WebAuto.dtos.car.CarRequestDTO;
import uz.pdp.WebAuto.entity.Car;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper extends EntityMapper<CarRequestDTO, Car> {

    CarMapper CAR_MAPPER = Mappers.getMapper(CarMapper.class);

    @Override
    Car toEntity(CarRequestDTO dto);

    @Override
    CarRequestDTO toDto(Car entity);

    @Override
    List<Car> toEntity(List<CarRequestDTO> list);

    @Override
    List<CarRequestDTO> toDto(List<Car> list);
}

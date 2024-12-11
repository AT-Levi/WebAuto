package uz.pdp.WebAuto.mapper;

import org.mapstruct.Mapper;
import uz.pdp.WebAuto.dtos.car.CarDTO;
import uz.pdp.WebAuto.entity.Car;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarDTOMapper extends EntityMapper<CarDTO, Car> {
    Car toEntity(CarDTO dto);

    CarDTO toDto(Car entity);

    List<Car> toEntity(List<CarDTO> list);

    List<CarDTO> toDto(List<Car> list);


}

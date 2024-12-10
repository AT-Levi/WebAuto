package uz.pdp.WebAuto.mapper;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import uz.pdp.WebAuto.dtos.car.CarDTO;
import uz.pdp.WebAuto.entity.Car;
import uz.pdp.WebAuto.enums.CarCondition;
import uz.pdp.WebAuto.enums.EngineType;
import uz.pdp.WebAuto.enums.FuelType;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CarMapper extends EntityMapper<CarDTO, Car> {

    @Override
    Car toEntity(CarDTO dto);

    @Override
    List<Car> toEntity(List<CarDTO> list);

    @Override
    List<CarDTO> toDto(List<Car> list);

    // Page<Car> to Page<CarDTO> mapping
    default Page<CarDTO> toDto(Page<Car> list) {
        List<CarDTO> carDTOList = list.stream()
                .map(this::toDto) // 'toDto' methoddan foydalanish
                .collect(Collectors.toList());
        return new PageImpl<>(carDTOList, list.getPageable(), list.getTotalElements());
    }

    CarDTO toDto(Car car);

    // FuelType enumni Stringga o'zgartirish
    default String map(FuelType fuelType) {
        return fuelType != null ? fuelType.name() : null;
    }

    // EngineType enumni Stringga o'zgartirish
    default String map(EngineType engineType) {
        return engineType != null ? engineType.name() : null;
    }

    // CarCondition enumni Stringga o'zgartirish
    default String map(CarCondition condition) {
        return condition != null ? condition.name() : null;
    }
}

package uz.pdp.WebAuto.dtos.car;

import uz.pdp.WebAuto.entity.Brand;
import uz.pdp.WebAuto.entity.Image;
import uz.pdp.WebAuto.entity.CarType;
import uz.pdp.WebAuto.enums.CarCondition;
import uz.pdp.WebAuto.enums.EngineType;
import uz.pdp.WebAuto.enums.FuelType;
import uz.pdp.WebAuto.enums.TransmissionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record CreateCarDTO(
        String model,
        int year,
        String description,
        String color,
        LocalDateTime warranty,
        FuelType fuelType,
        TransmissionType transmission,
        EngineType engineType,
        CarType carTypeId,
        Long mileage,
        CarCondition condition,
        Brand brand,
        BigDecimal price,
        List<Image> images) {
}

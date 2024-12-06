package uz.pdp.WebAuto.dtos.car;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.WebAuto.entity.Brand;
import uz.pdp.WebAuto.entity.CarType;
import uz.pdp.WebAuto.entity.Image;
import uz.pdp.WebAuto.enums.CarCondition;
import uz.pdp.WebAuto.enums.EngineType;
import uz.pdp.WebAuto.enums.FuelType;
import uz.pdp.WebAuto.enums.TransmissionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CarDTO {

    private String model;

    private int year;

    private String description;

    private String color;

    private LocalDateTime warranty;

    private FuelType fuelType;

    private TransmissionType transmission;

    private EngineType engineType;

    private CarType carTypeId;

    private Long mileage;

    private CarCondition condition;

    private Brand brand;

    private BigDecimal price;

    private List<Image> images;
}
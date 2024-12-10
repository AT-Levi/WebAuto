package uz.pdp.WebAuto.dtos.car;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.WebAuto.dtos.image.ImageResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
public class CarDTO {
    private Long id;
    private String model;
    private int year;
    private String description;
    private String color;
    private LocalDateTime warranty;

    private String fuelType;
    private String transmission;
    private String engineType;
    private Long carTypeId;

    private Long mileage;
    private String condition;
    private Long brandId;
    private BigDecimal price;
    private List<ImageResponseDTO> images;
}

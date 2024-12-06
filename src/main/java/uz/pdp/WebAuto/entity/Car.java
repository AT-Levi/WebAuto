package uz.pdp.WebAuto.entity;

import lombok.experimental.SuperBuilder;
import uz.pdp.WebAuto.enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.WebAuto.enums.TransmissionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class Car extends BaseEntity {

    private String model;

    private int year;

    @Column(length = 500)
    private String description;

    private String color;

    private String carNumber;

    private LocalDateTime warranty;

    @Enumerated(EnumType.STRING)
    @Column(name = "fuel_type")
    private FuelType fuelType;

    @Enumerated(EnumType.STRING)
    private TransmissionType transmission;

    @Enumerated(EnumType.STRING)
    @Column(name = "engine_type")
    private EngineType engineType;

    @OneToMany
    @JoinColumn(name = "car_type_id")
    private List<CarType> carTypes;

    private Long mileage;

    @Enumerated(EnumType.STRING)
    private CarCondition condition;

    @OneToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    private BigDecimal price;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Image> images;

    @OneToOne
    private CarDetails carDetails;
}

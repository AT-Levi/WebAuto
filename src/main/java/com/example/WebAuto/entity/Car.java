package com.example.WebAuto.entity;

import com.example.WebAuto.enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

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

    private String warranty;

    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    @Enumerated(EnumType.STRING)
    private TransmissionType transmission;

    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    @Enumerated(EnumType.STRING)
    private CarType carType;

    private Long mileage;

    @Enumerated(EnumType.STRING)
    private CarCondition condition;

    @OneToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    private double price;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Image> imgUrls;
}

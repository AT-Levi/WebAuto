package com.example.WebAuto.entity.user;

import com.example.WebAuto.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseEntity {
    private String street;

    private String city;

    private String country;

    private String house;

    private Double latitude;

    private Double longitude;


}

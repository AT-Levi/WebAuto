package com.example.WebAuto.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "brands")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class Brand extends BaseEntity{


    private String name;

    private String description;

    private String logoUrl;

    private String websiteUrl;

    private String owner;

    private String country;

    private Date createdDate;

    private List<String> imgUrls;

}

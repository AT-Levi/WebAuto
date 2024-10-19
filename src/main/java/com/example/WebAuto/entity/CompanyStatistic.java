package com.example.WebAuto.entity;

import jakarta.persistence.Column;
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
public class CompanyStatistic extends BaseEntity {

    @Column(name = "total_cars")
    private Long totalCars;

    @Column(name = "total_brands")
    private Long totalBrands;

    @Column(name = "total_orders")
    private Long totalOrders;

    @Column(name = "total_users")
    private Long totalUsers;

    @Column(name = "total_comments")
    private Long totalComments;

}

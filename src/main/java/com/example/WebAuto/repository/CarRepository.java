package com.example.WebAuto.repository;

import com.example.WebAuto.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,Long> {

}

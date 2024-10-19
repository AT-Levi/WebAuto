package com.example.WebAuto.repository;

import com.example.WebAuto.entity.car.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}

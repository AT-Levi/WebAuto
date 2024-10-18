package com.example.WebAuto.repository;

import com.example.WebAuto.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}

package com.example.WebAuto.repository;

import com.example.WebAuto.entity.user.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}

package uz.pdp.WebAuto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.WebAuto.entity.Car;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {


    List<Car> findByBrandId(Long brandId);

    List<Car> findByFuelType(String fuelType);

    List<Car> findByTransmission(String transmission);

    List<Car> findByYear(int year);

    List<Car> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
}

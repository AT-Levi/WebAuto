package uz.pdp.WebAuto.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.WebAuto.entity.Car;
import uz.pdp.WebAuto.enums.FuelType;
import uz.pdp.WebAuto.enums.TransmissionType;

import java.math.BigDecimal;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByBrandId(Long brandId);

    List<Car> findByFuelType(FuelType fuelType);

    List<Car> findByTransmission(TransmissionType transmission);

    List<Car> findByYear(int year);

    List<Car> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    Page<Car> findAll(Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "SELECT c.* FROM Car b")
    List<Car> getCarsForPage(Pageable pageRequest);
}

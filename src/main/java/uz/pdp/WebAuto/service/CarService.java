package uz.pdp.WebAuto.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.pdp.WebAuto.dtos.car.CarDTO;
import uz.pdp.WebAuto.dtos.car.CreateCarDTO;
import uz.pdp.WebAuto.entity.Car;

import java.math.BigDecimal;
import java.util.List;

public interface CarService {

    CarDTO findById(Long id);

    CarDTO save(CreateCarDTO dto);

    CarDTO save(Car dto);

    List<CarDTO> getAll();

    boolean delete(Long id);

    List<CarDTO> findByBrandId(Long brandId);

    List<CarDTO> findByFuelType(String fuelType);

    List<CarDTO> findByTransmission(String transmission);

    List<CarDTO> findByYear(int year);

    List<CarDTO> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);

    List<CarDTO> getAllForPage(Pageable pageRequest);

    CarDTO update(Car car);

    Page<CarDTO> findAllForPage(Pageable pageRequest);
}

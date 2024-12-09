package uz.pdp.WebAuto.controller.car;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.WebAuto.dtos.car.CarRequestDTO;
import uz.pdp.WebAuto.service.impl.CarServiceImp;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarServiceImp carService;


    @GetMapping("/{id}")
    public ResponseEntity<CarRequestDTO> getCarById(@PathVariable Long id) {
        CarRequestDTO car = carService.findById(id);
        return ResponseEntity.ok(car);
    }

    @PostMapping
    public ResponseEntity<CarRequestDTO> createCar(@RequestBody CarRequestDTO carRequestDTO) {
        CarRequestDTO createdCar = carService.save(carRequestDTO);
        return ResponseEntity.ok(createdCar);
    }

    @GetMapping
    public ResponseEntity<List<CarRequestDTO>> getAllCars() {
        List<CarRequestDTO> cars = carService.getAllCars();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/brand/{brandId}")
    public ResponseEntity<List<CarRequestDTO>> getCarsByBrand(@PathVariable Long brandId) {
        List<CarRequestDTO> cars = carService.findByBrandId(brandId);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/fuel/{fuelType}")
    public ResponseEntity<List<CarRequestDTO>> getCarsByFuelType(@PathVariable String fuelType) {
        List<CarRequestDTO> cars = carService.findByFuelType(fuelType);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/transmission/{transmission}")
    public ResponseEntity<List<CarRequestDTO>> getCarsByTransmission(@PathVariable String transmission) {
        List<CarRequestDTO> cars = carService.findByTransmission(transmission);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<CarRequestDTO>> getCarsByYear(@PathVariable int year) {
        List<CarRequestDTO> cars = carService.findByYear(year);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/price")
    public ResponseEntity<List<CarRequestDTO>> getCarsByPriceRange(
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice) {
        List<CarRequestDTO> cars = carService.findByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(cars);
    }
}

package uz.pdp.WebAuto.controller.user;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.WebAuto.dtos.car.CarDTO;
import uz.pdp.WebAuto.service.CarService;
import uz.pdp.WebAuto.util.ResponseDTO;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class UserController {

    private final CarService carService;

    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable Long id) {
        CarDTO car = carService.findById(id);
        return ResponseEntity.ok(car);
    }

    @GetMapping("/all-cars")
    public ResponseEntity<List<CarDTO>> getAllCars() {
        List<CarDTO> cars = carService.getAll();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/brand/{brandId}")
    public ResponseEntity<List<CarDTO>> getCarsByBrand(@PathVariable Long brandId) {
        List<CarDTO> cars = carService.findByBrandId(brandId);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/fuel/{fuelType}")
    public ResponseEntity<List<CarDTO>> getCarsByFuelType(@PathVariable String fuelType) {
        List<CarDTO> cars = carService.findByFuelType(fuelType);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/transmission/{transmission}")
    public ResponseEntity<List<CarDTO>> getCarsByTransmission(@PathVariable String transmission) {
        List<CarDTO> cars = carService.findByTransmission(transmission);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<CarDTO>> getCarsByYear(@PathVariable int year) {
        List<CarDTO> cars = carService.findByYear(year);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/price")
    public ResponseEntity<List<CarDTO>> getCarsByPriceRange(
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice) {
        List<CarDTO> cars = carService.findByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(cars);
    }
}

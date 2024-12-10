package uz.pdp.WebAuto.controller.admin;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.WebAuto.dtos.car.CarDTO;
import uz.pdp.WebAuto.dtos.car.CreateCarDTO;
import uz.pdp.WebAuto.entity.Car;
import uz.pdp.WebAuto.service.impl.CarServiceImp;
import uz.pdp.WebAuto.util.ResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/admin/car")
@PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN', 'DEALER')")
@SecurityRequirement(name = "bearerAuth")
public class CarManageController {
    private final CarServiceImp carService;

    public CarManageController(CarServiceImp carService) {
        this.carService = carService;
    }

    @GetMapping("/get/{id}")
    public ResponseDTO<CarDTO> getCarById(@PathVariable Long id) {
        return ResponseDTO.ok(carService.findById(id)).getBody();
    }

    @PostMapping("/create")
    public ResponseDTO<CarDTO> createCar(@RequestBody CreateCarDTO dto) {
        CarDTO savedCar = carService.save(dto);
        return ResponseDTO.ok(savedCar).getBody();
    }

    @GetMapping("/all-cars")
    public ResponseDTO<List<CarDTO>> getAllCars(Pageable pageable) {
        List<CarDTO> allCars = carService.getAll();
        return ResponseDTO.ok(allCars).getBody();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO<Object>> deleteCar(@PathVariable Long id) {
        boolean deleted = carService.delete(id);
        if (deleted) {
            return ResponseDTO.ok("Car successfully updated");
        }
        return ResponseDTO.error("Car not found or couldn't be deleted");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO<CarDTO>> updateCar(@RequestBody Car car) {
        CarDTO updatedCar = carService.update(car);
        return ResponseDTO.ok(updatedCar);
    }
}

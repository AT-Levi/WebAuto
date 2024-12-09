package uz.pdp.WebAuto.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.WebAuto.dtos.car.CarRequestDTO;
import uz.pdp.WebAuto.service.impl.CarServiceImp;
import uz.pdp.WebAuto.util.ResponseDTO;

import java.util.List;

@RestController
@RequestMapping("admin/car")
public class CarManageController{
    private final CarServiceImp carService;

    public CarManageController(CarServiceImp carService) {
        this.carService = carService;
    }

    @GetMapping("/get/{id}")
    public ResponseDTO<CarRequestDTO> getCarById(@PathVariable Long id) {
        return ResponseDTO.ok(carService.findById(id)).getBody();
    }

    @PostMapping("/create")
    public ResponseDTO<CarRequestDTO> createCar(@RequestBody CarRequestDTO dto) {
        CarRequestDTO savedCar = carService.save(dto);
        return ResponseDTO.ok(savedCar).getBody();
    }

    @GetMapping("/all-cars")
    public ResponseDTO<List<CarRequestDTO>> getAllCars() {
        List<CarRequestDTO> allCars = carService.getAllCars();
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
    public ResponseDTO<CarRequestDTO> updateCar(@PathVariable Long id, @RequestBody CarRequestDTO carRequestDTO) {
        CarRequestDTO updatedCar = carService.update(id, carRequestDTO);
        return ResponseDTO.ok(updatedCar).getBody();
    }
}

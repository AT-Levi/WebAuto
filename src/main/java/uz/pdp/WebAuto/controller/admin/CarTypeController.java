package uz.pdp.WebAuto.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.WebAuto.entity.CarType;
import uz.pdp.WebAuto.service.CarTypeService;
import uz.pdp.WebAuto.util.ResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/admin/car-type")
public class CarTypeController {

    private final CarTypeService carTypeService;

    public CarTypeController(CarTypeService carTypeService) {
        this.carTypeService = carTypeService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO<CarType>> createCarType(@RequestBody CarType carType) {
        return ResponseEntity.ok(carTypeService.save(carType));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO<CarType>> updateCarType(@PathVariable Long id, @RequestBody CarType carType) {
        return ResponseEntity.ok(carTypeService.update(id, carType));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO<Void>> deleteCarType(@PathVariable Long id) {
        return ResponseEntity.ok(carTypeService.delete(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<CarType>> getCarTypeById(@PathVariable Long id) {
        return ResponseEntity.ok(carTypeService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseDTO<List<CarType>>> getAllCarTypes() {
        return ResponseEntity.ok(carTypeService.findAll());
    }
}

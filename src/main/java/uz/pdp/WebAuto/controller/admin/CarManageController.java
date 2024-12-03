package uz.pdp.WebAuto.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.WebAuto.dtos.CreateCarDTO;
import uz.pdp.WebAuto.service.CarServiceImp;
import uz.pdp.WebAuto.service.imps.CarService;

import java.util.List;

@RestController
@RequestMapping("admin/car/**")
public class CarManageController {

    private final CarServiceImp carService;

    public CarManageController(CarServiceImp carService) {
        this.carService = carService;
    }
/*
    @GetMapping("/get/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id){
        Car car = carService.get(id).orElseThrow(() -> new RuntimeException("Car not found"));
        return ResponseEntity.ok(car);
    }

    @PostMapping("/create")
    public ResponseEntity<Car> createCar(CreateCarDTO dto){
        Car car = carService.create(dto);
        return ResponseEntity.ok(car);
    }

    @GetMapping("/all-cars")
    public ResponseEntity<List<Car>> getAllCars(){
        List<Car> allCars = carService.getAllCars();
        return ResponseEntity.ok(allCars);
    }*/
}

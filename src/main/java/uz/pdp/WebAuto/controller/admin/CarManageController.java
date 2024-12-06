package uz.pdp.WebAuto.controller.admin;

import org.springframework.web.bind.annotation.*;
import uz.pdp.WebAuto.dtos.car.CarDTO;
import uz.pdp.WebAuto.dtos.car.CarRequestDTO;
import uz.pdp.WebAuto.entity.Car;
import uz.pdp.WebAuto.exception.NotFoundException;
import uz.pdp.WebAuto.service.CarService;
import uz.pdp.WebAuto.util.ResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/admin/car")
public record CarManageController(CarService car) {

    @GetMapping("/get/{id}")
    public ResponseDTO<CarDTO> getCarById(@PathVariable Long id){
        return ResponseDTO.ok(car.findById(id)).getBody();
    }

    @PostMapping("/create")
    public ResponseDTO<CarDTO> createCar(@RequestBody CarRequestDTO dto){
        CarDTO save = car.save(dto);
        return ResponseDTO.ok(save).getBody();
    }

    @GetMapping("/all-cars")
    public ResponseDTO<List<CarDTO>> getAllCars(){
        List<CarDTO> allCars = car.getAllCars();
        return ResponseDTO.ok(allCars).getBody();
    }
}

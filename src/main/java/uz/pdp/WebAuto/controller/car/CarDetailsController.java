package uz.pdp.WebAuto.controller.car;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uz.pdp.WebAuto.dtos.car.CarDetailsDTO;
import uz.pdp.WebAuto.service.CarDetailsService;
import uz.pdp.WebAuto.util.ResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/admin/car-details")
public class CarDetailsController {
    private final CarDetailsService carDetailsService;

    public CarDetailsController(CarDetailsService carDetailsService) {
        this.carDetailsService = carDetailsService;
    }

    @PostMapping
    public ResponseDTO<CarDetailsDTO> create(@RequestBody CarDetailsDTO carDetailsDTO) {
        CarDetailsDTO saved = carDetailsService.save(carDetailsDTO);
        return ResponseDTO.ok(saved).getBody();
    }

    @GetMapping("/{id}")
    public ResponseDTO<CarDetailsDTO> getById(@PathVariable Long id) {
        return ResponseDTO.ok(carDetailsService.getById(id)).getBody();
    }

    @GetMapping
    public ResponseDTO<List<CarDetailsDTO>> getAll() {
        return ResponseDTO.ok(carDetailsService.getAll()).getBody();
    }

    @PutMapping("/{id}")
    public ResponseDTO<CarDetailsDTO> update(@PathVariable Long id, @RequestBody CarDetailsDTO carDetailsDTO) {
        return ResponseDTO.ok(carDetailsService.update(id, carDetailsDTO)).getBody();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<Object>> delete(@PathVariable Long id) {
        carDetailsService.delete(id);
        return ResponseDTO.ok(null);
    }
}

package uz.pdp.WebAuto.controller.car;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import uz.pdp.WebAuto.dtos.car.CarDetailsDTO;
import uz.pdp.WebAuto.service.CarDetailsService;
import uz.pdp.WebAuto.util.ResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/admin/car-details")
@PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN', 'DEALER')")
@SecurityRequirement(name = "bearerAuth")
public class CarDetailsController {
    private final CarDetailsService carDetailsService;

    public CarDetailsController(CarDetailsService carDetailsService) {
        this.carDetailsService = carDetailsService;
    }

    @PostMapping("/create")
    public ResponseDTO<CarDetailsDTO> create(@RequestBody CarDetailsDTO carDetailsDTO) {
        CarDetailsDTO saved = carDetailsService.save(carDetailsDTO);
        return ResponseDTO.ok(saved).getBody();
    }

    @GetMapping("/{id}")
    public ResponseDTO<CarDetailsDTO> getById(@PathVariable Long id) {
        return ResponseDTO.ok(carDetailsService.getById(id)).getBody();
    }

    @GetMapping("/get-all")
    public ResponseEntity<ResponseDTO<List<CarDetailsDTO>>> getAll() {
        return ResponseDTO.ok(carDetailsService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<CarDetailsDTO>> update(@PathVariable Long id, @RequestBody CarDetailsDTO carDetailsDTO) {
        return ResponseDTO.ok(carDetailsService.update(id, carDetailsDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<Object>> delete(@PathVariable Long id) {
        carDetailsService.delete(id);
        return ResponseDTO.ok("Car is deleted with id: " + id);
    }
}

package uz.pdp.WebAuto.controller.admin;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.WebAuto.dtos.carType.CarTypeResponseDTO;
import uz.pdp.WebAuto.dtos.carType.CreateCarTypeDTO;
import uz.pdp.WebAuto.entity.CarType;
import uz.pdp.WebAuto.service.CarTypeService;
import uz.pdp.WebAuto.util.ResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/admin/car-type")
@PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN', 'DEALER')")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class CarTypeController {

    private final CarTypeService carTypeService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO<CarTypeResponseDTO>> createCarType(@RequestBody CreateCarTypeDTO carTypeDTO) {
        return ResponseDTO.ok(carTypeService.saveByDto(carTypeDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO<CarTypeResponseDTO>> updateCarType(@RequestBody CarType carType) {
        return ResponseDTO.ok(carTypeService.update(carType));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO<Void>> deleteCarType(@PathVariable Long id) {
        carTypeService.delete(id);
        return ResponseDTO.ok("Car Type is deleted with id : " + id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<CarTypeResponseDTO>> getCarTypeById(@PathVariable Long id) {
        return ResponseDTO.ok(carTypeService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseDTO<List<CarTypeResponseDTO>>> getAllCarTypes() {
        return ResponseDTO.ok(carTypeService.findAll());
    }
}

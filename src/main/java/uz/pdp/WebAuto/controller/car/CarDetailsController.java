package uz.pdp.WebAuto.controller.car;


import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Yangi mashina detallari yaratish.", description = "Yangi mashina detal ma'lumotlarini tizimga saqlaydi va saqlangan ma'lumotlarni qaytaradi.")
    public ResponseDTO<CarDetailsDTO> create(@RequestBody CarDetailsDTO carDetailsDTO) {
        CarDetailsDTO saved = carDetailsService.save(carDetailsDTO);
        return ResponseDTO.ok(saved).getBody();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Mashina detallari ID orqali olish.", description = "Berilgan ID bo'yicha mashina detal ma'lumotlarini qaytaradi.")
    public ResponseDTO<CarDetailsDTO> getById(@PathVariable Long id) {
        return ResponseDTO.ok(carDetailsService.getById(id)).getBody();
    }

    @GetMapping
    @Operation(summary = "Barcha mashina detallari ma'lumotlarini olish.", description = "Tizimdagi barcha mashina detallari ro'yxatini qaytaradi.")
    public ResponseDTO<List<CarDetailsDTO>> getAll() {
        return ResponseDTO.ok(carDetailsService.getAll()).getBody();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mashina detallari yangilash.", description = "Berilgan ID bo'yicha mashina detal ma'lumotlarini yangilaydi va yangilangan ma'lumotlarni qaytaradi.")
    public ResponseDTO<CarDetailsDTO> update(@PathVariable Long id, @RequestBody CarDetailsDTO carDetailsDTO) {
        return ResponseDTO.ok(carDetailsService.update(id, carDetailsDTO)).getBody();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Mashina detallari o'chirish.", description = "Berilgan ID bo'yicha mashina detal ma'lumotlarini tizimdan o'chiradi.")
    public ResponseEntity<ResponseDTO<Object>> delete(@PathVariable Long id) {
        carDetailsService.delete(id);
        return ResponseDTO.ok(null);
    }
}

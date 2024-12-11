package uz.pdp.WebAuto.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.WebAuto.dtos.carType.CarTypeResponseDTO;
import uz.pdp.WebAuto.dtos.carType.CreateCarTypeDTO;
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
    @Operation(summary = "Yangi Car Type yaratish.", description = "Yangi car type yaratish uchun ma'lumotlarni qabul qiladi va saqlaydi.")
    public ResponseEntity<ResponseDTO<CarTypeResponseDTO>> createCarType(@RequestBody CreateCarTypeDTO carTypeDTO) {
        return ResponseDTO.ok(carTypeService.saveByDto(carTypeDTO));
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Car Type-ni yangilash.", description = "Berilgan car type ma'lumotlarini yangilash uchun foydalaniladi.")
    public ResponseEntity<ResponseDTO<CarTypeResponseDTO>> updateCarType(@RequestBody CarType carType) {
        return ResponseDTO.ok(carTypeService.update(carType));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Car Type-ni o'chirish.", description = "Berilgan ID bo'yicha car type-ni tizimdan o'chiradi.")
    public ResponseEntity<ResponseDTO<Void>> deleteCarType(@PathVariable Long id) {
        carTypeService.delete(id);
        return ResponseDTO.ok("Car Type is deleted with id : " + id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Car Type-ni ID bo'yicha olish.", description = "Berilgan ID bo'yicha car type ma'lumotlarini qaytaradi.")
    public ResponseEntity<ResponseDTO<CarTypeResponseDTO>> getCarTypeById(@PathVariable Long id) {
        return ResponseDTO.ok(carTypeService.findById(id));
    }

    @GetMapping("/all")
    @Operation(summary = "Barcha Car Type-larni ko'rish.", description = "Tizimda mavjud barcha car type-lar ro'yxatini qaytaradi.")
    public ResponseEntity<ResponseDTO<List<CarTypeResponseDTO>>> getAllCarTypes() {
        return ResponseDTO.ok(carTypeService.findAll());
    }
}

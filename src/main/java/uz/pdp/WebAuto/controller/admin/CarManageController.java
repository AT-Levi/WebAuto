package uz.pdp.WebAuto.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Mashina ma'lumotlarini olish", description = "Berilgan ID bo'yicha mashinaning batafsil ma'lumotlarini qaytaradi.")
    @GetMapping("/get/{id}")
    public ResponseDTO<CarDTO> getCarById(@PathVariable Long id) {
        return ResponseDTO.ok(carService.findById(id)).getBody();
    }

    @Operation(summary = "Yangi mashina yaratish", description = "Yangi mashina yaratish uchun ma'lumotlarni qabul qiladi va saqlaydi.")
    @PostMapping("/create")
    public ResponseDTO<CarDTO> createCar(@RequestBody CreateCarDTO dto) {
        CarDTO savedCar = carService.save(dto);
        return ResponseDTO.ok(savedCar).getBody();
    }

    @Operation(summary = "Barcha mashinalar ro'yxatini olish", description = "Sahifalash parametrlariga muvofiq barcha mavjud mashinalarning ro'yxatini qaytaradi.")
    @GetMapping("/all-cars")
    public ResponseDTO<List<CarDTO>> getAllCars(Pageable pageable) {
        List<CarDTO> allCars = carService.getAll();
        return ResponseDTO.ok(allCars).getBody();
    }

    @Operation(summary = "Mashina o'chirish", description = "Berilgan ID bo'yicha mashinani tizimdan o'chiradi.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO<Object>> deleteCar(@PathVariable Long id) {
        boolean deleted = carService.delete(id);
        if (deleted) {
            return ResponseDTO.ok("Mashina muvaffaqiyatli o'chirildi");
        }
        return ResponseDTO.error("Mashina topilmadi yoki o'chirib bo'lmadi");
    }

    @Operation(summary = "Mashina ma'lumotlarini yangilash", description = "Berilgan mashina ma'lumotlarini yangilash uchun foydalaniladi.")
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO<CarDTO>> updateCar(@RequestBody Car car) {
        CarDTO updatedCar = carService.update(car);
        return ResponseDTO.ok(updatedCar);
    }
}
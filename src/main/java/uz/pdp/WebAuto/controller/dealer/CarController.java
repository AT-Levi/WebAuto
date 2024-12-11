package uz.pdp.WebAuto.controller.dealer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.WebAuto.dtos.car.CarDTO;
import uz.pdp.WebAuto.dtos.car.CreateCarDTO;
import uz.pdp.WebAuto.service.CarService;
import uz.pdp.WebAuto.util.ResponseDTO;

import java.util.List;

@RequestMapping("/dealer/car")
@PreAuthorize("hasAnyRole('DEALER', 'SUPER_ADMIN')")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
@RestController
public class CarController {

    private final CarService carService;

    @PostMapping("/create")
    @Operation(summary = "Yangi mashina yaratish.", description = "Yangi mashina ma'lumotlarini tizimga saqlaydi va saqlangan ma'lumotlarni qaytaradi.")
    public ResponseDTO<CarDTO> createCar(@RequestBody CreateCarDTO dto) {
        CarDTO createdCar = carService.save(dto);
        return ResponseDTO.ok(createdCar).getBody();
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Mashina ma'lumotlarini ID orqali olish.", description = "Berilgan ID bo'yicha mashina ma'lumotlarini qaytaradi.")
    public ResponseEntity<ResponseDTO<CarDTO>> getCarById(@PathVariable(name = "id") Long carId) {
        return ResponseDTO.ok(carService.findById(carId));
    }

    @GetMapping("/get-page/{page}/{countOfItems}")
    @Operation(summary = "Mashinalarni sahifa bo'yicha olish.", description = "Berilgan sahifa raqami va elementlar soniga asosan mashinalar ro'yxatini qaytaradi.")
    public ResponseEntity<List<CarDTO>> getCarsForPage(
            @PathVariable Integer page, @PathVariable Integer countOfItems) {
        Pageable pageRequest = PageRequest.of(page, countOfItems);
        List<CarDTO> carsForPage = carService.getAllForPage(pageRequest);
        return ResponseEntity.ok(carsForPage);
    }

    @GetMapping("/get-page-info/{page}/{countOfItems}")
    @Operation(summary = "Mashinalar sahifasi haqida ma'lumot olish.", description = "Berilgan sahifa raqami va elementlar soniga asosan sahifa haqida qo'shimcha ma'lumot bilan mashinalar ro'yxatini qaytaradi.")
    public ResponseEntity<ResponseDTO<List<CarDTO>>> getCarsWithPageInfo(
            @PathVariable Integer page, @PathVariable Integer countOfItems) {
        Pageable pageRequest = PageRequest.of(page, countOfItems);
        Page<CarDTO> allForPage = carService.findAllForPage(pageRequest);
        return ResponseDTO.page(allForPage);
    }
}

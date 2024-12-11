package uz.pdp.WebAuto.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.WebAuto.dtos.car.CarDTO;
import uz.pdp.WebAuto.dtos.image.ImageResponseDTO;
import uz.pdp.WebAuto.service.CarService;
import uz.pdp.WebAuto.service.UserService;
import uz.pdp.WebAuto.util.ResponseDTO;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor

public class UserController {

    private final CarService carService;
    private final UserService userService;

    @Operation(
            summary = "Avtomobilni ID bo'yicha olish",
            description = "Bu endpoint avtomobilni uning ID si bo'yicha olish imkoniyatini beradi."
    )
    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable Long id) {
        CarDTO car = carService.findById(id);
        return ResponseEntity.ok(car);
    }

    @Operation(
            summary = "Barcha avtomobillarni olish",
            description = "Bu endpoint tizimdagi barcha avtomobillarni olish imkoniyatini beradi."
    )
    @GetMapping("/all-cars")
    public ResponseEntity<List<CarDTO>> getAllCars() {
        List<CarDTO> cars = carService.getAll();
        return ResponseEntity.ok(cars);
    }

    @Operation(
            summary = "Avtomobillarni brendi bo'yicha olish",
            description = "Bu endpoint avtomobillarni brend ID si bo'yicha olish imkoniyatini beradi."
    )
    @GetMapping("/brand/{brandId}")
    public ResponseEntity<List<CarDTO>> getCarsByBrand(@PathVariable Long brandId) {
        List<CarDTO> cars = carService.findByBrandId(brandId);
        return ResponseEntity.ok(cars);
    }

    @Operation(
            summary = "Avtomobillarni yonilg'i turi bo'yicha olish",
            description = "Bu endpoint avtomobillarni yonilg'i turi bo'yicha olish imkoniyatini beradi."
    )
    @GetMapping("/fuel/{fuelType}")
    public ResponseEntity<List<CarDTO>> getCarsByFuelType(@PathVariable String fuelType) {
        List<CarDTO> cars = carService.findByFuelType(fuelType);
        return ResponseEntity.ok(cars);
    }

    @Operation(
            summary = "Avtomobillarni transmissiya turi bo'yicha olish",
            description = "Bu endpoint avtomobillarni transmissiya turi bo'yicha olish imkoniyatini beradi."
    )
    @GetMapping("/transmission/{transmission}")
    public ResponseEntity<List<CarDTO>> getCarsByTransmission(@PathVariable String transmission) {
        List<CarDTO> cars = carService.findByTransmission(transmission);
        return ResponseEntity.ok(cars);
    }

    @Operation(
            summary = "Avtomobillarni chiqarilgan yil bo'yicha olish",
            description = "Bu endpoint avtomobillarni chiqarilgan yil bo'yicha olish imkoniyatini beradi."
    )
    @GetMapping("/year/{year}")
    public ResponseEntity<List<CarDTO>> getCarsByYear(@PathVariable int year) {
        List<CarDTO> cars = carService.findByYear(year);
        return ResponseEntity.ok(cars);
    }

    @Operation(
            summary = "Avtomobillarni narx oralig'i bo'yicha olish",
            description = "Bu endpoint avtomobillarni narx oralig'i (min va max) bo'yicha olish imkoniyatini beradi."
    )
    @GetMapping("/price")
    public ResponseEntity<List<CarDTO>> getCarsByPriceRange(
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice) {
        List<CarDTO> cars = carService.findByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(cars);
    }

    @Operation(
            summary = "Foydalanuvchi profil rasmiga yuklash",
            description = "Bu endpoint foydalanuvchi profil rasmiga yuklash imkoniyatini beradi."
    )
    @PostMapping(value = "/profileImage", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResponseDTO<ImageResponseDTO>> profileImage(
            @RequestPart("profileImage") MultipartFile profileImage) {
        return ResponseDTO.ok(userService.saveProfileImage(profileImage));
    }
}


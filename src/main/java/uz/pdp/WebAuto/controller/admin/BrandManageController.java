package uz.pdp.WebAuto.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.WebAuto.dtos.brand.BrandDTO;
import uz.pdp.WebAuto.dtos.brand.BrandRequestDTO;
import uz.pdp.WebAuto.entity.Brand;
import uz.pdp.WebAuto.service.BrandService;
import uz.pdp.WebAuto.util.ResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/admin/brand")
@SecurityRequirement(name = "bearerAuth")
@PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN', 'DEALER')")
@RequiredArgsConstructor
public class BrandManageController {

    private final BrandService service;

    @GetMapping("/{id}")
    @Operation(summary = "Brandni Id- si bo`yicha olish.", description = "Berilgan ID bo'yicha brand ma'lumotlarini qaytaradi.")
    public ResponseEntity<ResponseDTO<Brand>> getBrandById(@PathVariable Long id) {
        return ResponseDTO.ok(service.findById(id));
    }

    @PostMapping("/create")
    @Operation(summary = "Yangi Brand yaratish.", description = "Yangi brand yaratish uchun ma'lumotlarni qabul qiladi va saqlaydi.")
    public ResponseDTO<BrandDTO> createBrand(@RequestBody BrandRequestDTO brandDTO) {
        BrandDTO save = service.save(brandDTO);
        return ResponseDTO.ok(save).getBody();
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Brandni update qilish.", description = "Berilgan brand ma'lumotlarini yangilash uchun foydalaniladi.")
    public ResponseDTO<BrandDTO> updateBrand(@RequestBody BrandDTO brandDTO) {
        BrandDTO update = service.update(brandDTO);
        return ResponseDTO.ok(update).getBody();
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Brandni o`chirish", description = "Berilgan ID bo'yicha brandni tizimdan o'chiradi.")
    public ResponseEntity<ResponseDTO<String>> deleteBrand(@PathVariable Long id) {
        service.delete(id);
        return ResponseDTO.ok("Brand successfully deleted");
    }

    @GetMapping("/get-all")
    @Operation(summary = "Barcha brandlarni ko`rish", description = "Tizimda mavjud barcha brandlarning ro'yxatini qaytaradi.")
    public ResponseDTO<List<BrandDTO>> getAllBrands() {
        List<BrandDTO> allBrands = service.getAllBrands();
        return ResponseDTO.ok(allBrands).getBody();
    }
}

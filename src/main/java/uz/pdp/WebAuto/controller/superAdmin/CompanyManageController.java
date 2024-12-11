package uz.pdp.WebAuto.controller.superAdmin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.WebAuto.config.service.StorageService;
import uz.pdp.WebAuto.dtos.company.CompanyDataDTO;
import uz.pdp.WebAuto.dtos.company.CompanyResponseDTO;
import uz.pdp.WebAuto.dtos.company.CreateCompanyDTO;
import uz.pdp.WebAuto.dtos.image.ImageResponseDTO;
import uz.pdp.WebAuto.entity.Company;
import uz.pdp.WebAuto.service.CompanyService;
import uz.pdp.WebAuto.service.ImageService;
import uz.pdp.WebAuto.util.ResponseDTO;

import java.util.List;

@RequestMapping("/super-admin/company")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@PreAuthorize("hasRole('SUPER_ADMIN')")
@RestController
public class CompanyManageController {

    private final CompanyService companyService;
    private final ImageService imageService;
    private final StorageService storageService;

    @Operation(
            summary = "Yangi kompaniya yaratish",
            description = "Bu endpoint orqali siz yangi kompaniya yaratishingiz mumkin, kompaniya ma'lumotlarini taqdim etish orqali."
    )
    @PostMapping(value = "/create")
    public ResponseEntity<ResponseDTO<CompanyDataDTO>> createCompany(
            @RequestBody CreateCompanyDTO dto) {
        return ResponseDTO.ok(companyService.save(dto));
    }

    @Operation(
            summary = "Kompaniya logotipini yuklash",
            description = "Bu endpoint orqali kompaniya logotipini yuklash imkoniyatini beradi."
    )
    @PostMapping(value = "/logo", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResponseDTO<ImageResponseDTO>> logo(
            @RequestPart("logo") MultipartFile logo) {
        return ResponseDTO.ok(companyService.saveLogo(logo).logo());
    }

    @Operation(
            summary = "Kompaniya ma'lumotlarini ID bo'yicha olish",
            description = "Bu endpoint kompaniya ID orqali kompaniya ma'lumotlarini olish imkoniyatini beradi."
    )
    @GetMapping("get/{id}")
    public ResponseEntity<ResponseDTO<Company>> findCompanyById(@PathVariable(name = "id") Long companyId) {
        return ResponseDTO.ok(companyService.findById(companyId));
    }

    @Operation(
            summary = "Kompaniyani ID bo'yicha o'chirish",
            description = "Bu endpoint kompaniyani tizimdan o'chirish imkoniyatini beradi, kompaniya ID si orqali."
    )
    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseDTO<String>> deleteCompanyById(@PathVariable(name = "id") Long companyId) {
        companyService.deleteById(companyId);
        return ResponseDTO.ok("Kompaniya muvaffaqiyatli o'chirildi");
    }

    @Operation(
            summary = "Barcha kompaniyalarni olish",
            description = "Bu endpoint tizimdagi barcha kompaniyalarni olish imkoniyatini beradi."
    )
    @GetMapping("get-all")
    public ResponseEntity<ResponseDTO<List<CompanyResponseDTO>>> findAll() {
        return ResponseDTO.ok(companyService.findAll());
    }
}


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
import uz.pdp.WebAuto.dtos.company.CompanyRequestDTO;
import uz.pdp.WebAuto.dtos.company.CompanyResponseDTO;
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

    @PostMapping(value = "/create")
    public ResponseEntity<ResponseDTO<CompanyDataDTO>> createCompany(
            @RequestBody CompanyRequestDTO dto) {
        return ResponseDTO.ok(companyService.save(dto));
    }

    @PostMapping(value = "/logo", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResponseDTO<ImageResponseDTO>> logo(
            @RequestPart("logo") MultipartFile logo) {
        return ResponseDTO.ok(imageService.save(logo));
    }

    @GetMapping("get/{id}")
    public ResponseEntity<ResponseDTO<Company>> findCompanyById(@PathVariable(name = "id") Long companyId) {
        return ResponseDTO.ok(companyService.findById(companyId));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseDTO<String>> deleteCompanyById(@PathVariable(name = "id") Long companyId) {
        companyService.deleteById(companyId);
        return ResponseDTO.ok("Company successfully deleted");
    }

    @GetMapping("get-all")
    public ResponseEntity<ResponseDTO<List<CompanyResponseDTO>>> findAll() {
        return ResponseDTO.ok(companyService.findAll());
    }
}

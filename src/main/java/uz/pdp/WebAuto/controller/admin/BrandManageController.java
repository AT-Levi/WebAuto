package uz.pdp.WebAuto.controller.admin;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
public class BrandManageController {

    private final BrandService service;

    public BrandManageController(BrandService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<Brand>> getBrandById(@PathVariable Long id) {
        return ResponseDTO.ok(service.findById(id));
    }

    @PostMapping("/create")
    public ResponseDTO<BrandDTO> createBrand(@RequestBody BrandRequestDTO brandDTO) {
        BrandDTO save = service.save(brandDTO);
        return ResponseDTO.ok(save).getBody();
    }

    @PutMapping("/update/{id}")
    public ResponseDTO<BrandDTO> updateBrand(@RequestBody BrandDTO brandDTO) {
        BrandDTO update = service.update(brandDTO);
        return ResponseDTO.ok(update).getBody();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO<String>> deleteBrand(@PathVariable Long id) {
        service.delete(id);
        return ResponseDTO.ok("Brand successfully deleted");
    }

    @GetMapping("/all")
    public ResponseDTO<List<BrandDTO>> getAllBrands() {
        List<BrandDTO> allBrands = service.getAllBrands();
        return ResponseDTO.ok(allBrands).getBody();
    }
}
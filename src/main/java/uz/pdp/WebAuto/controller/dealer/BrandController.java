package uz.pdp.WebAuto.controller.dealer;

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

@RequestMapping("/dealer/brand")
@PreAuthorize("hasAnyRole('DEALER')")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO<Brand>> getBrandById(@PathVariable Long id) {
        return ResponseDTO.ok(brandService.findById(id));
    }

    @PostMapping("/create")
    public ResponseDTO<BrandDTO> createBrand(@RequestBody BrandRequestDTO brandDTO) {
        BrandDTO save = brandService.save(brandDTO);
        return ResponseDTO.ok(save).getBody();
    }

    @PutMapping("/update/{id}")
    public ResponseDTO<BrandDTO> updateBrand(@RequestBody BrandDTO brandDTO) {
        BrandDTO update = brandService.update(brandDTO);
        return ResponseDTO.ok(update).getBody();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO<String>> deleteBrand(@PathVariable Long id) {
        brandService.delete(id);
        return ResponseDTO.ok("Brand successfully deleted");
    }

    @GetMapping("/get-all")
    public ResponseDTO<List<BrandDTO>> getAllBrands() {
        List<BrandDTO> allBrands = brandService.getAllBrands();
        return ResponseDTO.ok(allBrands).getBody();
    }
}

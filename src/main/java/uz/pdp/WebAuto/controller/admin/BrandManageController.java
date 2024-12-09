package uz.pdp.WebAuto.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.WebAuto.dtos.BrandDTO;
import uz.pdp.WebAuto.entity.Brand;
import uz.pdp.WebAuto.service.BrandService;
import uz.pdp.WebAuto.service.impl.BrandServiceImp;
import uz.pdp.WebAuto.util.ResponseDTO;

import java.util.List;

@RestController
@RequestMapping("admin/brand")
public class BrandManageController {

    private final BrandService brandService;

    public BrandManageController(BrandServiceImp brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<Brand>> getBrandById(@PathVariable Long id) {
        return brandService.findById(id)
                .map(brand -> ResponseDTO.ok(brand, "Brand found"))
                .orElse(ResponseDTO.error("Brand not found"));
    }

    @PostMapping("/create")
    public ResponseDTO<BrandDTO> createBrand(@RequestBody BrandDTO brandDTO) {
        BrandDTO savedBrand = brandService.save(brandDTO);
        return ResponseDTO.ok(savedBrand, "Brand successfully created").getBody();
    }

    @PutMapping("/update/{id}")
    public ResponseDTO<BrandDTO> updateBrand(@PathVariable Long id, @RequestBody BrandDTO brandDTO) {
        // Создаем копию BrandDTO с заданным ID
        BrandDTO updatedBrand = new BrandDTO(
                id,
                brandDTO.name(),
                brandDTO.description(),
                brandDTO.logoUrl(),
                brandDTO.websiteUrl(),
                brandDTO.owner(),
                brandDTO.country(),
                brandDTO.createdDate(),
                brandDTO.icon()
        );
        brandService.update(updatedBrand);
        return ResponseDTO.ok(updatedBrand, "Brand successfully updated").getBody();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO<Object>> deleteBrand(@PathVariable Long id) {
        brandService.delete(id);
        return ResponseDTO.ok(null, "Brand successfully deleted");
    }

    @GetMapping("/all")
    public ResponseDTO<List<BrandDTO>> getAllBrands() {
        List<BrandDTO> allBrands = brandService.getAllBrands();
        return ResponseDTO.ok(allBrands, "List of all brands").getBody();
    }
}

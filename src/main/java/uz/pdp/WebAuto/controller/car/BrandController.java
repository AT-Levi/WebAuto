package uz.pdp.WebAuto.controller.car;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.WebAuto.dtos.BrandDTO;
import uz.pdp.WebAuto.entity.Brand;
import uz.pdp.WebAuto.service.impl.BrandServiceImp;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/brands")
public class BrandController {


    private final BrandServiceImp brandService;

    public BrandController(BrandServiceImp brandService) {
        this.brandService = brandService;
    }



    @GetMapping
    public ResponseEntity<List<BrandDTO>> getAllBrands() {
        List<BrandDTO> brands = brandService.getAllBrands();
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable Long id) {
        Optional<Brand> brand = brandService.getById(id);
        return brand.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping
    public ResponseEntity<Optional<Brand>> createBrand(@RequestBody BrandDTO brandDTO) {
        Optional<Brand> savedBrandDTO = brandService.save(brandDTO);
        return new ResponseEntity<>(savedBrandDTO, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Optional<Brand>> updateBrand(@PathVariable Long id, @RequestBody Optional<Brand> brandDTO) {
        if (!brandService.findById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        brandDTO = brandService.save(brandDTO);
        return new ResponseEntity<>(brandDTO, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
        Optional<Brand> brand = brandService.getById(id);
        if (brand.isPresent()) {
            brandService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

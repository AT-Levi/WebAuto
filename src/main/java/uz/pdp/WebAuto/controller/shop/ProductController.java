package uz.pdp.WebAuto.controller.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.WebAuto.entity.shop.CartItem;
import uz.pdp.WebAuto.entity.shop.Product;
import uz.pdp.WebAuto.service.shop.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Void> addProduct(@RequestParam String productCategory,
                                           @RequestParam String productName,
                                           @RequestParam int productPrice,
                                           @RequestParam String productDescription,
                                           @RequestParam int productCount,
                                           @RequestParam String productImageUrl,
                                           @RequestParam String base64Img) {
        productService.addProduct(productCategory, productName, productPrice, productDescription, productCount, productImageUrl, base64Img);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable int id) {
        if (productService.deleteProductById(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/reduce")
    public ResponseEntity<Void> reduceAmountOfProductByProductId(@RequestBody List<CartItem> items) {
        productService.reduceAmountOfProductByProductId(items);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/archived/{userId}")
    public ResponseEntity<List<Product>> getArchivedProductsByUserId(@PathVariable int userId) {
        return ResponseEntity.ok(productService.getArchivedProductsByUserId(userId));
    }

}

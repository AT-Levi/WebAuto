package uz.pdp.WebAuto.controller.shop;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

    @Operation(
            summary = "Yangi mahsulot qo'shish",
            description = "Yangi mahsulotni tizimga qo'shadi. Mahsulot haqida kategoriya, nom, narx, tavsif, miqdor, rasm URL'i va base64 formatdagi tasvirni qabul qiladi."
    )
    @PostMapping
    public ResponseEntity<Void> addProduct(
            @Parameter(description = "Mahsulot kategoriyasi") @RequestParam String productCategory,
            @Parameter(description = "Mahsulot nomi") @RequestParam String productName,
            @Parameter(description = "Mahsulot narxi") @RequestParam int productPrice,
            @Parameter(description = "Mahsulot tavsifi") @RequestParam String productDescription,
            @Parameter(description = "Mahsulot miqdori") @RequestParam int productCount,
            @Parameter(description = "Mahsulot rasmi URL") @RequestParam String productImageUrl,
            @Parameter(description = "Mahsulot rasmi base64 formatda") @RequestParam String base64Img
    ) {
        productService.addProduct(productCategory, productName, productPrice, productDescription, productCount, productImageUrl, base64Img);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Mahsulotni ID bo'yicha olish",
            description = "Berilgan ID bo'yicha mahsulotni qaytaradi."
    )
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(
            @PathVariable int id
    ) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @Operation(
            summary = "Barcha mahsulotlarni olish",
            description = "Tizimdagi barcha mavjud mahsulotlar ro'yxatini qaytaradi."
    )
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @Operation(
            summary = "Mahsulotni ID bo'yicha o'chirish",
            description = "Berilgan ID asosida mahsulotni tizimdan o'chiradi."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(
             @PathVariable int id
    ) {
        if (productService.deleteProductById(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Mahsulot miqdorini kamaytirish",
            description = "Savatchadagi mahsulotlar asosida ularning miqdorini kamaytiradi."
    )
    @PutMapping("/reduce")
    public ResponseEntity<Void> reduceAmountOfProductByProductId(
            @RequestBody List<CartItem> items
    ) {
        productService.reduceAmountOfProductByProductId(items);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Foydalanuvchining arxivlangan mahsulotlarini olish",
            description = "Berilgan foydalanuvchi IDsi asosida arxivlangan mahsulotlarni qaytaradi."
    )
    @GetMapping("/archived/{userId}")
    public ResponseEntity<List<Product>> getArchivedProductsByUserId(
           @PathVariable int userId
    ) {
        return ResponseEntity.ok(productService.getArchivedProductsByUserId(userId));
    }
}

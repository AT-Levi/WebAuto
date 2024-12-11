package uz.pdp.WebAuto.controller.shop;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.WebAuto.entity.shop.Cart;
import uz.pdp.WebAuto.entity.shop.CartItem;
import uz.pdp.WebAuto.service.shop.CartService;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    // Foydalanuvchining savat ma'lumotlarini olish
    @GetMapping("/user/{userId}")
    @Operation(summary = "Foydalanuvchining savatini olish.", description = "Berilgan foydalanuvchi ID bo'yicha uning savat ma'lumotlarini qaytaradi.")
    public ResponseEntity<Cart> getCartByUserId(@PathVariable int userId) {
        Cart cart = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cart);
    }

    // Mahsulotni savatga qo'shish
    @PostMapping("/{cartId}/add")
    @Operation(summary = "Savatga mahsulot qo'shish.", description = "Berilgan savat ID bo'yicha mahsulotni savatga qo'shadi.")
    public ResponseEntity<Void> addProductToCart(
            @PathVariable int cartId,
            @RequestParam int productId,
            @RequestParam int quantity) {
        cartService.addProductToCart(cartId, productId, quantity);
        return ResponseEntity.ok().build();
    }

    // Savatdagi barcha mahsulotlarni olish
    @GetMapping("/{cartId}/items")
    @Operation(summary = "Savatdagi mahsulotlarni olish.", description = "Berilgan savat ID bo'yicha barcha mahsulotlar ro'yxatini qaytaradi.")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable int cartId) {
        return ResponseEntity.ok(cartService.getCartItems(cartId));
    }

    // Foydalanuvchi uchun yangi savat yaratish
    @PostMapping("/user/{userId}/create")
    @Operation(summary = "Yangi savat yaratish.", description = "Berilgan foydalanuvchi ID bo'yicha yangi savat yaratadi.")
    public ResponseEntity<Void> createCartForUser(@PathVariable int userId) {
        cartService.createCartForUser(userId);
        return ResponseEntity.ok().build();
    }

    // Savatdagi barcha mahsulotlarni o'chirish
    @DeleteMapping("/{cartId}/items")
    @Operation(summary = "Savatdagi mahsulotlarni o'chirish.", description = "Berilgan savat ID bo'yicha barcha mahsulotlarni savatdan o'chiradi.")
    public ResponseEntity<Void> deleteItemsByCartId(@PathVariable int cartId) {
        cartService.deleteItemsByCartId(cartId);
        return ResponseEntity.ok().build();
    }

    // Foydalanuvchining savatidagi mahsulotlar to'langan statusini yangilash
    @PutMapping("/user/{userId}/paid")
    @Operation(summary = "Savat to'lov statusini yangilash.", description = "Berilgan foydalanuvchi ID bo'yicha mahsulotlar to'langan statusini yangilaydi.")
    public ResponseEntity<Void> updateCartItemsPaidStatus(
            @PathVariable int userId,
            @RequestParam boolean isPaid) {
        cartService.updateCartItemsPaidStatus(userId, isPaid);
        return ResponseEntity.ok().build();
    }

    // Foydalanuvchining savatidagi mahsulotlarni olish
    @GetMapping("/user/{userId}/items")
    @Operation(summary = "Foydalanuvchining savatidagi mahsulotlarni olish.", description = "Berilgan foydalanuvchi ID bo'yicha savatdagi mahsulotlar ro'yxatini qaytaradi.")
    public ResponseEntity<List<CartItem>> getCartItemsByUserId(@PathVariable int userId) {
        return ResponseEntity.ok(cartService.getCartItemsByUserId(userId));
    }
}

package uz.pdp.WebAuto.controller.shop;

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

    @GetMapping("/user/{userId}")
    public ResponseEntity<Cart> getCartByUserId(@PathVariable int userId) {
        Cart cart = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/{cartId}/add")
    public ResponseEntity<Void> addProductToCart(
            @PathVariable int cartId,
            @RequestParam int productId,
            @RequestParam int quantity) {
        cartService.addProductToCart(cartId, productId, quantity);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{cartId}/items")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable int cartId) {
        return ResponseEntity.ok(cartService.getCartItems(cartId));
    }

    @PostMapping("/user/{userId}/create")
    public ResponseEntity<Void> createCartForUser(@PathVariable int userId) {
        cartService.createCartForUser(userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{cartId}/items")
    public ResponseEntity<Void> deleteItemsByCartId(@PathVariable int cartId) {
        cartService.deleteItemsByCartId(cartId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/user/{userId}/paid")
    public ResponseEntity<Void> updateCartItemsPaidStatus(
            @PathVariable int userId,
            @RequestParam boolean isPaid) {
        cartService.updateCartItemsPaidStatus(userId, isPaid);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}/items")
    public ResponseEntity<List<CartItem>> getCartItemsByUserId(@PathVariable int userId) {
        return ResponseEntity.ok(cartService.getCartItemsByUserId(userId));
    }
}

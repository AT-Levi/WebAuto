package uz.pdp.WebAuto.service.shop;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.WebAuto.entity.shop.Cart;
import uz.pdp.WebAuto.entity.shop.CartItem;
import uz.pdp.WebAuto.repository.shop.CartItemRepository;
import uz.pdp.WebAuto.repository.shop.CartRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public Cart getCartByUserId(int userId) {
        return cartRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found for userId: " + userId));
    }

    public void addProductToCart(int cartId, int productId, int quantity) {
        CartItem item = cartItemRepository.findByCartId(cartId).stream()
                .filter(ci -> ci.getProductId() == productId)
                .findFirst()
                .orElse(CartItem.builder()
                        .cartId(cartId)
                        .productId(productId)
                        .quantity(0)
                        .isPaid(false)
                        .build());
        item.setQuantity(item.getQuantity() + quantity);
        cartItemRepository.save(item);
    }

    public List<CartItem> getCartItems(int cartId) {
        return cartItemRepository.findByCartId(cartId);
    }

    public void createCartForUser(int userId) {
        Cart cart = Cart.builder()
                .userId(userId)
                .createDate(new Date())
                .paid(false)
                .build();
        cartRepository.save(cart);
    }

    public void deleteItemsByCartId(int cartId) {
        cartItemRepository.deleteAll(cartItemRepository.findByCartId(cartId));
    }

    public void updateCartItemsPaidStatus(int userId, boolean isPaid) {
        List<CartItem> items = cartItemRepository.findCartItemsByUserId(userId);
        items.forEach(item -> item.setPaid(isPaid));
        cartItemRepository.saveAll(items);
    }

    public List<CartItem> getCartItemsByUserId(int userId) {
        return cartItemRepository.findCartItemsByUserId(userId);
    }
}

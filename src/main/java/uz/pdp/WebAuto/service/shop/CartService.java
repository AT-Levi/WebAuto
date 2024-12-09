package uz.pdp.WebAuto.service.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.WebAuto.entity.shop.Cart;
import uz.pdp.WebAuto.entity.shop.CartItem;
import uz.pdp.WebAuto.entity.shop.Product;
import uz.pdp.WebAuto.repository.shop.CartRepository;
import uz.pdp.WebAuto.repository.shop.ProductRepository;

@Service
public class CartService {
    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public Cart getCartByUserId(String userId) {
        return cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUserId(userId);
                    newCart.setTotalPrice(0.0);
                    return cartRepository.save(newCart);
                });
    }

    public Cart addProductToCart(String userId, Long productId, Integer quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Mahsulot topilmadi"));

        if (product.getStock() < quantity) {
            throw new RuntimeException("Omborda yetarli mahsulot yo'q");
        }

        Cart cart = getCartByUserId(userId);

        CartItem existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            CartItem newItem = new CartItem();
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            cart.getItems().add(newItem);
        }

        // Narxni hisoblash
        double newTotalPrice = cart.getItems().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
        cart.setTotalPrice(newTotalPrice);

        // Ombordagi mahsulotni kamaytirish
        product.setStock(product.getStock() - quantity);
        productRepository.save(product);

        return cartRepository.save(cart);
    }
}


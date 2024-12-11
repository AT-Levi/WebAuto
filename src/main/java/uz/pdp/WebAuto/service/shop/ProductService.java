package uz.pdp.WebAuto.service.shop;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.WebAuto.entity.shop.CartItem;
import uz.pdp.WebAuto.entity.shop.Product;
import uz.pdp.WebAuto.repository.shop.ProductsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductsRepository productsRepository;
    private final CartService cartService;

    public void addProduct(String productCategory, String productName, int productPrice,
                           String productDescription, int productCount, String productImageUrl, String base64Img) {
        Product product = Product.builder()
                .category(productCategory)
                .name(productName)
                .price(productPrice)
                .description(productDescription)
                .count(productCount)
                .imageUrl(productImageUrl)
                .imageBase64(base64Img)
                .build();
        productsRepository.save(product);
    }

    public Product getProductById(int productId) {
        return productsRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));
    }

    public List<Product> getAllProducts() {
        return productsRepository.findAll();
    }



    public boolean deleteProductById(int id) {
        if (productsRepository.existsById(id)) {
            productsRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void reduceAmountOfProductByProductId(List<CartItem> items) {
        items.forEach(item -> {
            Product product = getProductById(item.getProductId());
            if (product.getCount() > 0) {
                product.setCount(product.getCount() - 1);
                productsRepository.save(product);
            }
        });
    }

    public List<Product> getArchivedProductsByUserId(int userId) {
        List<CartItem> cartItems = cartService.getCartItemsByUserId(userId);
        List<Integer> archivedProductIds = cartItems.stream()
                .filter(CartItem::isPaid)
                .map(CartItem::getProductId)
                .collect(Collectors.toList());
        return productsRepository.findByIds(archivedProductIds);
    }

}


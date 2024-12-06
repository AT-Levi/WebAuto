package uz.pdp.WebAuto.service.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.WebAuto.entity.shop.Product;
import uz.pdp.WebAuto.repository.shop.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;


    public Product addProduct(Product product) {
        if (product.getPrice() <= 0) {
            throw new RuntimeException("Narx nol yoki manfiy bo'lishi mumkin emas!");
        }
        if (product.getStock() < 0) {
            throw new RuntimeException("Ombordagi mahsulot soni manfiy bo'lishi mumkin emas!");
        }
        return productRepository.save(product);
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> filterProducts(String category, Double minPrice, Double maxPrice, Double minPower, Double maxPower) {
        return productRepository.findAll().stream()
                .filter(p -> category == null || p.getCategory().equalsIgnoreCase(category))
                .filter(p -> (minPrice == null || maxPrice == null) || (p.getPrice() >= minPrice && p.getPrice() <= maxPrice))
                .filter(p -> (minPower == null || maxPower == null) || (p.getPower() >= minPower && p.getPower() <= maxPower))
                .collect(Collectors.toList());
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public List<Product> searchProducts(String query) {
        return productRepository.searchByNameOrCategory(query);
    }
}


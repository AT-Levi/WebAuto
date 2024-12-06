package uz.pdp.WebAuto.repository.shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.pdp.WebAuto.entity.shop.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category);
    List<Product> findByPriceBetween(Double min, Double max);
    List<Product> findByPowerBetween(Double min, Double max);
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:query% OR p.category LIKE %:query%")
    List<Product> searchByNameOrCategory(@Param("query") String query);
}


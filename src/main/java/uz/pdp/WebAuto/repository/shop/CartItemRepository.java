package uz.pdp.WebAuto.repository.shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.pdp.WebAuto.entity.shop.CartItem;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findByCartId(int cartId);

    @Query("SELECT ci FROM CartItem ci JOIN Cart c ON ci.cartId = c.id WHERE c.userId = :userId")
    List<CartItem> findCartItemsByUserId(@Param("userId") int userId);
}





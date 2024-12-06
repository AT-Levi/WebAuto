package uz.pdp.WebAuto.repository.shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.WebAuto.entity.shop.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}


package uz.pdp.WebAuto.repository.shop;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.WebAuto.entity.shop.OrderDetails;

import java.util.List;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {

    List<OrderDetails> findByUserId(int userId);
}


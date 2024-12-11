package uz.pdp.WebAuto.service.shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.WebAuto.entity.shop.CartItem;
import uz.pdp.WebAuto.entity.shop.OrderDetails;
import uz.pdp.WebAuto.repository.shop.OrderDetailsRepository;

import java.sql.SQLException;
import java.util.List;

@Service
public class OrderDetailsService {

    private final ProductService productService;
    private final OrderDetailsRepository orderDetailsRepository;
    private final CartService cartService;

    @Autowired
    public OrderDetailsService(ProductService productService, OrderDetailsRepository orderDetailsRepository, CartService cartService) {
        this.productService = productService;
        this.orderDetailsRepository = orderDetailsRepository;
        this.cartService = cartService;
    }

    @Transactional
    public void save(OrderDetails orderDetails, List<CartItem> items) throws SQLException {

        orderDetailsRepository.save(orderDetails);

        cartService.deleteItemsByCartId(orderDetails.getCart_id());

        productService.reduceAmountOfProductByProductId(items);
    }

    public List<OrderDetails> getArchives(int userId) {
        return orderDetailsRepository.findByUserId(userId);
    }
}


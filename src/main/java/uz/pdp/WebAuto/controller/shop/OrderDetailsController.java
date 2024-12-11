package uz.pdp.WebAuto.controller.shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.WebAuto.entity.shop.OrderDetails;
import uz.pdp.WebAuto.entity.shop.OrderDetailsRequest;
import uz.pdp.WebAuto.service.shop.OrderDetailsService;

import java.util.List;

@RestController
@RequestMapping("/api/order-details")
public class OrderDetailsController {

    private final OrderDetailsService orderDetailsService;

    @Autowired
    public OrderDetailsController(OrderDetailsService orderDetailsService) {
        this.orderDetailsService = orderDetailsService;
    }


    @PostMapping
    public ResponseEntity<Void> saveOrderDetails(@RequestBody OrderDetailsRequest orderDetailsRequest) {
        try {
            orderDetailsService.save(orderDetailsRequest.getOrderDetails(), orderDetailsRequest.getItems());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/archives/{userId}")
    public ResponseEntity<List<OrderDetails>> getOrderArchives(@PathVariable int userId) {
        List<OrderDetails> orderDetails = orderDetailsService.getArchives(userId);
        if (orderDetails.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(orderDetails);
    }
}

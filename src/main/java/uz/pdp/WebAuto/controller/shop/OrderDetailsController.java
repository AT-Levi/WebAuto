package uz.pdp.WebAuto.controller.shop;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.WebAuto.entity.shop.OrderDetails;
import uz.pdp.WebAuto.entity.shop.OrderDetailsRequest;
import uz.pdp.WebAuto.service.shop.OrderDetailsService;

import java.util.List;

@RestController
@RequestMapping("/shop/order-details")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('USER', 'ADMIN', 'DEALER')")
@SecurityRequirement(name = "bearerAuth")
public class OrderDetailsController {

    private final OrderDetailsService orderDetailsService;

    @Operation(
            summary = "Buyurtma tafsilotlarini saqlash",
            description = "Buyurtma tafsilotlari va unga tegishli elementlarni saqlash uchun ishlatiladi."
    )
    @PostMapping
    public ResponseEntity<Void> saveOrderDetails(@RequestBody OrderDetailsRequest orderDetailsRequest) {
        try {
            orderDetailsService.save(orderDetailsRequest.getOrderDetails(), orderDetailsRequest.getItems());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @Operation(
            summary = "Foydalanuvchi buyurtma arxivlarini olish",
            description = "Foydalanuvchining barcha buyurtma arxivlarini ID asosida olish uchun ishlatiladi."
    )
    @GetMapping("/archives/{userId}")
    public ResponseEntity<List<OrderDetails>> getOrderArchives(@PathVariable int userId) {
        List<OrderDetails> orderDetails = orderDetailsService.getArchives(userId);
        if (orderDetails.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(orderDetails);
    }
}

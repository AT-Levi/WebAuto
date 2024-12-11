package uz.pdp.WebAuto.entity.shop;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int cartId;
    private int productId;
    private int quantity;
    private boolean isPaid;

    public CartItem(int id, int quantity, boolean b) {}

    public CartItem(int cartId, int productId, int quantity, boolean isPaid) {
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
        this.isPaid = false;
    }

    public CartItem() {

    }
}


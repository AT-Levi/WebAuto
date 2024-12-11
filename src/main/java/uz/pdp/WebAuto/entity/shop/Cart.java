package uz.pdp.WebAuto.entity.shop;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    private boolean paid = false;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.createDate = new Date();
    }

    public Cart(int userId) {
        this.userId = userId;
    }

    public void addProduct(Product product, int quantity) {
        for (CartItem item : items) {
            if (item.getProductId() == product.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        this.items.add(new CartItem(product.getId(), quantity, false));
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
        for (CartItem item : items) {
            item.setPaid(paid);
        }
    }
}

package uz.pdp.WebAuto.entity.shop;

import java.util.List;

public class OrderDetailsRequest {

    private OrderDetails orderDetails;
    private List<CartItem> items;

    // Getters and setters
    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }
}

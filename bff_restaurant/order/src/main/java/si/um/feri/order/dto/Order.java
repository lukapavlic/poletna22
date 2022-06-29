package si.um.feri.order.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class Order {

    private int orderId;

    private int restaurantId;

    private int customerId;

    private String orderFromMenu;


    private boolean completed;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getOrderFromMenu() {
        return orderFromMenu;
    }

    public void setOrderFromMenu(String orderFromMenu) {
        this.orderFromMenu = orderFromMenu;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", restaurantId=" + restaurantId +
                ", customerId=" + customerId +
                ", orderFromMenu='" + orderFromMenu + '\'' +
                ", completed=" + completed +
                '}';
    }
}

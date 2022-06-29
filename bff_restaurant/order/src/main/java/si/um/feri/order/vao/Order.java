package si.um.feri.order.vao;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Ticket")
public class Order {

    public Order() {
    }

    public Order(int restaurantId, int customerId, String orderFromMenu, boolean completed) {
        this.restaurantId = restaurantId;
        this.customerId = customerId;
        this.orderFromMenu = orderFromMenu;
        this.completed = completed;
    }

    public Order(si.um.feri.order.dto.Order dto) {
        restaurantId = dto.getRestaurantId();
        customerId = dto.getCustomerId();
        orderFromMenu = dto.getOrderFromMenu();
        completed = dto.isCompleted();
    }

    public void updateFrom(si.um.feri.order.dto.Order dto) {
        restaurantId = dto.getRestaurantId();
        customerId = dto.getCustomerId();
        orderFromMenu = dto.getOrderFromMenu();
        completed = dto.isCompleted();
    }

    public si.um.feri.order.dto.Order toDto() {
        si.um.feri.order.dto.Order dto = new si.um.feri.order.dto.Order();
        dto.setOrderId(orderId);
        dto.setRestaurantId(restaurantId);
        dto.setCustomerId(customerId);
        dto.setOrderFromMenu(orderFromMenu);
        dto.setCompleted(completed);
        return dto;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int orderId;

    private int restaurantId;

    private int customerId;

    private String orderFromMenu;

    private boolean completed;

    private Calendar created = new GregorianCalendar();

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

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", restaurantId=" + restaurantId +
                ", customerId=" + customerId +
                ", orderFromMenu='" + orderFromMenu + '\'' +
                ", completed=" + completed +
                ", created=" + created +
                '}';
    }
}

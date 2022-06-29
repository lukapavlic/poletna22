package si.um.feri.client.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class Order {

	public Order(int restaurantId, int customerId, String orderFromMenu) {
		this.restaurantId = restaurantId;
		this.customerId = customerId;
		this.orderFromMenu = orderFromMenu;
	}

	private int orderId;

	private int restaurantId;

	private int customerId;

	private String orderFromMenu;

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

	@Override
	public String toString() {
		return "Order [id=" + orderId + ", restaurantId=" + restaurantId + ", customerId=" + customerId
				+ ", orderFromMenu=" + orderFromMenu + "]";
	}

}

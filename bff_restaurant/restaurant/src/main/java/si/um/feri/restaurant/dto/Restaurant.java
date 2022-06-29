package si.um.feri.restaurant.dto;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class Restaurant {

	private int id;

	private String restaurantName;

	private String address;

	private Map<String, Double> menu = new HashMap<String, Double>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Map<String, Double> getMenu() {
		return menu;
	}

	public void setMenu(Map<String, Double> menu) {
		this.menu = menu;
	}

	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", restaurantName=" + restaurantName + ", address=" + address + ", menu=" + menu
				+ "]";
	}

}

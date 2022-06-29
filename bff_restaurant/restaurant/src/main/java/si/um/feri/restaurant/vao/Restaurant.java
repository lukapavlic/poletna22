package si.um.feri.restaurant.vao;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Restaurant")
public class Restaurant {

	public Restaurant() {
	}

	public Restaurant(String restaurantName, String address, Map<String, Double> menu) {
		this.restaurantName = restaurantName;
		this.address = address;
		this.menu = menu;
	}

	public Restaurant(si.um.feri.restaurant.dto.Restaurant dto) {
		restaurantName = dto.getRestaurantName();
		address = dto.getAddress();
		menu = dto.getMenu();
	}

	public void updateFrom(si.um.feri.restaurant.dto.Restaurant dto) {
		restaurantName = dto.getRestaurantName();
		address = dto.getAddress();
		menu = dto.getMenu();
	}

	public si.um.feri.restaurant.dto.Restaurant toDto() {
		si.um.feri.restaurant.dto.Restaurant dto = new si.um.feri.restaurant.dto.Restaurant();
		dto.setId(id);
		dto.setRestaurantName(restaurantName);
		dto.setAddress(address);
		dto.setMenu(menu);
		return dto;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	private String restaurantName;

	private String address;

	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, Double> menu = new HashMap<String, Double>();

	private Calendar created = new GregorianCalendar();

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

	public Calendar getCreated() {
		return created;
	}

	public void setCreated(Calendar created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", restaurantName=" + restaurantName + ", address=" + address + ", menu=" + menu
				+ ", created=" + created + "]";
	}

}

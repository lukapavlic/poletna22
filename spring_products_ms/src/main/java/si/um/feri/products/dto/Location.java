package si.um.feri.products.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class Location {

	private int id;
	
	private String name;
	
	private String address;
	
	private Boolean hasProducts;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getHasProducts() {
		return hasProducts;
	}

	public void setHasProducts(Boolean hasProducts) {
		this.hasProducts = hasProducts;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", name=" + name + ", address=" + address + ", hasProducts=" + hasProducts + "]";
	}

}

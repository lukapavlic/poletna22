package si.um.feri.products.vao;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Location")
public class Location {
	
	public Location() {
	}
	
	public Location(si.um.feri.products.dto.Location dto) {
		name=dto.getName();
		address=dto.getAddress();
	}
	
	public void updateFrom(si.um.feri.products.dto.Location dto/*, ShopType s*/) {
		name=dto.getName();
		address=dto.getAddress();
	}
	
	public si.um.feri.products.dto.Location toDto() {
		si.um.feri.products.dto.Location dto=new si.um.feri.products.dto.Location();
		dto.setId(id);
		dto.setName(name);
		dto.setAddress(address);
		return dto;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	
	private String address;
	
	private Calendar created=new GregorianCalendar();

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

	public Calendar getCreated() {
		return created;
	}

	public void setCreated(Calendar created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", name=" + name + ", address=" + address + ", created=" + created + "]";
	}
	
}

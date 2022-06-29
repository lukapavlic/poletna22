package si.um.feri.customer.vao;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Customer")
public class Customer {

	public Customer() {
	}

	public Customer(String name, String address) {
		this.name = name;
		this.address = address;
	}

	public Customer(si.um.feri.customer.dto.Customer dto) {
		name = dto.getName();
		address = dto.getAddress();
	}

	public void updateFrom(si.um.feri.customer.dto.Customer dto) {
		name = dto.getName();
		address = dto.getAddress();
	}

	public si.um.feri.customer.dto.Customer toDto() {
		si.um.feri.customer.dto.Customer dto = new si.um.feri.customer.dto.Customer();
		dto.setId(id);
		dto.setName(name);
		dto.setAddress(address);
		return dto;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	private String name;

	private String address;

	private Calendar created = new GregorianCalendar();

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
		return "Customer [id=" + id + ", name=" + name + ", address=" + address + ", created=" + created + "]";
	}

}

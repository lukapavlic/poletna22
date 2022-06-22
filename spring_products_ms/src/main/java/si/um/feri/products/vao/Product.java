package si.um.feri.products.vao;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Product")
public class Product {

	public Product() {
	}

	public Product(si.um.feri.products.dto.Product dto, ProductCategory pc) {
		setName(dto.getName());
		setCategory(pc);
	}
	
	public void updateFrom(si.um.feri.products.dto.Product dto, ProductCategory pc) {
		setName(dto.getName());
		setCategory(pc);
	}
	
	public si.um.feri.products.dto.Product toDto() {
		si.um.feri.products.dto.Product dto=new si.um.feri.products.dto.Product();
		dto.setId(getId());
		dto.setName(getName());
		if (category!=null) dto.setCategoryId(category.getId());
		return dto;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected int id;
	
	protected String name;
	
	protected Calendar created=new GregorianCalendar();
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private ProductCategory category;

	@ManyToOne
	@JoinColumn(name = "location_id")
	private Location location;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Calendar getCreated() {
		return created;
	}

	public void setCreated(Calendar created) {
		this.created = created;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", created=" + created + ", category=" + category
				+ ", location=" + location + "]";
	}
	
}

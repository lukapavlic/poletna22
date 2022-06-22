package si.um.feri.products.vao;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ProductCategory")
public class ProductCategory {
	
	public ProductCategory() {
	}
	
	public ProductCategory(si.um.feri.products.dto.ProductCategory dto) {
		this.name=dto.getName();
	}
	
	public void updateFrom(si.um.feri.products.dto.ProductCategory dto) {
		this.name=dto.getName();
	}
	
	public si.um.feri.products.dto.ProductCategory toDto() {
		si.um.feri.products.dto.ProductCategory dto=new si.um.feri.products.dto.ProductCategory();
		dto.setId(getId());
		dto.setName(getName());
		return dto;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected int id;

	protected String name;

	protected Calendar created = new GregorianCalendar();

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

	public Calendar getCreated() {
		return created;
	}

	public void setCreated(Calendar created) {
		this.created = created;
	}
	
	@Override
	public String toString() {
		return "ProductCategory [id=" + id + ", name=" + name + ", created=" + created + "]";
	}
	
}

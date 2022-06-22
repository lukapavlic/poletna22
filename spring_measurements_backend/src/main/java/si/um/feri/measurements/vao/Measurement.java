package si.um.feri.measurements.vao;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import si.um.feri.measurements.Constants;
import si.um.feri.measurements.dto.post.PostMeasurement;

/**
 * A measurement of a product
 * Measurement type and Employee are just "markers"
 * Value - a measurement (temperature) being measured
 * isOK - regard to a product min-max
 */
@Entity
@Table(name = "Measurement")
public class Measurement {
	
	public Measurement() {
	}

	public Measurement(PostMeasurement m, Product p) {
		this.value=m.avgTemperature();
		this.measurementType=m.measurementType();
		this.subject=Constants.MeasurementSubjects.PRODUCT.getValue();
		this.product=p;
	}
	
	public si.um.feri.measurements.dto.Measurement toDto() {
		return new si.um.feri.measurements.dto.Measurement(
			id,
			Constants.JSON_DATE_FORMAT.format(created),
			(product!=null)?product.getId():-1,
			value,
			employee,
			isOk,
			measurementType
		);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private double value;
	
	private LocalDateTime created=LocalDateTime.now();
	
	private long createdLong=System.currentTimeMillis();
	
	private int subject=Constants.MeasurementSubjects.PRODUCT.getValue();
	
	private String measurementType;
	
	private String employee="Employee";

	private boolean isOk=true;
	
	@ManyToOne
	private Product product;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public long getCreatedLong() {
		return createdLong;
	}

	public void setCreatedLong(long createdLong) {
		this.createdLong = createdLong;
	}

	public int getSubject() {
		return subject;
	}

	public void setSubject(int subject) {
		this.subject = subject;
	}

	public String getMeasurementType() {
		return measurementType;
	}

	public void setMeasurementType(String measurementType) {
		this.measurementType = measurementType;
	}

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean ok) {
		isOk = ok;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Measurement{" +
				"id=" + id +
				", value=" + value +
				", created=" + created +
				", createdLong=" + createdLong +
				", subject=" + subject +
				", measurementType='" + measurementType + '\'' +
				", employee='" + employee + '\'' +
				", isOk=" + isOk +
				", product=" + product +
				'}';
	}

}

package si.um.feri.measurements.vao;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Product being measured
 * minMeasure - if a measurement of this product is below, the measurement will be marked as "not OK"
 * maxMeasure - if a measurement of this product is over, the measurement will be marked as "not OK"
 */
@Entity
@Table(name = "Product")
public class Product {

	public Product() {
	}

	public Product(si.um.feri.measurements.dto.Product dto) {
		setName(dto.name());
		setMaxMeasure(dto.maxMeasure());
		setMinMeasure(dto.minMeasure());
	}
	
	public void updateFrom(si.um.feri.measurements.dto.Product dto) {
		setName(dto.name());
		setMaxMeasure(dto.maxMeasure());
		setMinMeasure(dto.minMeasure());
	}
	
	public si.um.feri.measurements.dto.Product toDto() {
		return new si.um.feri.measurements.dto.Product(
			getId(),
			getName(),
			maxMeasure,
			minMeasure);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected int id;

	protected String name;

	protected LocalDateTime created=LocalDateTime.now();

	protected double maxMeasure;

	protected double minMeasure;

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

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public double getMaxMeasure() {
		return maxMeasure;
	}

	public void setMaxMeasure(double maxMeasure) {
		this.maxMeasure = maxMeasure;
	}

	public double getMinMeasure() {
		return minMeasure;
	}

	public void setMinMeasure(double minMeasure) {
		this.minMeasure = minMeasure;
	}

	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", name='" + name + '\'' +
				", created=" + created +
				", maxMeasure=" + maxMeasure +
				", minMeasure=" + minMeasure +
				'}';
	}

}

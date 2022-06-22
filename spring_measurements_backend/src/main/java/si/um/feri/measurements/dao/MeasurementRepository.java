package si.um.feri.measurements.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import si.um.feri.measurements.vao.Measurement;
import si.um.feri.measurements.vao.Product;

public interface MeasurementRepository extends CrudRepository<Measurement, Integer> {

	Long countByProduct(Product p);

	List<Measurement> findByCreatedLongGreaterThan(long createdLong);
	
}
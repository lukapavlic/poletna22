package si.um.feri.measurements.dao;

import org.springframework.data.repository.CrudRepository;
import si.um.feri.measurements.vao.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
	
}
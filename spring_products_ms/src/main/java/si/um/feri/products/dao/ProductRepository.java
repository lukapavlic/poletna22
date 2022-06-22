package si.um.feri.products.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import si.um.feri.products.vao.Product;
import si.um.feri.products.vao.ProductCategory;

public interface ProductRepository extends CrudRepository<Product, Integer> {

	List<Product> findByCategory(ProductCategory pc);

}
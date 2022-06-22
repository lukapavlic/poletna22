package si.um.feri.products.dao;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import si.um.feri.products.vao.ProductCategory;

public interface ProductCategoryRepository extends PagingAndSortingRepository<ProductCategory, Integer> {

	List<ProductCategory> findAllByNameLike(String name, Pageable pageable);
	
	long countByNameLike(String name);
	
}
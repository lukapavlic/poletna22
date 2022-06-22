package si.um.feri.products.dao;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import si.um.feri.products.vao.Location;

public interface LocationRepository extends PagingAndSortingRepository<Location, Integer> {

	List<Location> findAllByNameLike(String name, Pageable pageable);
	
	long countByNameLike(String name);
	
}
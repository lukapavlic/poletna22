package si.um.feri.restaurant.dao;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import si.um.feri.restaurant.vao.Restaurant;

public interface RestaurantRepository extends PagingAndSortingRepository<Restaurant, Integer> {

	List<Restaurant> findAllByRestaurantNameLike(String name, Pageable pageable);

}
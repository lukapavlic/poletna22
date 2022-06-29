package si.um.feri.order.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import si.um.feri.order.vao.Order;

public interface OrderRepository extends PagingAndSortingRepository<Order, Integer> {

	List<Order> findAllByRestaurantId(int restaurantId);

	List<Order> findAllByCustomerId(int customerId);

	List<Order> findAllByOrderFromMenu(String orderFromMenu, Pageable pageable);
}

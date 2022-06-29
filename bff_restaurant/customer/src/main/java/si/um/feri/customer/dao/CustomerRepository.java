package si.um.feri.customer.dao;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import si.um.feri.customer.vao.Customer;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer> {

	List<Customer> findAllByNameLike(String name, Pageable pageable);

}

package si.um.feri.customer.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import si.um.feri.customer.dao.CustomerRepository;
import si.um.feri.customer.vao.Customer;

@CrossOrigin
@RestController
public class CustomerController {

	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

	// Customer

	@Autowired
	private CustomerRepository daoCustomer;

	private List<si.um.feri.customer.dto.Customer> translateCustomer(Iterable<Customer> list) {
		List<si.um.feri.customer.dto.Customer> ret = new ArrayList<>();
		for (Customer sc : list)
			ret.add(sc.toDto());
		return ret;
	}

	@GetMapping("/customers")
	public @ResponseBody Iterable<si.um.feri.customer.dto.Customer> getAllCustomers(
			@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize,
			@RequestParam(required = false) String query) {

		int pageI = 0;
		int pageSizeI = Integer.MAX_VALUE;
		if (page != null && pageSize != null) {
			pageI = page.intValue();
			pageSizeI = pageSize.intValue();
		}

		log.info("" + pageI);
		log.info("" + pageSizeI);
		log.info("" + query);

		Iterable<Customer> ret = null;
		if (query != null)
			ret = daoCustomer.findAllByNameLike("%" + query + "%", PageRequest.of(pageI, pageSizeI, Sort.by("name")));
		else
			ret = daoCustomer.findAll(PageRequest.of(pageI, pageSizeI, Sort.by("name")));

		return translateCustomer(ret);
	}

	@GetMapping("/customers/{id}")
	public ResponseEntity<si.um.feri.customer.dto.Customer> getCustomerById(@PathVariable("id") int id) {
		Optional<Customer> val = daoCustomer.findById(id);
		if (val.isEmpty()) {
			log.info("/customers/" + id + " ; Customer not found!");
			return new ResponseEntity<si.um.feri.customer.dto.Customer>(HttpStatus.NOT_ACCEPTABLE);
		}
		return ResponseEntity.ok(val.get().toDto());
	}

	@PostMapping("/customers")
	public ResponseEntity<si.um.feri.customer.dto.Customer> postCustomer(
			@RequestBody si.um.feri.customer.dto.Customer c) {
		Customer vao = new Customer(c);
		daoCustomer.save(vao);
		return ResponseEntity.ok(vao.toDto());
	}

	@PutMapping("/customers/{id}")
	public ResponseEntity<si.um.feri.customer.dto.Customer> putCustomer(@PathVariable("id") int id,
			@RequestBody si.um.feri.customer.dto.Customer c) {
		// validate
		Optional<Customer> val = daoCustomer.findById(id);
		if (val.isEmpty()) {
			log.info("/customers/" + id + " ; Customer not found!");
			return new ResponseEntity<si.um.feri.customer.dto.Customer>(HttpStatus.NOT_ACCEPTABLE);
		}

		Customer vao = val.get();
		vao.updateFrom(c);
		daoCustomer.save(vao);
		return ResponseEntity.ok(vao.toDto());
	}

	@DeleteMapping("/customers/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") int id) {
		// validate
		Optional<Customer> val = daoCustomer.findById(id);
		if (val.isEmpty()) {
			log.info("/customers/" + id + " ; Customer not found!");
			return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
		}

		Customer vao = val.get();
		daoCustomer.delete(vao);
		return ResponseEntity.ok("deleted");
	}

}

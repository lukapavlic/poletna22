package si.um.feri.order.rest;

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

import si.um.feri.order.dao.OrderRepository;
import si.um.feri.order.vao.Order;

@CrossOrigin
@RestController
public class OrderController {

	private static final Logger log = LoggerFactory.getLogger(OrderController.class);

	// Order

	@Autowired
	private OrderRepository daoOrder;

	private List<si.um.feri.order.dto.Order> translateOrder(Iterable<Order> list) {
		List<si.um.feri.order.dto.Order> ret = new ArrayList<>();
		for (Order or : list)
			ret.add(or.toDto());
		return ret;
	}

	@GetMapping("/orders")
	public @ResponseBody Iterable<si.um.feri.order.dto.Order> getAllOrders(
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

		Iterable<Order> ret = null;
		if (query != null)
			ret = daoOrder.findAllByOrderFromMenu("%" + query + "%",
					PageRequest.of(pageI, pageSizeI, Sort.by("orderFromMenu")));
		else
			ret = daoOrder.findAll(PageRequest.of(pageI, pageSizeI, Sort.by("orderFromMenu")));

		return translateOrder(ret);
	}

	@GetMapping("/orders/{id}")
	public ResponseEntity<si.um.feri.order.dto.Order> getOrderById(@PathVariable("id") int id) {
		Optional<Order> val = daoOrder.findById(id);
		if (val.isEmpty()) {
			log.info("/orders/" + id + " ; Order not found!");
			return new ResponseEntity<si.um.feri.order.dto.Order>(HttpStatus.NOT_ACCEPTABLE);
		}
		return ResponseEntity.ok(val.get().toDto());
	}

	@PostMapping("/orders")
	public ResponseEntity<si.um.feri.order.dto.Order> postOrder(@RequestBody si.um.feri.order.dto.Order o) {
		Order vao = new Order(o);
		daoOrder.save(vao);
		return ResponseEntity.ok(vao.toDto());
	}

	@PutMapping("/orders/{id}")
	public ResponseEntity<si.um.feri.order.dto.Order> putOrder(@PathVariable("id") int id,
			@RequestBody si.um.feri.order.dto.Order o) {
		// validate
		Optional<Order> val = daoOrder.findById(id);
		if (val.isEmpty()) {
			log.info("/orders/" + id + " ; Order not found!");
			return new ResponseEntity<si.um.feri.order.dto.Order>(HttpStatus.NOT_ACCEPTABLE);
		}

		Order vao = val.get();
		vao.updateFrom(o);
		daoOrder.save(vao);
		return ResponseEntity.ok(vao.toDto());
	}

	@DeleteMapping("/orders/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable("id") int id) {
		// validate
		Optional<Order> val = daoOrder.findById(id);
		if (val.isEmpty()) {
			log.info("/orders/" + id + " ; Order not found!");
			return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
		}

		Order vao = val.get();
		daoOrder.delete(vao);
		return ResponseEntity.ok("deleted");
	}

	// Restaurant orders

	@GetMapping("/orders/restaurants/{id}")
	public Iterable<si.um.feri.order.dto.Order> getOrdersByRestaurantId(@PathVariable("id") int id) {
		Iterable<Order> val = daoOrder.findAllByRestaurantId(id);
		return translateOrder(val);
	}

	// Customer orders

	@GetMapping("/orders/customers/{id}")
	public Iterable<si.um.feri.order.dto.Order> getOrdersByCustomerId(@PathVariable("id") int id) {
		Iterable<Order> val = daoOrder.findAllByCustomerId(id);
		return translateOrder(val);
	}

}

package si.um.feri.restaurant.rest;

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

import si.um.feri.restaurant.dao.RestaurantRepository;
import si.um.feri.restaurant.vao.Restaurant;

@CrossOrigin
@RestController
public class RestaurantController {

	private static final Logger log = LoggerFactory.getLogger(RestaurantController.class);

	// Restaurant

	@Autowired
	private RestaurantRepository daoRestaurant;

	private List<si.um.feri.restaurant.dto.Restaurant> translateRestaurant(Iterable<Restaurant> list) {
		List<si.um.feri.restaurant.dto.Restaurant> ret = new ArrayList<>();
		for (Restaurant sr : list)
			ret.add(sr.toDto());
		return ret;
	}

	@GetMapping("/restaurants")
	public @ResponseBody Iterable<si.um.feri.restaurant.dto.Restaurant> getAllRestaurants(
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

		Iterable<Restaurant> ret = null;
		if (query != null)
			ret = daoRestaurant.findAllByRestaurantNameLike("%" + query + "%",
					PageRequest.of(pageI, pageSizeI, Sort.by("restaurantName")));
		else
			ret = daoRestaurant.findAll(PageRequest.of(pageI, pageSizeI, Sort.by("restaurantName")));

		return translateRestaurant(ret);
	}

	@GetMapping("/restaurants/{id}")
	public ResponseEntity<si.um.feri.restaurant.dto.Restaurant> getRestaurantById(@PathVariable("id") int id) {
		Optional<Restaurant> val = daoRestaurant.findById(id);
		if (val.isEmpty()) {
			log.info("/restaurants/" + id + " ; Restaurant not found!");
			return new ResponseEntity<si.um.feri.restaurant.dto.Restaurant>(HttpStatus.NOT_ACCEPTABLE);
		}
		return ResponseEntity.ok(val.get().toDto());
	}

	@PostMapping("/restaurants")
	public ResponseEntity<si.um.feri.restaurant.dto.Restaurant> postRestaurant(
			@RequestBody si.um.feri.restaurant.dto.Restaurant r) {
		Restaurant vao = new Restaurant(r);
		daoRestaurant.save(vao);
		return ResponseEntity.ok(vao.toDto());
	}

	@PutMapping("/restaurants/{id}")
	public ResponseEntity<si.um.feri.restaurant.dto.Restaurant> putRestaurant(@PathVariable("id") int id,
			@RequestBody si.um.feri.restaurant.dto.Restaurant r) {
		// validate
		Optional<Restaurant> val = daoRestaurant.findById(id);
		if (val.isEmpty()) {
			log.info("/restaurants/" + id + " ; Restaurant not found!");
			return new ResponseEntity<si.um.feri.restaurant.dto.Restaurant>(HttpStatus.NOT_ACCEPTABLE);
		}

		Restaurant vao = val.get();
		vao.updateFrom(r);
		daoRestaurant.save(vao);
		return ResponseEntity.ok(vao.toDto());
	}

	@DeleteMapping("/restaurants/{id}")
	public ResponseEntity<String> deleteRestaurant(@PathVariable("id") int id) {
		// validate
		Optional<Restaurant> val = daoRestaurant.findById(id);
		if (val.isEmpty()) {
			log.info("/restaurants/" + id + " ; Restaurant not found!");
			return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
		}

		Restaurant vao = val.get();
		daoRestaurant.delete(vao);
		return ResponseEntity.ok("deleted");
	}

}

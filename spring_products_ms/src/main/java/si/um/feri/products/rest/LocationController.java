package si.um.feri.products.rest;

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
import si.um.feri.products.dao.LocationRepository;
import si.um.feri.products.vao.Location;

@CrossOrigin
@RestController
public class LocationController {

	private static final Logger log = LoggerFactory.getLogger(LocationController.class);
	
	@Autowired
	private LocationRepository dao;
	
	private List<si.um.feri.products.dto.Location> translateLocationListToDTOList(Iterable<Location> list) {
		List<si.um.feri.products.dto.Location> ret=new ArrayList<>();
		for (Location sc :list)
			ret.add(sc.toDto());
		return ret;
	}
	
	@GetMapping("/locations")
	public @ResponseBody Iterable<si.um.feri.products.dto.Location> getAllLocations(
			@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer pageSize,
			@RequestParam(required = false) String query
			) {
		
		int pageI=0;
		int pageSizeI=Integer.MAX_VALUE;
		if (page!=null && pageSize!=null) {
			pageI=page.intValue();
			pageSizeI=pageSize.intValue();
		}

		Iterable<Location> ret=null;
		if (query!=null) ret=dao.findAllByNameLike("%"+query+"%",PageRequest.of(pageI,pageSizeI, Sort.by("name")));
		else ret=dao.findAll(PageRequest.of(pageI,pageSizeI, Sort.by("name")));
		
		return translateLocationListToDTOList(ret);
	}
	
	@GetMapping("/locations/{id}")
	public ResponseEntity<si.um.feri.products.dto.Location> getById(@PathVariable("id") int id) {
		Optional<Location> val=dao.findById(id);
		if (val.isEmpty()) {
			log.info("/locations/"+id+" ; Location not found!");
			return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
		}
	    return ResponseEntity.ok(val.get().toDto());
	}

	@PostMapping("/locations")
	public ResponseEntity<si.um.feri.products.dto.Location> postLocation(@RequestBody si.um.feri.products.dto.Location l) {
		Location vao=new Location(l);
		dao.save(vao);
	    return ResponseEntity.ok(vao.toDto());
	}
	
	@PutMapping("/locations/{id}")
	public ResponseEntity<si.um.feri.products.dto.Location> putLocation(@PathVariable("id") int id, @RequestBody si.um.feri.products.dto.Location l) {
		//validate
		Optional<Location> val=dao.findById(id);
		if (val.isEmpty()) {
			log.info("/locations/"+id+" ; Location not found!");
			return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
		}
		
		Location vao=val.get();
		vao.updateFrom(l);
		dao.save(vao);
	    return ResponseEntity.ok(vao.toDto());
	}
	
	@DeleteMapping("/locations/{id}")
	public ResponseEntity<String> deleteLocation(@PathVariable("id") int id) {
		//validate
		Optional<Location> val=dao.findById(id);
		if (val.isEmpty()) {
			log.info("/locations/"+id+" ; Location not found!");
			return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
		}
		
		Location vao=val.get();
		dao.delete(vao);
	    return ResponseEntity.ok("deleted");
	}
	
}
package si.um.feri.products.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import si.um.feri.products.dao.ProductCategoryRepository;
import si.um.feri.products.dao.ProductRepository;
import si.um.feri.products.vao.Product;
import si.um.feri.products.vao.ProductCategory;

@CrossOrigin
@RestController
public class ProductController {

	private static final Logger log = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductCategoryRepository categoryDao;
	
	@Autowired
	private ProductRepository dao;
	
	static List<si.um.feri.products.dto.Product> translateProductListToDtoList(Iterable<Product> list) {
		List<si.um.feri.products.dto.Product> ret=new ArrayList<>();
		for (Product sc :list)
			ret.add(sc.toDto());
		return ret;
	}
	
	@GetMapping("/products")
	public @ResponseBody Iterable<si.um.feri.products.dto.Product> getAllProducts() {
		return translateProductListToDtoList(dao.findAll());
	}
	
	@GetMapping("/products/{id}")
	public @ResponseBody ResponseEntity<si.um.feri.products.dto.Product> getAllProductsById(@PathVariable("id") int id) {
		//validate
		Optional<Product> val=dao.findById(id);
		if (val.isEmpty()) {
			log.info("/products/"+id+" ; Product not found!");
			return new ResponseEntity("product-not-found",HttpStatus.NOT_ACCEPTABLE);
		}
		return ResponseEntity.ok(val.get().toDto());
	}
	
	@PostMapping("/products")
	public ResponseEntity<si.um.feri.products.dto.Product> postProduct(@RequestBody si.um.feri.products.dto.Product pc) {
		//validate
		Optional<ProductCategory> val=categoryDao.findById(pc.getCategoryId());
		if (val.isEmpty()) {
			log.info("POST /products; ProductCategroy not found!");
			return new ResponseEntity("productcategory-not-found",HttpStatus.NOT_ACCEPTABLE);
		}
		
		Product vao=new Product(pc,val.get());
		dao.save(vao);
	    return ResponseEntity.ok(vao.toDto());
	}
	
	@PutMapping("/products/{id}")
	public ResponseEntity<si.um.feri.products.dto.Product> putProduct(@PathVariable("id") int id, @RequestBody si.um.feri.products.dto.Product v) {
		//validate
		Optional<Product> val=dao.findById(id);
		if (val.isEmpty()) {
			log.info("/products/"+id+" ; Product not found!");
			return new ResponseEntity("product-not-found",HttpStatus.NOT_ACCEPTABLE);
		}
		Optional<ProductCategory> val2=categoryDao.findById(v.getCategoryId());
		if (val2.isEmpty()) {
			log.info("PUT /products; ProductCategroy not found!");
			return new ResponseEntity("productcategory-not-found",HttpStatus.NOT_ACCEPTABLE);
		}
		
		Product vao=val.get();
		vao.updateFrom(v,val2.get());
		dao.save(vao);
	    return ResponseEntity.ok(vao.toDto());
	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") int id) {
		//validate
		Optional<Product> val=dao.findById(id);
		if (val.isEmpty()) {
			log.info("/products/"+id+" ; Product not found!");
			return new ResponseEntity("product-not-found",HttpStatus.NOT_ACCEPTABLE);
		}
		Product vao=val.get();
		dao.delete(vao);
	    return ResponseEntity.ok("deleted");
	}

}
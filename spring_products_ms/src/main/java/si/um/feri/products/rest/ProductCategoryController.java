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
import si.um.feri.products.vao.ProductCategory;

@CrossOrigin
@RestController
public class ProductCategoryController {

	private static final Logger log = LoggerFactory.getLogger(ProductCategoryController.class);
	
	@Autowired
	private ProductCategoryRepository categoryDao;
	
	@Autowired
	private ProductRepository dao;
	
	private List<si.um.feri.products.dto.ProductCategory> translateProductCategoryListToDtoList(Iterable<ProductCategory> list, boolean empty) {
		List<si.um.feri.products.dto.ProductCategory> ret=new ArrayList<>();
		for (ProductCategory sc :list) {
			si.um.feri.products.dto.ProductCategory dto=sc.toDto();
			ret.add(dto);
			if (!empty) dto.getProducts().addAll(ProductController.translateProductListToDtoList(dao.findByCategory(sc)));
		}
		return ret;
	}
	
	@GetMapping("/product_categories")
	public @ResponseBody Iterable<si.um.feri.products.dto.ProductCategory> getAllCategories() {
		return translateProductCategoryListToDtoList(categoryDao.findAll(),false);
	}
	
	@GetMapping("/product_categories/{id}")
	public ResponseEntity<si.um.feri.products.dto.ProductCategory> getById(@PathVariable("id") int id) {
		Optional<ProductCategory> val=categoryDao.findById(id);
		if (val.isEmpty()) {
			log.info("/product_categories/"+id+" ; Category not found!");
			return new ResponseEntity("category-not-found",HttpStatus.NOT_ACCEPTABLE);
		}
		si.um.feri.products.dto.ProductCategory dto=val.get().toDto();
		dto.getProducts().addAll(ProductController.translateProductListToDtoList(dao.findByCategory(val.get())));
	    return ResponseEntity.ok(dto);
	}
	
	@PostMapping("/product_categories")
	public ResponseEntity<si.um.feri.products.dto.ProductCategory> postCategory(@RequestBody si.um.feri.products.dto.ProductCategory pc) {
		ProductCategory vao=new ProductCategory(pc);
		categoryDao.save(vao);
	    return ResponseEntity.ok(vao.toDto());
	}
	
	@PutMapping("/product_categories/{id}")
	public ResponseEntity<si.um.feri.products.dto.ProductCategory> putCategory(@PathVariable("id") int id, @RequestBody si.um.feri.products.dto.ProductCategory v) {
		//validate
		Optional<ProductCategory> val=categoryDao.findById(id);
		if (val.isEmpty()) {
			log.info("/product_categories/"+id+" ; Category not found!");
			return new ResponseEntity("category-not-found",HttpStatus.NOT_ACCEPTABLE);
		}
		ProductCategory vao=val.get();
		vao.updateFrom(v);
		categoryDao.save(vao);
	    return ResponseEntity.ok(vao.toDto());
	}
	
	@DeleteMapping("/product_categories/{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable("id") int id) {
		//validate
		Optional<ProductCategory> val=categoryDao.findById(id);
		if (val.isEmpty()) {
			log.info("/product_categories/"+id+" ; Category not found!");
			return new ResponseEntity("category-not-found",HttpStatus.NOT_ACCEPTABLE);
		}
		ProductCategory vao=val.get();
		categoryDao.delete(vao);
	    return ResponseEntity.ok("deleted");
	}
	
	@GetMapping("/product_categories_empty/{id}")
	public ResponseEntity<si.um.feri.products.dto.ProductCategory> getEmptyById(@PathVariable("id") int id) {
		Optional<ProductCategory> val=categoryDao.findById(id);
		if (val.isEmpty()) {
			log.info("/product_categories/"+id+" ; Category not found!");
			return new ResponseEntity("category-not-found",HttpStatus.NOT_ACCEPTABLE);
		}
	    return ResponseEntity.ok(val.get().toDto());
	}

}
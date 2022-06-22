package si.um.feri.measurements.rest;

import java.util.Optional;
import java.util.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import si.um.feri.measurements.dao.MeasurementRepository;
import si.um.feri.measurements.dao.ProductRepository;
import si.um.feri.measurements.dto.post.PostMeasurement;
import si.um.feri.measurements.dto.post.PostMeasurementResponse;
import si.um.feri.measurements.vao.Measurement;
import si.um.feri.measurements.vao.Product;

/**
 * Posting a new measurement
 */
@CrossOrigin
@RestController
public class MeasurementPostController {

	private static final Logger log = Logger.getLogger(MeasurementPostController.class.toString());

	public MeasurementPostController(MeasurementRepository dao, ProductRepository productDao) {
		this.dao = dao;
		this.productDao = productDao;
	}

	private MeasurementRepository dao;
	
	private ProductRepository productDao;
	
	@PostMapping("/product_measurement")
	public ResponseEntity<PostMeasurementResponse> postProductMeasurement(@RequestBody PostMeasurement m) {
		//validate
		Optional<Product> val=productDao.findById(m.id());
		if (val.isEmpty()) {
			log.info("/product_measurement sent: "+m+"; Product not found!");
			return new ResponseEntity("product-not-found",HttpStatus.NOT_ACCEPTABLE);
		}
		Product product=val.get();

		Measurement vao=new Measurement(m,product);
		boolean ok=true;

		//action?
		if (m.avgTemperature() < product.getMinMeasure()) {
			log.info("/product_measurement sent: "+m+"; product: "+product+"; ACTION NEEDED-lower");
			ok=false;
		}
		if (m.avgTemperature() > product.getMaxMeasure()) {
			log.info("/product_measurement sent: "+m+"; product: "+product+"; ACTION NEEDED-higher");
			ok=false;
		}

		//save
		vao.setOk(ok);
		dao.save(vao);

		//response
	    return ResponseEntity.ok(new PostMeasurementResponse(ok?"ok":"not_ok"));
	}
	
}
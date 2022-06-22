package si.um.feri.measurements;

import si.um.feri.measurements.dao.MeasurementRepository;
import si.um.feri.measurements.dao.ProductRepository;
import si.um.feri.measurements.dto.post.PostMeasurement;
import si.um.feri.measurements.vao.Measurement;
import si.um.feri.measurements.vao.Product;
import java.util.logging.Logger;

/**
 * Initializing DB with some Products and Measurements
 */
public class MeasurementsRestServiceInit {

	private static final Logger log = Logger.getLogger(MeasurementsRestServiceInit.class.toString());

	void populateTestDataIfNotPresent(
			ProductRepository daoProd,
			MeasurementRepository daoMeas
			) {
		if (daoProd.count()>0) {
			log.info("populateTestData - skipped.");
			return;
		}
		log.info("populateTestData initiated.");
		
		Product p1=new Product();
		p1.setName("Milka Classic");
		p1.setMinMeasure(-5.0);
		p1.setMaxMeasure(18.0);
		daoProd.save(p1);

		Product p2=new Product();
		p2.setName("Chicken Breasts");
		p2.setMinMeasure(-25.0);
		p2.setMaxMeasure(-8.0);
		daoProd.save(p2);

		PostMeasurement pm1=new PostMeasurement(p1.getId(),12,"PERIODIC");
		daoMeas.save(new Measurement(pm1,p1));

		PostMeasurement pm2=new PostMeasurement(p2.getId(),-10,"PERIODIC");
		daoMeas.save(new Measurement(pm2,p2));
	}
	
}

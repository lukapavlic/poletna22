package si.um.feri.measurements;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import si.um.feri.measurements.dao.MeasurementRepository;
import si.um.feri.measurements.dao.ProductRepository;
import java.util.logging.Logger;

/**
 * Entry point
 */
@SpringBootApplication
public class MeasurementsApplication {

	private static final Logger log = Logger.getLogger(MeasurementsApplication.class.toString());

	public static void main(String[] args) {
		SpringApplication.run(MeasurementsApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ProductRepository daoProd, MeasurementRepository daoMeas) {
		return args -> {
			log.info("Ready, Set, Go!");
			new MeasurementsRestServiceInit().populateTestDataIfNotPresent(daoProd,daoMeas);
		};
	}
}

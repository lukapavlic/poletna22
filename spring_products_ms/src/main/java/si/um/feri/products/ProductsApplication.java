package si.um.feri.products;

import java.util.logging.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import si.um.feri.products.dao.LocationRepository;
import si.um.feri.products.dao.ProductCategoryRepository;
import si.um.feri.products.dao.ProductRepository;

@SpringBootApplication
public class ProductsApplication {

	private static final Logger log = Logger.getLogger(ProductsApplication.class.toString());
	
	public static void main(String[] args) {
		SpringApplication.run(ProductsApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(
			ProductRepository daoProd,
			ProductCategoryRepository daoProdCat,
			LocationRepository daoLoc
			) {
		return args -> {
			log.info("Ready, Set, Go!");
			new ProductsRestServiceInit().populateTestDataIfNotPresent(daoProd,daoProdCat,daoLoc);
		};
	}

}

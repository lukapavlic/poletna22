package si.um.feri.products;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import si.um.feri.products.dao.LocationRepository;
import si.um.feri.products.dao.ProductCategoryRepository;
import si.um.feri.products.dao.ProductRepository;
import si.um.feri.products.vao.Location;
import si.um.feri.products.vao.Product;
import si.um.feri.products.vao.ProductCategory;

public class ProductsRestServiceInit {

	private static final Logger log = LoggerFactory.getLogger(ProductsRestServiceInit.class);

	void populateTestDataIfNotPresent(
			ProductRepository daoProd,
			ProductCategoryRepository daoProdCat,
			LocationRepository daoLoc
			) {
		if (daoLoc.count()>0) {
			log.info("populateTestData - skipped.");
			return;
		}
		log.info("populateTestData initiated.");
		
		Location loc1 = new Location();
		loc1.setName("Location 1");
		loc1.setAddress("Address 1");
		daoLoc.save(loc1);

		Location loc2 = new Location();
		loc2.setName("Location 2");
		loc2.setAddress("Address 2");
		daoLoc.save(loc2);

		ProductCategory pc1=new ProductCategory();
		pc1.setName("Product Category 1");
		daoProdCat.save(pc1);

		ProductCategory pc2=new ProductCategory();
		pc2.setName("Product Category 2");
		daoProdCat.save(pc2);

		Product p1=new Product();
		p1.setName("Product 2.1");
		p1.setCategory(pc2);
		p1.setLocation(loc1);
		daoProd.save(p1);

		Product p2=new Product();
		p2.setName("Product 2.2");
		p1.setLocation(loc2);
		daoProd.save(p2);
		
	}
	
}

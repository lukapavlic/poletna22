package si.um.feri.products;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import si.um.feri.products.dao.LocationRepository;
import si.um.feri.products.vao.Location;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ProductsApplicationTests {

    @Autowired
    LocationRepository dao;

	@Test
	void contextLoads() {
        Location l=new Location();
        l.setName("NOVA");
        dao.save(l);
        System.out.println("Shranjeno");

        dao.findAll().forEach(System.out::println);

        assertThat(dao.count()).isEqualTo(3);
	}

}

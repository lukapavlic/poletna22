package si.um.feri.demo.actuatordemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.logging.Logger;

@SpringBootApplication
public class ActuatordemoApplication {

	private static final Logger log = Logger.getLogger(ActuatordemoApplication.class.toString());

	public static void main(String[] args) {
		SpringApplication.run(ActuatordemoApplication.class, args);
	}

}

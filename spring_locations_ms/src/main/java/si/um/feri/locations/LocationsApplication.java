package si.um.feri.locations;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import si.um.feri.locations.dao.LocationRepository;

import java.util.logging.Logger;

@SpringBootApplication
public class LocationsApplication {

    private static final Logger log = Logger.getLogger(LocationsApplication.class.toString());

    public static void main(String[] args) {
        SpringApplication.run(LocationsApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(LocationRepository dao) {
        return args -> {
            log.info("Ready, Set, Go!");
            new LocationsApplicationInit().populateTestDataIfNotPresent(dao);
        };
    }

}

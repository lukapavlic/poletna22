package si.um.feri.locations;

import si.um.feri.locations.dao.LocationRepository;
import si.um.feri.locations.vao.Location;

import java.util.logging.Logger;

/**
 * Initializing DB with some Products and Measurements
 */
public class LocationsApplicationInit {

	private static final Logger log = Logger.getLogger(LocationsApplicationInit.class.toString());

	void populateTestDataIfNotPresent(
			LocationRepository dao
			) {
		if (dao.count()>0) {
			log.info("populateTestData - skipped.");
			return;
		}
		log.info("populateTestData initiated.");

		dao.save(new Location("FERI","Koroška 46"));
		dao.save(new Location("UM","Slomškov trg 15"));

	}
	
}

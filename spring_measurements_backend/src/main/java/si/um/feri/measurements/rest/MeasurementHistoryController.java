package si.um.feri.measurements.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import si.um.feri.measurements.dao.MeasurementRepository;
import si.um.feri.measurements.vao.Measurement;

/**
 * Listing previous Measurement for all products; up to settings - "dayslimit"
 */
@CrossOrigin
@RestController
public class MeasurementHistoryController {

	private static final Logger log = Logger.getLogger(MeasurementHistoryController.class.toString());

	public MeasurementHistoryController(MeasurementRepository dao) {
		this.dao = dao;
	}

	private MeasurementRepository dao;

	@Value("${history.dayslimit}")
	private int envHistoryDayslimit;

	@GetMapping("/history")
	public @ResponseBody Iterable<si.um.feri.measurements.dto.Measurement> getHistory() {
		long history = System.currentTimeMillis() - envHistoryDayslimit * 3_600_000 * 24;
		List<si.um.feri.measurements.dto.Measurement> ret = new ArrayList<>();
		for (Measurement m : dao.findByCreatedLongGreaterThan(history)) {
			ret.add(m.toDto());
		}
		return ret;
	}

}
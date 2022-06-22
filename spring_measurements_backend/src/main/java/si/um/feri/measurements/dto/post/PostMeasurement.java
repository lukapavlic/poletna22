package si.um.feri.measurements.dto.post;

/**
 * id - id of a product, being measured
 * avgTemperature - a measurement
 */
public record PostMeasurement (
	int id,
	double avgTemperature,
	String measurementType) {}
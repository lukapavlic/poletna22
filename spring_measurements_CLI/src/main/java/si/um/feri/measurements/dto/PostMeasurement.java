package si.um.feri.measurements.dto;

public record PostMeasurement(
	int id,
	double avgTemperature,
	String measurementType) {}
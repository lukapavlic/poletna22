package si.um.feri.measurements.dto;

public record Measurement(
	int id,
	String date,
	int productId,
	double avgTemperature,
	String employee,
	boolean isOk,
	String measurementType) {}

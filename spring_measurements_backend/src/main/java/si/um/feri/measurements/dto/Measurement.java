package si.um.feri.measurements.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public record Measurement (
	int id,
	String date,
	int productId,
	double avgTemperature,
	String employee,
	boolean isOk,
	String measurementType) {}

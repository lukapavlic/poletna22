package si.um.feri.measurements.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public record Product (
		int id,
		String name,
		double maxMeasure,
		double minMeasure) {}

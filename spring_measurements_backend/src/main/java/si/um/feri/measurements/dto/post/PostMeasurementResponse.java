package si.um.feri.measurements.dto.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Result values: ok, not_ok
 */
@JsonInclude(value = Include.NON_NULL)
public record PostMeasurementResponse (String result) {

	public PostMeasurementResponse() {
		this("ok");
	}

}

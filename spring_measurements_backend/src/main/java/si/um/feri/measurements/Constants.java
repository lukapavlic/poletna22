package si.um.feri.measurements;

import java.time.format.DateTimeFormatter;

/**
 * Settings constants
 */
public interface Constants {

	public enum MeasurementSubjects {
		PRODUCT(0);
		private int value;
		MeasurementSubjects(int value) {
			this.value = value;
		}
		public int getValue() {
			return value;
		}
	}

	DateTimeFormatter JSON_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	
}

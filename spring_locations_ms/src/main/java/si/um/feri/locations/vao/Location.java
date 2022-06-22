package si.um.feri.locations.vao;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Entity
@Data
@NoArgsConstructor
public class Location {

	public Location(String name, String address) {
		this.name = name;
		this.address = address;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	
	private String address;
	
	private Calendar created=new GregorianCalendar();

}

package si.um.feri.banka.rest;

import jakarta.ejb.EJB;
import si.um.feri.banka.dao.Dao;
import si.um.feri.banka.dao.InMemoryDao;
import si.um.feri.banka.dto.OsebaExtended;
import si.um.feri.banka.vao.Oseba;
import java.util.Collection;
import java.util.logging.Logger;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/osebe")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OsebaResource {

	private static final Logger log=Logger.getLogger("OsebaResource");

	Dao dao= InMemoryDao.getInstance();

	//@EJB
	//Dao dao;

	@GET
	@Produces({MediaType.TEXT_PLAIN})
	@Path("/info")
	public String info() {
		return "OsebaResource";
	}

	@GET
	public Collection<Oseba> getVseOsebe() {
		return dao.vrniVseOsebe();
	}

	@GET
	@Path("/{email}")
	public Oseba getOseba(@PathParam("email") String email) {
		return dao.najdiOsebo(email);
	}

	@GET
	@Path("/ext/{email}")
	public OsebaExtended getOsebaExtended(@PathParam("email") String email) {
		return new OsebaExtended(dao.najdiOsebo(email),dao.najdiBancniRacunLastnika(email));
	}

	@POST
	public Oseba dodajOsebo(Oseba o) throws Exception {
		log.info("dodajOsebo:"+o);
		return dao.shrani(o);
	}

	@PUT
	@Path("/{email}")
	public Oseba spremeniOsebo(Oseba o,@PathParam("email") String email) throws Exception {
		if (dao.najdiOsebo(email)==null)
			throw new Exception("Oseba še ne obstaja.");
		return dao.shrani(o);
	}

}

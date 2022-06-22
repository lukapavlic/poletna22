package si.um.feri.banka.rest;

import jakarta.ejb.EJB;
import si.um.feri.banka.dao.Dao;
import si.um.feri.banka.dao.InMemoryDao;
import si.um.feri.banka.dto.BancniRacun;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/racuni")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BancniRacunResource {

	Dao dao= InMemoryDao.getInstance();

	//@EJB
	//Dao dao;

	@GET
	@Produces({MediaType.TEXT_PLAIN})
	@Path("/info")
	public String info() {
		return "BancniRacunResource";
	}

	@GET
	@Path("/")
	public List<BancniRacun> getVseRacune() {
		List<BancniRacun> ret= new ArrayList<>();
		for (si.um.feri.banka.vao.BancniRacun br : dao.vrniVseRacune())
			ret.add(new BancniRacun(br));
		return ret;
	}

	@GET
	@Path("/{iban}")
	public BancniRacun getRacun(@PathParam("iban") String iban) {
		return new BancniRacun(dao.najdiBancniRacun(iban));
	}

	@POST
	@Path("/")
	public void dodajBancniRacun(BancniRacun br) throws Exception {
		dao.shrani(br.asVao());
	}

	@PUT
	@Path("/{iban}")
	public void spremeniBancniRacun(BancniRacun br,@PathParam("iban") String iban) throws Exception {
		if (dao.najdiBancniRacun(iban)==null)
			throw new Exception("Bančni račun še ne obstaja.");
		dao.shrani(br.asVao());
	}

}

package si.um.feri.banka.dao;

import si.um.feri.banka.vao.BancniRacun;
import si.um.feri.banka.vao.Oseba;
import java.util.Collection;
import java.util.List;

public interface Dao {

    Collection<Oseba> vrniVseOsebe();

    Collection<BancniRacun> vrniVseRacune();

    Oseba najdiOsebo(String email);

    BancniRacun najdiBancniRacun(String iban);

    List<BancniRacun> najdiBancniRacunLastnika(String email);

    void shrani(BancniRacun br) throws Exception;

    Oseba shrani(Oseba os) throws Exception;
}

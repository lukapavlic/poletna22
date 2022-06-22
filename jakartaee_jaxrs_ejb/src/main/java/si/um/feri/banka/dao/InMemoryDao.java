package si.um.feri.banka.dao;

import si.um.feri.banka.vao.BancniRacun;
import si.um.feri.banka.vao.Oseba;
import java.math.BigDecimal;
import java.util.*;

public class InMemoryDao implements Dao {

    private static InMemoryDao _inst=new InMemoryDao();

    private InMemoryDao() {
        Oseba o= null;
        try {
            o = shrani(new Oseba("peter.klepec@gmail.com","Peter","Klepec"));
            BancniRacun br=new BancniRacun("123-123-123",o,new BigDecimal(0),true);
            BancniRacun br2=new BancniRacun("789-789-789",o,new BigDecimal(0),false);
            shrani(br);
            shrani(br2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static InMemoryDao getInstance() {
        return _inst;
    }

    private Map<String, BancniRacun> racuni=Collections.synchronizedMap(new HashMap<>());

    private Map<String, Oseba> osebe=Collections.synchronizedMap(new HashMap<>());

    @Override
    public Collection<Oseba> vrniVseOsebe() {
        return osebe.values();
    }

    @Override
    public Collection<BancniRacun> vrniVseRacune() {
        return racuni.values();
    }

    @Override
    public Oseba najdiOsebo(String email) {
        return osebe.get(email);
    }

    @Override
    public BancniRacun najdiBancniRacun(String iban) {
        return racuni.get(iban);
    }

    @Override
    public List<BancniRacun> najdiBancniRacunLastnika(String email) {
        List<BancniRacun> ret=new ArrayList<>();
        for (BancniRacun br:racuni.values()) {
            if (br.getLastnik().getEmail().equals(email))
                ret.add(br);
        }
        return ret;
    }

    @Override
    public void shrani(BancniRacun br) throws Exception {
        if (br.getIban()==null || br.getIban().isEmpty())
            throw new Exception("Manjka IBAN");
        if (br.getLastnik()==null)
            throw new Exception("Manjka lastnik");
        shrani(br.getLastnik());
        racuni.put(br.getIban(),br);
    }

    @Override
    public Oseba shrani(Oseba os) throws Exception {
        if (os.getEmail()==null || os.getEmail().isEmpty())
            throw new Exception("Manjka email");
        if (najdiOsebo(os.getEmail())==null)
            os.setId(System.nanoTime());
        osebe.put(os.getEmail(),os);
        return os;
    }

}

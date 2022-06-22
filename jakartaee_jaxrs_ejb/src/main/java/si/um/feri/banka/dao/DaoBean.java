package si.um.feri.banka.dao;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import si.um.feri.banka.vao.BancniRacun;
import si.um.feri.banka.vao.Oseba;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@Stateless
@Local
public class DaoBean implements Dao {

    private static final Logger log=Logger.getLogger("DaoImpl");

    @PersistenceContext
    EntityManager em;

    @Override
    public Collection<Oseba> vrniVseOsebe() {
        log.info(this+" vračam vse Osebe");
        return em.createQuery("select o from Oseba o").getResultList();
    }

    @Override
    public Collection<BancniRacun> vrniVseRacune() {
        log.info(this+" vračam vse Račune");
        return em.createQuery("select o from BancniRacun o").getResultList();
    }

    @Override
    public Oseba najdiOsebo(String email) {
        log.info(this+" iščem osebo "+email);
        Query q=em.createQuery("select o from Oseba o where o.email = :e");
        q.setParameter("e", email);
        try {
            return (Oseba)q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public BancniRacun najdiBancniRacun(String iban) {
        log.info(this+" iščem račun "+iban);
        return em.find(BancniRacun.class,iban);
    }

    @Override
    public List<BancniRacun> najdiBancniRacunLastnika(String email) {
        log.info(this+" iščem račun lastnika "+email);
        Query q=em.createQuery("select o from BancniRacun o where o.lastnik.email = :e");
        q.setParameter("e", email);
        try {
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void shrani(BancniRacun br) throws Exception {
        log.info(this+" shranjujem račun "+br);
        em.persist(br);
    }

    @Override
    public Oseba shrani(Oseba os) throws Exception {
        log.info(this+" shranjujem osebo "+os);
        em.persist(os);
        return os;
    }

}

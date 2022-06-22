package si.um.feri.banka.vao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

@Entity
public class BancniRacun {

    @Id
    private String iban;

    @ManyToOne
    private Oseba lastnik;

    protected BigDecimal trenutnoStanje;

    boolean aktiven;

    public BancniRacun() {
    }

    public BancniRacun(String iban, Oseba lastnik, BigDecimal trenutnoStanje, boolean aktiven) {
        this.iban = iban;
        this.lastnik = lastnik;
        this.trenutnoStanje = trenutnoStanje;
        this.aktiven = aktiven;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Oseba getLastnik() {
        return lastnik;
    }

    public void setLastnik(Oseba lastnik) {
        this.lastnik = lastnik;
    }

    public BigDecimal getTrenutnoStanje() {
        return trenutnoStanje;
    }

    public void setTrenutnoStanje(BigDecimal trenutnoStanje) {
        this.trenutnoStanje = trenutnoStanje;
    }

    public boolean isAktiven() {
        return aktiven;
    }

    public void setAktiven(boolean aktiven) {
        this.aktiven = aktiven;
    }

    @Override
    public String toString() {
        return "BancniRacun{" +
                "iban='" + iban + '\'' +
                ", lastnik=" + lastnik +
                ", trenutnoStanje=" + trenutnoStanje +
                ", aktiven=" + aktiven +
                '}';
    }

}

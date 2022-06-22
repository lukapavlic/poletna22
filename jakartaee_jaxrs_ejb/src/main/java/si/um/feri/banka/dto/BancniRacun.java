package si.um.feri.banka.dto;

import si.um.feri.banka.vao.Oseba;
import java.math.BigDecimal;

public class BancniRacun {

    public BancniRacun(si.um.feri.banka.vao.BancniRacun br) {
        this.iban=br.getIban();
        this.email_komitenta=br.getLastnik().getEmail();
        this.ime_komitenta=br.getLastnik().getIme();
        this.priimek_komitenta=br.getLastnik().getPriimek();
        this.aktiven=br.isAktiven();
        this.trenutnoStanje=br.getTrenutnoStanje();
    }

    public si.um.feri.banka.vao.BancniRacun asVao() {
        si.um.feri.banka.vao.BancniRacun ret=new si.um.feri.banka.vao.BancniRacun();
        Oseba o=new Oseba(getEmail_komitenta(),getIme_komitenta(),getPriimek_komitenta());
        ret.setLastnik(o);
        ret.setIban(getIban());
        ret.setAktiven(isAktiven());
        ret.setTrenutnoStanje(getTrenutnoStanje());
        return ret;
    }

    private String iban;
    private String ime_komitenta;
    private String priimek_komitenta;
    private String email_komitenta;
    protected BigDecimal trenutnoStanje;
    boolean aktiven;

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getIme_komitenta() {
        return ime_komitenta;
    }

    public void setIme_komitenta(String ime_komitenta) {
        this.ime_komitenta = ime_komitenta;
    }

    public String getPriimek_komitenta() {
        return priimek_komitenta;
    }

    public void setPriimek_komitenta(String priimek_komitenta) {
        this.priimek_komitenta = priimek_komitenta;
    }

    public String getEmail_komitenta() {
        return email_komitenta;
    }

    public void setEmail_komitenta(String email_komitenta) {
        this.email_komitenta = email_komitenta;
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
                ", ime_komitenta='" + ime_komitenta + '\'' +
                ", priimek_komitenta='" + priimek_komitenta + '\'' +
                ", email_komitenta='" + email_komitenta + '\'' +
                ", trenutnoStanje=" + trenutnoStanje +
                ", aktiven=" + aktiven +
                '}';
    }

}

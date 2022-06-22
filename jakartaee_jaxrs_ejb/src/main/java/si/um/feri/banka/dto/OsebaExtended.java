package si.um.feri.banka.dto;

import si.um.feri.banka.vao.BancniRacun;
import si.um.feri.banka.vao.Oseba;
import jakarta.json.bind.annotation.JsonbProperty;
import java.util.ArrayList;
import java.util.List;

public class OsebaExtended {

    public OsebaExtended(Oseba o, List<BancniRacun> racuni) {
        this.email=o.getEmail();
        this.ime=o.getIme();
        this.priimek=o.getPriimek();
        for (BancniRacun br:racuni)
            this.racuniKomitenta.add(br.getIban());
    }

    private String email;

    private String ime;

    private String priimek;

    @JsonbProperty("racuni")
    private List<String> racuniKomitenta=new ArrayList<>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPriimek() {
        return priimek;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

    public List<String> getRacuniKomitenta() {
        return racuniKomitenta;
    }

    public void setRacuniKomitenta(List<String> racuniKomitenta) {
        this.racuniKomitenta = racuniKomitenta;
    }

}

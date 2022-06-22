package si.um.feri.banka;

public class Oseba {

    private String email;

    private String ime;

    private String priimek;

    public Oseba() {
        this("","","");
    }

    public Oseba(String email, String ime, String priimek) {
        this.email = email;
        this.ime = ime;
        this.priimek = priimek;
    }

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

    @Override
    public String toString() {
        return "Oseba{" +
                "email='" + email + '\'' +
                ", ime='" + ime + '\'' +
                ", priimek='" + priimek + '\'' +
                '}';
    }

}

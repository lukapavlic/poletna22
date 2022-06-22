package si.um.feri.banka;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RestClientJEEDemo {
	                                         
    static final URI OSEBE_URI = URI.create("http://localhost:8080/jakartaee_jaxrs_ejb-1.0-SNAPSHOT/api/osebe/");
    static final URI OSEBE_OSEBA_URI = URI.create("http://localhost:8080/jakartaee_jaxrs_ejb-1.0-SNAPSHOT/api/osebe/");

    HttpClient client = HttpClient.newBuilder().build();
    Jsonb jsonb= JsonbBuilder.create();

    void vseOsebe() throws Exception {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(OSEBE_URI).build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    void dodaj(Oseba o) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(jsonb.toJson(o)))
                .header("Content-Type","application/json")
                .uri(OSEBE_OSEBA_URI)
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.discarding());
        System.out.println(response.body());
    }

    public static void main(String[] args) throws Exception {
        RestClientJEEDemo client=new RestClientJEEDemo();
        client.dodaj(new Oseba("martin2@krpan.si","Martin2","Krpan2"));
        client.vseOsebe();
    }

}

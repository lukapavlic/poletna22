package si.um.feri.measurements.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import si.um.feri.measurements.dto.Measurement;
import si.um.feri.measurements.dto.PostMeasurement;
import si.um.feri.measurements.dto.PostMeasurementResponse;
import si.um.feri.measurements.dto.Product;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

/**
 * Accessing data via REST endpoints
 */
public class RestDao implements Dao {

    public RestDao() {
    }

    public RestDao(String host) {
        this.HOST = host;
    }

    HttpClient client = HttpClient.newBuilder().build();
    ObjectMapper mapper = new ObjectMapper();

    String HOST="http://127.0.0.1:8280";
    String PRODUCTS_URL = "/api/v1/products/";
    String HISTORY_URL = "/api/v1/history";
    String MEASUREMENTS_URL = "/api/v1/product_measurement";

    @Override
    public List<Product> getProducts() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(HOST+PRODUCTS_URL))
                .header("Accept", "application/json")
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return mapper.readValue(response.body().toString(), new TypeReference<List<Product>>(){});
    }

    @Override
    public void postProduct(Product p) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(HOST+PRODUCTS_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(p)))
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.discarding());
    }

    @Override
    public List<Measurement> getMeasurements() throws Exception {
        throw new Exception("NotJetImplementedException");
    }

    @Override
    public PostMeasurementResponse postMeasurement(PostMeasurement pm) throws Exception {
        throw new Exception("NotJetImplementedException");
    }

}

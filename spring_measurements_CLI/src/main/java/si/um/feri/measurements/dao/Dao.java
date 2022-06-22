package si.um.feri.measurements.dao;

import si.um.feri.measurements.dto.Measurement;
import si.um.feri.measurements.dto.PostMeasurement;
import si.um.feri.measurements.dto.PostMeasurementResponse;
import si.um.feri.measurements.dto.Product;
import java.util.List;

public interface Dao {

    List<Product> getProducts() throws Exception;
    void postProduct(Product p) throws Exception;

    List<Measurement> getMeasurements() throws Exception;
    PostMeasurementResponse postMeasurement(PostMeasurement pm) throws Exception;

}

package si.um.feri.measurements;

import picocli.CommandLine;
import si.um.feri.measurements.dao.RestDao;
import si.um.feri.measurements.dto.Product;

/**
 * Command for adding new product
 */
@CommandLine.Command(
        name = "addproduct",
        description = "Adds a Products"
)
public class AddProductCommand implements Runnable {

    @CommandLine.Option(names = { "-s", "--server" }, defaultValue = "http://127.0.0.1:8280", description = "Server to connect to")
    String server;

    @CommandLine.Parameters(paramLabel = "<name>", defaultValue = "NAME", description = "Product name")
    private String name;

    @CommandLine.Parameters(paramLabel = "<min>", defaultValue = "0.0", description = "Product minimum acceptable temperature")
    private double min;

    @CommandLine.Parameters(paramLabel = "<max>", defaultValue = "0.0", description = "Product maximum acceptable temperature")
    private double max;

    @Override
    public void run() {
        try {
            new RestDao(server).postProduct(new Product(0,name,max,min));
            System.out.println("Success. Now you might want to execute 'list products'");
        } catch (Exception e) {
            System.out.println("Failure: "+e.getMessage());
        }
    }

}

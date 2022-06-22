package si.um.feri.measurements;

import org.apache.commons.lang3.StringUtils;
import picocli.CommandLine;
import si.um.feri.measurements.dao.RestDao;
import si.um.feri.measurements.dto.Product;
import java.util.Arrays;
import java.util.List;

/**
 * Command for listing products (or other things TBD)
 */
@CommandLine.Command(
        name = "list",
        description = "Lists Products or Measurements"
)
public class ListCommand implements Runnable {

    @CommandLine.Option(names = { "-n", "--nice" }, description = "User friendly data display")
    boolean nice;

    @CommandLine.Option(names = { "-s", "--server" }, defaultValue = "http://127.0.0.1:8280", description = "Server to connect to")
    String server;

    @CommandLine.Parameters(paramLabel = "<entities>", defaultValue = "products, measurements", description = "Which entities to display")
    private String[] entities = { "products,", "measurements" };

    @Override
    public void run() {
        try {
            if (Arrays.asList(entities).contains("products")) listProducts(nice);
        } catch (Exception e) {
            System.out.println("Failure: "+e.getMessage());
        }
    }

    private void listProducts(boolean nice) throws Exception{
        List<Product> list=new RestDao(server).getProducts();
        System.out.println("Products:");
        if (nice) {
            System.out.println("ID    | PRODUCT NAME                             | TEMPERATURE RANGE");
            System.out.println("------+------------------------------------------+---------------------------");
            list.forEach(p->
                System.out.println(
                        StringUtils.rightPad(""+p.id(),5,' ')+" | "+
                        StringUtils.rightPad(p.name(),40,' ') +" | "+
                        p.minMeasure()+" .. "+p.maxMeasure()));
        } else {
            list.forEach(System.out::println);
        }
        System.out.println("\nProduct count: "+list.size());
    }

}

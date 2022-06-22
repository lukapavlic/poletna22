package si.um.feri.measurements;

import picocli.CommandLine;

/**
 * Entry point
 * Meaningfull are subcommands: list, addproduct
 */
@CommandLine.Command(
        name = "MeasCLI",
        version = "0.0.1",
        mixinStandardHelpOptions = true,
        subcommands = {ListCommand.class, AddProductCommand.class}
)
public class MeasCLI implements Runnable {

    public static void main(String[] args) {
        int exitCode = new CommandLine(new MeasCLI()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        System.out.println("-= MeasCLI =-");
        System.out.println("Usage examples:");
        System.out.println("\tMeasCLI --help");
        System.out.println("\tMeasCLI list products -n");
        System.out.println("\tMeasCLI list products -n -s http://127.0.0.1:8280");
        System.out.println("\tMeasCLI addproduct IceCream -18 -5.5");
    }

}
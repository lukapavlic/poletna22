package si.um.feri.grpc.greeting.server;

import io.grpc.*;

import java.io.File;
import java.io.IOException;

public class GreetingServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hello, gRPC!");
        //plaintext server
        Server server = ServerBuilder.forPort(50051).addService(new GreetServiceImpl()).build();

        //secure server
        //Server server = ServerBuilder.forPort(50051).addService(new GreetServiceImpl()).useTransportSecurity(new File("ssl/server.crt"), new File("ssl/server.pem")).build();

        /*ServerCredentials creds = TlsServerCredentials.create(new File("ssl/server.crt"), new File("ssl/server.pem"));
        Server server = Grpc.newServerBuilderForPort(50051, creds)
                .addService(new GreetServiceImpl())
                .build();*/

        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Received Shutdown Request");
            server.shutdown();
            System.out.println("Successfully stopped the server");
        }));

        server.awaitTermination();
    }
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;

public class Server {

    static int PORT=8080;

    public static void main(String[] args) throws Exception {
        System.out.println("Čakam, da me kdo pokliče; port:"+PORT);
        ServerSocket serverSocket = new ServerSocket(PORT);
        while (true) {
            BufferedReader br=new BufferedReader(new InputStreamReader(serverSocket.accept().getInputStream()));
            System.out.println(br.readLine());
        }
    }

}

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class JuliusClient {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Type in the port number: ");
        int portNumber = sc.nextInt();
        sc.nextLine();
        System.out.println("Type in the IP address: ");
        String address= sc.nextLine();

        try {

            Socket kkSocket = new Socket(address, portNumber);
            System.out.println("Client connected.");

            PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));

            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser;

            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);

                fromUser = stdIn.readLine();

                if (fromUser != null) {
                    if (fromUser.equals("Okay Thank U Bye.")) {
                        System.exit(0);
                    } else {
                        System.out.println("Client: " + fromUser);
                        out.println(fromUser);
                    }
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + address);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    address);
            System.exit(1);
        }
    }
}

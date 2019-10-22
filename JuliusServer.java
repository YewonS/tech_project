import java.net.*;
import java.io.*;
import java.util.Scanner;

public class JuliusServer {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Type in the port number: ");
        int portNumber = sc.nextInt();

        try {

            ServerSocket serverSocket = new ServerSocket(portNumber);
            System.out.println("Server running...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected");

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine, outputLine;

            // Initiate conversation with client
            JuliusProtocol jp = new JuliusProtocol();
            outputLine = jp.processInput(null);
            out.println(outputLine);

            while ((inputLine = in.readLine()) != null) {
                if (outputLine.equals("Okay Thank U Bye.")) {
                    System.out.println("Client closed connection.");
                    System.exit(0);
                } else {
                    outputLine = jp.processInput(inputLine);
                    out.println(outputLine);
                }
            }

        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}

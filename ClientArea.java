/**
 *
 * @author gunaw
 */

// ClientArea.java

import java.io.*;
import java.net.*;

public class ClientArea {
    public static void main(String[] args) throws IOException {
        String ip = "127.0.0.1";
        int port = 1254; // Corrrect port
        Socket s = new Socket(ip, port); // socket
        System.out.println("Socket open at " + ip + ":" + port + "\n");

        DataInputStream serverInput = new DataInputStream(s.getInputStream());
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        String message;
        while (true) {
            // check finish
            message = serverInput.readUTF();
            System.out.println("Server message: " + message);
            if (message.equals("finish"))
                break;

            // send user input
            System.out.print("Enter message to send to server: ");
            String userMessage = userInput.readLine();
            DataOutputStream serverOutput = new DataOutputStream(s.getOutputStream());
            serverOutput.writeUTF(userMessage);
        }
        // close input from server
        serverInput.close();
        // close socket
        s.close();
        System.out.println("Socket closed at " + ip + ":" + port + "\n");
    }
}

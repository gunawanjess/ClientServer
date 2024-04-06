/**
 *
 * @author gunaw
 */

// ServerArea.java

import java.io.*;
import java.net.*;

public class ServerArea {
    public static void main(String[] args) throws IOException {
        int port = 1254;
        ServerSocket serv = new ServerSocket(port);
        System.out.println("Running server on port " + port + "\n");

        while (true) {
            Socket cli = serv.accept();
            System.out.println("Client connected: " + cli);

            DataInputStream clientInput = new DataInputStream(cli.getInputStream());
            DataOutputStream clientOutput = new DataOutputStream(cli.getOutputStream());

            String message;
            while (true) {
                // Read client message
                message = clientInput.readUTF();
                System.out.println("Client message: " + message);

                // Send reply to client
                clientOutput.writeUTF(message);

                if (message.equals("finish"))
                    break;
            }
            // close input from client
            clientInput.close();
            // close output to client
            clientOutput.close();
            // close client socket
            cli.close();
            System.out.println("Client disconnected\n");
        }
    }
}

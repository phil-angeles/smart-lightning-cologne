package nk;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class PfistererServer {

    private static final String host = "localhost";
    private static final int port = 9999;

    @SuppressWarnings("resource")
    public static void main(String[] args) {

        // Listen on a server socket and on connection send some
    	//\n-delimited text to the client
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(port);

                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    PrintWriter out = new
                    	PrintWriter(clientSocket.getOutputStream(), true);

                    for (; true;) {
                    	// Hier die Koordinaten ausgeben
                        out.println("123,6509");
                        out.flush();
                        Thread.sleep(100);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start(); 
    }
}

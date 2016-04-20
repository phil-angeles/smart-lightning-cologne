package nk;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
    private static final int port = 9999;

    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception{    	
    	MyCSVReader reader = new MyCSVReader();
		reader.listeKuerzen();
		reader.bereicheBerechnen();    	

        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(port);

                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    // Warten damit der Stream startet und sich connecten kann
                    Thread.sleep(3000);
                    PrintWriter out = new
                    	PrintWriter(clientSocket.getOutputStream(), true);

                    for (int i = 0; i < 499; i++) {
                    	// Hier die Koordinaten ausgeben
                    	List<String[]> liste = reader.zeitbereicheUnterscheiden(i);
                        for(String[] s : liste){
                        	out.println(s[1] + "," + s[2]);
                        }
                        out.flush();
                        Thread.sleep(Stream.WAITTIME);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start(); 
        
        new Stream().start();
    }
}

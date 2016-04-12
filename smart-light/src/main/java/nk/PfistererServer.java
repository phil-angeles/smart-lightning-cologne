package nk;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import spark.Spark;

public class PfistererServer {

    private static final String host = "localhost";
    private static final int port = 9999;

    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception{
    	
    	System.out.println("Spark Server gestartet");
    	Spark.get("/hello", (req, resp) -> {
    		return "Hello World";
    	});
    	
    	MyCSVReader reader = new MyCSVReader();
		reader.listeKuerzen();
		System.out.println("Liste gelesen");
		reader.bereicheBerechnen();
		System.out.println("Reader fertig");
		//reader.zeitbereicheAusgeben(0);	
    	

        // Listen on a server socket and on connection send some
    	//\n-delimited text to the client
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(port);

                while (true) {
                    Socket clientSocket = serverSocket.accept();
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
                        Thread.sleep(4000);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start(); 
        
        Stream stream = new Stream();
        stream.streamMethode();
    }
}

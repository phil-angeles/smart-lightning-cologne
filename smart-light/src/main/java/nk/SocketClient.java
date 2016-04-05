package nk;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketClient {
	
	final private static int PORT=1234;

	public static void setKoordinaten(String x, String y, String ts) 
	{
		Socket server = null;

		try {
			server = new Socket("localhost", PORT);
			Scanner in = new Scanner(server.getInputStream());
			PrintWriter out = new PrintWriter(server.getOutputStream(), true);

			out.println(x);
			out.println(y);
			out.println(ts);
			System.out.println(in.nextLine());
			
		} 
		catch (UnknownHostException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		finally {
			if (server != null)
				try {
					server.close();
				} catch (IOException e) {
				}
		}
	}
}

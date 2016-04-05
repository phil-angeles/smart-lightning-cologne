package nk;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SocketServerTest
{
	final private static int PORT=1234;
	
  private static void handleConnection( Socket client ) throws IOException
  {
    Scanner     in  = new Scanner( client.getInputStream() );
    PrintWriter out = new PrintWriter( client.getOutputStream(), true );

    String x = in.nextLine();
    String y = in.nextLine();
    String ts = in.nextLine();
    
    out.println(x+" "+y+" "+ts);
  }

  
  public static void main( String[] args ) throws IOException
  {
    ServerSocket server = new ServerSocket( PORT );

    while ( true )
    {
      Socket client = null;

      try
      {
        client = server.accept();
        handleConnection ( client );
      }
      catch ( IOException e ) {
        e.printStackTrace();
      }
      finally {
        if ( client != null )
          try { client.close(); } catch ( IOException e ) { }
      }
    }
  }
}

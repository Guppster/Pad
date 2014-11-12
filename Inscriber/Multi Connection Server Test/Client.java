//Imports
import java.net.*;
import java.io.*;

public class Client implements Runnable
{
	static Socket sClient = null;
	static BufferedReader in = null;
	static BufferedReader in2 = null;
	static PrintWriter out = null;
	static boolean closed = false;

	public static void main(String [] args)
    {
		try
		{
            sClient = new Socket("localhost", 22222);
            in = new BufferedReader(new InputStreamReader(sClient.getInputStream()));
            in2 = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(sClient.getOutputStream(), true);
        }catch(Exception e){}

        if (sClient != null && out != null && in != null)
        {
            try {

			// Create a thread to read from the server
			new Thread(new Client()).start();

			while(!closed)
			{
            	out.println(in2.readLine());
            }

			// Clean up:
			// close the output stream
			// close the input stream
			// close the socket

			out.close();
			in.close();
			sClient.close();

            }catch(IOException e){System.err.println("Doing it wrong brah");}
        }
    }

    public void run()
    {
    	String response;

    	try
    	{
    		while((response = in.readLine()) != null)
    		{
    			System.out.println(response);
    		}
    		closed = true;
    	}catch(IOException e){System.out.println(e);}
    }
}
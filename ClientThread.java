//Matthew Ufimzeff and Gurpreet Singh
//June 14

//Import Java API packages
import java.net.*;
import java.io.*;

public class ClientThread extends Thread
{
	//Declare class fields and objects
	Socket sClient;
	ClientThread connections[];

	//Initializes class fields and objects
    public ClientThread(Socket sClient, ClientThread[] connections)
    {
    	this.sClient = sClient;
    	this.connections = connections;
    }//End of ClientThread constructor method

	//A method to perform actions to this particular connected users(kick, ban, etc)
	public void run()
	{
		
		
		
		/*//TESTING PURPOSES ONLY
		String line;
        String name;
        boolean flag = true;

		try
		{
		    in = new BufferedReader(new InputStreamReader(sClient.getInputStream()));
            out = new PrintWriter(sClient.getOutputStream(), true);

		    out.println("Enter your name.");
		    name = in.readLine();

		    out.println("Hello " + name + "! Welcome to Juniors' project test program!\nTo leave enter /quit in a new line.");

		    for(int x = 0; x < 5; x++)
		    {
		    	if (connections[x]!= null && connections[x]!= this)
		    		connections[x].out.println("*** " + name + " connected! ***");
		    }

		    while(flag = true)
		    {
				line = in.readLine();
	            if(line.startsWith("/quit"))
	            	 break;

				for(int y = 0; y < 5; y++)
				{
					if(connections[y]!= null)
						connections[y].out.println("<" + name + ">: " + line);
				}
		    }

		    for(int z = 0; z < 5; z++)
		    {
		    	if(connections[z]!= null && connections[z]!= this)
			    	connections[z].out.println("*** " + name + " has disconnected! ***" );
		    }

		    // Clean up:
		    // Set to null the current thread variable such that other client could
		    // be accepted by the server

		    for(int l = 0; l < 5; l++)
		    {
		    	if(connections[l] == this)
					connections[l] = null;
		    }
		    // close the output stream
		    // close the input stream
		    // close the socket

		    in.close();
		    out.close();
		    sClient.close();
		}catch(IOException e){};*/
	}//End of run method
}//End of ClientThread class
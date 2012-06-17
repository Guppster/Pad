//Matthew Ufimzeff and Gurpreet Singh
//June 17

//Import Java API Packages
import java.net.*;
import java.io.*;

public class StatusThread extends Thread
{
	//Declare class fields and objects
	private volatile boolean stop;
	private int connectionsAvailable;
	private boolean full;
	private ClientThread[] connections;
	private Server server;

	//Initializes class fields and objects
    public StatusThread()
    {
    	//Initialize class fields and objects
    	stop = false;
    	connectionsAvailable = 5;
    	full = false;
    	connections = new ClientThread[connectionsAvailable];
    	server = new Server();
    }//End of StatusThread constructor method

    //When a user disconnects, this method is called, and a connection spot is freed
    public void freeAConnection()
    {
    	connectionsAvailable++;
    }//End of freeConnection method

    //When the thread is started(the StatusThread class is called) run this method
    public void run()
    {
    	//Keep looping until the server is stopped by the server owner
		while(!stop)
		{
			while(connectionsAvailable > 0)//Keep accepting connections if there are free connections
			{
				server.acceptAConnection();

				for(int x = 0; x < connections.length; x++)//Go through the connections array and search for an empty connection
				{
					if(connections[x] == null)
					{
						(connections[x] = new ClientThread(server.getConnection(), connections)).start();
						connectionsAvailable--;
					   	break;
					}
				}
			}//End of inner while loop
		}//End of outer while loop
    }//End of run method
}//End of StatusThread class
//Imports
import java.net.*;
import java.sql.*;
import java.io.*;

public class Server
{
	//Declare class fields and objects
	private ServerSocket sServer;
	private Socket sClient;
	private ClientThread[] connections;

	//Initializes class fields and objects
	public Server()
	{
		//Initialize class fields and objects
		sServer = null;
		sClient = null;
		connections = new ClientThread[5];
	}//End of Server constructor method

	//Bind the ServerSocket object to listen to a specified port
	private void bindPort(int port)
	{
		try
		{
			sServer = new ServerSocket(22222);//Bind the server to port 22222
		}catch(IOException e){System.out.println(e + " - Could not bind to specified port.");}
	}//End of bindPort method

	private void startServer()
	{
		//Keep looping until the ma
		while(true)
		{
			try
			{
				sClient = sServer.accept();//Accept the connection

				for(int x = 0; x < connections.length; x++)//Go through the connections array and search for an empty connection
				{
					if(connections[x] == null)
					{
						(connections[x] = new ClientThread(sClient, connections)).start();
				    	break;
					}
				}
			}catch(IOException e){System.out.println(e);}
		}
	}//End of acceptConnections method

	//Attempt to stop the server
	private void stopServer()
	{
		try
		{
			sServer.close();
		}catch(IOException e){System.out.println(e + " - The server dropped the bass... WUB-WUB-WUB-WUB-WUB!");}
	}//End of stopServer method

	//Start the console and accept commands from the server owner
	private void startConsole()
	{
		//Keep prompting the user owner to enter in a command
		while(true)
		{
			System.out.println("Enter /help to display a list of commands.");
			System.out.print("<Command>: ");
			command = in.getNext();
			System.out.println();

			if(command.equals("/help"))
			{
				System.out.println("/start  -> Starts the server.");
				System.out.println("/stop   -> Stops the server.");
				System.out.println("/status -> Shows the status of the server.");
				System.out.println("/users  -> Shows the users connected to the server.");
				System.out.println("/help   -> Displays this list of commands.");
			}
			else if(command.equals("/start"))
			{
				System.out.println("Starting the server...");
				startServer();
				serverUp = true;
			}
			else if(command.equals("/stop"))
			{
				System.out.println("Stopping the server...");
				stopServer();
				serverUp = false;
			}
			else if(command.equals("/status")()
			{
				System.out.println("Server Status: " + getServerStatus())
			}
			else if(command.equals("/users"))
			{
				displayConnectedUsers();
			}
		}
	}//End of listenForConnection method

	//Run the code in this method when the class is run
	public static void main(String [] args)
	{
		//Inform the server owner of some
		System.out.println("Starting server...");

		//Bind the ServerSocket object to listen to a specified port
		bindPort(22222);

		//

		//Start the console and allow the server owner to enter console commands
		startConsole();
	}//End of Server main method
}//End of Server class
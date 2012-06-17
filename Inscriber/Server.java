//Matthew Ufimzeff and Gurpreet Singh
//June 16

//Import Java API packages
import java.net.*;
import java.sql.*;
import java.io.*;
import java.util.*;

public class Server
{
	//Declare class fields and objects
	private ServerSocket sServer;
	private Socket sClient;
	private Scanner in;
	private boolean serverUp;
	private String command;
	private StatusThread statusThread;

	//Initializes class fields and objects
	public Server()
	{
		//Initialize class fields and objects
		sServer = null;
		sClient = null;
		in = new Scanner(System.in);
		serverUp = false;
		command = "";
		statusThread = new StatusThread();
	}//End of Server constructor method

	//Bind the ServerSocket object to listen to a specified port
	private void bindPort(int port)
	{
		try
		{
			sServer = new ServerSocket(22222);//Bind the server to port 22222
		}catch(IOException e){System.out.println(e + " - Could not bind to specified port.");}

		//Output a message informing the server owner that the port was bound sucessfully
		System.out.println("Port was bound sucessfully!");
	}//End of bindPort method

	//Accept a connection to the server
	public void acceptAConnection()
	{
		try
		{
			sClient = sServer.accept();//Accept the connection
		}catch(IOException e){System.out.println(e + " - A Connection was refused.");}
	}//End of acceptAConnection method

	private void startServer()
	{

	}//End of acceptConnections method

	//Attempt to stop the server
	private void stopServer()
	{
		try
		{
			sServer.close();
		}catch(IOException e){System.out.println(e + " - The server dropped the bass... WUB-WUB-WUB-WUB-WUB!");}
	}//End of stopServer method

	//Get the status of the server(is it up or down)
	private String getServerStatus()
	{
		//Declare a field
		String status = "";

		if(serverUp)
			status = "Up";
		else
			status = "Down";

		return status;
	}//End of getServerStatus method

	//Get the connected users(Display the amount of people connected)
	private int getConnectedUsers()
	{
		//Declare a field
		int numConnUsers = 0;



		return numConnUsers;
	}//End of getConnectedUsers method

	//Start the console and accept commands from the server owner
	private void startConsole() throws InterruptedException
	{
		//Let the user know that they can enter in a command to view all the commands
		System.out.println("Enter /help to display a list of commands.");

		//Keep prompting the user owner to enter in a command
		while(true)
		{
			System.out.print("<Command>: ");
			command = in.next();
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
				Thread.sleep(2000);
				statusThread.start();
				serverUp = true;
				System.out.println("Server started sucessfully!");
			}
			else if(command.equals("/stop"))
			{
				System.out.println("Stopping the server...");
				Thread.sleep(2000);
				stopServer();
				serverUp = false;
			}
			else if(command.equals("/status"))
				System.out.println("Server Status: " + getServerStatus());
			else if(command.equals("/users"))
				System.out.println("Connected Users: " + getConnectedUsers());
			else
			{
				System.out.println("Command not reconized.");
			}
		}
	}//End of listenForConnection method

	//Return the connection that was just made above
	public Socket getConnection()
	{
		return sClient;
	}//End of getConnection method

	//Run the code in this method when the class is run
	public static void main(String [] args) throws InterruptedException
	{
		//Create a new Server object so we can call methods in the main method
		Server server = new Server();

		//Inform the server owner of some actions being done
		System.out.println("Attempting to bind port...");

		//Bind the ServerSocket object to listen to a specified port
		server.bindPort(22222);

		//Inform the server owner of some actions being done
		System.out.println("Opening console...");

		//Start the console and allow the server owner to enter console commands
		server.startConsole();
	}//End of Server main method
}//End of Server class
//Imports
import java.net.*;
import java.sql.*;
import java.io.*;

public class Server
{
	//Run the code in this method when the class is run
	public static void main(String [] args)
	{
		ServerSocket sServer = null;
		Socket sClient;
		ClientThread connections[] = new ClientThread[5];
		boolean flag = true;

		//Prompt the program admin that the server is being started
		System.out.println("Starting server...");

		//Bind the server to the specified port number
		try
		{
			sServer = new ServerSocket(22222);//Bind the server to port 1
		}catch(IOException e){System.out.println("Could not bind to specified port.");}

		while(flag == true)
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
		try
		{
			sServer.close();
		}catch(Exception e){System.out.println("Lol your doin' it wrong.");}
	}//End of Server main method
}//End of Server class
/**
 * @(#)ClientThread.java
 *
 * @Description
 *
 * @author Gurpreet Singh, Matt Ufimsef
 * @version 1.00 2012/6/15
 *
 * @Latest Updates:
 *
 * @Status: In Testing
 */

//Import Java API packages
import java.net.*;
import java.io.*;

public class ClientThread extends Thread
{
	//Declare class fields and objects
	Socket sClient;
	ClientThread connections[];
	BufferedReader  in;
	PrintWriter  out;

	//Initializes class fields and objects
    public ClientThread(Socket sClient, ClientThread[] connections)
    {
    	this.sClient = sClient;
    	this.connections = connections;
    	in = null;
    	out = null;
    }//End of ClientThread constructor method

	//A method to perform actions to this particular connected users(kick, ban, etc)
	public void run()
	{


	}//End of run method
}//End of ClientThread class
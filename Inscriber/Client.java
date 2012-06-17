//Matthew Ufimzeff and Gurpreet Singh
//June 17

//Import Java API Packages
import java.net.*;
import java.io.*;

public class Client
{
	private Socket sClient;
	private Database database;

	public Client()
	{

	}

	//Attempt to connect to the server
	public void tryConnect()
	{
		try
		{
            sClient = new Socket("localhost", 22222);
        }catch(IOException e){System.out.println(e + " - Could not connect to the server.");}

        if(sClient != null)
        {
        	System.out.println("Connected to server.");
			database.checkLogin()
        }
	}
}//End of Client class
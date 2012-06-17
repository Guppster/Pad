//Matthew Ufimzeff and Gurpreet Singh
//June 17

//Import Java API Packages
import java.net.*;
import java.io.*;

public class Client
{
	//Declare class fields and objects
	private Socket sClient;
	private Database database;
	private User tempUser;

	//Initializes class fields and objects
	public Client(User tempUser)
	{
		//Initialize class fields and objects
		this.tempUser = tempUser;
	}//End of Client constructor method

	//Attempt to connect to the server
	public void tryConnect()
	{
		try
		{
            sClient = new Socket("localhost", 22222);
        }catch(IOException e){eHandler.displayError("CNC");}

        if(sClient != null)
        {
			try
			{
				database.checkLogin(tempUser);
			}catch(Exception e){System.out.println(e + " - An error occurred while contacting the database.");}
        }
	}
}//End of Client class
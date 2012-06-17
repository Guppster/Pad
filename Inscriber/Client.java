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
	private ErrorHandler eHandler;

	//Initializes class fields and objects
	public Client(User tempUser)
	{
		//Initialize class fields and objects
		this.tempUser = tempUser;
		eHandler = new ErrorHandler();
	}//End of Client constructor method

	//Attempt to connect to the server
	public void tryConnect()
	{
		if(tries < 6)
		{
			try
			{
	            sClient = new Socket("localhost", 22222);
	        }catch(IOException e){eHandler.displayError("CNC") eHandler.displayError(".");}

			//If it connects fine(The socket won't be null), check the users login information
	        if(sClient != null)
	        {
				try
				{
					validLogin = database.checkLogin(tempUser);
				}catch(Exception e){eHandler.displayError("CNAD") eHandler.displayError(".");}

				if(validLogin)
					new Lobby();
				else
				{
					tries++;
					eHandler.displayError("WL");
					eHandler.displayError(".");
					sClient = null;
				}
	        }
		}
	}//End of tryConnect method
}//End of Client class
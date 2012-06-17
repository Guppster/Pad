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
		try
		{
            sClient = new Socket("localhost", 22222);
        }catch(IOException e){eHandler.displayError("CNC") eHandler.displayError(".");}

        if(sClient != null)
        {
			try
			{
				database.checkLogin(tempUser);
			}catch(Exception e){eHandler.displayError("CNAD") eHandler.displayError(".");}
        }
	}
}//End of Client class
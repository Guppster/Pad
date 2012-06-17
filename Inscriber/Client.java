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
	private int tries;

	//Initializes class fields and objects
	public Client(User tempUser)
	{
		//Initialize class fields and objects
		this.tempUser = tempUser;
		eHandler = new ErrorHandler();
		tries = 0;
	}//End of Client constructor method

	//Attempt to connect to the server
	public void tryConnect() throws Exception
	{
		if(tries < 6)
		{
			try
			{
	            sClient = new Socket("localhost", 22222);
	        }catch(IOException e){eHandler.displayError("CNC"); eHandler.displayError(".");}

			//If it connects fine(The socket won't be null), check the users login information
	        if(sClient != null)
	        {
				try
				{
					System.out.println(database.checkLogin(tempUser));
				}catch(Exception e){e.printStackTrace();}



				if(database.checkLogin(tempUser))
					new Lobby();//Open the Lobby screen
				else
				{
					tries++;//Increase their amount of tries left
					System.out.println("Tries " + tries);
					eHandler.displayError("WL");//Send an error code to the ErrorHandler class
					eHandler.displayError(".");//Display the error sent over
					sClient = null;//Disconnect them
				}
	        }
		}
	}//End of tryConnect method
}//End of Client class
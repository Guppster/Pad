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
	private String user;
	private String pass;

	//Initializes class fields and objects
	public Client(User tempUser)
	{
		//Initialize class fields and objects
		this.tempUser = tempUser;
		eHandler = new ErrorHandler();
		tries = 0;
		database = new Database();
	}//End of Client constructor method

	//Attempt to connect to the server
	public void tryConnect() throws UnknownHostException
	{
		if(tries < 6)
		{
			try
			{
	            sClient = new Socket("99.249.132.206", 25565);
	        }catch(IOException e){System.out.println("Error - " + e); eHandler.displayError("CNC"); eHandler.displayError(".");}

			System.out.println("Hi");

			/*//If it connects fine(The socket won't be null), check the users login information
	        if(sClient != null)
	        {
				try
				{
					if(database.checkLogin(tempUser))//Check if the login credentials match
						new Lobby();//Open the Lobby screen
					else
					{
						tries++;//Increase their amount of tries left
						System.out.println("Tries " + tries);
						eHandler.displayError("WL");//Send an error code to the ErrorHandler class
						eHandler.displayError(".");//Display the error sent over
						sClient = null;//Disconnect them
					}
				}catch(Exception e){eHandler.displayError("WL"); eHandler.displayError(".");}
	        }*/
		}
	}//End of tryConnect method
}//End of Client class
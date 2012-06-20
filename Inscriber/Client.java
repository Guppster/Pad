/**
 * @(#)Client.java
 *
 * @Description
 *
 * @author Gurpreet Singh, Matt Ufimsef
 * @version 1.00 2012/6/15
 *
 * @Latest Updates:
 *
 * @Status: Complete ~ accomodating for currently created classes
 */


//Import Java API Packages
import java.net.*;
import java.io.*;
import java.util.*;

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
		System.out.println(this.tempUser);
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
	            sClient = new Socket("99.249.132.206", 22222);
	        }catch(IOException e){System.out.println("Error - blah " + e);}

			//If it connects fine(The socket won't be null), check the users login information
	        if(sClient != null)
	        {
				try
				{
					if(database.checkLogin(tempUser))//Check if the login credentials match
					{
						new Lobby();//Open the Lobby screen

						if(!(database.getLoginStatus(tempUser))) // Check if the user is already logged in
						{
							database.switchLoginStatus(tempUser);//Set the users status to logged in, so another client may not login with identical credentials
						}
						else
							eHandler.displayError("ALI");
					}
					else
					{
						tries++;//Increase their amount of tries left
						System.out.println("Tries " + tries);
						eHandler.displayError("WL");//Send an error code to the ErrorHandler class
						eHandler.displayError(".");//Display the error sent over
						sClient = null;//Disconnect the user
					}
				}catch(Exception e){e.printStackTrace();}
	        }
		}
	}//End of tryConnect method
}//End of Client class
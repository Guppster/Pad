/**
 * @(#)Client.java
 *
 * @author Gurpreet Singh, Matt Ufimsef
 * @version 1.00 2012/6/15
 *
 * @Description: This class is used to connect to the server.
 *
 * @Status: Finished - Fully commeneted - No Errors.
 */

//Import Java API Packages
import java.net.*;
import java.io.*;
import java.util.*;

public class Client
{
	//Declare class fields and objects
	private Socket sClient;//Create a new connection object
	private Database database;//Create a new Database object
	private User tempUser;//Create a new User object
	private ErrorHandler eHandler;//Create a new ErrorHandler object
	private int tries;//Used to hold the number of login attempts by the user

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
		if(tries <= 5)
		{
			try
			{
	            sClient = new Socket("192.168.0.22", 22222);
	        }catch(IOException e){eHandler.displayError("CNC"); eHandler.displayError(".");}

			try
			{
				if(database.checkLogin(tempUser))//Check if the login credentials match
				{
					if(!(database.getLoginStatus(tempUser))) // Check if the user is already logged in
					{
						new Lobby(tempUser, this);//Open the Lobby screen
						database.switchLoginStatus(tempUser);//Set the users status to logged in, so another client may not login with identical credentials
					}
					else
					{
						//Indicates to the user that that account is already logged in elsewhere
						eHandler.displayError("ALI");
						eHandler.displayError(".");
						new Login(); //Login is reopened because program is suspeciously closed
					}
				}
				else
				{
					tries++;//Increase their amount of tries left
					System.out.println("Tries " + tries);
					eHandler.displayError("WL");//Send an error code to the ErrorHandler class
					eHandler.displayError(".");//Display the error sent over
					//new Login();//Login is reopened because program is suspeciously closed
				}
			}catch(Exception e){eHandler.displayError("CNAD"); eHandler.displayError(".");}
		}
		else
		{
			eHandler.displayError("TMT");
			eHandler.displayError(".");
		}
	}//End of tryConnect method

	public Socket getConnection()
	{
		return sClient;
	}//End of getConnection method
}//End of Client class
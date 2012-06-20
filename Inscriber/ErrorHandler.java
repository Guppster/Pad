/**
 * @(#)ErrorHandler.java
 *
 * @Description
 *
 * @author Gurpreet Singh, Matt Ufimsef
 * @version 1.00 2012/6/15
 *
 * @Latest Updates: initialized the string in the constructor, took out creation of new line after each message
 *
 * @Status: Complete ~ accomodating for currently created classes
 */

//Import API packages
import javax.swing.*;

public class ErrorHandler
{
	//Declare class fields and objects
	private String errorToDisplay;

	//Initialize class fields and objects
    public ErrorHandler()
    {
    	errorToDisplay = "";
    }//End of ErrorHandler constructor method

    //Displays a error message to the user
	public void displayError(String errorCode)
	{
		if(errorCode.equals("WL"))
			errorToDisplay = "Wrong username or password.";
		else if(errorCode.equals("MF"))
			errorToDisplay = "Please enter a first name.";
		else if(errorCode.equals("ML"))
			errorToDisplay = "Please enter a last name.";
		else if(errorCode.equals("MGN"))
			errorToDisplay = "Please enter a group name.";
		else if(errorCode.equals("ME"))
			errorToDisplay = "Please enter a valid email address.";
		else if(errorCode.equals("ENM"))
			errorToDisplay = "Entered email addresses do not match!";
		else if(errorCode.equals("MU"))
			errorToDisplay = "Please enter a username.";
		else if(errorCode.equals("MP"))
			errorToDisplay = "Please enter a valid password.";
		else if(errorCode.equals("PNM"))
			errorToDisplay = "Entered passwords do not match!.";
		else if(errorCode.equals("UE"))
			errorToDisplay = "The username already exists, choose another.";
		else if(errorCode.equals("EE"))
			errorToDisplay = "The email is already taken, choose another.";
		else if(errorCode.equals("UAEE"))
			errorToDisplay = "That email and username is already taken, choose another.";
		else if(errorCode.equals("CA"))
			errorToDisplay = "A critical error has occured.";
		else if(errorCode.equals("CNC"))
			errorToDisplay = "Could not connect to server.";
		else if(errorCode.equals("CNSTTS"))
			errorToDisplay = "Could not send text to server.";
		else if(errorCode.equals("CNCC"))
			errorToDisplay = "Could not close the connection.";
		else if(errorCode.equals("CNAD"))
			errorToDisplay = "Could not access database.";
		else if(errorCode.equals("UNF"))
			errorToDisplay = "Username does not exist.";
		else if(errorCode.equals("ALI"))
			errorToDisplay = "User is already logged in elsewhere.";
		else if(errorCode.equals("TMT"))
			errorToDisplay = "You have tried logging in too frequently, please take a break and try later.";
		else if(errorCode.equals("NS"))
			errorToDisplay = "This functionality has not been implemented in this release of Inscriber.";
		else if(errorCode.equals("CNLG"))
			errorToDisplay = "Could not load groups.";
		else if(errorCode.equals("CNRG"))
			errorToDisplay = "Could not remove group.";
		else if(errorCode.equals("NGS"))
			errorToDisplay = "No group selected!";

		// the "." is the flag that triggers the error, this allows to add multiple errors to one message box
		if(errorCode.equals("."))
		{
			JOptionPane.showMessageDialog(null, errorToDisplay);
			errorToDisplay = "";
		}
	}//End of displayError method
}//End of ErrorHandler class
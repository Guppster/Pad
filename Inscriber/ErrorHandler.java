//Matthew Ufimzeff and Gurpreet Singh
//June 16

//Import API packages
import javax.swing.*;

public class ErrorHandler
{
	//Declare class fields and objects
	private String errorToDisplay;

	//Initialize class fields and objects
    public ErrorHandler()
    {
    }//End of ErrorHandler constructor method

    //Displays a error message to the user
	public void displayError(String errorCode)
	{
		if(errorCode.equals("WL"))
			errorToDisplay += "Wrong username or password. \n";
		else if(errorCode.equals("MF"))
			errorToDisplay += "Please enter a first name. \n";
		else if(errorCode.equals("ML"))
			errorToDisplay += "Please enter a last name. \n";
		else if(errorCode.equals("MGN"))
			errorToDisplay += "Please enter a group name \n";
		else if(errorCode.equals("ME"))
			errorToDisplay += "Please enter a valid email address. \n";
		else if(errorCode.equals("ENM"))
			errorToDisplay += "Entered email addresses do not match! \n";
		else if(errorCode.equals("MU"))
			errorToDisplay += "Please enter a username. \n";
		else if(errorCode.equals("MP"))
			errorToDisplay += "Please enter a valid password. \n";
		else if(errorCode.equals("PNM"))
			errorToDisplay += "Entered passwords do not match! \n";
		else if(errorCode.equals("UE"))
			errorToDisplay += "The Username Already Exists, Choose Another \n";
		else if(errorCode.equals("EE"))
			errorToDisplay += "The Email is already taken, Choose Another \n";
		else if(errorCode.equals("UAEE"))
			errorToDisplay += "That Email and Username is already taken, Choose Another \n";
		else if(errorCode.equals("CA"))
			errorToDisplay += "A Critical Error has occured";

		// the "." is the flag that triggers the error, this allows to add multiple errors to one message box
		if(errorCode.equals("."))
		{
			JOptionPane.showMessageDialog(null, errorToDisplay);
			errorToDisplay = "";
		}


	}//End of displayError method

}//End of ErrorHandler class
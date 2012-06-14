//Matthew Ufimzeff and Gurpreet Singh
//June 11

//Import API packages
import javax.swing.*;

public class DataHandler
{
	//Declare class fields and objects
	private Server server;
	private boolean accepted;
	private User user;
	private String errorToDisplay;

	//Initialize class fields and objects
    public DataHandler(User user)
    {
    	//Initialize class fields and objects
    	server = new Server();
    	accepted = false;
    	this.user = user;
    }//End of LoginInformationHandler constructor method

    //Displays a error message to the user
	public void displayError(String errorCode)
	{
		if(errorCode.equals("WL"))
			errorToDisplay += "Wrong username or password. \n";
		else if(errorCode.equals("MF"))
			errorToDisplay += "Please enter a first name. \n";
		else if(errorCode.equals("ML"))
			errorToDisplay += "Please enter a last name. \n";
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
			
		// the "." is the flag that triggers the error, this allows to add multiple errors to one message box
		
		if(errorCode.equals("."))
			JOptionPane.showMessageDialog(null, errorToDisplay);
	}//End of displayError method

	//Test the username and password entered to see if found in database
    public void checkInfo()
    {
			
	}//End of checkInfo method
	
	public void saveUser()
	{
		//Save info stuff goes here

	}//End of saveInfo method

    private void passwordAccepted()
    {
    	//Set the accepted boolean test field to true
    	accepted = true;

    	//Create a Lobby object, thus displaying the Lobby frame
    	new Lobby();
    }//End of passwordAccepted method

    //Check whether or not the user is allowed access - Used in the Login class
    public boolean isAccepted()
    {
    	if(accepted)
    		return true;
    	else
    		return false;
    }//End of isAccepted return method
}//End of DataHandler class
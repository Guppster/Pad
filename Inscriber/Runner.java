/**
 * @(#)Runner.java
 *
 * @author Matthew Ufimzeff, Gurpreet Singh
 * @version 1.00 2012/6/20
 *
 * @Class Description: This class is used to start the actual project, and run everything.
 *
 * @Status: Finished - Completely commented - No errors.
 *
 *@Note: The 2 LookAndFeel libaries MUST be imported into the JDK profile for the LookAndFeel to work. See Software Manual for further help.
 */

//Import Java API Packages
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Runner
{
    public static void main(String [] args)
    {
    	//Create a new LookAndFeel object so we can set the look and feel of ALL the GUI screens
	    new LookAndFeel();

		//Run the Login screen(Open it)
	    SwingUtilities.invokeLater(new Runnable()
	    {
	    	public void run()
	        {
	        	new Login();
	      	}
	    });
    }//End of Runner main method
}//End of Runner class
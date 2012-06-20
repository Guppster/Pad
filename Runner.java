//Matthew Ufimzeff and Gurpreet Singh
//June 20

//Import Java API Packages
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Runner
{
    public static void main(String [] args)
    {
    	//Create a LookAndFeel object so we can set the look and fell of the GUI
	    new LookAndFeel();

	    SwingUtilities.invokeLater(new Runnable()
	    {
	    	public void run()
	        {
	        	new Login();
	      	}
	    });
    }
}
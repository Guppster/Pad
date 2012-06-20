//Matthew Ufimzeff and Gurpreet Singh
//June 20

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
/**
 * @(#)LookAndFeel.java
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

//Import API packages
import java.awt.*;
import javax.swing.*;

public class LookAndFeel
{
    public LookAndFeel()
    {
    	JFrame.setDefaultLookAndFeelDecorated(true);//Allows the Look and Feel to change the window frame GUI

   		try
   		{
   			SwingUtilities.invokeAndWait(new Runnable()
   			{
   				public void run()
   				{
   					try
   					{
   						//Set the Look and Feel to Magellan
   						UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.SubstanceMagellanLookAndFeel");
   				   	}catch(Exception e){System.out.println("Substance-Magellan failed to initialize");}
   				}
   			});
   		}catch(Exception e){System.out.println("Look and Feel failed to initialize.");}
    }//End of LookAndFeel constructor method
}//End of LookAndFeel class
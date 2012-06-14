
//Import the API packages being used
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
//import java.awt.image.*;
//import java.text.*;
//import java.io.*;

public class DrawingMainBoard extends JFrame implements MouseListener, MouseMotionListener
{
    public DrawingMainBoard() 
    {
    	//Adds the mouselisteners to the frame
		addMouseListener(this);
		addMouseMotionListener(this);
    	
    	//Set the JPanel as the pane that we will be drawing/putting objects on
		this.setContentPane(new JPanel());
		
		//Create an instance of the layout manager
		SpringLayout layout = new SpringLayout();
		
		//Add the layout manager to the this so we can arrange where the objects go on the this
		this.getContentPane().setLayout(layout);
		
		//Sets the properties of the JFrame
    	this.setSize(500,500);
    	this.setResizable(false);
    	this.setLocationRelativeTo(null);
    	this.setVisible(true);
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    	this.setTitle("MUCA Drawing Board");//MUCA = Multi User Collaboration Application
    }//End of main method 
    
    public void mouseClicked(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseDragged(MouseEvent e){}
	public void mouseMoved(MouseEvent e){}
	 
}//End of class
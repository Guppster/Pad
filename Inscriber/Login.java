/**
 * @(#)Login.java
 *
 * @Description
 *
 * @author Gurpreet Singh, Matt Ufimsef
 * @version 1.00 2012/6/15
 *
 * @Latest Updates:
 *
 * @Status: Complete
 */

//Import Java API packages
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.net.*;
import java.sql.*;
import java.io.*;
import java.util.*;

public class Login extends JFrame implements ActionListener, KeyListener
{
	//Declare class fields and objects
	private JTextField txtUser;
	private JPasswordField pfPass;
	private JButton cmdLogin;
	private JButton cmdNewUser;
	private JLabel lblUser;
	private JLabel lblPass;
	private SpringLayout layout;
	private User tempUser;
	private Client client;
	private ErrorHandler eHandler;

	public Login()
	{
		//Initialize class fields and objects
		tempUser = new User();
		txtUser = new JTextField(15);
		pfPass = new JPasswordField(15);
		cmdLogin = new JButton("Login");
		cmdNewUser = new JButton("Register New Account");
		lblUser = new JLabel("Username");
		lblPass = new JLabel("Password:");
		layout = new SpringLayout();

		//Call the setGUI method
		setGUI();
	}//End of Login constructor method

	//Construct all the GUI components
	private void setGUI()
	{
		//Add the layout manager to the GUI frame
		this.getContentPane().setLayout(layout);

		//Add a KeyListener to the object, add the object to the frame, and set the coordinates of the object
		txtUser.addKeyListener(this);
	    this.add(txtUser);
		layout.putConstraint(SpringLayout.WEST, txtUser, 90, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, txtUser, 25, SpringLayout.NORTH, this.getContentPane());

		//Add a KeyListener to the object, add the object to the frame, and set the coordinates of the object
		pfPass.addKeyListener(this);
		this.add(pfPass);
		layout.putConstraint(SpringLayout.WEST, pfPass, 90, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, pfPass, 60, SpringLayout.NORTH, this.getContentPane());

		//Add an ActionListener to the object, set a keyword for the object, add the object to the frame, and set the coordinates of the object
		cmdLogin.addActionListener(this);
		this.add(cmdLogin);
		cmdLogin.setActionCommand("login");
		layout.putConstraint(SpringLayout.WEST, cmdLogin, 300, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, cmdLogin, 40, SpringLayout.NORTH, this.getContentPane());

		//Add an ActionListener to the object, set a keyword for the object, add the object to the frame, and set the coordinates of the object
		cmdNewUser.addActionListener(this);
		this.add(cmdNewUser);
		cmdNewUser.setActionCommand("new");
	    layout.putConstraint(SpringLayout.WEST, cmdNewUser, 375, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, cmdNewUser, 40, SpringLayout.NORTH, this.getContentPane());

		//Add the object to the frame, and set the coordinates of the object
		this.add(lblUser);
		layout.putConstraint(SpringLayout.WEST, lblUser, 25, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, lblUser, 25, SpringLayout.NORTH, this.getContentPane());

		//Add the object to the frame, and set the coordinates of the object
		this.add(lblPass);
		layout.putConstraint(SpringLayout.WEST, lblPass, 25, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, lblPass, 60, SpringLayout.NORTH, this.getContentPane());

		//Set the properties for the GUI frame
	    this.setSize(525, 125);
	    this.setResizable(false);
	    this.setLocationRelativeTo(null);
	    this.setTitle("Inscriber Login: Welcome to Inscriber!");
	   	this.setVisible(true);
	    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}//End of setGUI method

    //Perform an action whenever a button is pressed
    public void actionPerformed (ActionEvent evt)
    {
    	//Initialize class fields and objects
    	tempUser.setUsername(txtUser.getText());//Get the username typed by the user and put it in a field of user
    	tempUser.setPassword(pfPass.getPassword());//Get the password typed by the user and put it in a field of user

		//Send the temp User object to the Client class so it can check the username/password
    	client = new Client(tempUser);

    	//Check which button was pressed
		if("login".equals(evt.getActionCommand()))
		{
			try
			{
				//Try a connection with the server
				client.tryConnect();

				//Get rid of the Login frame to conserve resources
				this.dispose();
			}catch(IOException e){eHandler.displayError("CNC"); eHandler.displayError(".");}
		}
		else if ("new".equals(evt.getActionCommand()))
		{
			//Create a Register object, thus displaying the Register frame
			new Register();
		}
    }//End of actionPerformed method

    //Perform an action whenever a certain key is pressed
    public void keyPressed(KeyEvent e)
    {
    	//Declare and initialize fields
     	int key = e.getKeyCode();

		//Check if the 'Enter' key was pressed
     	if(key == KeyEvent.VK_ENTER)
     	{
			try
			{
				//Try a connection with the server
				client.tryConnect();

				//Get rid of the Login frame to conserve resources
				this.dispose();
			}catch(IOException e2){eHandler.displayError("CNC"); eHandler.displayError(".");}
     	}
    }//End of keyPressed method

    //Not needed but required
    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e){}

    //Test Harness
    public static void main(String[] args)
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
    }//End of Test Harness
}//End of Login class
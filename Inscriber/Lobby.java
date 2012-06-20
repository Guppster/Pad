/**
 * @(#)Lobby.java
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

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Lobby extends JFrame implements ActionListener
{
	//Declare class fields and objects
	private JLabel lblDocument;
	private JLabel lblDocumentPass;
	private JLabel lblRecentlyCreatedDocuments;
	private JTextField txtDocumentName;
	private JPasswordField pfDocumentPass;
	private JButton cmdCreate;
	private JButton cmdBrowse;
	private JButton cmdCheckForUpdates;
	private JButton cmdLogout;
	private JTable tRecentlyCreated;
	private SpringLayout layout;
	private Database database;
	private User user;

	//Constructor
    public Lobby(User user)
    {
		//Initialize class fields and objects
		lblDocument = new JLabel("Document ID:");
		lblDocumentPass = new JLabel("Document Pass(optional)");
		lblRecentlyCreatedDocuments = new JLabel("Recently Created Public Documents");
		txtDocumentName = new JTextField(15);
		pfDocumentPass = new JPasswordField(15);
		cmdBrowse = new JButton("Browse Public Documents");
		cmdCheckForUpdates = new JButton("Check for Updates");
		cmdCreate = new JButton("Create New Document");
		cmdLogout = new JButton("Logout");
		tRecentlyCreated = new JTable(7, 3); //col's author name, document name, date of creation.
		layout = new SpringLayout();
		this.user = user;

		//Initialize the user object
		initializeUser();

		//Call method to create GUI
		setGUI();
    }//End of Lobby constructor method

    //This helper method initializes the user object from the database (The tempUser passed in from client only contains the username and password from login, so the database class finds the rest of the fields and inputs them into their according user fields)
    private void initializeUser()
    {
    	user = database.initializeUser(user);
    }//End of initializeUser

	//Adds and positions the GUI objects on the screen
    private void setGUI()
    {
    	//Add the layout manager to the GUI frame
		this.getContentPane().setLayout(layout);

		//Add the object to the frame, and set the coordinates of the object
		this.add(lblDocument);
		layout.putConstraint(SpringLayout.NORTH, lblDocument, 25, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, lblDocument, 50, SpringLayout.WEST, this.getContentPane());

    	//Add the object to the frame, and set the coordinates of the object
		this.add(lblDocumentPass);
		layout.putConstraint(SpringLayout.NORTH, lblDocumentPass, 60, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, lblDocumentPass, 50, SpringLayout.WEST, this.getContentPane());

    	//Add the object to the frame, and set the coordinates of the object
		this.add(lblRecentlyCreatedDocuments);
		layout.putConstraint(SpringLayout.NORTH, lblRecentlyCreatedDocuments, 115, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, lblRecentlyCreatedDocuments, 10, SpringLayout.WEST, this.getContentPane());

    	//Add the object to the frame, and set the coordinates of the object
		this.add(txtDocumentName);
		layout.putConstraint(SpringLayout.NORTH, txtDocumentName, 25, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, txtDocumentName, 300, SpringLayout.WEST, this.getContentPane());

    	//Add the object to the frame, and set the coordinates of the object
		this.add(pfDocumentPass);
		layout.putConstraint(SpringLayout.NORTH, pfDocumentPass, 60, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, pfDocumentPass, 300, SpringLayout.WEST, this.getContentPane());

    	//Add the object to the frame, and set the coordinates of the object
		this.add(cmdBrowse);
		cmdBrowse.setActionCommand("browse");
		layout.putConstraint(SpringLayout.NORTH, cmdBrowse, 140, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, cmdBrowse, 320, SpringLayout.WEST, this.getContentPane());

    	//Add the object to the frame, and set the coordinates of the object
		this.add(cmdCheckForUpdates);
		cmdCheckForUpdates.setActionCommand("update");
		layout.putConstraint(SpringLayout.NORTH, cmdCheckForUpdates, 180, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, cmdCheckForUpdates, 320, SpringLayout.WEST, this.getContentPane());

    	//Add the object to the frame, and set the coordinates of the object
		this.add(cmdCreate);
		cmdCreate.setActionCommand("create");
		layout.putConstraint(SpringLayout.NORTH, cmdCreate, 220, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, cmdCreate, 320, SpringLayout.WEST, this.getContentPane());

		//Add the object to the frame, and set the coordinates of the object
		this.add(cmdLogout);
		cmdLogout.setActionCommand("logout");
		layout.putConstraint(SpringLayout.NORTH, cmdLogout, 260, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, cmdLogout, 320, SpringLayout.WEST, this.getContentPane());

		//Add the object to the frame, and set the coordinates of the object
		this.add(tRecentlyCreated);
		layout.putConstraint(SpringLayout.NORTH, tRecentlyCreated, 135, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, tRecentlyCreated, 10, SpringLayout.WEST, this.getContentPane());

		//Set the properties of the frame/pane
    	this.setSize(510,295);
    	this.setResizable(false);
    	this.setLocationRelativeTo(null);
    	this.setTitle("Inscriber Lobby");
    	this.setVisible(true);
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }//End of setGUI method;

    //Perform an action whenever a button is pressed
    public void actionPerformed (ActionEvent evt)
    {
    	//Check which button was pressed
		if("logout".equals(evt.getActionCommand()))
		{
			logout();
		}
		else if ("browse".equals(evt.getActionCommand()))
		{
			browse();
		}
		else if ("create".equals(evt.getActionCommand()))
		{
			create();
		}
		else if ("update".equals(evt.getActionCommand()))
		{
			//new Splash();
		}
    }//End of actionPerformed method

    //Method is called when logout button is pressed by user in lobby, disconnects the user and closes lobby, reopens the login screen.
    private void logout()
    {
		try
		{
			database.switchLoginStatus(user);
		}catch(Exception e){eHandler.displayError("CNL"); eHandler.displayError(".");}

		new Login();
		this.dispose();
    }//End of logout method

    //Method is called when create new document button is pressed, closes lobby and opens a new writingmainboard using the info entered in the text fields
    private void create()
    {


    }//End of create method

	//Method is called when the browse button is pressed, closes lobby and opens the browseDocuments screen
    private void browse()
    {

    }//End of browse method
}//End of Main class
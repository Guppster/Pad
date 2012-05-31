//Import java API packages
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.*;
import java.text.*;

public class CreateNewGroup extends JFrame implements ActionListener
{
	//Declare class objects and fields
	private JTextField txtGroupName;
	private JButton cmdPresetPermissions;
	private JRadioButton optEditDocs;
	private JRadioButton optChat;
	private JRadioButton optExport;
	private JRadioButton optFormatting;
	private JRadioButton optAccess;
	private JButton cmdCreate;
	//private Server server;          No server class yet
	private Dimension frameSize;
	
	//Setup the layout manager
    SpringLayout layout;//Create a SpringLayout object

    public CreateNewGroup() 
    {
    	//Initialize values for class objects
		//server = new Server();	  No server class yet
		txtGroupName = new JTextField();
		cmdPresetPermissions = new JButton("Preset Permissions");
		cmdCreate = new JButton("Create Group");
		optEditDocs = new JRadioButton("Users can edit document?");
		optChat = new JRadioButton("Users can chat?");
		optExport = new JRadioButton("Users can export document?(Save document to their own computer)");
		optFormatting = new JRadioButton("Users can use formatting in document?(Bold, italics, etc)");
		optAccess = new JRadioButton("Users can access admin controls?");
		frameSize = new Dimension(500, 300);
		layout = new SpringLayout();
    	
    	//Constructs the GUI components and displays them on the screen
    	setGUI();
    }//End of Default Constructor
    
    //Construct the GUI components
	private void setGUI()
	{	
		//Add objects to the GUI frame
		this.add(txtGroupName);
		this.add(cmdPresetPermissions);
		this.add(optEditDocs);
		this.add(optChat);
	 	this.add(optExport);
		this.add(optFormatting);
		this.add(optAccess);
		this.add(cmdCreate);
		
		//Add actionlistener to active objects
		cmdPresetPermissions.addActionListener(this);
		cmdCreate.addActionListener(this);
		
		//Change the frame color
		this.getContentPane().setBackground(Color.BLACK);
		
		//Set the layout for the frame
		this.getContentPane().setLayout(layout);
		
		//Set the west and north positions of the objects on the frame
		//txtGroupName TextField Cordinates
		layout.putConstraint(SpringLayout.WEST, txtGroupName, 10, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, txtGroupName, 10, SpringLayout.NORTH, this.getContentPane());
		
		//cmdPresetPermissions Button Cordinates
		layout.putConstraint(SpringLayout.WEST, cmdPresetPermissions, 10, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, cmdPresetPermissions, 10, SpringLayout.NORTH, this.getContentPane());
		
		//optEditDocs option box Cordinates
		layout.putConstraint(SpringLayout.WEST, optEditDocs, 10, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, optEditDocs, 10, SpringLayout.NORTH, this.getContentPane());
		
		//optChat option box Cordinates
		layout.putConstraint(SpringLayout.WEST, optChat, 10, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, optChat, 10, SpringLayout.NORTH, this.getContentPane());

		//optChat option box Cordinates
		layout.putConstraint(SpringLayout.WEST, optChat, 10, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, optChat, 10, SpringLayout.NORTH, this.getContentPane());
		
		//optFormatting option box Cordinates
		layout.putConstraint(SpringLayout.WEST, optFormatting, 10, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, optFormatting, 10, SpringLayout.NORTH, this.getContentPane());
		
		//optAccess option box Cordinates
		layout.putConstraint(SpringLayout.WEST, optAccess, 10, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, optAccess, 10, SpringLayout.NORTH, this.getContentPane());
		
		//cmdCreate Button Cordinates
		layout.putConstraint(SpringLayout.WEST, cmdCreate, 10, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, cmdCreate, 10, SpringLayout.NORTH, this.getContentPane());
		
		//Call the setting methods for the frame
		this.setSize(frameSize);
		this.setLocationRelativeTo(null);
		this.setTitle("Create New Group");
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}//End of setGUI method
	
	//Update the GUI frame
	private void updateGUI()
	{
		
	}//End of updateGUI method
	
	//Loads the preset options for the group
	private void loadDefaults()
	{
		
	}//End of loadDefaults method
	
	//Saves the group and permissions to the database
	private void saveGroup()
	{
		
	}//End of saveGroup method
		
	//Perform an action whenever a button is pressed
	public void actionPerformed(ActionEvent evt)
	{
		
	}//End of actionPerformed method

    //Test Harness
	public static void main(String [] args)
	{
		new CreateNewGroup();
	}//End of Test Harness main method 
}//End of class
//By Matthew Ufimzeff and Gurpreet Singh
//June 9

//Import java API packages
import javax.swing.*;
import java.awt.*;
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
	//private Server server;
	private Dimension frameSize;
	private SpringLayout layout;

    public CreateNewGroup()
    {
    	//Initialize class objects and fields
		//server = new Server();
		txtGroupName = new JTextField();
		cmdPresetPermissions = new JButton("Preset Permissions");
		cmdCreate = new JButton("Create Group");
		optEditDocs = new JRadioButton("Users can edit document?");
		optChat = new JRadioButton("Users can chat?");
		optExport = new JRadioButton("Users can export document?(Save document to their own computer)");
		optFormatting = new JRadioButton("Users can use formatting in document?(Bold, italics, etc)");
		optAccess = new JRadioButton("Users can access admin controls?");
		layout = new SpringLayout();

    	//Constructs the GUI components and displays them on the screen
    	setGUI();
    }//End of Default Constructor

    //Construct the GUI components
	private void setGUI()
	{
		this.add(txtGroupName);
		this.add(cmdPresetPermissions);
		this.add(optEditDocs);
		this.add(optChat);
	 	this.add(optExport);
		this.add(optFormatting);
		this.add(optAccess);
		this.add(cmdCreate);

		//Add action listener objects to the JButton objects
		cmdPresetPermissions.addActionListener(this);
		cmdCreate.addActionListener(this);

		//Set the action commands of the JButton objects so the action listener object knows which button we are referring to
		cmdPresetPermissions.setActionCommand("preset");
		cmdCreate.setActionCommand("create");

		//Set the layout manager of the GUI frame
		this.getContentPane().setLayout(layout);

		this.add(txtGroupName);
		layout.putConstraint(SpringLayout.WEST, txtGroupName, 10, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, txtGroupName, 10, SpringLayout.NORTH, this.getContentPane());

		this.add(cmdPresetPermissions);
		layout.putConstraint(SpringLayout.WEST, cmdPresetPermissions, 10, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, cmdPresetPermissions, 10, SpringLayout.NORTH, this.getContentPane());

		this.add(optEditDocs);
		layout.putConstraint(SpringLayout.WEST, optEditDocs, 10, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, optEditDocs, 10, SpringLayout.NORTH, this.getContentPane());

		this.add(optChat);
		layout.putConstraint(SpringLayout.WEST, optChat, 10, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, optChat, 10, SpringLayout.NORTH, this.getContentPane());

		this.add(optFormatting);
		layout.putConstraint(SpringLayout.WEST, optFormatting, 10, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, optFormatting, 10, SpringLayout.NORTH, this.getContentPane());

		this.add(optAccess);
		layout.putConstraint(SpringLayout.WEST, optAccess, 10, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, optAccess, 10, SpringLayout.NORTH, this.getContentPane());

		this.add(cmdCreate);
		layout.putConstraint(SpringLayout.WEST, cmdCreate, 10, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, cmdCreate, 10, SpringLayout.NORTH, this.getContentPane());

		//Set up some GUI properties
		this.setSize(500, 300);
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
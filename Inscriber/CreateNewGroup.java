/**
 * @(#)CreateNewGroup.java
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

//Import java API packages
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;

public class CreateNewGroup extends JFrame implements ActionListener
{
	//Declare class objects and fields
	private JLabel lblGroupName;
	private JTextField txtGroupName;
	private JButton cmdPresetPermissions;
	private JRadioButton optEditDocs;
	private JRadioButton optChat;
	private JRadioButton optExport;
	private JRadioButton optFormatting;
	private JRadioButton optAccess;
	private JButton cmdCreate;
	private Dimension frameSize;
	private SpringLayout layout;
	private ErrorHandler handler;
	private Database database;
	private UserGroup group;
	private boolean[] permissions;
	private boolean[] defaultPermissions;

    public CreateNewGroup()
    {
    	//Initialize class objects and fields
    	lblGroupName = new JLabel("Group Name:");
		txtGroupName = new JTextField(15);
		cmdPresetPermissions = new JButton("Preset Permissions");
		cmdCreate = new JButton("Create Group");
		optEditDocs = new JRadioButton("Users can edit document?");
		optChat = new JRadioButton("Users can chat?");
		optExport = new JRadioButton("Users can export document?(Save document to their own computer)");
		optFormatting = new JRadioButton("Users can use formatting in document?(Bold, italics, etc)");
		optAccess = new JRadioButton("Users can access admin controls?");
		database = new Database();
		layout = new SpringLayout();
		group = new UserGroup();
		permissions = new boolean[5];
		defaultPermissions = new boolean[5];

    	//Constructs the GUI components and displays them on the screen
    	setGUI();
    }//End of Default Constructor

    //Construct the GUI components
	private void setGUI()
	{
		//Add action listener objects to the JButton objects
		cmdPresetPermissions.addActionListener(this);
		cmdCreate.addActionListener(this);

		//Set the action commands of the JButton objects so the action listener object knows which button we are referring to
		cmdPresetPermissions.setActionCommand("preset");
		cmdCreate.setActionCommand("create");

		//Set the layout manager of the GUI frame
		this.getContentPane().setLayout(layout);

		this.add(lblGroupName);
		layout.putConstraint(SpringLayout.WEST, lblGroupName, 10, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, lblGroupName, 10, SpringLayout.NORTH, this.getContentPane());

		this.add(txtGroupName);
		layout.putConstraint(SpringLayout.WEST, txtGroupName, 90, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, txtGroupName, 10, SpringLayout.NORTH, this.getContentPane());

		this.add(cmdPresetPermissions);
		layout.putConstraint(SpringLayout.WEST, cmdPresetPermissions, 320, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, cmdPresetPermissions, 10, SpringLayout.NORTH, this.getContentPane());

		this.add(optEditDocs);
		layout.putConstraint(SpringLayout.WEST, optEditDocs, 10, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, optEditDocs, 70, SpringLayout.NORTH, this.getContentPane());

		this.add(optChat);
		layout.putConstraint(SpringLayout.WEST, optChat, 10, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, optChat, 95, SpringLayout.NORTH, this.getContentPane());

		this.add(optExport);
		layout.putConstraint(SpringLayout.WEST, optExport, 10, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, optExport, 120, SpringLayout.NORTH, this.getContentPane());

		this.add(optFormatting);
		layout.putConstraint(SpringLayout.WEST, optFormatting, 10, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, optFormatting, 145, SpringLayout.NORTH, this.getContentPane());

		this.add(optAccess);
		layout.putConstraint(SpringLayout.WEST, optAccess, 10, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, optAccess, 170, SpringLayout.NORTH, this.getContentPane());

		this.add(cmdCreate);
		layout.putConstraint(SpringLayout.WEST, cmdCreate, 10, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, cmdCreate, 210, SpringLayout.NORTH, this.getContentPane());

		//Set up some GUI properties
		this.setSize(450, 275);
		this.setLocationRelativeTo(null);
		this.setTitle("Inscriber: Create a New User Group");
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}//End of setGUI method

	//Loads the preset options for the group
	private void loadDefaults()
	{
		defaultPermissions = database.retrieveDefaultPermissions();
	}//End of loadDefaults method

	//A method to get the states of the JRadioButton objects, and store them in a boolean array
	private void loadGroup()
	{
		permissions[0] = optEditDocs.isSelected();
		permissions[1] = optChat.isSelected();
		permissions[2] = optExport.isSelected();
		permissions[3] = optFormatting.isSelected();
		permissions[4] = optAccess.isSelected();
		group.setPermissions(permissions);
	}//End of loadGroup method

	//A method to set the states of the JRadioButton objects
	private void setState()
	{
		//Sets the state of the 'edit documents' JRadioButton object
		if(defaultPermissions[0])
			optEditDocs.setSelected(true);
		else
			optEditDocs.setSelected(false);

		//Sets the state of the 'chat' JRadioButton object
		if(defaultPermissions[1])
			optChat.setSelected(true);
		else
			optChat.setSelected(false);

		//Sets the state of the 'export' JRadioButton object
		if(defaultPermissions[2])
			optExport.setSelected(true);
		else
			optExport.setSelected(false);

		//Sets the state of the 'formatting' JRadioButton object
		if(defaultPermissions[3])
			optFormatting.setSelected(true);
		else
			optFormatting.setSelected(false);

		//Sets the state of the 'access' JRadioButton object
		if(defaultPermissions[4])
			optAccess.setSelected(true);
		else
			optAccess.setSelected(false);
	}//End of setState method

	//Saves the group and permissions to the database
	private void saveGroup()
	{
		if(!(setGroupName().equals("")))
		{
			group.setName(setGroupName());
			database.addNewGroup(group);
		}
		else
		{
			handler.displayError("MGN");
			handler.displayError(".");
		}
	}//End of saveGroup method

	//Sets the name of the group, so we can name the group we are creating
	private String setGroupName()
	{
		//Declare and initialize field
		String name = "";

		name = txtGroupName.getText();
		return name;
	}//End of setGroupName method

	//Perform an action whenever a button is pressed
	public void actionPerformed(ActionEvent evt)
	{
		if(evt.getActionCommand().equals("create"))//Loads the states of the JRadioButton objects into an array, then save the group to the database
		{
			loadGroup();
			setGroupName();
			saveGroup();
		}
		else if(evt.getActionCommand().equals("preset"))
		{
			loadDefaults();
			setState();
			txtGroupName.setText("Default");
		}
	}//End of actionPerformed method

    //Test Harness
	public static void main(String [] args)
	{
		//Create a LookAndFeel object so we can set the look and fell of the GUI
	    new LookAndFeel();

	    SwingUtilities.invokeLater(new Runnable()
	    {
	    	public void run()
	        {
	        	new CreateNewGroup();
	      	}
	    });
	}//End of Test Harness main method
}//End of class
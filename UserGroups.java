//Import java API packages
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.*;
import java.text.*;

public class UserGroups extends JFrame implements ActionListener
{
	//Declare class objects and fields
	private JButton cmdNew;
	private JButton cmdEdit;
	private JButton cmdRemove;
	private JTable tGroups;
	private JScrollPane spGroupsTable;
	private Dimension frameSize;

	//Setup the layout manager
    SpringLayout layout;//Create a SpringLayout object

    public UserGroups()
    {
    	//Initialize values of class objects
		cmdNew = new JButton("Create new User group");
		cmdEdit = new JButton("Edit Selected User group");
		cmdRemove = new JButton("Remove Selected User group");
		tGroups = new JTable(10, 1);
		spGroupsTable = new JScrollPane(tGroups);
		frameSize = new Dimension(245, 300);
		layout = new SpringLayout();

		//Constructs the GUI components and displays them on the screen
		setGUI();
    }//End of constructor

    //Construct the GUI components
	private void setGUI()
	{
		//Add objects to the GUI frame
		this.add(cmdNew);
		this.add(cmdEdit);
		this.add(cmdRemove);
		this.add(spGroupsTable);

		tGroups.setTableHeader(null);
		spGroupsTable.setPreferredSize(new Dimension(200,165));


		//Add actionlistener to active objects
		cmdNew.addActionListener(this);
		cmdEdit.addActionListener(this);
		cmdRemove.addActionListener(this);

		//Change the frame color
		this.getContentPane().setBackground(Color.BLACK);

		//Set the layout for the frame
		this.getContentPane().setLayout(layout);

		//Set the west and north positions of the objects on the frame
		//New Button Cordinates
		layout.putConstraint(SpringLayout.WEST, cmdNew, 20, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, cmdNew, 180, SpringLayout.NORTH, this.getContentPane());

		//Edit Button Cordinates
		layout.putConstraint(SpringLayout.WEST, cmdEdit, 20, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, cmdEdit, 210, SpringLayout.NORTH, this.getContentPane());

		//Remove Button Cordinates
		layout.putConstraint(SpringLayout.WEST, cmdRemove, 20, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, cmdRemove, 240, SpringLayout.NORTH, this.getContentPane());

		//Table of Groups Cordinates
		layout.putConstraint(SpringLayout.WEST, spGroupsTable, 20, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, spGroupsTable, 10, SpringLayout.NORTH, this.getContentPane());

		//Call the setting methods for the frame
		this.setSize(frameSize);
		this.setLocationRelativeTo(null);
		this.setTitle("User Groups");
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}//End of setGUI method

	//Updates the GUI frame
	private void updateGUI()
	{

	}//End of updateGUI method

	//Perform an action whenever a button is pressed
	public void actionPerformed(ActionEvent evt)
	{
		/*
		if(evt == "cmdNew")
		{
			newGroup();
		}
		else if(evt == "cmdEdit")
		{
			editGroup();
		}
		else if(evt == "cmdRemove")
		{
			removeGroup();
		}
		*/
	}//End of actionPerformed method

	//Opens the New Group frame
	private void newGroup()
	{
		//Opens the Create new Group Screen
		//new CreateNewGroup();

	}//End of newGroup method

	//Opens the EditGroup frame
	private void editGroup()
	{

	}//End of editGroup method

	//Loads the saved user groups from the database
	private void loadGroups()
	{

	}//End of loadGroups method

	//Adds a user group to the database
	private void addGroup()
	{
		//new CreateNewGroup();
	}//End of addGroup method

	//Removes a user group from the database
	private void removeGroup()
	{

	}//End of removeGroup method

	//Test Harness
	public static void main(String [] args)
	{
		//UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel");
		new UserGroups();
	}//End of Test Harness main method
}//End of class

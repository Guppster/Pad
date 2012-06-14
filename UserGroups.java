//By Matthew Ufimzeff and Gurpreet Singh
//June 9

//Import java API packages
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;

public class UserGroups extends JFrame implements ActionListener
{
	//Declare class objects and fields
	private JButton cmdNew;
	private JButton cmdEdit;
	private JButton cmdRemove;
	private JTable tGroups;
	private JScrollPane spGroups;
    private SpringLayout layout;

    public UserGroups()
    {
    	//Initialize class objects and fields
		cmdNew = new JButton("Create new User group");
		cmdEdit = new JButton("Edit Selected User group");
		cmdRemove = new JButton("Remove Selected User group");
		tGroups = new JTable(5, 1);
		spGroups = new JScrollPane(tGroups);
		layout = new SpringLayout();

		//Constructs the GUI components and displays them on the screen
		setGUI();
    }//End of constructor

    //Construct the GUI components
	private void setGUI()
	{
		//Set some properties for the JTable object
		tGroups.setTableHeader(null);
		spGroups.setPreferredSize(new Dimension(255, 82));

		//Add action listener objects to the JButton objects
		cmdNew.addActionListener(this);
		cmdEdit.addActionListener(this);
		cmdRemove.addActionListener(this);

		//Set the action commands of the JButton objects so the action listener object knows which button we are referring to
		cmdNew.setActionCommand("new");
		cmdEdit.setActionCommand("edit");
		cmdRemove.setActionCommand("remove");

		//Set the layout manager of the GUI frame
		this.getContentPane().setLayout(layout);

		this.add(cmdNew);//Add the add new user group button object to the GUI frame and position it
		layout.putConstraint(SpringLayout.WEST, cmdNew, 20, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, cmdNew, 120, SpringLayout.NORTH, this.getContentPane());

		this.add(cmdEdit);//Add the edit user group button object to the GUI frame and position it
		layout.putConstraint(SpringLayout.WEST, cmdEdit, 20, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, cmdEdit, 160, SpringLayout.NORTH, this.getContentPane());

		this.add(cmdRemove);//Add the remove user group button object to the GUI frame and position it
		layout.putConstraint(SpringLayout.WEST, cmdRemove, 20, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, cmdRemove, 200, SpringLayout.NORTH, this.getContentPane());

		this.add(spGroups);//Add the user groups scrollpane object to the GUI frame and position it
		layout.putConstraint(SpringLayout.WEST, spGroups, 20, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, spGroups, 10, SpringLayout.NORTH, this.getContentPane());

		//Set some properties of the GUI frame
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		this.setTitle("Inscriber User Groups");
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}//End of setGUI method

	//Perform an action whenever a button is pressed
	public void actionPerformed(ActionEvent evt)
	{
		if("new".equals(evt.getActionCommand()))
		{
			newGroup();
		}
		else if("edit".equals(evt.getActionCommand()))
		{
			editGroup();
		}
		else if("remove".equals(evt.getActionCommand()))
		{
			removeGroup();
		}
	}//End of actionPerformed method

	//Open the CreateNewGroup GUI screen
	private void newGroup()
	{
		new CreateNewGroup();
	}//End of newGroup method

	//
	private void editGroup()
	{

	}//End of editGroup method

	//
	private void loadGroups()
	{

	}//End of loadGroups method

	//
	private void removeGroup()
	{

	}//End of removeGroup method

	//Test Harness
	public static void main(String [] args)
	{
		//Create a LookAndFeel object so we can set the look and fell of the GUI
	    new LookAndFeel();

	    SwingUtilities.invokeLater(new Runnable()
	    {
	    	public void run()
	        {
	        	new UserGroups();
	      	}
	    });
	}//End of Test Harness main method
}//End of class

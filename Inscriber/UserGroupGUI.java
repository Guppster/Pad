/**
 * @(#)UserGroups.java
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
import javax.swing.table.DefaultTableModel;

public class UserGroupGUI extends JFrame implements ActionListener
{
	//Declare class objects and fields
	private JButton cmdNew;
	private JButton cmdEdit;
	private JButton cmdRemove;
	private DefaultTableModel model;
	private JTable tGroups;
	private JScrollPane spGroups;
    private SpringLayout layout;
    private TableHelper helper;
    private Database database;
    private ErrorHandler eHandler;

    public UserGroupGUI()
    {
    	//Initialize class objects and fields
		cmdNew = new JButton("Create new User group");
		cmdEdit = new JButton("Edit Selected User group");
		cmdRemove = new JButton("Remove Selected User group");
		model = new DefaultTableModel();
		tGroups = new JTable(model);
		spGroups = new JScrollPane(tGroups);
		layout = new SpringLayout();
		helper = new TableHelper();
		database = new Database();
		eHandler = new ErrorHandler();

		//Constructs the GUI components and displays them on the screen
		setGUI();
    }//End of constructor

    //Construct the GUI components
	private void setGUI()
	{
		//Add 1 column to the JTable object
		model.addColumn(new Object[]{});

		//Add 6 rows to the JTable object
		for(int y = 0; y < 5; y++)
		{
			model.addRow(new Object[]{});
		}

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

	//Open the EditUserGroup GUI class
	private void editGroup()
	{
		//new EditUserGroup();
		eHandler.displayError("NS");
		eHandler.displayError(".");
	}//End of editGroup method

	//Load a list of user groups from the database
	private void loadGroups()
	{
		for(int x = 0; x < database.getNumGroups(); x++)
		{
			helper.addElement(tGroups, model, database.getGroupName(x));
		}
	}//End of loadGroups method

	//Remove a group from the JTable object and attempt to remove it from the database
	private void removeGroup()
	{
		int rowIndex = tGroups.getSelectedRow();
		int columnIndex = tGroups.getSelectedColumn();

		helper.removeSelectedElement(tGroups);
		socket.database.removeGroup(tGroups.getValueAt(rowIndex, columnIndex).toString());
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

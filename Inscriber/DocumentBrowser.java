/**
 * @(#)DocumentBrowser.java
 *
 * @Description
 *
 * @author Gurpreet Singh, Matt Ufimsef
 * @version 1.00 2012/6/15
 *
 * @Latest Updates:
 *
 * @Status: In-Complete
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

public class DocumentBrowser extends JFrame implements ActionListener
{
	//Declare class fields and objects
	private JTable tDocuments;
	private DefaultTableModel model;
	private JScrollPane spDocuments;
	private JButton cmdSortByName;
	private JButton cmdSortByWords;
	private JButton cmdSortByCharacters;
	private JButton cmdSortBySentences;
	private JButton cmdSearch;
	private SpringLayout layout;
	private TableHelper tHelper;
	private Database database;

	//Default Constructor, Initializes the declared fields
    public DocumentBrowser()
    {
    	model = new DefaultTableModel();
		tDocuments = new JTable(model);
		spDocuments = new JScrollPane(tDocuments);
		tHelper = new TableHelper();
		database = new Database();
		cmdSortByName = new JButton("Sort by Name");
		cmdSortByCharacters = new JButton("Sort by Character");
		cmdSortBySentences = new JButton("Sort by Sentences");
		cmdSortByWords = new JButton("Sort by Words");
		cmdSearch = new JButton("Search for Document");
		layout = new SpringLayout();

		//Call method to create GUI
		setGUI();

		//initializes the data in table
		InitializeTableData();
    }//End of default constructor

    private void setGUI()
    {
    	//Add the layout manager to the GUI frame
		this.getContentPane().setLayout(layout);

		//Adds 30 rows to the table
		for(int y = 0; y <= 30; y++)
		{
			model.addRow(new Object[]{});
		}

		//Adds 5 columns to the table
		for(int y = 0; y < 5; y++)
		{
			model.addColumn(new Object[]{});
		}

		//Set some properties for the JTable object
		tDocuments.setTableHeader(null);
		spDocuments.setPreferredSize(new Dimension(400, 371));

		//Add the object to the frame, and set the coordinates of the object
		this.add(spDocuments);
		layout.putConstraint(SpringLayout.NORTH, tDocuments, 25, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, tDocuments, 50, SpringLayout.WEST, this.getContentPane());

		//Add the object to the frame, and set the coordinates of the object
		this.add(cmdSortByName);
		cmdSortByName.setActionCommand("sortname");
		layout.putConstraint(SpringLayout.NORTH, cmdSortByName, 45, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, cmdSortByName, 438, SpringLayout.WEST, this.getContentPane());

		//Add the object to the frame, and set the coordinates of the object
		this.add(cmdSortByCharacters);
		cmdSortByCharacters.setActionCommand("sortchars");
		layout.putConstraint(SpringLayout.NORTH, cmdSortByCharacters, 90, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, cmdSortByCharacters, 438, SpringLayout.WEST, this.getContentPane());

		//Add the object to the frame, and set the coordinates of the object
		this.add(cmdSortByWords);
		cmdSortByWords.setActionCommand("sortwords");
		layout.putConstraint(SpringLayout.NORTH, cmdSortByWords, 135, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, cmdSortByWords, 438, SpringLayout.WEST, this.getContentPane());

		//Add the object to the frame, and set the coordinates of the object
		this.add(cmdSortBySentences);
		cmdSortBySentences.setActionCommand("sortsentences");
		layout.putConstraint(SpringLayout.NORTH, cmdSortBySentences, 180, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, cmdSortBySentences, 438, SpringLayout.WEST, this.getContentPane());

		//Add the object to the frame, and set the coordinates of the object
		this.add(cmdSearch);
		cmdSortBySentences.setActionCommand("search");
		layout.putConstraint(SpringLayout.NORTH, cmdSearch, 225, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, cmdSearch, 438, SpringLayout.WEST, this.getContentPane());

		//Set the properties of the frame/pane
		this.setSize(600,400);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setTitle("Document Browser");
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}//End of setGUI method

	//Sets the initial data of the table
	private void InitializeTableData()
	{
		for(int x = 0; x <= tDocuments.getRowCount(); x++)
			{
				tHelper.addElement(tDocuments, model, database.getDocumentsData(x))
			}
	}//End of InitializeTableData method

	//Perform an action whenever a button is pressed
    public void actionPerformed (ActionEvent evt)
    {
    	//Check which button was pressed
		if("sortname".equals(evt.getActionCommand()))
		{
			sortByName();
		}
		else if ("sortchars".equals(evt.getActionCommand()))
		{
			sortByCharacters();
		}
		else if ("sortwords".equals(evt.getActionCommand()))
		{
			sortByWords();
		}
		else if ("sortsentences".equals(evt.getActionCommand()))
		{
			sortBySentences();
		}
    }//End of actionPerformed method

    private void sortByName()
    {
		boolean sorted = false;
    	string temp = "";
    	do
    	{
    		for(int x = 0; x < tDocuments.getRowCount(); x++)
    		{
	    		if(tDocuments.getValueAt(0,x) > tDocuments.getValueAt(0,x+1))
	    		{
	    			temp = tDocuments.getValueAt(0,x);
	    			tDocuments.setValueAt(tDocuments.getValueAt(0,x+1), 0, x);
					tDocuments.setValueAt(temp), 0, x+1);

	    			sorted = false;
	    		}
    		}
   		}while(sorted != true);
    }

    private void sortByCharacters()
    {
		boolean sorted = false;
    	string temp = "";
    	do
    	{
    		for(int x = 0; x < tDocuments.getRowCount(); x++)
    		{
	    		if(tDocuments.getValueAt(0,x) > tDocuments.getValueAt(0,x+1))
	    		{
	    			temp = tDocuments.getValueAt(0,x);
	    			tDocuments.setValueAt(tDocuments.getValueAt(0,x+1), 0, x);
					tDocuments.setValueAt(temp), 0, x+1);

	    			sorted = false;
	    		}
    		}
   		}while(sorted != true);
    }

    private void sortByWords()
    {

    }

    private void sortBySentences()
    {

    }

    public static void main(String [] args)
    {
		new DocumentBrowser();
    }

}//End of class
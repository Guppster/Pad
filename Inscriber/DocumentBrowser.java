/**
 * @(#)DocumentBrowser.java
 *
 * @Description
 *
 * @author Gurpreet Singh, Matthew Ufimzeff
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
	private boolean sorted = false;
	private String temp = "";

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
		for(int y = 0; y < 27; y++)
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
		cmdSortByName.addActionListener(this);
		cmdSortByName.setActionCommand("sortname");
		layout.putConstraint(SpringLayout.NORTH, cmdSortByName, 45, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, cmdSortByName, 438, SpringLayout.WEST, this.getContentPane());

		//Add the object to the frame, and set the coordinates of the object
		this.add(cmdSortByCharacters);
		cmdSortByCharacters.addActionListener(this);
		cmdSortByCharacters.setActionCommand("sortchars");
		layout.putConstraint(SpringLayout.NORTH, cmdSortByCharacters, 90, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, cmdSortByCharacters, 438, SpringLayout.WEST, this.getContentPane());

		//Add the object to the frame, and set the coordinates of the object
		this.add(cmdSortByWords);
		cmdSortByWords.addActionListener(this);
		cmdSortByWords.setActionCommand("sortwords");
		layout.putConstraint(SpringLayout.NORTH, cmdSortByWords, 135, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, cmdSortByWords, 438, SpringLayout.WEST, this.getContentPane());

		//Add the object to the frame, and set the coordinates of the object
		this.add(cmdSortBySentences);
		cmdSortBySentences.addActionListener(this);
		cmdSortBySentences.setActionCommand("sortsentences");
		layout.putConstraint(SpringLayout.NORTH, cmdSortBySentences, 180, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, cmdSortBySentences, 438, SpringLayout.WEST, this.getContentPane());

		//Add the object to the frame, and set the coordinates of the object
		this.add(cmdSearch);
		cmdSearch.addActionListener(this);
		cmdSortBySentences.setActionCommand("search");
		layout.putConstraint(SpringLayout.NORTH, cmdSearch, 225, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, cmdSearch, 438, SpringLayout.WEST, this.getContentPane());

		//Set the properties of the frame/pane
		this.setSize(600,400);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setTitle("Inscriber: Document Browser");
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}//End of setGUI method

	//Sets the initial data of the table
	private void InitializeTableData()
	{
		/*for(int x = 0; x <= tDocuments.getRowCount(); x++)
			{
				tHelper.addArrayList(tDocuments, model, database.getDocumentsData());
			}*/
	}//End of InitializeTableData method

	//Performs an action whenever a button is pressed
    public void actionPerformed (ActionEvent evt)
    {
    	//Check which button was pressed and perform an action based on which button was pressed
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

	//A helper method to sort the data in the JTable object by the name of each document
    private void sortByName()
    {
    	while(!sorted)
    	{
    		sorted = true;

    		for(int x = 0; x < tDocuments.getRowCount() - 1; x++)
		   	{
			    if((tDocuments.getValueAt(x, 0).toString()).compareTo((tDocuments.getValueAt(x+1, 0).toString())) > 0)
			    {
			    			temp = (tDocuments.getValueAt(x,0).toString());
			    			tDocuments.setValueAt(tDocuments.getValueAt(x+1, 0), x, 0);
							tDocuments.setValueAt(temp, x+1, 0);
							sorted = false;
			   	}
    		}
    	}

    	//Display a message to the user informing them that the sort has been completed
    	JOptionPane.showMessageDialog(null, "Table sorted by document name.");
    }//End of sortByName method

	//A helper method to sort the data in the JTable object by the number of characters each document has
    private void sortByCharacters()
    {
    	while(!sorted);
    	{
    		for(int x = 0; x < tDocuments.getRowCount() - 1; x++)
    		{
	    		if((tDocuments.getValueAt(x,1).toString()).compareTo(tDocuments.getValueAt(x+1, 1).toString()) > 0)
	    		{
	    			int value = (Integer)tDocuments.getValueAt(x,1);
	    			int tempNum = value.intValue();
	    			tDocuments.setValueAt(tDocuments.getValueAt(x+1, 1), 1, x);
					tDocuments.setValueAt(temp, x+1, 1);

	    			sorted = false;
	    		}
    		}
   		}
    }//End of sortByCharacter method

	//A helper method to sort the data in the JTable object by the number of words each document has
    private void sortByWords()
    {
		boolean sorted = false;
    	String temp = "";
    	do
    	{
    		for(int x = 0; x < tDocuments.getRowCount(); x++)
    		{
	    		if((tDocuments.getValueAt(2,x).toString()).compareTo(tDocuments.getValueAt(2,x+1).toString()) > 0)
	    		{
	    			temp = (tDocuments.getValueAt(2,x).toString());
	    			tDocuments.setValueAt(tDocuments.getValueAt(1,x+1), 2, x);
					tDocuments.setValueAt(temp, 2, x+1);

	    			sorted = false;
	    		}
    		}
   		}while(sorted != true);
    }//End of sortByWords method

	//A helper method to sort the data in the JTable object by the number of sentences each document has
    private void sortBySentences()
    {
		boolean sorted = false;
    	String temp = "";
    	do
    	{
    		for(int x = 0; x < tDocuments.getRowCount(); x++)
    		{
	    		if((tDocuments.getValueAt(3,x).toString()).compareTo(tDocuments.getValueAt(3,x+1).toString()) > 0)
	    		{
	    			temp = (tDocuments.getValueAt(3,x).toString());
	    			tDocuments.setValueAt(tDocuments.getValueAt(1,x+1), 3, x);
					tDocuments.setValueAt(temp, 3, x+1);

	    			sorted = false;
	    		}
    		}
   		}while(sorted != true);
    }//End of sortBySentences method

	//Test harness
    public static void main(String [] args)
    {
		new DocumentBrowser();
    }//End of test harness
}//End of Document Browser class
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

public class DocumentBrowser extends JFrame implements ActionListener
{
	//Declare class fields and objects
	private JTable tDocuments;
	private JButton cmdSortByName;
	private JButton cmdSortByWords;
	private JButton cmdSortByCharacters;
	private JButton cmdSortBySentences;
	private JButton cmdSearch;

	//Default Constructor
    public DocumentBrowser()
    {
		tDocuments = new JTable();
		cmdSortByName = new JButton("Sort by Name");
		cmdSortByCharacters = new JButton("Sort by Character");
		cmdSortBySentences = new JButton("Sort by Sentences");
		cmdSortByWords = new JButton("Sort by Words");
		cmdSearch = new JButton("Search for Document");

		//Call method to create GUI
		setGUI();
    }//End of default constructor

    private void setGUI()
    {
    	//Add the layout manager to the GUI frame
		this.getContentPane().setLayout(layout);

		//Add the object to the frame, and set the coordinates of the object
		this.add(tDocuments);
		layout.putConstraint(SpringLayout.NORTH, tDocuments, 25, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, tDocuments, 50, SpringLayout.WEST, this.getContentPane());

		//Add the object to the frame, and set the coordinates of the object
		this.add(cmdSortByName);
		cmdSortByName.setActionCommand("sortname");
		layout.putConstraint(SpringLayout.NORTH, cmdSortByName, 25, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, cmdSortByName, 50, SpringLayout.WEST, this.getContentPane());

		//Add the object to the frame, and set the coordinates of the object
		this.add(cmdSortByCharacters);
		cmdSortByCharacters.setActionCommand("sortchars");
		layout.putConstraint(SpringLayout.NORTH, cmdSortByCharacters, 25, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, cmdSortByCharacters, 50, SpringLayout.WEST, this.getContentPane());

		//Add the object to the frame, and set the coordinates of the object
		this.add(cmdSortByWords);
		cmdSortByWords.setActionCommand("sortwords");
		layout.putConstraint(SpringLayout.NORTH, cmdSortByWords, 25, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, cmdSortByWords, 50, SpringLayout.WEST, this.getContentPane());

		//Add the object to the frame, and set the coordinates of the object
		this.add(cmdSortBySentences);
		cmdSortBySentences.setActionCommand("sortsentences");
		layout.putConstraint(SpringLayout.NORTH, cmdSortBySentences, 25, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, cmdSortBySentences, 50, SpringLayout.WEST, this.getContentPane());

		//Set the properties of the frame/pane
		this.setSize(510,295);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setTitle("Document Browser");
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}//End of setGUI method

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
}//End of class
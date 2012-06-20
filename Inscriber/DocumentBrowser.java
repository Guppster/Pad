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

	//Default Constructor
    public DocumentBrowser()
    {
		tDocuments = new JTable();
		cmdSortByName = new JButton("Sort by Name");
		cmdSortByCharacters = new JButton("Sort by Character");
		cmdSortBySentences = new JButton("Sort by Sentences");
		cmdSortBySentences = new JButton("Sort by Sentences");
    }//End of default constructor


}//End of class
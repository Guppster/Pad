//Import java API packages
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.*;
import java.text.*;
import java.awt.*;

public class WordFilter extends JFrame implements ActionListener
{
	//Declare class objects and fields
	private String wordToFilter;
	private String removeWord;  
	private String[] presetFilter;
	private JButton cmdAddWord;
	private JTable tFilteredWords;
	private JButton cmdRemoveWord;
	private JButton cmdPreset;
	private JTextField txtFilter;
	private JScrollPane spFilteredTable;
	private int presetWordFilterNum;
	private Dimension frameSize;
	
	//Setup the layout manager
    SpringLayout layout;//Create a SpringLayout object
    
    /* Object Constructor */
    public WordFilter() 
    {
    	//Initialize values for class objects
    	//presetFilter = new String[presetWordFilterNum];    were getting thur ;)
		cmdAddWord = new JButton("Add word");
		tFilteredWords = new JTable(10,4);
		cmdRemoveWord = new JButton("Remove Selected Word");
		txtFilter = new JTextField("Enter a word to filter", 20);
		cmdPreset = new JButton("Preset Word Filter");
		spFilteredTable = new JScrollPane(tFilteredWords);
	 	frameSize = new Dimension(500, 300);
	 	layout = new SpringLayout();
    	
    	//Constructs the GUI components and displays them on the screen
    	setGUI();
    }//End of Default constructor method
    
    //Constructs the GUI components
	private void setGUI()
	{	
	 	//Add objects to the GUI frame
		this.add(cmdAddWord);
		this.add(spFilteredTable);
		this.add(cmdPreset);
		this.add(cmdRemoveWord);
	 	this.add(txtFilter);
	 	
	 	tFilteredWords.setTableHeader(null);
	 	tFilteredWords.setCellSelectionEnabled(true);
	 	spFilteredTable.setPreferredSize(new Dimension(310,169));
	 	
	 	//Add actionlistener to active objects
		cmdAddWord.addActionListener(this);
		cmdPreset.addActionListener(this);
		cmdRemoveWord.addActionListener(this);
		txtFilter.addActionListener(this);
		
		//Change the frame color
		this.getContentPane().setBackground(Color.BLACK);
		
		//Set the layout for the frame
		this.getContentPane().setLayout(layout);
		
		//Set the west and north positions of the objects on the frame
		//cmdAddWord Button Cordinates
		layout.putConstraint(SpringLayout.WEST, cmdAddWord, 280, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, cmdAddWord, 10, SpringLayout.NORTH, this.getContentPane());
		
		//Table of Filtered Words Cordinates
		layout.putConstraint(SpringLayout.WEST, spFilteredTable, 10, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, spFilteredTable, 80, SpringLayout.NORTH, this.getContentPane());
		
		//cmdPreset Button Cordinates
		layout.putConstraint(SpringLayout.WEST, cmdPreset, 330, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, cmdPreset, 150, SpringLayout.NORTH, this.getContentPane());
		
		//cmdRemoveWord Cordinates
		layout.putConstraint(SpringLayout.WEST, cmdRemoveWord, 280, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, cmdRemoveWord, 40, SpringLayout.NORTH, this.getContentPane());

		//txtFilter Cordinates
		layout.putConstraint(SpringLayout.WEST, txtFilter, 20, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, txtFilter, 10, SpringLayout.NORTH, this.getContentPane());
		
		//Call the setting methods for the frame
		this.setSize(frameSize);
		this.setLocationRelativeTo(null);
		this.setTitle("Word Filter");
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}//End of setGUI method
	
	//Updates the GUI frame
	private void updateGUI()
	{
		
	}//End of updateGUI method
    
    //Tests a word against the current word filter and check if it is filtered or not
	public boolean isFiltered(String word)
	{
		return true;
	}//End of isFiltered method
	
	//Filters a word out of the document
	public void filter(String word)
	{
		
	}//End of filter method
	
	//Adds a word to the word filter JTable
	public void addWord()
	{
		
	}//End of addWord method
	
	//Removes a word from the word filter JTable
	public void removeWord()
	{
		
	}//End of removeWord method
	
	//Loads the preset filter into the JTable
	public void loadPreset()
	{
		
	}//End of loadPreset method
	
	//Perform an action whenever a button is pressed
	public void actionPerformed(ActionEvent evt)
	{
		
	}//End of actionPerformed method
	
	//Test Harness
	public static void main(String [] args)
	{
		new WordFilter();
	}//End of Test Harness main method
}//End of class
/**
 * @(#)WordFilter.java
 *
 * @Description 
 *
 * @author Gurpreet Singh, Matt Ufimsef
 * @version 1.00 2012/6/15
 *
 * @Latest Updates: 
 *
 * @Status: CLASS IS FINISHED EXCEPT FOR:
 *			-----------------------------
 *			1. Create the server class - Done
 *			2. Send the data entered into the JTable to the server
 *			3. Load the preset data from the server
 */

//Import API packages
import javax.swing.*;
import java.awt.event.*;
import java.text.*;
import java.awt.*;
import javax.swing.table.*;

public class WordFilter extends JFrame implements ActionListener, FocusListener, WindowListener
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
	private int presetNum;
	private Dimension frameSize;
	//private Server server;
    private SpringLayout layout;
    private Object [] data;
    private DefaultTableModel model;
    private TableHelper tHelper;

    public WordFilter()
    {
    	//Initialize the Server object
    	//server = new Server();

    	//Set a variable to the number of words in the preset word filter database
    	//presetNum = server.getPresetNum();
    	presetNum = 5;//TEMPORARY

    	//Initialize the other class objects and fields
    	presetFilter = new String[presetNum];
		cmdAddWord = new JButton("Add word");
		cmdRemoveWord = new JButton("Remove Selected Word");
		txtFilter = new JTextField("Enter a word to filter", 20);
		cmdPreset = new JButton("Preset Word Filter");
	 	frameSize = new Dimension(500, 300);
	 	layout = new SpringLayout();
	 	model = new DefaultTableModel();
	 	tFilteredWords = new JTable(model);
	 	spFilteredTable = new JScrollPane(tFilteredWords);
	 	tHelper = new TableHelper();

    	//Constructs the GUI components and displays them on the screen
    	setGUI();
    }//End of WordFilter constructor method

    //Constructs the GUI components
	private void setGUI()
	{
		//Add 4 columns to the JTable
		for(int x = 0; x < 5; x++)
		{
			model.addColumn(new Object[]{});
		}

		//Add 10 rows to the JTable
		for(int y = 0; y < 11; y++)
		{
			model.addRow(new Object[]{});
		}

		//Set some properties for the JTable object
		tFilteredWords.setTableHeader(null);
	 	tFilteredWords.setCellSelectionEnabled(true);
	 	tFilteredWords.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	 	//Set the size of the JScrollPane object
	 	spFilteredTable.setPreferredSize(new Dimension(310, 163));

	 	//Set the layout manager for the frame
		this.getContentPane().setLayout(layout);

	 	//Add an ActionListener to the object, add the object to the frame, and set the coordinates of the object
		cmdAddWord.addActionListener(this);
		cmdAddWord.setActionCommand("add");
		this.add(cmdAddWord);
		layout.putConstraint(SpringLayout.WEST, cmdAddWord, 280, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, cmdAddWord, 10, SpringLayout.NORTH, this.getContentPane());

		//Add the object to the frame, and set the coordinates of the object
		this.add(spFilteredTable);
		layout.putConstraint(SpringLayout.WEST, spFilteredTable, 10, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, spFilteredTable, 80, SpringLayout.NORTH, this.getContentPane());

		//Add an ActionListener to the object, add the object to the frame, and set the coordinates of the object
		cmdPreset.addActionListener(this);
		cmdPreset.setActionCommand("preset");
		this.add(cmdPreset);
		layout.putConstraint(SpringLayout.WEST, cmdPreset, 330, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, cmdPreset, 150, SpringLayout.NORTH, this.getContentPane());

		//Add an ActionListener to the object, add the object to the frame, and set the coordinates of the object
		cmdRemoveWord.addActionListener(this);
		cmdRemoveWord.setActionCommand("remove");
		this.add(cmdRemoveWord);
		layout.putConstraint(SpringLayout.WEST, cmdRemoveWord, 280, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, cmdRemoveWord, 40, SpringLayout.NORTH, this.getContentPane());

		//Add an ActionListener to the object, add the object to the frame, and set the coordinates of the object
	 	txtFilter.addFocusListener(this);
	 	this.add(txtFilter);
	 	layout.putConstraint(SpringLayout.WEST, txtFilter, 20, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, txtFilter, 10, SpringLayout.NORTH, this.getContentPane());

	 	//Add a WindowListener to the frame
	 	this.addWindowListener(this);

		//Set the properties for the GUI frame
		this.setSize(frameSize);
		this.setLocationRelativeTo(null);
		this.setTitle("Inscriber Word Filter");
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}//End of setGUI method

	//Loads the preset filter into the JTable
	public void loadPreset()
	{

	}//End of loadPreset method

	private void sendToDatabase()
	{
		//tHelper.loadArray();
		//server.addData("WordFilter.db", data);
	}//End of sendToDatabase method

	//Perform an action whenever a button is pressed
	public void actionPerformed(ActionEvent evt)
	{
		if("add".equals(evt.getActionCommand()))
		{
			tHelper.addElement(tFilteredWords, model, txtFilter.getText());
		}
		else if("preset".equals(evt.getActionCommand()))
		{
			//Load the preset data from the server
		}
		else if("remove".equals(evt.getActionCommand()))
		{
			tHelper.removeSelectedElement(tFilteredWords);
		}
	}//End of actionPerformed method

	public void focusLost(FocusEvent evt)
	{
		if(txtFilter.getText().equals(""))
			txtFilter.setText("Enter a word to filter");
	}//End of focusLost method

	public void focusGained(FocusEvent evt)
	{
		if(txtFilter.getText().equals("Enter a word to filter"))
			txtFilter.setText("");
	}//End of focusGained method

	public void windowOpened(WindowEvent e)
	{
		this.requestFocus();
	}//End of windowOpened method

	//Needed methods but not used
	public void windowActivated(WindowEvent e){}
	public void windowClosed(WindowEvent e){}
	public void windowClosing(WindowEvent e){}
	public void windowDeactivated(WindowEvent e){}
	public void windowDeiconified(WindowEvent e){}
	public void windowIconified(WindowEvent e){}

	//Test Harness
	public static void main(String [] args)
	{
		new WordFilter();
	}//End of Test Harness method
}//End of WordFilter class
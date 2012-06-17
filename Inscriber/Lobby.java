/*	By: Matthew Ufimzeff
        Gurpreet Singh	*/

import javax.swing.*;
import java.awt.*;

public class Lobby extends JFrame
{
	//Declare class fields and objects
	private JLabel lblDocument;
	private JLabel lblDocumentPass;
	private JLabel lblRecentlyCreatedDocuments;
	private JTextField txtDocumentName;
	private JPasswordField pfDocumentPass;
	private JButton cmdCreate;
	private JButton cmdBrowse;
	private JButton cmdCheckForUpdates;
	private JTable tRecentlyCreated;
	private SpringLayout layout;

	//Constructor
    public Lobby()
    {
		//Initialize class fields and objects
		lblDocument = new JLabel("Document ID:");
		lblDocumentPass = new JLabel("Document Pass(optional)");
		lblRecentlyCreatedDocuments = new JLabel("Recently Created Public Documents");
		txtDocumentName = new JTextField(15);
		pfDocumentPass = new JPasswordField(15);
		cmdBrowse = new JButton("Browse Public Documents");
		cmdCheckForUpdates = new JButton("Check for Updates");
		cmdCreate = new JButton("Create New Document");
		tRecentlyCreated = new JTable(7, 3); //col's author name, document name, date of creation.
		layout = new SpringLayout();

		//Call method to create GUI
		setGUI();
    }//End of Lobby constructor method

	//Adds and positions the GUI objects on the screen
    private void setGUI()
    {
    	//Add the layout manager to the GUI frame
		this.getContentPane().setLayout(layout);

		//Add the object to the frame, and set the coordinates of the object
		this.add(lblDocument);
		layout.putConstraint(SpringLayout.NORTH, lblDocument, 25, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, lblDocument, 50, SpringLayout.WEST, this.getContentPane());

    	//Add the object to the frame, and set the coordinates of the object
		this.add(lblDocumentPass);
		layout.putConstraint(SpringLayout.NORTH, lblDocumentPass, 60, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, lblDocumentPass, 50, SpringLayout.WEST, this.getContentPane());

    	//Add the object to the frame, and set the coordinates of the object
		this.add(lblRecentlyCreatedDocuments);
		layout.putConstraint(SpringLayout.NORTH, lblRecentlyCreatedDocuments, 115, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, lblRecentlyCreatedDocuments, 10, SpringLayout.WEST, this.getContentPane());

    	//Add the object to the frame, and set the coordinates of the object
		this.add(txtDocumentName);
		layout.putConstraint(SpringLayout.NORTH, txtDocumentName, 25, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, txtDocumentName, 300, SpringLayout.WEST, this.getContentPane());

    	//Add the object to the frame, and set the coordinates of the object
		this.add(pfDocumentPass);
		layout.putConstraint(SpringLayout.NORTH, pfDocumentPass, 60, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, pfDocumentPass, 300, SpringLayout.WEST, this.getContentPane());

    	//Add the object to the frame, and set the coordinates of the object
		this.add(cmdBrowse);
		layout.putConstraint(SpringLayout.NORTH, cmdBrowse, 140, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, cmdBrowse, 320, SpringLayout.WEST, this.getContentPane());

    	//Add the object to the frame, and set the coordinates of the object
		this.add(cmdCheckForUpdates);
		layout.putConstraint(SpringLayout.NORTH, cmdCheckForUpdates, 180, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, cmdCheckForUpdates, 320, SpringLayout.WEST, this.getContentPane());

    	//Add the object to the frame, and set the coordinates of the object
		this.add(cmdCreate);
		layout.putConstraint(SpringLayout.NORTH, cmdCreate, 220, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, cmdCreate, 320, SpringLayout.WEST, this.getContentPane());

		//Add the object to the frame, and set the coordinates of the object
		this.add(tRecentlyCreated);
		layout.putConstraint(SpringLayout.NORTH, tRecentlyCreated, 135, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, tRecentlyCreated, 10, SpringLayout.WEST, this.getContentPane());

		//Set the properties of the frame/pane
    	this.setSize(510,295);
    	this.setResizable(false);
    	this.setLocationRelativeTo(null);
    	this.setTitle("Inscriber Lobby");
    	this.setVisible(true);
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }//End of setGUI method;

    //Test harness
	public static void main(String [] args)
	{
		//Create a LookAndFeel object so we can set the look and fell of the GUI
	    new LookAndFeel();

	    SwingUtilities.invokeLater(new Runnable()
	    {
	    	public void run()
	        {
	        	new Lobby();
	      	}
	    });
	}//End of test harness
}//End of Main class
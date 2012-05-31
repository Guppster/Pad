//Import API packages
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.text.*;
import javax.swing.text.*;  
import javax.swing.JMenu;
import javax.swing.TransferHandler;
import java.awt.datatransfer.Clipboard;
import javax.swing.Timer;  
import java.awt.font.*;

public class WritingMainBoard extends JFrame
{	
	//Declare class objects and fields
	private SpringLayout layout;
	private MenuCreator menu;
	private DocumentOptions options;
	private Text text;
	private JTextArea taDocument;
	private JButton cmdLogout;
	private JButton cmdLockDoc;
	private JButton cmdLockChat;
	private JButton cmdKick;
	private JButton cmdBan;
	private JButton cmdLockPage;
	private JTable tConnected;
	private JLabel lblConnected;
	private JLabel lblChat;
	private JLabel lblPing;
	private JLabel lblDocName;
	private JTextField txtChat;
	private JTextField txtPgToLock;
	private JToolBar tbAdmin;
	private JScrollPane spDocument;
	private String [][] documentGrid;
	private int timeoutTimer;
	private int banTimer;
	private String docName;
	private String textToFind;
	private String textToCopy;
	private String textToCut;
	private String textToPaste;
	private String docToOpen;
	private String docToSave;

	public WritingMainBoard()
    {  	
    	//Initialize the documentGrid array
    	initializeGrid();
    	
    	//Initialize the class objects and fields
    	menu = new MenuCreator(this);
    	options = new DocumentOptions();
    	text = new Text();
    	taDocument = new JTextArea();
    	cmdLogout = new JButton("Logout");
    	cmdLockDoc = new JButton("Lock Document");
    	cmdLockChat = new JButton("Lock Chat");
    	cmdKick = new JButton("Kick Selected");
    	cmdBan = new JButton("Ban Selected");
    	cmdLockPage = new JButton("Lock Page");
    	tConnected = new JTable(10, 2);
    	lblConnected = new JLabel("Connected Users");
    	lblChat = new JLabel("Chat");
    	lblPing = new JLabel();
    	lblDocName = new JLabel();
    	txtChat = new JTextField(50);
    	txtPgToLock = new JTextField(2);
    	spDocument = new JScrollPane(taDocument);
    	tbAdmin = new JToolBar("Admin Options");	
		layout = new SpringLayout();

		//Call the setMenus method to construct all the menus
    	setMenus();
		
		//Call the setGUI method to construct all the GUI components
    	setGUI();	
    }//End of WritingMainBoard constructer method
    
    //Construct all the GUI components
    private void setGUI()
    {			
    	//JToolBar related stuff
    	tbAdmin.add(cmdLockDoc);
    	tbAdmin.add(cmdLockChat);
    	tbAdmin.add(cmdKick);
    	tbAdmin.add(cmdBan);
    	tbAdmin.add(cmdLockPage);
    	tbAdmin.add(txtPgToLock);
    	tbAdmin.addSeparator(new Dimension(245, 0));//Add a seperator to the JToolBar
    	tbAdmin.setFloatable(false);
    	
		//Add the layout manager to the this so we can arrange where the objects go on the this
		this.getContentPane().setLayout(layout);
		
		//Add the GUI components to the frame
		this.add(spDocument);
		layout.putConstraint(SpringLayout.WEST, spDocument, 500, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, spDocument, 500, SpringLayout.NORTH, this.getContentPane());
		
		this.add(cmdLogout);
		layout.putConstraint(SpringLayout.WEST, cmdLogout, 500, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, cmdLogout, 500, SpringLayout.NORTH, this.getContentPane());

		this.add(tConnected);
		layout.putConstraint(SpringLayout.WEST, tConnected, 500, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, tConnected, 500, SpringLayout.NORTH, this.getContentPane());
		
		this.add(lblConnected);
		layout.putConstraint(SpringLayout.WEST, lblConnected, 500, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, lblConnected, 500, SpringLayout.NORTH, this.getContentPane());
		
		this.add(lblChat);
		layout.putConstraint(SpringLayout.WEST, lblChat, 500, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, lblChat, 500, SpringLayout.NORTH, this.getContentPane());
		
		this.add(lblPing);
		layout.putConstraint(SpringLayout.WEST, lblPing, 500, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, lblPing, 500, SpringLayout.NORTH, this.getContentPane());
		
		this.add(lblDocName);
		layout.putConstraint(SpringLayout.WEST, lblDocName, 500, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, lblDocName, 500, SpringLayout.NORTH, this.getContentPane());
		
		this.add(txtChat);
		layout.putConstraint(SpringLayout.WEST, txtChat, 500, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, txtChat, 500, SpringLayout.NORTH, this.getContentPane());

		this.add(tbAdmin);
		layout.putConstraint(SpringLayout.WEST, tbAdmin, 0, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, tbAdmin, 0, SpringLayout.NORTH, this.getContentPane());

		//Don't display the button animation when someone moves their mouse over the buttons in the JToolBar
		tbAdmin.setRollover(false);
	
		//Sets the properties of the JFrame
    	this.setSize(700, 700);
    	tbAdmin.setPreferredSize(new Dimension(this.getWidth(), 32));
    	this.setLocationRelativeTo(null);
    	this.setVisible(true);
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    	this.setTitle("Clientype Writingboard");
    }//End of setGUI method
    
	//Update the GUI frame
	private void updateGUI()
	{
		
	}//End of updateGUI method
	
	//Initialize the documentGrid array
	private void initializeGrid()
	{
		
	}//End of initializeGrid method
	
	//Save the current document to the server
	private void saveDocument()
	{
		
	}//End of saveDocument method
	
	//Open a previous document
	private void openDocument()
	{
		
	}//End of openDocument method
	
	//Copy some text
	private void copy()
	{
		
	}//End of copy method
	
	//Paste some text
	private void paste()
	{
		
	}//End of paste method
	
	//Cut some text
	private void cut()
	{
		
	}//End of cut method
	
	//Finds the entered text in the document
	private String findText()
	{
		return "";
	}//End of findText method
	
	//Replaces the found text with something else
	private void replaceText()
	{
		
	}//End of replaceText method
	
	//Loads the 'About' page, has credits, etc
	public void loadAbout()
	{
		
	}//End of loadAbout method
	
	//Bans a selected user from the document room
	private void ban(int period)
	{
		
	}//End of ban method
	
	//Kicks a selected user from the document room
	private void kick()
	{
		
	}//End of kick method
	
	//Times a selected user out, disallowing them to do anything but logout
	private void timeout(int period)
	{
		
	}//End of timeout method
	
	//Locks the entire document from being edited for a timed period
	private boolean lock(int period)//Returns whether or not the document has been unlocked
	{
		//if(admin clicks on ban user button)
		//{
			//target user.banFor(period);
			//return true;
		//}
		//else
			return false;
	}//End of lock method
	
	//Opens the EditUserGroups page
	private void editGroups()
	{
		//EditUserGroups eug = new EditUserGroups();
	}//End of editGroups method
	
	//Opens the Lobby page
	private void logout()
	{
		//Lobby lobby = new Lobby();
	}//End of logout method
	
	//Construct all the menus
    private void setMenus()
    {
    	//Create the 'backbone' of the menus
    	menu.createMenuBar();
    	
    	//Create the 'File' menus
    	menu.createMainMenu("File");
    	menu.createMenuItem("New");
    	menu.createMenuItem("Open");
    	menu.createMenuItem("Save");
    	menu.createMenuItem("Save as");
    	menu.createMenuItem("Print");
    	menu.createMenuItem("Exit");
		
		//Create the 'Edit' menus
		menu.createMainMenu("Edit");
		menu.createMenuItem("Undo");
		menu.createMenuItem("Redo");
		menu.createMenuItem("Cut");
		menu.createMenuItem("Copy");
		menu.createMenuItem("Paste");
		menu.createMenuItem("Select All");
		menu.createMenuItem("Find/Replace");
		
		//Create the 'View' menus
		menu.createMainMenu("View");
		menu.createMenuItem("Show Line Numbers");
		menu.createSubMenu("Zoom");
		menu.createSubMenuItem("25%");
		menu.createSubMenuItem("50%");
		menu.createSubMenuItem("75%");
		menu.createSubMenuItem("100%");
		menu.createSubMenu("Toolbars");
		menu.createSubMenuItem("View/Hide Admin Toolbar");
		
		//Create the 'Admin Panel' menus
		menu.createMainMenu("Admin Panel");
		menu.createMenuItem("User Options");
		menu.createMenuItem("Kick");
		menu.createMenuItem("Ban");
		menu.createSubMenu("Timeout User");
		menu.createSubMenuItem("1 Minute");
		menu.createSubMenuItem("5 Minutes");
		menu.createSubMenuItem("10 Minutes");
		menu.createSubMenuItem("30 Minutes");
		menu.createMenuItem("Lock Document Screen");
		
		//Create the 'Options' menus
		menu.createMainMenu("Options");
		menu.createMenuItem("Bold");
		menu.createMenuItem("Italics");
		menu.createMenuItem("Underline");
		menu.createMenuItem("Edit Text Colour");
		menu.createMenuItem("Edit Font Type");
		menu.createMenuItem("Edit Font Size");
		
		//Create the 'About' menus
		menu.createMainMenu("About");
		menu.createMenuItem("Help");
		menu.createMenuItem("Credits");	
    }//End of setMenus method
    
    //TEST HARNESS		  	
    public static void main (String [] args)
    {
    	 WritingMainBoard board = new WritingMainBoard();
    }//End of main method 	 
}//End of class
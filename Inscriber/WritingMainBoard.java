/**
 * @(#)WritingMainBoard.java
 *
 * @Description
 *
 * @author Gurpreet Singh, Matt Ufimsef
 * @version 1.00 2012/6/15
 *
 * @Latest Updates:
 *
 * @Status: GUI Complete. Functionality Pending.
 */

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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.net.*;

public class WritingMainBoard extends JFrame implements PropertyChangeListener
{
	//Declare class objects and fields
	private SpringLayout leftSideLayout;
	private SpringLayout rightSideLayout;
	private SpringLayout mainLayout;
	private SpringLayout frameLayout;
	private MenuCreator menu;
	private DocumentOptions options;
	private JTextArea taDocument;
	private JTextArea taChat;
	private JButton cmdChat;
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
	private JScrollPane spChat;
	private JScrollPane spConnected;
	private JSplitPane splMain;
	private JComponent pLeftSide;
	private JComponent pRightSide;
	private JPanel pMain;
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
	private Dimension screenSize;
	private int leftRightSizeX;
	private int leftRightSizeY;
	private boolean connChatClicked;
	private boolean adminClicked;
	private boolean lineNumberToggle;
	private TextLineNumber tln;
	private Document document;
	private User user;
	private Socket sClient;

    public WritingMainBoard(User user, Socket socket)
    {
    	//Initialize the class objects and fields
    	menu = new MenuCreator(this);
    	options = new DocumentOptions();
    	taDocument = new JTextArea();
    	taChat = new JTextArea();
    	cmdLogout = new JButton("Logout");
    	cmdLockDoc = new JButton("Lock Document");
    	cmdLockChat = new JButton("Lock Chat");
    	cmdKick = new JButton("Kick Selected");
    	cmdBan = new JButton("Ban Selected");
    	cmdLockPage = new JButton("Lock Page");
    	tConnected = new JTable(15, 2);
    	lblConnected = new JLabel("Connected Users");
    	lblChat = new JLabel("Chat");
    	lblPing = new JLabel();
    	lblDocName = new JLabel();
    	txtChat = new JTextField(21);
    	cmdChat = new JButton("Send");
    	txtPgToLock = new JTextField(2);
    	spDocument = new JScrollPane(taDocument);
    	spChat = new JScrollPane(taChat);
    	spConnected = new JScrollPane(tConnected);
    	tbAdmin = new JToolBar("Admin Options");
    	pLeftSide = new JPanel();
    	pRightSide = new JPanel();
    	pMain = new JPanel();
    	leftSideLayout = new SpringLayout();
		rightSideLayout = new SpringLayout();
		mainLayout = new SpringLayout();
		frameLayout = new SpringLayout();
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println(screenSize);
		leftRightSizeX = (int)screenSize.getWidth() / 2;
		leftRightSizeY = (int)screenSize.getHeight();
		splMain = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		connChatClicked = true;
		adminClicked = true;
		tln = new TextLineNumber(taDocument);
		document = new Document();
		this.user = user;
		sClient = socket;

		//Call the setMenus method to construct all the menus
    	setMenus();

		//Call the setGUI method to construct all the GUI components
    	setGUI();
    }//End of WritingMainBoard constructor method

    //Construct all the GUI components
    private void setGUI()
    {
    	//Add a property change listener object to our splipane object
    	splMain.addPropertyChangeListener(this);

		//Set the preferred size(object size) of some objects
    	spDocument.setPreferredSize(new Dimension(650, 550));
    	spChat.setPreferredSize(new Dimension(300, 350));
    	spConnected.setPreferredSize(new Dimension(300, 199));
    	tbAdmin.setPreferredSize(new Dimension((int)screenSize.getWidth(), 32));
    	pLeftSide.setPreferredSize(new Dimension(leftRightSizeX, leftRightSizeY));
		pRightSide.setPreferredSize(new Dimension(leftRightSizeX, leftRightSizeY));
		pMain.setPreferredSize(screenSize);
		pLeftSide.setMinimumSize(new Dimension(680, 550));

		//Adjust some properties for the splitpane object
		splMain.setContinuousLayout(true);
	    splMain.setDividerLocation(680);
	    splMain.setPreferredSize(new Dimension((int)screenSize.getWidth() - 20, (int)screenSize.getHeight()));

    	//Add objects to the toolbar object, and set the properties of the toolbar object
    	tbAdmin.add(cmdLockDoc);
    	tbAdmin.add(cmdLockChat);
    	tbAdmin.add(cmdKick);
    	tbAdmin.add(cmdBan);
    	tbAdmin.add(cmdLockPage);
    	tbAdmin.add(txtPgToLock);
    	tbAdmin.addSeparator(new Dimension(550, 0));
    	tbAdmin.setFloatable(false);

		//JTextArea object properties
		taChat.setLineWrap(true);
		taChat.setEditable(false);
		taDocument.setLineWrap(true);

    	//Set the layout managers of the panel objects
		pLeftSide.setLayout(leftSideLayout);
		pRightSide.setLayout(rightSideLayout);
		pMain.setLayout(mainLayout);
		this.getContentPane().setLayout(frameLayout);

		pLeftSide.add(spDocument);//Add the document scrollpane object to the left side panel and position it
		leftSideLayout.putConstraint(SpringLayout.WEST, spDocument, 10, SpringLayout.WEST, pLeftSide);
		leftSideLayout.putConstraint(SpringLayout.NORTH, spDocument, 40, SpringLayout.NORTH, pLeftSide);

		pRightSide.add(lblConnected);//Add the connected users label object to the top right side panel and position it
		rightSideLayout.putConstraint(SpringLayout.WEST, lblConnected, 10, SpringLayout.WEST, pRightSide);
		rightSideLayout.putConstraint(SpringLayout.NORTH, lblConnected, 0, SpringLayout.NORTH, pRightSide);

		pRightSide.add(spConnected);//Add the connected users scrollpane object to the top right side panel and position it
		rightSideLayout.putConstraint(SpringLayout.WEST, spConnected, 10, SpringLayout.WEST, pRightSide);
		rightSideLayout.putConstraint(SpringLayout.NORTH, spConnected, 20, SpringLayout.NORTH, pRightSide);

		pRightSide.add(lblChat);//Add the chat label object to the bottom right side panel and position it
		rightSideLayout.putConstraint(SpringLayout.WEST, lblChat, 10, SpringLayout.WEST, pRightSide);
		rightSideLayout.putConstraint(SpringLayout.NORTH, lblChat, 220, SpringLayout.NORTH, pRightSide);

		pRightSide.add(spChat);//Add the chat scrollpane object to the bottom right side panel and position it
		rightSideLayout.putConstraint(SpringLayout.WEST, spChat, 10, SpringLayout.WEST, pRightSide);
		rightSideLayout.putConstraint(SpringLayout.NORTH, spChat, 240, SpringLayout.NORTH, pRightSide);

		pRightSide.add(txtChat);//Add the chat textbox object to the bottom right side panel and position it
		rightSideLayout.putConstraint(SpringLayout.WEST, txtChat, 10, SpringLayout.WEST, pRightSide);
		rightSideLayout.putConstraint(SpringLayout.NORTH, txtChat, 610, SpringLayout.NORTH, pRightSide);

		pRightSide.add(cmdChat);//Add the chat textbox object to the bottom right side panel and position it
		rightSideLayout.putConstraint(SpringLayout.WEST, cmdChat, 247, SpringLayout.WEST, pRightSide);
		rightSideLayout.putConstraint(SpringLayout.NORTH, cmdChat, 606, SpringLayout.NORTH, pRightSide);

		//Add the left and right side panels to the splitpane object
		splMain.setLeftComponent(pLeftSide);
		splMain.setRightComponent(pRightSide);

		pMain.add(tbAdmin);//Add the Admin Toolbar object to the main panel
		mainLayout.putConstraint(SpringLayout.WEST, splMain, 0, SpringLayout.WEST, pMain);
		mainLayout.putConstraint(SpringLayout.NORTH, splMain, 0, SpringLayout.NORTH, pMain);

		pMain.add(splMain);//Add the main splitpane object to the main panel and position it
		mainLayout.putConstraint(SpringLayout.WEST, splMain, 10, SpringLayout.WEST, pMain);
		mainLayout.putConstraint(SpringLayout.NORTH, splMain, 40, SpringLayout.NORTH, pMain);

		this.add(pMain);//Add the main panel to the frame object
		frameLayout.putConstraint(SpringLayout.WEST, splMain, 0, SpringLayout.WEST, this.getContentPane());
		frameLayout.putConstraint(SpringLayout.NORTH, splMain, 0, SpringLayout.NORTH, this.getContentPane());

		//Sets the properties of the JFrame
        this.setSize(screenSize);
    	this.setLocationRelativeTo(null);
    	this.setVisible(true);
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    	this.setTitle("Inscriber Board");
    }//End of setGUI method

	//Save the current document to the server
	private void saveDocument()
	{
		String fileName = JOptionPane.showInputDialog(null, "", "Enter in a filename.", 1);
		document.saveFileToServer(user.getUsername(), fileName, taDocument.getText(), sClient);
		fileName = "";
	}//End of saveDocument method

	//Open a previous document
	private void openDocument()
	{
		String fileName = JOptionPane.showInputDialog(null, "", "Enter in a filename.", 1);
		String text = document.getFileFromServer(fileName, sClient);
		taDocument.setText(text);
		fileName = "";
		text = "";
	}//End of openDocument method

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

	//Opens the Lobby page
	private void logout()
	{
		Lobby lobby = new Lobby(this.user);
		this.dispose();
	}//End of logout method

	private void toggleConnChatToolbar()
	{
		if(connChatClicked)//Remove the Connected Users and Chat pane and increase the size of the document
		{
			splMain.setDividerLocation((int)screenSize.getWidth() - 20);
			spDocument.setPreferredSize(new Dimension(970 ,550));
			connChatClicked = false;
		}
		else//ReAdd the Connected Users and Chat pane, reset the splitpane divider and reduce the size of the document
		{
			splMain.setDividerLocation(680);
			spDocument.setPreferredSize(new Dimension(650 ,550));
			connChatClicked = true;
		}
	}//End of toggleConnChatToolbar method

	private void toggleAdminToolbar()
	{
		if(adminClicked)
		{
			tbAdmin.setVisible(false);
			adminClicked = false;
		}
		else
		{
			tbAdmin.setVisible(true);
			adminClicked = true;
		}
	}//End of toggleAdminToolbar method

	//This shows, and hides the line numbers
	private void toggleLineNumbers()
	{
		if(!lineNumberToggle)
		{
			spDocument.setRowHeaderView(tln);
			lineNumberToggle = true;
		}
		else
		{
			spDocument.setRowHeader(new JViewport());
			lineNumberToggle = false;
		}
	}//End of toggleLineNumbers

	//Listen for changes of the splitpane, and do some actions accordingly
	public void propertyChange(PropertyChangeEvent changeEvent)
	{
        String propertyName = changeEvent.getPropertyName();

        if(propertyName.equals(JSplitPane.LAST_DIVIDER_LOCATION_PROPERTY))
        {
          spDocument.setPreferredSize(new Dimension(splMain.getDividerLocation() - 30, 550));
        }
	}//End of propertyChange method

	//Construct all the menus
    private void setMenus()
    {
    	//Create the 'backbone' of the menus
    	menu.createMenuBar();

    	//Create the 'File' menus
    	menu.createMainMenu("File");
    	menu.createMenuItem("New");
    	menu.getMenuItem().addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					taDocument.setText("");
				}
			});

    	menu.createMenuItem("Open");
    	menu.getMenuItem().addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("Open");
				}
			});

    	menu.createMenuItem("Save");
    	menu.getMenuItem().addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					saveDocument();
				}
			});

    	menu.createMenuItem("Exit");
    	menu.getMenuItem().addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.exit(0);
				}
			});

		//Create the 'Edit' menus
		menu.createMainMenu("Edit");
		menu.createMenuItem("Find/Replace");
		menu.getMenuItem().addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("Find/Replace");
				}
			});

		//Create the 'View' menus
		menu.createMainMenu("View");
		menu.createCheckMenuItem("Show Line Numbers");
		menu.getCheckMenuItem().addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					toggleLineNumbers();
				}
			});

		menu.createSubMenu("Toolbars");
		menu.createSubCheckMenuItem("Hide Admin Toolbar");
		menu.getSubCheckMenuItem().addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					toggleAdminToolbar();
				}
			});

		menu.createSubCheckMenuItem("Hide Connected Users & Chat Toolbar");
		menu.getSubCheckMenuItem().addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					toggleConnChatToolbar();
				}
			});

		//Create the 'About' menus
		menu.createMainMenu("About");
		menu.getMenuItem().addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("About");
				}
			});
		menu.createMenuItem("Help");
		menu.getMenuItem().addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("Help");
				}
			});

		menu.createMenuItem("Credits");
		menu.getMenuItem().addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("Credits");
				}
			});
    }//End of setMenus method
}//End of WritingMainBoard class
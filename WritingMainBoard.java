//By Matthew Ufimzeff and Gurpreet Singh
//June 2

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

public class WritingMainBoard extends JFrame implements PropertyChangeListener
{
	//Declare class objects and fields
	private SpringLayout leftSideLayout;
	private SpringLayout rightSideLayout;
	private SpringLayout mainLayout;
	private SpringLayout frameLayout;
	private MenuCreator menu;
	private DocumentOptions options;
	private Text text;
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
	private TextLineNumber tln;

    public WritingMainBoard()
    {
    	//Initialize the documentGrid array
    	initializeGrid();

    	//Initialize the class objects and fields
    	menu = new MenuCreator(this);
    	options = new DocumentOptions();
    	text = new Text();
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

	private void hideConnChatToolbar()
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
	}//End of hideConnChatToolbar method

	private void hideAdminToolbar()
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
	}//End of hideAdminToolbar method
	
	private void toggleLineNumbers()
	{
		spDocument.setRowHeaderView(tln);
	}

	//Listen for changes of the splitpane, and do some actions accordingly
	public void propertyChange(PropertyChangeEvent changeEvent)
	{
        JSplitPane sourceSplitPane = (JSplitPane) changeEvent.getSource();

        String propertyName = changeEvent.getPropertyName();

        if (propertyName.equals(JSplitPane.LAST_DIVIDER_LOCATION_PROPERTY))
        {
          int current = sourceSplitPane.getDividerLocation();
          System.out.println("Current: " + current);

          Integer last = (Integer) changeEvent.getNewValue();
          System.out.println("Last: " + last);

          Integer priorLast = (Integer) changeEvent.getOldValue();
          System.out.println("Prior last: " + priorLast);
        }
	}

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
					System.out.println("New");
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
					System.out.println("Save");
				}
			});

    	menu.createMenuItem("Save as");
    	menu.getMenuItem().addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("Save as");
				}
			});

    	menu.createMenuItem("Print");
    	menu.getMenuItem().addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("Print");
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
		menu.createMenuItem("Undo");
		menu.getMenuItem().addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("Undo");
				}
			});

		menu.createMenuItem("Redo");
		menu.getMenuItem().addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("Redo");
				}
			});

		menu.createMenuItem("Cut");
		menu.getMenuItem().addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("Cut");
				}
			});

		menu.createMenuItem("Copy");
		menu.getMenuItem().addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("Copy");
				}
			});

		menu.createMenuItem("Paste");
		menu.getMenuItem().addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("Paste");
				}
			});

		menu.createMenuItem("Select All");
		menu.getMenuItem().addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("Select All");
				}
			});

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
					System.out.println("Show Line Numbers");
					toggleLineNumbers();
				}
			});

		menu.createSubMenu("Zoom");
		menu.createSubMenuItem("25%");
		menu.getSubMenuItem().addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("25%");
				}
			});

		menu.createSubMenuItem("50%");
		menu.getSubMenuItem().addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("50%");
				}
			});

		menu.createSubMenuItem("75%");
		menu.getSubMenuItem().addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("75%");
				}
			});

		menu.createSubMenuItem("100%");
		menu.getSubMenuItem().addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("100%");
				}
			});

		menu.createSubMenu("Toolbars");
		menu.createSubCheckMenuItem("Hide Admin Toolbar");
		menu.getSubCheckMenuItem().addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					hideAdminToolbar();
				}
			});

		menu.createSubCheckMenuItem("Hide Connected Users & Chat Toolbar");
		menu.getSubCheckMenuItem().addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					hideConnChatToolbar();
				}
			});

		//Create the 'Admin Panel' menus
		menu.createMainMenu("Admin Panel");
		menu.createMenuItem("User Options");
		menu.getMenuItem().addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("User Options");
				}
			});

		menu.createMenuItem("Kick");
		menu.getMenuItem().addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					kick();
				}
			});

		menu.createMenuItem("Ban");
		menu.getMenuItem().addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					ban(0);//0 means permanent
				}
			});

		menu.createSubMenu("Timeout User");
		menu.createSubMenuItem("1 Minute");
		menu.getSubMenuItem().addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					timeout(1);//1 means 1 minute
				}
			});

		menu.createSubMenuItem("5 Minutes");
		menu.getSubMenuItem().addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					timeout(5);//1 means 5 minutes
				}
			});

		menu.createSubMenuItem("10 Minutes");
		menu.getSubMenuItem().addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					timeout(10);//1 means 10 minutes
				}
			});

		menu.createSubMenuItem("30 Minutes");
		menu.getSubMenuItem().addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					timeout(30);//1 means 30 minutes
				}
			});

		menu.createMenuItem("Lock Document Screen");
		menu.getMenuItem().addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("Lock Document Screen");
				}
			});


		//Create the 'Options' menus
		menu.createMainMenu("Options");

		//Create all the 'Options' menu, menu items
		menu.createMenuItem("Bold");
		menu.getMenuItem().addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					//text.setBold(spDocument);
				}
			});

		menu.createMenuItem("Italics");
		menu.getMenuItem().addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					//text.setItalics(spDocument);
				}
			});

		menu.createMenuItem("Underline");
		menu.getMenuItem().addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					//text.setUnderline(spDocument);
				}
			});

		menu.createMenuItem("Edit Text Colour");
		menu.getMenuItem().addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					//text.setTextColour(spDocument);
				}
			});


		menu.createMenuItem("Edit Font Type");
		menu.getMenuItem().addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("Edit Font Type");
				}
			});

		menu.createMenuItem("Edit Font Size");
		menu.getMenuItem().addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("Edit Font Size");
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

    //TEST HARNESS
    public static void main (String [] args)
    {
    	//Create a LookAndFeel object so we can set the look and fell of the GUI
	    new LookAndFeel(); 
	    
	    SwingUtilities.invokeLater(new Runnable()
	    {
	    	public void run()
	        {
	        	WritingMainBoard board = new WritingMainBoard();
	      	}
	    });
    }//End of main method
}//End of WritingMainBoard class
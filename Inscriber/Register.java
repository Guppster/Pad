//Matthew Ufimzeff and Gurpreet Singh
//June 11

//Import Java API packages
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Register extends JFrame implements ActionListener, KeyListener
{
	//Declare class fields and objects
	private JLabel lblFirst;
	private JLabel lblLast;
	private JLabel lblEmail;
	private JLabel lblEConfirm;
	private JLabel lblNewUser;
	private JLabel lblNewPass;
	private JLabel lblPConfirm;
	private JTextField txtFirst;
	private JTextField txtLast;
	private JTextField txtEmail;
	private JTextField txtEConfirm;
	private JTextField txtNewUser;
	private JPasswordField pfNewPass;
	private JPasswordField pfPConfirm;
	private JButton cmdRegister;
	private JButton cmdBack;
	private ErrorHandler handler;
	private SpringLayout layout;
	private String eConfirm;
	private char[] tempPConfirm;
	private String pConfirm;
	private User user;

	//Initialize class fields and objects
    public Register()
    {
    	//Initialize class fields and objects
		lblFirst = new JLabel("First Name:");
		lblLast = new JLabel("Last Name:");
		lblEmail = new JLabel("Email Address:");
		lblEConfirm = new JLabel("Retype Email:");
		lblNewUser = new JLabel("Desired Username:");
		lblNewPass = new JLabel("Desired Password:");
		lblPConfirm = new JLabel("Retype Password:");
		txtFirst = new JTextField(15);
		txtLast = new JTextField(15);
		txtEmail = new JTextField(15);
		txtEConfirm = new JTextField(15);
		txtNewUser = new JTextField(15);
		pfNewPass = new JPasswordField(15);
		pfPConfirm = new JPasswordField(15);
		cmdRegister = new JButton("                                    Register                                   ");
		cmdBack = new JButton("Back to login screen");
		layout = new SpringLayout();
		user = new User();
		handler = new ErrorHandler();

		//Call the setGUI method to construct all the GUI components
		setGUI();
    }//End of Register constructor method

	//Construct all the GUI components
	private void setGUI()
	{
    	//Add the layout manager to the GUI frame
		this.getContentPane().setLayout(layout);

		//Add the object to the frame, and set the coordinates of the object
		this.add(lblFirst);
		layout.putConstraint(SpringLayout.NORTH, lblFirst, 25, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, lblFirst, 25, SpringLayout.WEST, this.getContentPane());

		//Add the object to the frame, and set the coordinates of the object
		this.add(lblLast);
		layout.putConstraint(SpringLayout.NORTH, lblLast, 60, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, lblLast, 25, SpringLayout.WEST, this.getContentPane());

		//Add the object to the frame, and set the coordinates of the object
		this.add(lblEmail);
		layout.putConstraint(SpringLayout.NORTH, lblEmail, 95, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, lblEmail, 25, SpringLayout.WEST, this.getContentPane());

		//Add the object to the frame, and set the coordinates of the object
		this.add(lblEConfirm);
		layout.putConstraint(SpringLayout.NORTH, lblEConfirm, 130, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, lblEConfirm, 25, SpringLayout.WEST, this.getContentPane());

		//Add the object to the frame, and set the coordinates of the object
		this.add(lblNewUser);
		layout.putConstraint(SpringLayout.NORTH, lblNewUser, 165, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, lblNewUser, 25, SpringLayout.WEST, this.getContentPane());

		//Add the object to the frame, and set the coordinates of the object
		this.add(lblNewPass);
		layout.putConstraint(SpringLayout.NORTH, lblNewPass, 200, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, lblNewPass, 25, SpringLayout.WEST, this.getContentPane());

		//Add the object to the frame, and set the coordinates of the object
		this.add(lblPConfirm);
		layout.putConstraint(SpringLayout.NORTH, lblPConfirm, 235, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, lblPConfirm, 25, SpringLayout.WEST, this.getContentPane());

		//Add a KeyListener to the object, add the object to the frame, and set the coordinates of the object
		txtFirst.addKeyListener(this);
		this.add(txtFirst);
		layout.putConstraint(SpringLayout.NORTH, txtFirst, 25, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, txtFirst, 150, SpringLayout.WEST, this.getContentPane());

		//Add a KeyListener to the object, add the object to the frame, and set the coordinates of the object
		txtLast.addKeyListener(this);
		this.add(txtLast);
		layout.putConstraint(SpringLayout.NORTH, txtLast, 60, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, txtLast, 150, SpringLayout.WEST, this.getContentPane());

		//Add a KeyListener to the object, add the object to the frame, and set the coordinates of the object
		txtEmail.addKeyListener(this);
		this.add(txtEmail);
		layout.putConstraint(SpringLayout.NORTH, txtEmail, 95, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, txtEmail, 150, SpringLayout.WEST, this.getContentPane());

		//Add a KeyListener to the object, add the object to the frame, and set the coordinates of the object
		txtEConfirm.addKeyListener(this);
		this.add(txtEConfirm);
		layout.putConstraint(SpringLayout.NORTH, txtEConfirm, 130, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, txtEConfirm, 150, SpringLayout.WEST, this.getContentPane());

		//Add a KeyListener to the object, add the object to the frame, and set the coordinates of the object
		txtNewUser.addKeyListener(this);
		this.add(txtNewUser);
		layout.putConstraint(SpringLayout.NORTH, txtNewUser, 165, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, txtNewUser, 150, SpringLayout.WEST, this.getContentPane());

		//Add a KeyListener to the object, add the object to the frame, and set the coordinates of the object
		pfNewPass.addKeyListener(this);
		this.add(pfNewPass);
    	layout.putConstraint(SpringLayout.NORTH, pfNewPass, 200, SpringLayout.NORTH, this.getContentPane());
    	layout.putConstraint(SpringLayout.WEST, pfNewPass, 150, SpringLayout.WEST, this.getContentPane());

		//Add a KeyListener to the object, add the object to the frame, and set the coordinates of the object
		pfPConfirm.addKeyListener(this);
		this.add(pfPConfirm);
		layout.putConstraint(SpringLayout.NORTH, pfPConfirm, 235, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, pfPConfirm, 150, SpringLayout.WEST, this.getContentPane());

		//Add an ActionListener to the object, set a keyword for the object, add the object to the frame, and set the coordinates of the object
		cmdRegister.addActionListener(this);
		cmdRegister.setActionCommand("register");
		this.add(cmdRegister);
		layout.putConstraint(SpringLayout.NORTH, cmdRegister, 270, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, cmdRegister, 25, SpringLayout.WEST, this.getContentPane());

		//Add an ActionListener to the object, set a keyword for the object, add the object to the frame, and set the coordinates of the object
		cmdBack.addActionListener(this);
		cmdBack.setActionCommand("back");
		this.add(cmdBack);
		layout.putConstraint(SpringLayout.NORTH, cmdBack, 300, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, cmdBack, 25, SpringLayout.WEST, this.getContentPane());

		//Set the properties of the frame/pane
    	this.setSize(380,360);
    	this.setResizable(false);
    	this.setLocationRelativeTo(null);
    	this.setTitle("Inscriber Register: Register a new account, it's FREE!");
    	this.setVisible(true);
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}//End of setGUI method

	private boolean checkErrorsAndInitialize()
	{
		boolean errorThrown = false;

    	//Initialize class fields
    	if(txtFirst.getText() != null)
    		user.setFirstName(txtFirst.getText());
    	else
    	{
    		errorThrown = true;
    		handler.displayError("MF");
    	}

    	if(txtLast.getText() != null)
    		user.setLastName(txtLast.getText());
    	else
    	{
    		errorThrown = true;
    		handler.displayError("ML");
    	}

    	if(txtEmail.getText() != null)
    		user.setEmail(txtEmail.getText());
    	else
    	{
    		errorThrown = true;
    		handler.displayError("ME");
    	}

    	if(txtEmail.getText() != txtEConfirm.getText())
    	{
    		errorThrown = true;
    		handler.displayError("ENM");
    	}

    	if(txtNewUser.getText() != null)
    		user.setUsername(txtNewUser.getText());
    	else
    	{
    		errorThrown = true;
    		handler.displayError("MU");
    	}

    	if(pfNewPass.getText() != null)
    		user.setPassword(pfNewPass.getPassword());
    	else
    	{
    		errorThrown = true;
    		handler.displayError("MP");
    	}

    	if(pfNewPass.getPassword() != pfPConfirm.getPassword())
    	{
    		errorThrown = true;
    		handler.displayError("PNM");
    	}

    	if(errorThrown)
    	{
    		handler.displayError(".");
    		return true;
    	}
    	else
    		return false;
	}//end of checkErrors method

	private boolean confirmIndividuality() throws Exception
	{
		boolean [] conditionResults = server.findUserExists(user);

		//Query the server to check if there is a username matching the one entered, then check if the password matches
		//return true means the user field exists, false means it doesnt exist
	   	if(conditionResults[0] == false && conditionResults[1] == false)
	    {
	    	return true;
		}
		else if(conditionResults[0] == true && conditionResults[1] == false)
		{
			handler.displayError("UE");
			return false;
		}
		else if(conditionResults[0] == false && conditionResults[1] == true)
		{
			handler.displayError("EE");
			return false;
		}
		else if(conditionResults[0] == true && conditionResults[1] == true)
		{
			handler.displayError("UAEE");
			return false;
		}
		else
		{
			return false;
		}

	}//End of confirmIndivusuality method

	//Perform an action whenever a button is pressed
    public void actionPerformed (ActionEvent evt)
    {
    	//Check which button was pressed
		if("register".equals(evt.getActionCommand()))
		{
			if(!checkErrorsAndInitialize())
			{
				if(confirmIndividuality())
				{
					database = new Database(user);
					database.saveUser();
				}
			}
		}
		else if ("back".equals(evt.getActionCommand()))
		{
			//Create a Login object, thus displaying the Login frame
			new Login();

			//Get rid of the Register frame to conserve resources
			this.dispose();
		}
    }//End of actionPerformed method

    //Perform an action whenever a certain key is pressed
    public void keyPressed(KeyEvent e)
    {
    	//Declare and initialize fields
     	int key = e.getKeyCode();

		//Check if the 'Enter' key was pressed
     	if(key == KeyEvent.VK_ENTER)
     	{
			if(!checkErrorsAndInitialize())
			{
				if(confirmIndividuality())
				{
					database = new Database();
					database.addUser(user);
				}
			}
       	}
    }//End of keyPressed method

    //Not needed but required
    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e){}

	//Test harness
	public static void main(String [] args)
	{
		//Create a LookAndFeel object so we can set the look and fell of the GUI
	    new LookAndFeel();

	    SwingUtilities.invokeLater(new Runnable()
	    {
	    	public void run()
	        {
	        	new Register();
	      	}
	    });
	}//End of test harness
}//End of Register class
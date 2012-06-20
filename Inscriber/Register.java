/**
 * @(#)Register.java
 *
 * @author Gurpreet Singh, Matthew Ufimzeff
 * @version 1.00 2012/6/20
 *
 * @Description: This class is used to allow the user to create a new account for the program. It will also allow them the ability to login to use
 *					the program.
 *
 * @Status: Finished - Completely commented - No errors.
 */

//Import Java API packages
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Register extends JFrame implements ActionListener, KeyListener
{
	//Declare class objects and fields
	private JLabel lblFirst;//Creates a new JLabel object
	private JLabel lblLast;//Creates a new JLabel object
	private JLabel lblEmail;//Creates a new JLabel object
	private JLabel lblEConfirm;//Creates a new JLabel object
	private JLabel lblNewUser;//Creates a new JLabel object
	private JLabel lblNewPass;//Creates a new JLabel object
	private JLabel lblPConfirm;//Creates a new JLabel object
	private JTextField txtFirst;//Creates a new JTextField object
	private JTextField txtLast;//Creates a new JTextField object
	private JTextField txtEmail;//Creates a new JTextField object
	private JTextField txtEConfirm;//Creates a new JTextField object
	private JTextField txtNewUser;//Creates a new JTextField object
	private JPasswordField pfNewPass;//Creates a new JPasswordField object
	private JPasswordField pfPConfirm;//Creates a new JPasswordField object
	private JButton cmdRegister;//Creates a new JButton object
	private JButton cmdBack;//Creates a new JButton object
	private ErrorHandler handler;//Creates a new ErrorHandler object
	private SpringLayout layout;//Creates a new SpringLayout object
	private User user;//Creates a new User object
	private Database database;//Creates a new Database object
	private String eConfirm;//Used to hold whether or not the email entered matches the confirm email field
	private char[] tempPConfirm;//Used to hold the raw password from the JPasswordField
	private String pConfirm;//Used to hold the converted password
	private boolean errorThrown;

	//Initializes class objects and fields
    public Register()
    {
    	//Initialize class objects and fields
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
		database = new Database();
		errorThrown = false;

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
    	this.setTitle("Inscriber Register: Register a new account");
    	this.setVisible(true);
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}//End of setGUI method

	//A method to check the input from the user for errors - and displaying an error message to the user if an error exists
	private boolean checkErrorsAndInitialize()
	{
    	if(txtFirst.getText() != null)//Missing first name
    		user.setFirstName(txtFirst.getText());
    	else
    	{
    		errorThrown = true;
    		handler.displayError("MF");
    		handler.displayError(".");

    	}

    	if(txtLast.getText() != null)//Missing last name
    		user.setLastName(txtLast.getText());
    	else
    	{
    		errorThrown = true;
    		handler.displayError("ML");
    		handler.displayError(".");
    	}

    	if(txtEmail.getText() != null)//Missing email
    		user.setEmail(txtEmail.getText());
    	else
    	{
    		errorThrown = true;
    		handler.displayError("ME");
    		handler.displayError(".");
    	}

    	if(!(txtEmail.getText().equals(txtEConfirm.getText())))//Email does not match email confirm
    	{
    		errorThrown = true;
    		handler.displayError("ENM");
    		handler.displayError(".");
    	}

    	if(txtNewUser.getText() != null)//Missing username
    		user.setUsername(txtNewUser.getText());
    	else
    	{
    		errorThrown = true;
    		handler.displayError("MU");
    		handler.displayError(".");
    	}

    	if(pfNewPass.getText() != null)//Missing password
    		user.setPassword(pfNewPass.getPassword());
    	else
    	{
    		errorThrown = true;
    		handler.displayError("MP");
    		handler.displayError(".");
    	}

    	if(!(pfNewPass.getText().equals(pfPConfirm.getText())))//Password doesn't match password confirm
    	{
    		errorThrown = true;
    		handler.displayError("PNM");
    		handler.displayError(".");
    	}

    	if(errorThrown)
    		return true;
    	else
    		return false;
	}//end of checkErrors method

	//Confirm if the desired username has already been taken, or if the entered email is already registered
	private boolean confirmIndividuality()
	{
		//Declare and initialize a field
		boolean confirmed = false;

		try
		{
			//Declare a field and initialize it
			boolean [] conditionResults = database.findUserExists(user);

			//return true means the user field exists, false means it doesnt exist
		   	if(conditionResults[0] == false && conditionResults[1] == false)
		    {
		    	confirmed = true;
			}
			else if(conditionResults[0] == true && conditionResults[1] == false)
			{
				handler.displayError("UE");
				handler.displayError(".");
				confirmed = false;
			}
			else if(conditionResults[0] == false && conditionResults[1] == true)
			{
				handler.displayError("EE");
				handler.displayError(".");
				confirmed = false;
			}
			else if(conditionResults[0] == true && conditionResults[1] == true)
			{
				handler.displayError("UAEE");
				handler.displayError(".");
				confirmed = false;
			}
			else
			{
				confirmed = false;
			}
		}catch(Exception ex){handler.displayError("CNAD"); handler.displayError(".");}

		//Return the 'individuality'
		return confirmed;
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
					try
					{
						database.addUser(user);//Try adding the new user to the database
					}catch(Exception e){handler.displayError("CNAD"); handler.displayError(".");}
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
					try
					{
						database.addUser(user);//Try adding a new user to the database
					}catch(Exception exc){handler.displayError("CNAD"); handler.displayError(".");}
				}
			}
       	}
    }//End of keyPressed method

    //Not needed but required
    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e){}
}//End of Register class
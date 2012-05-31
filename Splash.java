//Import java API packages
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.*;
import java.awt.Dimension;

public class Splash extends JFrame
{
    public Splash()
    {
    	//Create a new Shape object
    	Shape frameShape;//Used to hold the shape of the physical GUI frame

    	//Create new JButton objects
    	JButton btnCheck;//Used to check if the application needs to be updated
    	JButton btnUpdate;//Used to update the application
    	JButton btnStart;//Used to start the application once checking and/or updating are finished
		
		JTextArea txtUpdates = new JTextArea("This is a testThis is a testThis is a testThis is a testThis is a testThis is a testThis is a testThis is a testThis is a testThis is a testThis is a testThis is a testThis is a test");
		
		txtUpdates.setPreferredSize(new Dimension(100, 100));
		txtUpdates.setLineWrap(true);
		txtUpdates.setEditable(false);
		
    	//Create a new JScrollPane object and attach the JList object to it
    	JScrollPane scrollUpdates = new JScrollPane(txtUpdates);

    	//Create and initialize new Image objects
    	Image checkImage = new Image();//Used to hold the 'Check for Updates' image
		Image updateImage = new Image();//Used to hold the 'Update' image
		Image startImage = new Image();//Used to hold the 'Start' image

		//Load images from file
    	checkImage.loadIcon("CheckButton.png");
    	updateImage.loadIcon("UpdateButton.png");
    	startImage.loadIcon("StartButton.png");

		//Create and initialize new ImageIcon objects
    	ImageIcon checkIcon = new ImageIcon();//Used to hold the 'Check for Updates' image for the button
    	ImageIcon updateIcon = new ImageIcon();//Used to hold the 'Update' image for the button(If they need to update)
    	ImageIcon startIcon = new ImageIcon();//Used to hold the 'Start' image for the button

    	//Set the icon objects to the image objects
    	checkIcon = checkImage.getIcon();
    	updateIcon = updateImage.getIcon();
    	startIcon = startImage.getIcon();

		//Setup the 'Check for Updates' button
		btnCheck = new JButton(checkIcon);//Initialize a button with the ImageIcon object attached to it
		btnCheck.setPreferredSize(new Dimension(123, 38));//Set the size of the button so it fits the icon size nicely

		//Setup the layout manager
        SpringLayout layout = new SpringLayout();//Create a SpringLayout object
		this.getContentPane().setLayout(layout);//Set the layout object to the content pane (GUI frame)

		//Add objects to the GUI frame
		this.add(btnCheck);
		this.add(scrollUpdates);

		//Set the position of the objects on the GUI frame
		layout.putConstraint(SpringLayout.WEST, btnCheck, 365, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, btnCheck, 435, SpringLayout.NORTH, this.getContentPane());

		layout.putConstraint(SpringLayout.WEST, scrollUpdates, 20, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, scrollUpdates, 20, SpringLayout.NORTH, this.getContentPane());

        //Setup the GUI frame properties
        this.getContentPane().setBackground(Color.black);//Set the colour of the GUI frame
        this.setUndecorated(true);//Set the GUI frame to have no borders or title bar
        this.setSize(500,500);//Set the size of the GUI frame
        this.setTitle("Unnamed");//Set the name of the GUI frame in the taskbar
        this.setLocationRelativeTo(null);//Set the position of the GUI frame on creation(Center screen)
    	this.setVisible(true);//Display the GUI frame on creation
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);//Close the java process after the GUI frame is closed

        //Initialize the Shape object
        frameShape = new RoundRectangle2D.Double(0, 0, this.getWidth(), this.getHeight(), 50, 50);

		//Set the GUI frame shape to the Shape object
        AWTUtilitiesWrapper.setWindowShape(this, frameShape);
    }//End of constructor method
}//End of Splash class
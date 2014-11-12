/**
 * @(#)MenuCreator.java
 *
 * @Description 
 *
 * @author Gurpreet Singh, Matt Ufimsef
 * @version 1.00 2012/6/15
 *
 * @Latest Updates: 
 *
 * @Status: Complete ~ accomodating for currently created classes
 */

//Import API packages
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.text.*;

public class MenuCreator extends JMenuBar
{
	//Declare class fields and objects
	private JMenuBar menuBar;
	private JMenu mainMenu;
	private JMenu mainMenuReference;
	private JMenu subMenu;
	private JMenu subMenuReference;
	private JMenuItem menuItem;
	private JMenuItem menuItemReference;
	private JMenuItem subMenuItem;
	private JMenuItem subMenuItemReference;
	private JCheckBoxMenuItem checkItem;
	private JCheckBoxMenuItem checkItemReference;
	private JCheckBoxMenuItem subCheckItem;
	private JCheckBoxMenuItem subCheckItemReference;
	private WritingMainBoard board;

    public MenuCreator(WritingMainBoard board)
    {
    	this.board = board;
    }//End of MenuCreator constructor method

    //Create a new menu bar or 'backbone' of the menus
    public void createMenuBar()
    {
    	//Initialize a class object
    	menuBar = new JMenuBar();

    	//Add the newly created menubar to the WritingMainBoard so we can create the menus on it
    	board.setJMenuBar(menuBar);
    }//End of method

    //Create a new main menu and add it to the 'backbone' menubar
    public void createMainMenu(String itemHeading)
    {
    	//Initialize a class object
    	mainMenu = new JMenu(itemHeading);

    	//Add the newly created mainmenu to the menubar we created earlier
    	menuBar.add(mainMenu);

    	//Set the reference mainmenu to the newly created mainmenu so we can use it later in the class
    	mainMenuReference = mainMenu;
    }//End of method

    //Create a new menu item and add it to a main menu
    public void createMenuItem(String itemHeading)
    {
    	//Initialize a class object
    	menuItem = new JMenuItem(itemHeading);

    	//Add the newly created menu item to the mainmenu we created earlier
    	mainMenuReference.add(menuItem);

    	//Set the reference menu item to the newly created menu item so we can use it later in the class
    	menuItemReference = menuItem;
    }//End of method

	//Create a new CheckboxMenuItem object and add it to a menu
	public void createCheckMenuItem(String itemHeading)
	{
		//Initialize a class object
		checkItem = new JCheckBoxMenuItem(itemHeading);

		//Add the newly created checkbox menu item to the main menu we created earlier
		mainMenuReference.add(checkItem);

		//Set the reference checkbox menu item to the newly created checkbox menu item so we can use it later in the class
		checkItemReference = checkItem;
	}//End of createCheckMenuItem method

    //Create a new sub menu and add it to a main menu
    public void createSubMenu(String itemHeading)
    {
    	//Initialize a class object
    	subMenu = new JMenu(itemHeading);

    	//Add the newly created submenu to the mainmenu we created earlier
    	mainMenuReference.add(subMenu);

    	//Set the reference submenu to the newly created submenu so we can use it later in the class
    	subMenuReference = subMenu;
    }//End of createSubMenu method

    //Create a new sub menu item and add it to a submenu
    public void createSubMenuItem(String itemHeading)
    {
    	//Initialize a class object
    	subMenuItem = new JMenuItem(itemHeading);

    	//Add the newly created submenu item to the submenu we created earlier
    	subMenuReference.add(subMenuItem);

    	//Set the reference submenu item to the newly created submenu item so we can use it later in the class
    	subMenuItemReference = subMenuItem;
    }//End of createSubMenuItem method

	//Create a new CheckboxMenuItem object and add it to a menu
	public void createSubCheckMenuItem(String itemHeading)
	{
		//Initialize a class object
		subCheckItem = new JCheckBoxMenuItem(itemHeading);

		//Add the newly created checkbox menu item to the main menu we created earlier
		subMenuReference.add(subCheckItem);

		//Set the reference checkbox menu item to the newly created checkbox menu item so we can use it later in the class
		subCheckItemReference = subCheckItem;
	}//End of createCheckMenuItem method

	//Return the current JMenuItem
    public JMenuItem getMenuItem()
    {
    	return menuItemReference;
    }//End of getMenuItem method

    //Return the current CheckBoxMenuItem
    public JCheckBoxMenuItem getCheckMenuItem()
    {
    	return checkItemReference;
    }//End of getCheckMenuItem method

    //Return the current sub-JMenuItem
    public JMenuItem getSubMenuItem()
    {
    	return subMenuItemReference;
    }//End of getMenuItem method

    //Return the current sub-JCheckBoxMenuItem
    public JMenuItem getSubCheckMenuItem()
    {
    	return subCheckItemReference;
    }//End of getMenuItem method
}//End of class
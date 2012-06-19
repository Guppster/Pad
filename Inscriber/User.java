/**
 * @(#)User.java
 *
 * @Description
 *
 * @author Gurpreet Singh, Matt Ufimsef
 * @version 1.00 2012/6/15
 *
 * @Latest Updates: Added usergroup as a field
 */

import java.util.*;

public class User
{
	//Declare instance variables
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String password;
	private UserGroup group;
	private boolean loggedIn;

	//Construct a default person object
	public User()
	{
		firstName = "";
		lastName = "";
		username = "";
		password = "";
		group = new UserGroup();
	}//End of default constructor

	//Construct person object with parameters passed in
	public User(String firstName, String lastName, String username, String password, String Email, UserGroup group)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.group = group;
	}//End of constructor

	//Accessor methods
	public String getFirstName()
	{
		return firstName;
	}//End of getFirstName method

	public String getLastName()
	{
		return lastName;
	}//End of getLastName method

	public String getUsername()
	{
		return username;
	}//End of getUsername method

	public String getPassword()
	{
		return password;
	}//End of getPassword method

	public String getEmail()
	{
		return email;
	}//End of getEmail method

	public String getGroup()
	{
		return group.getName();
	}//End of getGroup method

	//Mutator methods
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}//End of setFirstName method

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}//End of setLastName method

	public void setUsername (String username)
	{
		this.username = username;
	}//End of setUsername method

	public void setPassword(char [] passAsChar)
	{
		this.password = convertPassword(passAsChar);
	}//End of setPassword method

	public void setEmail(String email)
	{
		this.email = email;
	}//End of setEmail method

	public void setGroup(UserGroup group)
	{
		this.group = group;
	}//End of setGroup method

	//A method used to transfer the password from a char array to a string object
	public String convertPassword(char [] tempPass)
	{
		String tempString = "";

		for(int x = 0; x < tempPass.length; x++)
		{
			tempString += tempPass[x];
		}

		return tempString;
	}//End of loadPassword method

	//A String to store a Person object's data for display
	public String toString()
	{
		String output = "";

		output =  "First Name:  " + firstName;
		output += "\nLast Name: " + lastName;
		output += "\nUsername:  " + username;
		output += "\nPassword:  " + password;
		output += "\nEmail:     " + email;
		output += "\nUser Group:" + getGroup();

		return output;
	}//End of toString method
}//End of class
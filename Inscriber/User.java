/**
 * @(#)User.java
 *
 *
 * @author Gurpreet Singh, Matt Ufimsef
 * @version 1.00 2012/6/15
 *
 * @Latest Updates: getEmail accessor was missing, added.
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
	
	//Construct a default person object
	public User()
	{
		firstName = "";
		lastName = "";
		username = "";
		password = "";
		
	}//End of default constructor
	
	//Construct person object with parameters passed in
	public User(String firstName, String lastName, String username, String password, String Email)
	{		
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
	}//End of constructor
	
	//Accessor methods
	public String getFirstName()
	{
		return firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	//Mutator methods
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	public void setUsername (String username)
	{
		this.username = username;
	}
	
	public void setPassword(char [] passAsChar)
	{
		this.password = convertPassword(passAsChar);
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
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
		
		return output;
	}//End of toString method
}//End of class
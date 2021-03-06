/**
 * @(#)Database.java
 *
 * @author Gurpreet Singh, Matthew Ufimzeff
 * @version 1.00 2012/6/20
 *
 * @Class Description: This class is used to do everything database related, such as add, delete, search, edit, the database - This class also allows
 *						the connection to the database to be made.
 *
 * @Status: Finished - Completely commented - No errors.
 */

//Import Java API Packages
import java.net.*;
import java.sql.*;
import java.io.*;
import java.util.*;

public class Database
{
	//Constant variables storing database access information
	private static String url = "jdbc:mysql://192.168.0.22/inscriber";//Used to store the IP, port, and database name for the database(Used to connect to the database)
	private static String dbUser = "project";//Used to hold the database username
	private static String dbPass = "derp";//Used to hold the database password
	private static String driver = "com.mysql.jdbc.Driver";//Used to hold the MYSQL driver package(Similar to BeckerRobot.jar - except this is a package path

	//Database default constructor
	public Database(){}//End of Database default constructor

	//A method used to create a new table in the database
    public void createTable(String tableName) throws Exception
    {
    	//Declare and initialize some fields
        Class.forName(driver);//Access' the MYSQL driver folder so we can use methods from it(Same as beckerrobot.jar)
        Connection conn = DriverManager.getConnection(url, dbUser, dbPass);//Create a new connection with the specified hostname, port, and database username/password
        Statement statement = conn.createStatement();//Create a new statement under the new connection

        //Execute some commands to the MYSQL database
        statement.executeUpdate("DROP TABLE if EXISTS " + tableName + ";");
        statement.executeUpdate("CREATE table " + tableName + ";");

		//Close the connection
        conn.close();
    }//End of createTable method

	//Adds a new user record to the database
    public void addUser(User user) throws Exception
    {
    	//Declare and initialize some fields
    	Class.forName(driver);//Access' the MYSQL driver folder so we can use methods from it(Same as beckerrobot.jar)
        Connection conn = DriverManager.getConnection(url, dbUser, dbPass);//Create a new connection with the specified hostname, port, and database username/password
		Statement statement = conn.createStatement();//Create a new statement under the new connection

		//Compile a command into a string so we can execute it
		String state = "INSERT INTO accounts VALUES ('" + user.getFirstName() + "', '" + user.getLastName() + "', '" + user.getUsername() + "', '" + user.getPassword()
			+ "', '" + user.getEmail() + "', '" + user.getGroup() + "', " + 0 + ")";

		//Execute the command
        statement.executeUpdate(state);

		//Close the connection
		statement.close();
        conn.close();
    }//End of addUser method

	//Adds a new file name and author name to the database
    public void addDocument(String fileName, int numChars, int numSentences, int numWords) throws Exception
    {
    	//Declare and initialize some fields
    	Class.forName(driver);//Access' the MYSQL driver folder so we can use methods from it(Same as beckerrobot.jar)
        Connection conn = DriverManager.getConnection(url, dbUser, dbPass);//Create a new connection with the specified hostname, port, and database username/password
		Statement statement = conn.createStatement();

		//Compile a command into a string so we can execute it
		String state = "INSERT documents VALUES ('" + fileName + "', " + numChars + ", " + numSentences + ", " + numWords + ")";

		//Execute the command
        statement.executeUpdate(state);

		//Close the connection
        conn.close();
  	}//End of addDocument method

	//A method to check the database to see if a username or email already exists
    public boolean [] findUserExists(User user) throws Exception
    {
    	//Declare and initialize some fields
    	Class.forName(driver);//Access' the MYSQL driver folder so we can use methods from it(Same as beckerrobot.jar)
        Connection conn = DriverManager.getConnection(url, dbUser, dbPass);//Create a new connection with the specified hostname, port, and database username/password
    	Statement statement = conn.createStatement();//Create a new statement under the new connection
    	ResultSet rs = statement.executeQuery("SELECT * FROM accounts;");//Creates a result set object so we can query the database
    	boolean [] conditions = new boolean[2];//Used to hold the conditions of whether the user already exists, or the email already exists, both, or neither

    	//While there is a 'non-null' column to select
        while (rs.next())
        {
        	if(user.getUsername().equals(rs.getString("user")))
        	{
        		//Username already exists in database
        		conditions[0] = true;
        	}

        	if(user.getEmail().equals(rs.getString("email")))
        	{
        		//Email already exists in database
        		conditions[1] = true;
        	}
        }

        //Close the ResultSet and connection
        rs.close();
        conn.close();

        //Return which conditions were triggered
        return conditions;
    }//End of findUserExists method

	//A method to check the username and password entered by the user, against the ones in the database
    public boolean checkLogin(User user) throws Exception
    {
    	//Declare and initialize some fields
    	Class.forName(driver);//Access' the MYSQL driver folder so we can use methods from it(Same as beckerrobot.jar)
        Connection conn = DriverManager.getConnection(url, dbUser, dbPass);//Create a new connection with the specified hostname, port, and database username/password
    	Statement stat = conn.createStatement();//Create a new statement under the new connection
		ResultSet rsUser = stat.executeQuery("SELECT accounts.user FROM accounts;");//Creates a result set object so we can query the database
		ResultSet rsPass = null;//Creates a result set object so we can query the database
		boolean userFound = false;//Stores the boolean flag for if the username was found
		boolean passFound = false;//Stores the boolean flag for if the password was found/matched

		//While there is a 'non-null' column to select
        while(rsUser.next())
        {
        	if(user.getUsername().equals(rsUser.getString("user")))
        	{
        		//Username entered, was found in the database
        		userFound = true;

        		//Close the current ResultSet and break out of the while
        		rsUser.close();
        		break;
        	}
        }

		//If the entered username wasn't found in the database, close the ResultSet object, close the connection, and return that the login is not valid
        if(!userFound)
        {
        	//Reset the boolean flags
        	userFound = false;
			passFound = false;
			rsUser.close();
       		conn.close();
			return false;
        }

		//If the entered username is found in the database, check the entered pass against the password in the database
        if(userFound)
        {
        	rsPass = stat.executeQuery("SELECT accounts.pass FROM accounts;");

        	while(rsPass.next())
	        {
	        	if(user.getPassword().equals(rsPass.getString("pass")))
	        	{
	        		//Password found and matches one passed in
	        		passFound = true;
	        		return true;//Login is valid
	        	}
	        }
	        if(!passFound)
	        {
	        	//Reset the boolean flags
	        	userFound = false;
	        	passFound = false;
	        	return false;//Login is invalid
	        }

			//Close the ResultSet and connection and reset the boolean flags
			userFound = false;
			passFound = false;
	        rsPass.close();
	        conn.close();
        }

		//If the program manages to break - just return false and don't allow the user to login
        return false;
    }//End of Check login method

	//Finds the user according to the login credentials and sets its loggedIn varaiable to true;
    public void switchLoginStatus(User user) throws Exception
    {
    	//Declare and initialize some fields
		Class.forName(driver);//Access' the MYSQL driver folder so we can use methods from it(Same as beckerrobot.jar)
        Connection conn = DriverManager.getConnection(url, dbUser, dbPass);//Create a new connection with the specified hostname, port, and database username/password
    	Statement stat = conn.createStatement();//Create a new statement under the new connection
    	ResultSet rs = stat.executeQuery("SELECT * FROM accounts;");//Creates a result set object so we can query the database

		//While the selected column is not null
		while(rs.next())
        {
        	if(user.getUsername().equals(rs.getString("user")))
        	{
        		 if(rs.getInt(7) == 0)
        		 {
        		 	stat.executeUpdate("UPDATE `accounts` SET `loggedIn`='1' WHERE (`user`='" + user.getUsername() + "') AND (`pass`='" + user.getPassword() + "') AND (`loggedIn`='0') LIMIT 1;");
        		 	break;
        		 }
        		 else
        		 {
        		 	stat.executeUpdate("UPDATE `accounts` SET `loggedIn`='0' WHERE (`user`='" + user.getUsername() + "') AND (`pass`='" + user.getPassword() + "') AND (`loggedIn`='1') LIMIT 1;");
        			break;
        		 }
        	}
        }

		//Close the ResultSet and connection
        rs.close();
        conn.close();
    }//End of switchLoginStatus method

    //Returns the login status of the user from the server - Used to avoid dual login under the same username
    public boolean getLoginStatus(User user) throws Exception
    {
    	//Declare and initialize some fields
    	Class.forName(driver);//Access' the MYSQL driver folder so we can use methods from it(Same as beckerrobot.jar)
        Connection conn = DriverManager.getConnection(url, dbUser, dbPass);//Create a new connection with the specified hostname, port, and database username/password
    	Statement stat = conn.createStatement();//Create a new statement under the new connection
    	ResultSet rs = stat.executeQuery("SELECT * FROM accounts;");//Creates a result set object so we can query the database

		//While the selected column is not null
    	while(rs.next())
    	{
    		if(user.getUsername().equals(rs.getString("user")))
        	{
        		if(rs.getInt(7) == 1)
        		{
        			//Close the ResultSet and connection and return that the user is logged in
        			rs.close();
        			conn.close();
        			return true;
        		}
        		else
        		{
        			//Close the ResultSet and connection and return that the user is not logged in
        			rs.close();
        			conn.close();
        			return false;
        		}
        	}
    	}

    	//If the program breaks, and all else fails, close the ResultSet and connection and return that the user is not currently logged in
		rs.close();
        conn.close();
		return false;
    }//End of getLoginStatus

	//A method to retrieve the default permissions for the default user group from the database
    public boolean [] retrieveDefaultPermissions() throws Exception
    {
    	//Declare and initialize some fields
 		Class.forName(driver);//Access' the MYSQL driver folder so we can use methods from it(Same as beckerrobot.jar)
        Connection conn = DriverManager.getConnection(url, dbUser, dbPass);//Create a new connection with the specified hostname, port, and database username/password
    	Statement stat = conn.createStatement();//Create a new statement under the new connection
	 	ResultSet rs = stat.executeQuery("SELECT * FROM usergroups;");//Creates a result set object so we can query the database
	 	boolean [] permissions = new boolean[5];//Used to store the values for the permissions

    	if(rs.isFirst())
        {
			permissions[0] = rs.getBoolean("canEdit");
			permissions[1] = rs.getBoolean("canChat");
			permissions[2] = rs.getBoolean("canExport");
			permissions[3] = rs.getBoolean("canFormat");
			permissions[4] = rs.getBoolean("canAccess");
        }

	    //Closes the ResultSet and connection
        rs.close();
        conn.close();

		//Return the values for the permissions we just retrieved from the server
        return permissions;
    }//End of retrieveDefaultPermissions method

	//A method to add a new group to the database
    public void addNewGroup(UserGroup group) throws Exception
    {
    	//Declare and initialize some fields
		Class.forName(driver);//Access' the MYSQL driver folder so we can use methods from it(Same as beckerrobot.jar)
        Connection conn = DriverManager.getConnection(url, dbUser, dbPass);//Create a new connection with the specified hostname, port, and database username/password
		Statement statement = conn.createStatement();//Create a new statement under the new connection
        boolean [] permissions = new boolean[5];//Used to store the permissions for the passined in UserGroup object
    	permissions = group.getPermissions();//Initialize the permissions array

    	//Compile a command into a string so we can execute it
    	String state = "INSERT usergroups VALUES ("+ permissions[0] + ", " + permissions[1] + ", " + permissions[2] + ", "
    		+ permissions[3] + ", " + permissions[4] + ")";

		//Execute a command
		statement.executeUpdate(state);

        //Closes the connection
        conn.close();
    }//End of addNewGroup method

	//A method to retrieve the name of a group at a specified index in the database
    public String getGroupName(int index) throws Exception
    {
    	//Declare and initialize some fields
    	Class.forName(driver);//Access' the MYSQL driver folder so we can use methods from it(Same as beckerrobot.jar)
        Connection conn = DriverManager.getConnection(url, dbUser, dbPass);//Create a new connection with the specified hostname, port, and database username/password
    	Statement stat = conn.createStatement();//Create a new statement under the new connection
	 	ResultSet rs = stat.executeQuery("SELECT * FROM usergroups;");//Creates a result set object so we can query the database

		//While the next column selected is not null
	 	while(rs.next())
	 	{
			if(rs.getRow() == index)
			{
				rs.close();
        		conn.close();
				return rs.getString("name");
			}
	 	}

		//Closes the ResultSet and connection
	    rs.close();
        conn.close();

		//Group name was not found in the database, return nothing
		return "";
    }//End of getGroupName method

	//A method to get the number of groups in the database
    public int getNumGroups() throws Exception
    {
    	//Declare and initialize some fields
    	Class.forName(driver);//Access' the MYSQL driver folder so we can use methods from it(Same as beckerrobot.jar)
        Connection conn = DriverManager.getConnection(url, dbUser, dbPass);//Create a new connection with the specified hostname, port, and database username/password
    	Statement stat = conn.createStatement();//Create a new statement under the new connection
     	ResultSet rs = stat.executeQuery("SELECT * FROM usergroups;");//Creates a result set object so we can query the database
     	int numRows = 0;//Used to hold the number of rows(groups)

		//While the next selected column is not null
     	while(rs.next())
	 	{
			numRows++;
	 	}

		//Closes the ResultSet and connection
	 	rs.close();
	 	conn.close();

		//Return the number of rows that was calculated
	 	return numRows;
    }//End of getNumGroups method

	//A method to remove a group from the database(Database searches for passed in group name, if it exists, it deletes the entire group from the database)
    public void removeGroup(String groupName) throws Exception
    {
    	//Declare and initialize some fields
    	Class.forName(driver);//Access' the MYSQL driver folder so we can use methods from it(Same as beckerrobot.jar)
        Connection conn = DriverManager.getConnection(url, dbUser, dbPass);//Create a new connection with the specified hostname, port, and database username/password
    	Statement stat = conn.createStatement();//Create a new statement under the new connection
    	ResultSet rs = stat.executeQuery("SELECT * FROM usergroups;");//Creates a result set object so we can query the database

		//While the next selected column is not null
    	while(rs.next())
	 	{
			if(groupName.equals(rs.getString("name")))
			{
				rs.deleteRow();
			}
	 	}

    	//Closes the ResultSet and connection
	 	rs.close();
	 	conn.close();
    }//End of removeGroup method

	//A method to initialize a new user object from the registered users on the database
    public User initializeUser(User user) throws Exception
    {
    	//Declare and initialize some fields
		Class.forName(driver);//Access' the MYSQL driver folder so we can use methods from it(Same as beckerrobot.jar)
    	Connection conn = DriverManager.getConnection(url, dbUser, dbPass);//Create a new connection with the specified hostname, port, and database username/password
    	Statement stat = conn.createStatement();//Create a new statement under the new connection
    	ResultSet rs = stat.executeQuery("SELECT * FROM accounts;");//Creates a result set object so we can query the database
    	User iniUser = new User();//Creates a new empty User object

		//While the next selected column is not null
    	while(rs.next())
    	{
    		//Find the user in database which matches login credentials
    		if(user.getUsername().equals(rs.getString("user")) && user.getPassword().equals(rs.getString("pass")))
			{
				//Initialize all fields of new user object based on the selected entry
				iniUser.setFirstName(rs.getString("first"));
				iniUser.setLastName(rs.getString("last"));
				iniUser.setUsername(rs.getString("user"));
				iniUser.setPassword(rs.getString("pass").toCharArray());
				iniUser.setEmail(rs.getString("email"));
				iniUser.setGroup(initializeGroup(rs.getString("group")));
			}
    	}

	    //Close the ResultSet and connection
    	rs.close();
    	conn.close();

		//Return the new full User object
    	return iniUser;
    }//End of initializeUser method

    //This method looks in the usergroup table and finds the group name and then initializes the rest of the fields(permissions) for the usergroup object it will return
    public UserGroup initializeGroup(String groupName) throws Exception
    {
    	//Declare and initialize some fields
		Class.forName(driver);//Access' the MYSQL driver folder so we can use methods from it(Same as beckerrobot.jar)
    	Connection conn = DriverManager.getConnection(url, dbUser, dbPass);//Create a new connection with the specified hostname, port, and database username/password
    	Statement stat = conn.createStatement();//Create a new statement under the new connection
    	ResultSet rs = stat.executeQuery("SELECT * FROM usergroups;");//Creates a result set object so we can query the database
    	UserGroup group = new UserGroup();//Creates a new empty UserGroup object
    	boolean [] permissions = new boolean[5];//Used to store the permissions from the database

		//While the next selected column is not null
    	while(rs.next())
    	{
    		if(rs.getString("name").equals(groupName))
    		{
    			group.setName(rs.getString("name"));
    			if(rs.getInt("canEdit") == 1){permissions[0] = true;}else{permissions[0] = false;}
    			if(rs.getInt("canChat") == 1){permissions[1] = true;}else{permissions[1] = false;}
    			if(rs.getInt("canExport") == 1){permissions[2] = true;}else{permissions[2] = false;}
    			if(rs.getInt("canFormat") == 1){permissions[3] = true;}else{permissions[3] = false;}
    			if(rs.getInt("canAccess") == 1){permissions[4] = true;}else{permissions[4] = false;}
    		}
    	}

    	//Close the ResultSet and connection
    	rs.close();
    	conn.close();

		//Return the new full UserGroup object
    	return group;
    }//End of initializeGroup method

	//This class was used to get all the document properties from the database, store them in an arrayList and then return the ArrayList - DOES NOT WORK
    public ArrayList<String> getDocumentsData() throws Exception
   	{
   		//Declare and initialize some fields
   		Class.forName(driver);//Access' the MYSQL driver folder so we can use methods from it(Same as beckerrobot.jar)
   		Connection conn = DriverManager.getConnection(url, dbUser, dbPass);//Create a new connection with the specified hostname, port, and database username/password
    	Statement stat = conn.createStatement();//Create a new statement under the new connection
    	ResultSet rs = stat.executeQuery("SELECT * FROM documents;");//Creates a result set object so we can query the database
    	ArrayList<String> list = new ArrayList<String>();//Used to hold all the arrays full of document properties retrieved from the database
    	String[] data = new String[3];//Used to hold all the properties we take in from the database

		//While the next selected column is not null
    	while(rs.next())
    	{
    		//Get the name for that particular document and store it in an index in our array
			ResultSet rsName = stat.executeQuery("SELECT documents.name FROM documents;");

			while(rsName.next())
			{
				data[0] = rsName.getString("name");
				rsName.close();
				break;
			}

			//Get the number of characters for that particular document and store it in an index in our array
			ResultSet rsNumChars = stat.executeQuery("SELECT documents.numCharacters FROM documents;");

			while(rsNumChars.next())
			{
				data[1] = rsNumChars.getString("numChars");
				rsNumChars.close();
				break;
			}

			//Get the number of sentences for that particular document and store it in an index in our array
			ResultSet rsNumSentences = stat.executeQuery("SELECT documents.numSentences FROM documents;");

			while(rsNumSentences.next())
			{
				data[2] = rsNumSentences.getString("numSentences");
				rsNumSentences.close();
				break;

			}

			//Get the number of words for that particular document and store it in an index in our array
			ResultSet rsNumWords = stat.executeQuery("SELECT documents.numWords FROM documents;");

			while(rsNumWords.next())
			{
				data[3] = rsNumWords.getString("numWords");
				rsNumWords.close();
				break;
			}

			//Add the array to the array list
			//list.add(data);
    	}

    	//No more non null rows found, return the filled ArrayList object
		return list;
	}//End of getDocumentsData method

	public void saveDocOptions()
	{
		//To be implemented in later release, method will save options to document database fields (currently not created)
	}//End of saveDocOptions method

}//End of Databae class
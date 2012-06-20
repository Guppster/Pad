/**
 * @(#)Database.java
 *
 *
 * @author Gurpreet Singh, Matt Ufimzeff
 * @version 1.00 2012/6/15
 *
 * @Latest Updates: Added constant variables for information regarding connection to the database, and converted code from sqlite to MySQL
 *
 * @Status: Complete ~ accomodating for currently created classes
 */

//Import Java API Packages
import java.net.*;
import java.sql.*;
import java.io.*;
import java.util.*;

public class Database
{
	//Constant variables storing database access information
	private static String url = "jdbc:mysql://192.168.0.22/inscriber";
	private static String dbUser = "project";
	private static String dbPass = "derp";
	private static String driver = "com.mysql.jdbc.Driver";

	//Default constructor
	public Database()
    {
    }//End of default constructor

    public void createTable(String tableName) throws Exception
    {
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
        Statement stat = conn.createStatement();
        stat.executeUpdate("DROP TABLE if EXISTS " + tableName + ";");
        stat.executeUpdate("CREATE table " + tableName + ";");

		//Close the connection
        conn.close();
    }//End of createDB method

	//Adds a new user record to the database
    public void addUser(User user) throws Exception
    {
    	//Declare and initialize some fields
    	Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
		Statement statement = (Statement)conn.createStatement();

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
    public void createNewDocument(String fileName, int numChars, int numSentences, int numWords) throws Exception
    {
    	//Declare and initialize some fields
    	Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
		Statement statement = conn.createStatement();

		//Compile a command into a string so we can execute it
		String state = "INSERT documents VALUES ('" + fileName + "', " + numChars + ", " + numSentences + ", " + numWords + ")";

		//Execute the command
        statement.executeUpdate(state);

		//Close the connection
        conn.close();
  	}//End of createNewDocument method

    public boolean [] findUserExists(User user) throws Exception
    {
    	Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
    	Statement stat = conn.createStatement();

    	boolean [] conditions = new boolean[2];

    	ResultSet rs = stat.executeQuery("SELECT * FROM accounts;");
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

        //Close the ResultSet and Connection
        rs.close();
        conn.close();
        return conditions;
    }//End of findUserExists

    public boolean checkLogin(User user) throws Exception
    {
    	Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
    	Statement stat = conn.createStatement();

		ResultSet rsUser = stat.executeQuery("SELECT accounts.user FROM accounts;");
		ResultSet rsPass = null;
		boolean userFound = false;
		boolean passFound = false;

        while(rsUser.next())
        {
        	if(user.getUsername().equals(rsUser.getString("user")))
        	{
        		userFound = true;

        		//Close the current ResultSet
        		rsUser.close();
        		break;
        	}
        }

        if(!userFound)
        {
        	//Username not found
        	//Close the ResultSet and Connection
			rsUser.close();
       		conn.close();
			return false;//Login is not valid
        }

        if(userFound)
        {

        	rsPass = stat.executeQuery("SELECT accounts.pass FROM accounts;");

        	while(rsPass.next())
	        {
	        	//Username found
	        	if(user.getPassword().equals(rsPass.getString("pass")))
	        	{
	        		//Password found and matches one passed in
	        		passFound = true;
	        		return true;//Login is valid
	        	}
	        }
	        if(!passFound)
	        {
	        	return false;//Login is valid
	        }

			//Close the ResultSet and Connection
	        rsPass.close();
	        conn.close();
        }
        else
        {
        	//Close the ResultSet and Connection
	        rsUser.close();
	        conn.close();
	        return false;
        }

        return false;
    }//End of Check login method

	//Finds the user according to the login credentials and sets its loggedIn varaiable to true;
    public void switchLoginStatus(User user) throws Exception
    {
		Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
    	Statement stat = conn.createStatement();

    	ResultSet rs = stat.executeQuery("SELECT * FROM accounts;");

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

		//Close the ResultSet and Connection
        rs.close();
        conn.close();
    }//End of loginUser method

    //returns a boolean indicating weather the user is logged in or not
    public boolean getLoginStatus(User user) throws Exception
    {
    	Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
    	Statement stat = conn.createStatement();

    	ResultSet rs = stat.executeQuery("SELECT * FROM accounts;");

    	while(rs.next())
    	{
    		if(user.getUsername().equals(rs.getString("user")))
        	{
        		if(rs.getInt(7) == 1)
        		{
        			//Closes the resultSet and connection
        			rs.close();
        			conn.close();
        			return true;
        		}
        		else
        		{
        			//Closes the resultSet and connection
        			rs.close();
        			conn.close();
        			return false;
        		}
        	}
    	}

    	//Closes the resultSet and connection
		rs.close();
        conn.close();
		return false;
    }//End of getLoginStatus

    public boolean [] retrieveDefaultPermissions() throws Exception
    {
    	boolean [] permissions = new boolean[5];
        Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
    	Statement stat = conn.createStatement();

	 	ResultSet rs = stat.executeQuery("SELECT * FROM usergroups;");

    	if(rs.isFirst())
        {
			permissions[0] = rs.getBoolean("canEdit");
			permissions[1] = rs.getBoolean("canChat");
			permissions[2] = rs.getBoolean("canExport");
			permissions[3] = rs.getBoolean("canFormat");
			permissions[4] = rs.getBoolean("canAccess");
        }

	    //Closes the resultSet and connection
        rs.close();
        conn.close();

        return permissions;
    }//End of retrieveDefaultPermissions method

    public void addNewGroup(UserGroup group) throws Exception
    {
    	boolean [] permissions = new boolean[5];
    	permissions = group.getPermissions();

        Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
		PreparedStatement prep = conn.prepareStatement(
        	      "INSERT usergroups VALUES (?, ?, ?, ?, ?);");

        prep.setBoolean(1, permissions[0]);
        prep.setBoolean(2, permissions[1]);
        prep.setBoolean(3, permissions[2]);
        prep.setBoolean(4, permissions[3]);
        prep.setBoolean(5, permissions[4]);
        prep.addBatch();

		conn.setAutoCommit(false);
        prep.executeBatch();
        conn.setAutoCommit(true);

        //Closes the connection
        conn.close();
    }//End of addNewGroup method

    public String getGroupName(int index) throws Exception
    {
        Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
    	Statement stat = conn.createStatement();

	 	ResultSet rs = stat.executeQuery("SELECT * FROM usergroups;");

	 	while(rs.next())
	 	{
			if(rs.getRow() == index)
			{
				rs.close();
        		conn.close();
				return rs.getString("name");
			}
	 	}

		//Closes the resultSet and connection
	    rs.close();
        conn.close();

		return null;
    }//End of getGroupName

    public int getNumGroups() throws Exception
    {
    	int numRows = 0;
        Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
    	Statement stat = conn.createStatement();

     	ResultSet rs = stat.executeQuery("SELECT * FROM usergroups;");

     	while(rs.next())
	 	{
			numRows++;
	 	}

		//Closes the resultSet and connection
	 	rs.close();
	 	conn.close();

	 	return numRows;
    }//End og getNumGroups

    public void removeGroup(String groupName) throws Exception
    {
        Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
    	Statement stat = conn.createStatement();

    	ResultSet rs = stat.executeQuery("SELECT * FROM usergroups;");

    	while(rs.next())
	 	{
			if(groupName.equals(rs.getString("name")))
			{
				rs.deleteRow();
			}
	 	}

    	//Closes the resultSet and connection
	 	rs.close();
	 	conn.close();

    }//End of removeGroup method

    public User initializeUser(User user) throws Exception
    {
    	User iniUser = new User();

    	Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
    	Statement stat = conn.createStatement();

    	ResultSet rs = stat.executeQuery("SELECT * FROM accounts;");

    	while(rs.next())
    	{
    		//find the user in database which matches login credentials
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

	    //Closes the resultSet and connection
    	rs.close();
    	conn.close();

    	return iniUser;
    }//End of initializeUser method

    //This method looks in the usergroup table and finds the group name and then initializes the rest of the fields(permissions) for the usergroup object it will return
    public UserGroup initializeGroup(String groupName) throws Exception
    {
    	UserGroup group = new UserGroup();
    	Boolean [] permissions = new Boolean[5];

    	Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
    	Statement stat = conn.createStatement();

    	ResultSet rs = stat.executeQuery("SELECT * FROM usergroups;");

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

    	//Closes the resultSet and connection
    	rs.close();
    	conn.close();

    	return group;
    }//End of initializeGroup method
/*
    public ArrayList<String> getDocumentsData()
   	{
   		//PUT ALL THE "data[0]... data[1]... etc" INTO A LOOP TO LOOP THROUGH UNTIL IT REACHES A NULL ROW(while rs.next())

   		ArrayList<String> list = new ArrayList<String>();
   		String[] data = new String[3];

   		Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
    	Statement stat = conn.createStatement();

    	ResultSet rs = stat.executeQuery("SELECT * FROM documents;");

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
//			list.add(data);
    	}

    	//No more non null rows found
		return list;
	}//End of getDocumentsData method
	*/
}//End of class class
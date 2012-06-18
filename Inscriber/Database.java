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

public class Database
{
	//Constant variables storing database access information
	private static String url = "jdbc:mysql://localhost:3306/inscriber";
	private static String dbUser = "Project";
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

        conn.close();
    }//End of createDB method

    public void addUser(User user) throws Exception
    {
    	Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
		PreparedStatement prep = conn.prepareStatement(
        	      "INSERT accounts VALUES (?, ?, ?, ?, ?, ?);");

		conn.setAutoCommit(false);
        prep.setString(1, user.getFirstName());
        prep.setString(2, user.getLastName());
        prep.setString(3, user.getUsername());
        prep.setString(4, user.getPassword());
        prep.setString(5, user.getEmail());
        prep.setString(6, user.getGroup());
        prep.addBatch();

        conn.commit();
        prep.executeBatch();

        conn.close();
    }//End of addUser method

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
        rs.close();
        conn.close();
        return conditions;
    }//End of findUserExists

    public boolean checkLogin(User user) throws Exception
    {
    	Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
        System.out.print("Database loaded");
    	Statement stat = conn.createStatement();

		ResultSet rs = stat.executeQuery("SELECT accounts.user FROM accounts;");

        while(rs.next())
        {
        	if(user.getUsername().equals(rs.getString("user")))
        	{
        		//Username found
        		if(user.getPassword().equals(rs.getString("pass")))
        		{
        			//Password found and matches one passed in
        			rs.close();
        			conn.close();
        			return true;//Login is valid
        		}
        	}
			else
			{
				//Username not found
				rs.close();
       			conn.close();
				return false;//Login is not valid
			}
        }
		System.out.print("Error - Cannot connect to database");
        rs.close();
        conn.close();
        return false;
    }//End of Check login method

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

	 	rs.close();
	 	conn.close();
    }//End of removeGroup method
}//End of class class
/**
 * @(#)Database.java
 *
 *
 * @author
 * @version 1.00 2012/6/15
 */

import java.net.*;
import java.sql.*;
import java.io.*;

public class Database
{
	//Constant variables storing database access information
	private static url = "jdbc:mysql://localhost:3306/";
	private static user = "root";
	private static pass = "root";
	private static driver = "com.mysql.jdbc.Driver";
	
	public Database()
    {

    }

    private void createDB(String dbName) throws Exception
    {
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, user, pass);
        Statement stat = conn.createStatement();
        stat.executeUpdate("drop table if exists " + dbName + ";");
        stat.executeUpdate("create table " + dbName + ";");

        conn.close();
    }//End of createDB method

    public void addUser(User user) throws Exception
    {
        Connection conn = DriverManager.getConnection(url + "accounts", user, pass);
		PreparedStatement prep = conn.prepareStatement(
        	      "INSERT accounts VALUES (?, ?, ?, ?, ?);");

        prep.setString(1, user.getFirstName());
        prep.setString(2, user.getLastName());
        prep.setString(3, user.getUsername());
        prep.setString(4, user.getPassword());
        prep.setString(5, user.getEmail());
        prep.addBatch();

        conn.setAutoCommit(false);
        prep.executeBatch();
        conn.setAutoCommit(true);

        conn.close();
    }//End of addUser method

    public boolean [] findUserExists(User user) throws Exception
    {
    	Connection conn = DriverManager.getConnection("jdbc:sqlite:accounts.db");
    	Statement stat = conn.createStatement();

    	boolean [] conditions = new boolean[2];

    	ResultSet rs = stat.executeQuery("select * from accounts;");
        while (rs.next())
        {

        	if(user.getUsername().equals(rs.getString("user")))
        	{
        		System.out.println("Username Already Exists In Database");
        		conditions[0] = true;
        	}

        	if(user.getEmail().equals(rs.getString("email")))
        	{
        		System.out.println("Email Already Exists In Database");
        		conditions[1] = true;
        	}
        }
        rs.close();
        conn.close();
        return conditions;
    }//End of findUserExists

    public boolean checkLogin(User user) throws Exception
    {
    	Connection conn = DriverManager.getConnection("jdbc:sqlite:accounts.db");
    	Statement stat = conn.createStatement();

    	ResultSet rs = stat.executeQuery("select * from accounts;");

        while(rs.next())
        {
        	if(user.getUsername().equals(rs.getString("user")))
        	{
        		System.out.println("Username Found");

        		if(user.getPassword().equals(rs.getString("pass")))
        		{
        			System.out.println("Password Found");
        			rs.close();
        			conn.close();
        			return true;
        		}
        	}
			else
			{
				System.out.println("Username not Found");
				rs.close();
       			conn.close();
				return false;
			}
        }
        rs.close();
        conn.close();

        System.out.println("Username not Found - While Escaped");
        return false;
    }//End of Check login method/

    public boolean [] retrieveDefaultPermissions() throws Exception
    {
    	boolean [] permissions = new boolean[5];
    	Connection conn = DriverManager.getConnection("jdbc:sqlite:usergroups.db");
    	Statement stat = conn.createStatement();

	 	ResultSet rs = stat.executeQuery("select * from usergroups;");

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

	    Connection conn = DriverManager.getConnection("jdbc:sqlite:usergroups.db");
		PreparedStatement prep = conn.prepareStatement(
        	      "insert into usergroups values (?, ?, ?, ?, ?);");

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
		Connection conn = DriverManager.getConnection("jdbc:sqlite:usergroups.db");
    	Statement stat = conn.createStatement();

	 	ResultSet rs = stat.executeQuery("select * from usergroups;");

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
    	Connection conn = DriverManager.getConnection("jdbc:sqlite:usergroups.db");
    	Statement stat = conn.createStatement();

     	ResultSet rs = stat.executeQuery("select * from usergroups;");

     	while(rs.next())
	 	{
			numRows++;
	 	}

	 	rs.close();
	 	conn.close();

	 	return numRows;
    }//End og getNumGroups

    public void removeGroup(String columnLabel) throws Exception
    {
		Connection conn = DriverManager.getConnection("jdbc:sqlite:usergroups.db");
    	Statement stat = conn.createStatement();

    	ResultSet rs = stat.executeQuery("select * from usergroups;");

    	while(rs.next())
	 	{
			if(columnLabel.equals(rs.getString("name")))
			{
				rs.deleteRow();
			}
	 	}
    }//End of removeGroup method
}//End of class class
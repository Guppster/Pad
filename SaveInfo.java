/**
 * @author Matthew Ufimzeff
 * @author Gurpreet Singh
 */

//Import a package
package Login.Screen;

/**
 * Save the registered information.
 */
public class SaveInfo 
{
    //Create variables
    private String fName;
    private String lName;
    private final String eAddress;
    private final String username;
    private final String password;
    
    /**
     * Saves the users first name, last name, email address, username and password to a
     * database.
    */
    public SaveInfo(String fName, String lName, String eAddress,
            String username, String password)
    {
        this.fName = fName;
        this.lName = lName;   
        this.eAddress = eAddress;
        this.username = username;
        this.password = password;
    }//End of constructor method
   
    /**
     * Retrieves the users first and last name from a database.
    */
    public String getName()
    {
        //Create and initialize variable
        String name = "";
        
        //Put the first and last name together, and return the full name
        name = fName + lName;
        
        return name;
    }//End of method
    
    /**
     * Retrieves the users email address from a database.
    */
    public String getEAddress()
    {
        return eAddress;
    }//End of method
    
    /**
     * Retrieves the users username and password from a database.
    */
    public String getInfo()
    {
        //Create and initialize variable
        String account = "";
        
        //Put the first and last name together, and return the full name
        account = username + " " + password;
        
        return account;
    }//End of method
    
    public static String toString(String name, String eAddress,
            String username, String password)
    {
    	String string = "";
        
        string += name;
        string += eAddress;
        string += username;
        string += password;
        
        return string;
    }//End of method  
}//End of class

/**
 * @(#)UserGroup.java
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

public class UserGroup
{
	private String name;
	private boolean [] permissions;

    public UserGroup()
    {
    	name = "Default";
    	permissions = new boolean[5]; //canEdit, canChat, canExport, canFormat, canAccess
    }//End of default constructor

    public UserGroup(String name, boolean [] permissions)
    {
    	this.name = name;
    	this.permissions = permissions;
    }//End of object constructor


	/********************
	 * ACCESSOR METHODS *
	 ********************/

	/**
	 * Gets the value of name.
	 *
	 * @return The value of name.
	 */
	public String getName()
	{
		return this.name;
	}//End of getName method

	/**
	 * Gets the value of permissions.
	 *
	 * @return The value of permissions.
	 */
	public boolean [] getPermissions()
	{
		return this.permissions;
	}//End of isPermissions method

	/***************************
	 * END OF ACCESSOR METHODS *
	 ***************************/


	/*******************
	 * MUTATOR METHODS *
	 *******************/

	/**
	 * Sets the value of name.
	 *
	 * @param name [] The new value for name.
	 */
	public void setName(String name)
	{
		this.name = name;
	}//End of setName method

	/**
	 * Sets the value of permissions.
	 *
	 * @param permissions The new value for permissions.
	 */
	public void setPermissions(boolean [] permissions)
	{
		this.permissions = permissions;
	}//End of setPermissions method

	/**************************
	 * END OF MUTATOR METHODS *
	 **************************/
}//end of UserGroup Class
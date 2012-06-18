//Matthew Ufimzeff and Gurpreet Singh
//June 17

//Import Java API Objects
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.*;

public class TFileFilter extends FileFilter
{
	//Declare class fields and objects
	private String extension;

	//Initializes class fields and objects
	public TFileFilter()
	{
		extension = "";
	}//End of TFileFilter constructor method

    //Accept all directories and all .txt files
    public boolean accept(File file)
    {
        if (file.isDirectory())
        {
            return true;
        }

 		//Initialize a class field
        extension = Utils.getExtension(file);

        if(extension != null)
        {
            if(extension.equals(Utils.txt))
            	return true;
            else
                return false;
        }

        return false;
    }//End of accept method

    //The description of this filter
    public String getDescription()
    {
        return "All text files";
    }
}
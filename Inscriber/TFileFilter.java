//Matthew Ufimzeff and Gurpreet Singh
//June 17

//Import Java API Objects
import java.io.File;
import javax.swing.filechooser.FileFilter;

public class TFileFilter extends FileFilter
{
	//Declare class fields and objects

	//Initializes class fields and objects
	public TFileFilter()
	{

	}//End of TFileFilter constructor method

    //Accept all directories and all .txt files
    public boolean accept(File file)
    {
        if (file.isDirectory())
            return true;

        if(file.getName().toLowerCase().endsWith(".txt"))
        	return true;
		else
			return false;
    }//End of accept method

    //The description of this filter
    public String getDescription()
    {
        return "All *.txt files";
    }
}
import java.util.*;

public class Tester
{
    public static void main(String [] args)
    {

    	String imageName = "";
    	String type = "";
    	Scanner input = new Scanner(System.in);

    	System.out.println("Enter a filename: ");
    	imageName = input.next();

    	for(int x = 0; x < imageName.length(); x++)
    	{
    		if(imageName.charAt(x) == '.')
    		{
    			type = imageName.substring(x + 1, imageName.length());
				type = type.toLowerCase();

    			if(!type.equals("png"))
    			{
    				System.out.println("Error - File type not supported and/or not reconized. Please use PNG image types.");
    			}
    		}
    	}
    }
}
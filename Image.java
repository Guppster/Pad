//Import API objects
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.ImageIcon;

public class Image
{
	//Declare/Create and initialize instance variables and objects
	private BufferedImage tempImage;
	private BufferedImage image;
	private String imageType = "";
	private String iconType = "";
	private ImageIcon tempIcon = new ImageIcon();

    public Image(){}//End of constructor method

	//Load an image from file with the given filename, and check to make sure it is a PNG image file
    public void loadImage(String imageName)
    {
    	for(int x = 0; x < imageName.length(); x++)
    	{
    		if(imageName.charAt(x) == '.')
    		{
    			imageType = imageName.substring(x + 1, imageName.length());//Get the last few letters after the '.'
    		}
    	}

    	if(imageType.equalsIgnoreCase("png"))//Check if the last few letters after the '.' is 'png' or 'PNG'
    	{
    		//Load an image from file
	    	try
	    	{
	    		tempImage = ImageIO.read(new File(System.getProperty("user.dir") + "/Images/" + imageName));
	    	}
	    	catch(Exception e){System.out.println("Error - Could not load image.");}
    	}
    	else
    		System.out.println("Error - File type not supported and/or not reconized. Please use PNG image types.");
    }//End of loadImage method

	public void loadIcon(String iconName)
	{
		for(int x = 0; x < iconName.length(); x++)
    	{
    		if(iconName.charAt(x) == '.')
    		{
    			iconType = iconName.substring(x + 1, iconName.length());//Get the last few letters after the '.'
    		}
    	}

    	if(iconType.equalsIgnoreCase("png"))//Check if the last few letters after the '.' is 'png' or 'PNG'
    	{
    		//Load an image from file
	    	try
	    	{
				ImageIcon loadedIcon = new ImageIcon("Images/" + iconName);
				tempIcon = loadedIcon;

	    	}
	    	catch(Exception e){System.out.println("Error - Could not load image.");}
    	}
    	else
    		System.out.println("Error - File type not supported and/or not reconized. Please use PNG image types.");
	}

	//Return the image that was loaded
    public BufferedImage getImage()
    {
		//Pass the temp image to the image we will return, so we can reset the temp image to null so we can load another image
		image = tempImage;

    	if(image != null)
    	{
    		tempImage = null;
    		return image;
    	}
    	else
    	{
    		System.out.println("Sorry! You must load an image before getting it!");
    		return null;
    	}
    }//End of getImage method

    public ImageIcon getIcon()
    {
    	ImageIcon imageIcon = new ImageIcon();

    	//Transfer the image to the other instance variable so we can reset the the temp for loading another image
    	imageIcon = tempIcon;

    	if(tempIcon != null)
    	{
    		tempIcon = null;
    		return imageIcon;
    	}
    	else
    	{
    		System.out.println("Sorry! You must load an image before getting it!");
    		return null;
    	}
    }//End of getIco method
}//End of class
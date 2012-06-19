/**
 * @(#)Database.java
 *
 * @Description
 *
 * @author Gurpreet Singh, Matt Ufimzeff
 * @version 1.00 2012/6/15
 *
 * @Latest Updates:
 *
 * @Status: InComplete
 */

 //Import Jsva API packages
import java.awt.*;
import java.net.*;
import java.io.*;

public class Document
{
	//Declare class fields and objects
	private Socket sClient;
    private PrintWriter out;
    private ErrorHandler eHandler;

	//Initializes class fields and objects
    public Document()
    {
    	//Initialize class fields and objects
		sClient = null;
		out = null;
		eHandler = new ErrorHandler();
    }//End of Document default constructor

	//Gets whatever is in the JTextArea on WritingMainBoard, and sends it to the server
	public void saveFileToServer(String filename, String text, Socket socket)
	{
		//Initialize a class object
		sClient = socket;

		//Try writing a line of text to the server
    	try
    	{
    		//Initialize a class object
    		out = new PrintWriter(socket.getOutputStream(), true);

	    	//Send the server the filename
	    	out.println(filename);

	    	//Send the server one line of text from the WritingMainBoard JTextArea
	    	out.println(text);
    	}catch(IOException exc1){eHandler.displayError("CNSTTS");}

    	//Try closing the writer and the socket conenction
    	try
    	{
    		out.close();
    		sClient.close();
    	}catch(IOException exc2){eHandler.displayError("CNCC");}
	}//End of saveFile method

	//Retrieves a text file from the server and 'opens it'
	public void getFileFromServer(Socket socket)throws IOException
	{
		/*sClient = socket;

	    //Create a read object
		PrintStream ps = new PrintStream(sClient.getOutputStream());

		//Initialize read object
		input = new BufferedReader(new FileReader(fileToGet.getFileName()));

		//Read in the text and count the number of lines
		while (input.readLine() != null)
		{
			stringToSend += input.readLine() + "\n";
		}

		ps.out(stringToSend);

		input.close();*/
	}//End of countLines method
}//End of Document Class
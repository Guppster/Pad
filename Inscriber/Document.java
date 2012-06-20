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
import java.net.*;
import java.io.*;

public class Document
{
	//Declare class fields and objects
	private Socket sClient;
    private PrintWriter out;
    private ErrorHandler eHandler;
    private String fileName;
	private int numOfWords;
	private int numOfSentences;
	private int numOfCharacters;

	//Initializes class fields and objects
    public Document()
    {
    	//Initialize class fields and objects
    	fileName = "";
		numOfWords = 0;
		numOfSentences = 0;
		numOfCharacters = 0;
		sClient = null;
		out = null;
		eHandler = new ErrorHandler();
    }//End of Document default constructor

	//Sends the passed in filename and text from the passed in connection, to the server
	public void saveFileToServer(String author, String fileName, String textFromWritingMain, Socket socket)
	{
		//Initialize class fields
		sClient = socket;
		this.fileName = fileName;
		this.author = author;
		text = textFromWritingMain;
		in = new BufferedReader(new InputStreamReader(sClient.getInputStream()));
    	out = new PrintWriter(sClient.getOutputStream(), true);

		//Send the text we are recieving from WritingMainBoard to the server to be saved
    	try
    	{
    		//Send the server the filename
    		out.println(author)
	    	out.println(filename);

	    	//Send the server one line of text from the WritingMainBoard JTextArea
	    	out.println(text);
    	}catch(IOException exc1){eHandler.displayError("CNSTTS");}

    	//Try closing the writer and the socket conenction and reset class fields
    	try
    	{
    		out.close();
    		sClient.close();
    		text = "";
    		sClient = null;
    	}catch(IOException exc2){eHandler.displayError("CNCC");}
	}//End of saveFileToServer method

	//Retrieves a text file from the server and 'opens it'
	public void getFileFromServer(Socket socket)throws IOException
	{
		//Initialize class fields
		sClient = socket;
    	in = new BufferedReader(new InputStreamReader(sClient.getInputStream()));

		//While there is text coming in
    	while((text = in.readLine()) != null)
    	{
     		writer.write(text);
		}

		in.close();
		sClient.close();
	}//End of countLines method
}//End of Document Class
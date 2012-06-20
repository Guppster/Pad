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
	private BufferedReader in;
    private PrintWriter out;
    private ErrorHandler eHandler;
    private String fileName;
    private String author;
    private String text;
	private int numOfWords;
	private int numOfSentences;
	private int numOfCharacters;

	//Initializes class fields and objects
    public Document()
    {
    	//Initialize class fields and objects
    	sClient = null;
    	in = null;
    	out = null;
    	eHandler = new ErrorHandler();
    	fileName = "";
    	author = "";
    	text = "";
		numOfWords = 0;
		numOfSentences = 0;
		numOfCharacters = 0;
    }//End of Document constructor

	//Sends text to the server to be compiled and saved
	public void saveFileToServer(String author, String fileName, String textFromWritingMain, Socket socket)
	{
		//Initialize class fields
		sClient = socket;
		this.fileName = fileName;
		this.author = author;
		text = textFromWritingMain;

    	try
    	{
    		out = new PrintWriter(sClient.getOutputStream(), true);
    	}catch(IOException e){System.out.println("Error - " + e);}

		//Send the server the author of the file, and the filename
		out.println(author);
    	out.println(fileName);

    	//Send the server the text we got from WritingMainBoard
    	while(!(textFromWritingMain.equals("")))
    	{
    		out.println(text);
    	}

    	//Try closing the writer and the socket conenction and reset class fields
    	try
    	{
    		text = "";
    		this.author = "";
    		this.fileName = "";
    		sClient = null;
    		out.close();
    		sClient.close();
    	}catch(IOException e2){eHandler.displayError("CNCC");}
	}//End of saveFileToServer method

	//Retrieves a text file from the server and 'opens it'
	public String getFileFromServer(Socket socket)
	{
		//Initialize class fields
		sClient = socket;

    	try
    	{
    		in = new BufferedReader(new InputStreamReader(sClient.getInputStream()));
    	}catch(IOException e){System.out.println("Error 2 - " + e);}

		//While there is text coming in
    	while(!((text = in.readLine()).equals("")))
    	{
			textFromServer += text;
		}

		in.close();
		sClient.close();

		return textFromServer;
	}//End of countLines method
}//End of Document Class
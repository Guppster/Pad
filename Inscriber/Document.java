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
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.sql.*;
import java.io.*;
import java.util.*;

public class Document
{
	//Declare class fields and objects
	private String docName;
	private Socket sClient;
	private int numOfWords;
	private int numOfSentences;
	private int numOfCharacters;
	private DataInputStream in;
    private DataOutputStream out;
    private FileInputStream fis;
    private BufferedReader reader;

	//Default constructor
    public Document()
    {
		numOfWords = 0;
		numOfSentences = 0;
		numOfCharacters = 0;
		sClient = null;
		in = null;
		out = null;
		fis = null;
    }//End of Document default constructor

	//Initializes class fields and objects
    public Document(String docName) throws IOException
	{
		//readInFromFile(docName);
		//countNumWords();
		//countNumSentences();
		//countNumCharacters();
		//saveFile(docName);
		sClient = null;
		in = null;
		out = null;
		fis = null;
	}//End of Document constructor method

	//Gets whatever is in the JTextArea on WritingMainBoard, and sends it to the server
	public void saveFileToServer(String text, Socket socket)
	{
		//Initialize a class object
		sClient = socket;

		out = new BufferedWriter(new FileWriter())
		PrintStream ps = new PrintStream(sClient.getOutputStream());


	}//End of saveFile method

	public void getFileFromServer(Socket socket)throws IOException
	{
		sClient = socket;

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

		input.close();
	}//End of countLines method
}//End of Document Class
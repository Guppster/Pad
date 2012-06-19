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
	private Socket sClient;
    private PrintWriter out;

	//Initializes class fields and objects
    public Document()
    {
    	//Initialize class fields and objects
		sClient = null;
		out = null;
    }//End of Document default constructor

	//Gets whatever is in the JTextArea on WritingMainBoard, and sends it to the server
	public void saveFileToServer(String filename, String text, Socket socket)
	{
		//Initialize class objects
		sClient = socket;
		out = new PrintWriter(new BufferedWriter(new FileWriter(filename)));

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
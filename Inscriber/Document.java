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
 * @Status: In-Complete
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
	private int numOfWords;
	private int numOfSentences;
	private int numOfCharacters;

	//Default constructor
    public Document()
    {
		//docName = "NewDocument.txt";
		numOfWords = 0;
		numOfSentences = 0;
		numOfCharacters = 0;
    }//End of Document default constructor

	//Initializes class fields and objects
    public Document(String docName)
	{
		//readInFromFile(docName);
		//countNumWords();
		//countNumSentences();
		//countNumCharacters();
	}//End of Document constructor method

	//Gets whatever is in the JTextArea on WritingMainBoard, and writes it to a file
	public void saveFile(String text) throws IOException
	{
		//Declare class fields and objects
		JFileChooser fileChooser = new JFileChooser();
		FileFilter fileFilter = (FileFilter)new TFileFilter();
		fileChooser.addChoosableFileFilter(fileFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		Writer output = null;
		int intVar = fileChooser.showSaveDialog(null);

		// let the user choose the destination file
		if (intVar == JFileChooser.APPROVE_OPTION)
		{
			File file = fileChooser.getSelectedFile();

			output = new BufferedWriter(new FileWriter(file));
			output.write(text);
			output.close();

		}

		/* if (doExport)
		    {
		        output = new BufferedWriter(new FileWriter(file));
				output.write(text);
				output.close();
		    }*/
	}//End of saveFile method

	//Uploads the file to the Server
	//private void uploadToServer(File file)
	//{

	//}//End of uploadToServer method
}//End of Document Class
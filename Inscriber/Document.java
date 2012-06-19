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

	//Gets whatever is in the JTextArea on WritingMainBoard, and writes it to a file
	public void saveFile(String text) throws IOException
	{
		//Declare class fields and objects
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new TFileFilter());
		fileChooser.setAcceptAllFileFilterUsed(false);
		int code = fileChooser.showSaveDialog(null);

		// let the user choose the destination file
		if (code == JFileChooser.APPROVE_OPTION)
		{
			File file = fileChooser.getSelectedFile();
			saveToServer(file);
		}
	}//End of saveFile method

	public void getFileFromServer(File fileToGet, Socket socket)throws IOException
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

	    //Read in some data
	    do
	    {
	       	numBytesRead = is.read(dataArray, 0, dataArray.length);
	    } while(numBytesRead > -1);

	    bos.write(dataArray, 0 , dataArray.length);
	    bos.flush();
	    bos.close();
	    sClient.close();
	}

	//Uploads the file to the Server
	private void saveToServer(File fileToSave, Socket socket)throws Exception
	{
		sClient = socket;
		in = new DataInputStream(new BufferedInputStream(sClient.getInputStream()));
        out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

        try
        {
            File file = fileToSave;

            byte[] buf=new byte[512];
            int len;

            String action = in.readUTF().trim();

            Long fileLong = new Long(files[i].length());
            out.writeUTF("get");
            out.writeLong(fileLong.longValue());
            out.flush();

            // send the filename
            out.writeUTF(files[i].getName());

            out.writeLong(files[i].length());
            fis=new FileInputStream(files[i]);

            while((len=fis.read(buf))!=-1)
            {
                out.write(buf,0,len);
            }

            out.flush();
        }catch (IOException e){e.printStackTrace();}
        sClient = null;
	}//End of uploadToServer method

    protected void finalize()
    {
        //Clean up
        try
        {
            fis.close();
            out.close();
            in.close();
            socket.close();
        }
        catch (IOException e)
        {
            System.out.println("Could not close.");
            System.exit(-1);
        }
    }
}//End of Document Class
//By Matthew Ufimzeff and Gurpreet Singh

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.image.*;
import java.text.*;
import java.io.*;
import javax.swing.text.*;  
import javax.swing.JMenu;
import javax.swing.TransferHandler;
import java.awt.datatransfer.Clipboard;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.Timer;  
import java.awt.font.*;

public class LoadUpdates
{
	//Declare variables
	private String[] updates;

    public LoadUpdates(){}//End of constructor method

    private void recieveUpdates()
    {
    	int SOMENUMBER = 1;
    	//Get the updates information from the database
    	updates = new String[SOMENUMBER];
    	//setUpdates(SOMEDATA);
    }//End of recieveUpdates method

    private void setUpdates(String somedata)
    {
    	for(int x = 0; x < updates.length; x++)
    	{
    		updates[x] = somedata;
    	}
    }//End of setUpdates method

    //Return the updates
    public String[] getUpdates()
    {
    	return updates;
    }//End of getUpdates method
}//End of LoadUpdates class
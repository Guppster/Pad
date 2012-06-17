/**
 * @(#)TableHelper.java
 *
 *
 * @author
 * @version 1.00 2012/6/11
 */

//Import API packages
import javax.swing.*;
import java.awt.event.*;
import java.text.*;
import java.awt.*;
import javax.swing.table.*;

public class TableHelper
{
	private int rowIndex;
    private int columnIndex;
    private int selectedRowIndex;
    private int selectedColumnIndex;
    private boolean empty;

    public TableHelper()
    {
    	rowIndex = 0;
	 	columnIndex = 0;
	 	selectedRowIndex = 0;
	 	selectedColumnIndex = 0;
	 	empty = false;
    }//End of default constructor

    //Adds a word to the word filter JTable
	public void addElement(JTable table, DefaultTableModel model, String data)
	{
		//Check if there is a empty cell to add the word
		nextEmpty(table, model);

		//Check that there is an empty spot, and we haven't reached the end of the table
		table.setValueAt(data, rowIndex , columnIndex);

		//Reset the coordinates variables
		rowIndex = 0;
		columnIndex = 0;
	}//End of addWord method

	//Removes a word from the word filter JTable
	public void removeSelectedElement(JTable table)
	{
		//Get the coordinates of the cell that is selected
		selectedRowIndex = table.getSelectedRow();
		selectedColumnIndex = table.getSelectedColumn();

		//Remove the word at the selected cell coordinates
		table.setValueAt(null, selectedRowIndex, selectedColumnIndex);
	}//End of removeWord method

    //A helper method to check for the next available empty cell
	private void nextEmpty(JTable table, DefaultTableModel model)
	{
		//Reset the boolean test variable
		empty = false;

		//Loop through the JTable object and search for a empty cell to fill data with, if one is found, record the coordinates
		for(int x = 0; x < table.getRowCount(); x++)
		{
			for(int y = 0; y < table.getColumnCount(); y++)
			{
				if(table.getValueAt(x, y) == null)
				{
					rowIndex = x;
					columnIndex = y;
					empty = true;
					break;
				}
			}
			if(empty)
				break;
		}

		//If there is no empty spot found, there must be no more room to put data, so insert a new row at the end of the JTable object, and recheck for an empty spot
		if(!empty)
		{
			model.insertRow(table.getRowCount(),new Object[]{});
			nextEmpty(table, model);
		}
	}//End of nextEmpty method

	/*//A method to fill an array with the data from the JTable so we can send it to the server
	private void loadArray(JTable table)
	{
		data = new Object[getArrayLength(table)];

		//Loop through the JTable object and search for a empty cell to fill data with, if one is found, record the coordinates
		for(int x = 0; x < table.getRowCount(); x++)
		{
			for(int y = 0; y < table.getColumnCount(); y++)
			{
				if(!(table.getValueAt(x, y) == null))
				{
					//Array at[the row number we are on * the length of the row(How many columns we have) + the current column we are on]
					data[x * table.getColumnCount() + y] = table.getValueAt(x,y);
				}
			}
		}
	}//End of loadArray method

	//A helper method to calculate the length of the array to hold the JTable data
	private int getArrayLength(JTable table)
	{
		//Declare a variable to hold the length of the array we will be returning
		int arrayLength = 0;

		//Loop through the JTable object and search for a empty cell to fill data with, if one is found, record the coordinates
		for(int x = 0; x < table.getRowCount(); x++)
		{
			for(int y = 0; y < table.getColumnCount(); y++)
			{
				if(!(table.getValueAt(x, y) == null))
				{
					arrayLength++;
				}
			}
		}

	}//End of getArrayLength Method*/
}//End of class
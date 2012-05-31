//Import the API packages being used
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.image.*;
import java.text.*;
import java.io.*;
import javax.swing.text.*;  
import java.awt.font.*;
import javax.swing.DefaultListModel;
import javax.swing.JList;

public class DocumentOptions extends JFrame implements ActionListener
{
	//Create the content pane
	JPanel oPane = new JPanel();
	
	//Create the combobox and a label
	JLabel lblFonts = new JLabel("Choose the fonts available in the Document");
	JComboBox cboFonts = new JComboBox();
	JLabel lblSizes = new JLabel("Choose the font sizes available in the Document");
	JComboBox cboSizes = new JComboBox();
	
	//Create model list and list to store the combobox selections
	DefaultListModel fontModel;
	JList fontList;
	DefaultListModel sizeModel;
	JList sizeList;
    
    JButton addFontEle = new JButton("Add");
    JButton removeFontEle = new JButton("Remove");
    
    JButton addSizeEle = new JButton("Add");
    JButton removeSizeEle = new JButton("Remove");
    
	public DocumentOptions()
    {   
    	//Create the listmodel object(We add 'items' here)
    	fontModel = new DefaultListModel();
    	sizeModel = new DefaultListModel();
    	
    	//Create the list object and attach the listmodel to it
    	fontList = new JList(fontModel);
    	fontList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        fontList.setFixedCellHeight(17);
        fontList.setFixedCellWidth(150);
        
        sizeList = new JList(sizeModel);
    	sizeList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        sizeList.setFixedCellHeight(17);
        sizeList.setFixedCellWidth(150);
    	
    	//Create the scrollpane so we can add the list to it
    	JScrollPane fontScrollPane = new JScrollPane(fontList);
    	JScrollPane sizeScrollPane = new JScrollPane(sizeList);
    	
    	//Set the dialog box's contentpane
    	this.setContentPane(oPane);
    	
    	//Create an instance of the spring layout manager
    	SpringLayout layout = new SpringLayout();
    	
    	//Set the panels layout to the spring layout we created earlier
    	oPane.setLayout(layout);
    	
    	//Add the actionlistener to the button and set an action command for it
    	addFontEle.addActionListener(this);
    	addFontEle.setActionCommand("fontAdd");
    	removeFontEle.addActionListener(this);
    	removeFontEle.setActionCommand("fontRemove");
    	addSizeEle.addActionListener(this);
    	addSizeEle.setActionCommand("sizeAdd");
    	removeSizeEle.addActionListener(this);
    	removeSizeEle.setActionCommand("sizeRemove");

    	//Add the objects to the conentpane(JPanel) 	                    
    	oPane.add(lblFonts);
    	oPane.add(cboFonts);
    	oPane.add(cboSizes);
		oPane.add(fontScrollPane);
		oPane.add(sizeScrollPane);
    	oPane.add(addFontEle);
    	oPane.add(removeFontEle);
    	oPane.add(addSizeEle);
    	oPane.add(removeSizeEle);
    	
    	//Setup the stuff for the font combo box	
    	cboFonts.addItem("");
    	cboFonts.addItem("Times New Roman");
    	cboFonts.addItem("Arial");
    	cboFonts.addItem("Arial Black");	
    	cboFonts.addItem("Arial Narrow");
    	cboFonts.addItem("Tahoma");
    	cboFonts.addItem("Verdana");
    	cboFonts.addItem("Comic Sans MS");
    	cboFonts.addItem("All");
    	
    	//Setup the stuff for the font size combo box
    	cboSizes.addItem("");
    	cboSizes.addItem("10");
    	cboSizes.addItem("12");
    	cboSizes.addItem("14");	
    	cboSizes.addItem("16");
    	cboSizes.addItem("18");
    	cboSizes.addItem("20");
    	cboSizes.addItem("22");
    	cboSizes.addItem("24");
    	cboSizes.addItem("26");
    	cboSizes.addItem("28");
    	cboSizes.addItem("30");
    	cboSizes.addItem("32");
    	cboSizes.addItem("All");
    	
    	//Set the position of the objects on the dialog box		
    	layout.putConstraint(SpringLayout.WEST, lblFonts, 10, SpringLayout.WEST, oPane);
		layout.putConstraint(SpringLayout.NORTH, lblFonts, 10, SpringLayout.NORTH, oPane);
		
		layout.putConstraint(SpringLayout.WEST, cboFonts, 10, SpringLayout.WEST, oPane);
		layout.putConstraint(SpringLayout.NORTH, cboFonts, 35, SpringLayout.NORTH, oPane);
		
		layout.putConstraint(SpringLayout.WEST, cboSizes, 10, SpringLayout.WEST, oPane);
		layout.putConstraint(SpringLayout.NORTH, cboSizes, 215, SpringLayout.NORTH, oPane);
		
		layout.putConstraint(SpringLayout.WEST, fontScrollPane, 400, SpringLayout.WEST, oPane);
		layout.putConstraint(SpringLayout.NORTH, fontScrollPane, 10, SpringLayout.NORTH, oPane);
		
		layout.putConstraint(SpringLayout.WEST, sizeScrollPane, 400, SpringLayout.WEST, oPane);
		layout.putConstraint(SpringLayout.NORTH, sizeScrollPane, 200, SpringLayout.NORTH, oPane);
		
		layout.putConstraint(SpringLayout.WEST, addFontEle, 150, SpringLayout.WEST, oPane);
		layout.putConstraint(SpringLayout.NORTH, addFontEle, 35, SpringLayout.NORTH, oPane);
		
		layout.putConstraint(SpringLayout.WEST, removeFontEle, 210, SpringLayout.WEST, oPane);
		layout.putConstraint(SpringLayout.NORTH, removeFontEle, 35, SpringLayout.NORTH, oPane);
		
		layout.putConstraint(SpringLayout.WEST, addSizeEle, 150, SpringLayout.WEST, oPane);
		layout.putConstraint(SpringLayout.NORTH, addSizeEle, 215, SpringLayout.NORTH, oPane);
		
		layout.putConstraint(SpringLayout.WEST, removeSizeEle, 210, SpringLayout.WEST, oPane);
		layout.putConstraint(SpringLayout.NORTH, removeSizeEle, 215, SpringLayout.NORTH, oPane);
		
		//Set the dialog box parameters
		this.setAlwaysOnTop(true);	        
    	this.setSize(800, 400);
    	this.setLocationRelativeTo(null);
    	this.setVisible(true);
    	this.setResizable(false);
    	this.setTitle("Document Options");
    }//End of constructor method
    
    public void actionPerformed(ActionEvent evt) 
    {
    	int fontListIndex = fontList.getSelectedIndex();
    	int sizeListIndex = sizeList.getSelectedIndex();
    	int fontComboIndex = cboFonts.getSelectedIndex();
    	int sizeComboIndex = cboSizes.getSelectedIndex();
    	   	
    	if(evt.getActionCommand().equals("fontAdd"))
    	{	
    		if(cboFonts.getSelectedItem() == "All")
    		{
    			//Add all the fonts available
    			fontModel.addElement(cboFonts.getSelectedItem());
    		}
    		else
    		{
    			//Add the fonts selected to the scrollpane
    			fontModel.addElement(cboFonts.getSelectedItem());
    		}
    	}
    	else if(evt.getActionCommand().equals("fontRemove"))
    	{
    		if(fontListIndex >= 0)
    			fontModel.remove(fontListIndex);
    		else
    			System.out.println("Nothing Selected!");
    	}
    	else if(evt.getActionCommand().equals("sizeAdd"))
    	{	
    		if(cboSizes.getSelectedItem() == "All")
    		{
    			//Add all the fonts available
    			sizeModel.addElement(cboSizes.getSelectedItem());
    		}
    		else
    		{
    			//Add the fonts selected to the scrollpane
    			sizeModel.addElement(cboSizes.getSelectedItem());
    		}
    	}
    	else if(evt.getActionCommand().equals("sizeRemove"))
    	{
    		if(sizeListIndex >= 0)
    			sizeModel.remove(sizeListIndex);
    		else
    			System.out.println("Nothing Selected!");
    	}
    	
    	//Disable the added fonts in the combobox
    	if(cboFonts.getSelectedItem().equals("Times New Roman"))
    	{
    		fontModel.remove(fontComboIndex);
    		System.out.println(fontComboIndex);
    		System.out.println(fontModel);
    	}		
   	}//End of action performed method  
}
/**
 * @(#)DocumentOptions.java
 *
 * @Description
 *
 * @author Gurpreet Singh, Matt Ufimsef
 * @version 1.00 2012/6/15
 *
 * @Latest Updates:
 *
 * @Status: Complete ~ accomodating for currently created classes
 */
//Import API packages
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

public class DocumentOptions extends JFrame implements ActionListener
{
	//Declare class objects and fields
	private JComboBox<String> cboFonts;
	private JComboBox<String> cboSizes;
	private DefaultTableModel fontsModel;
	private DefaultTableModel sizesModel;
	private JTable tFonts;
	private JTable tSizes;
	private JScrollPane spFonts;
	private JScrollPane spSizes;
	private JButton cmdAddFont;
	private JButton cmdRemoveFont;
	private JButton cmdAddSize;
	private JButton cmdRemoveSize;
	private JButton cmdFilter;
	private SpringLayout layout;
	private TableHelper tHelper;

    public DocumentOptions()
    {
    	//Initialize the class objects and fields
    	cboFonts = new JComboBox<String>();
    	cboSizes = new JComboBox<String>();
    	fontsModel = new DefaultTableModel();
    	sizesModel = new DefaultTableModel();
    	tFonts = new JTable(fontsModel);
    	tSizes = new JTable(sizesModel);
    	spFonts = new JScrollPane(tFonts);
    	spSizes = new JScrollPane(tSizes);
    	cmdAddFont = new JButton("Add font");
    	cmdRemoveFont = new JButton("Remove font");
    	cmdAddSize = new JButton("Add font size");
    	cmdRemoveSize = new JButton("Remove font size");
    	cmdFilter = new JButton("Word Filter");

    	layout = new SpringLayout();

    	//Call the setGUI method to construct all the GUI components
    	setGUI();
    }//End of DocumentOptions constructor method


	private void setGUI()
    {
    	//Add 4 columns to the JTable objects
		for(int x = 0; x < 4; x++)
		{
			fontsModel.addColumn(new Object[]{});
			sizesModel.addColumn(new Object[]{});
		}

		//Add 6 rows to the JTable objects
		for(int y = 0; y < 6; y++)
		{
			fontsModel.addRow(new Object[]{});
			sizesModel.addRow(new Object[]{});
		}

		//Set action commands for the JButton objects
		cmdAddFont.setActionCommand("addFont");
		cmdRemoveFont.setActionCommand("removeFont");
		cmdAddSize.setActionCommand("addFontSize");
		cmdRemoveSize.setActionCommand("removeFontSize");
		cmdFilter.setActionCommand("filter");

		cmdFilter.addActionListener(this);
		cmdAddFont.addActionListener(this);
		cmdRemoveFont.addActionListener(this);
		cmdAddSize.addActionListener(this);
		cmdRemoveSize.addActionListener(this);

    	//Set some properties for the JTable objects
    	tFonts.setTableHeader(null);
	 	tFonts.setCellSelectionEnabled(true);
	 	tFonts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	 	tSizes.setTableHeader(null);
	 	tSizes.setCellSelectionEnabled(true);
	 	tSizes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	 	spFonts.setPreferredSize(new Dimension(350, 98));
	 	spSizes.setPreferredSize(new Dimension(350, 98));

    	//Setup the stuff for the font combo box
    	cboFonts.addItem("Choose the fonts available to the document");
    	cboFonts.addItem("Times New Roman");
    	cboFonts.addItem("Arial");
    	cboFonts.addItem("Arial Black");
    	cboFonts.addItem("Arial Narrow");
    	cboFonts.addItem("Tahoma");
    	cboFonts.addItem("Verdana");
    	cboFonts.addItem("Comic Sans MS");
    	cboFonts.addItem("All");

    	//Setup the stuff for the font size combo box
    	cboSizes.addItem("Choose the font sizes available to the document");
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

		//Set the layout manager for the GUI frame
		this.getContentPane().setLayout(layout);

    	//Set the position of the objects on the dialog box
    	this.add(cboFonts);
    	layout.putConstraint(SpringLayout.WEST, cboFonts, 10, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, cboFonts, 10, SpringLayout.NORTH, this.getContentPane());

    	this.add(cboSizes);
    	layout.putConstraint(SpringLayout.WEST, cboSizes, 10, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, cboSizes, 150, SpringLayout.NORTH, this.getContentPane());

  		this.add(spFonts);
		layout.putConstraint(SpringLayout.WEST, spFonts, 400, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, spFonts, 10, SpringLayout.NORTH, this.getContentPane());

		this.add(spSizes);
		layout.putConstraint(SpringLayout.WEST, spSizes, 400, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, spSizes, 150, SpringLayout.NORTH, this.getContentPane());

		this.add(cmdAddFont);
		layout.putConstraint(SpringLayout.WEST, cmdAddFont, 110, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, cmdAddFont, 50, SpringLayout.NORTH, this.getContentPane());

		this.add(cmdRemoveFont);
		layout.putConstraint(SpringLayout.WEST, cmdRemoveFont, 200, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, cmdRemoveFont, 50, SpringLayout.NORTH, this.getContentPane());

		this.add(cmdAddSize);
		layout.putConstraint(SpringLayout.WEST, cmdAddSize, 95, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, cmdAddSize, 190, SpringLayout.NORTH, this.getContentPane());

		this.add(cmdRemoveSize);
		layout.putConstraint(SpringLayout.WEST, cmdRemoveSize, 210, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, cmdRemoveSize, 190, SpringLayout.NORTH, this.getContentPane());

		this.add(cmdFilter);
    	layout.putConstraint(SpringLayout.WEST, cmdFilter, 10, SpringLayout.WEST, this.getContentPane());
		layout.putConstraint(SpringLayout.NORTH, cmdFilter, 350, SpringLayout.NORTH, this.getContentPane());

		//Set the dialog box parameters
    	this.setSize(775, 425);
    	this.setLocationRelativeTo(null);
    	this.setTitle("Inscriber Document Options");
    	this.setVisible(true);
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }//End of setGUI method

	//Perform an action whenever a JButton object is triggered
    public void actionPerformed(ActionEvent evt)
    {
    	if("addFont".equals(evt.getActionCommand()))
		{
			tHelper.addElement(tFonts, fontsModel, (String)cboFonts.getSelectedItem());
		}
    	else if("removeFont".equals(evt.getActionCommand()))
		{
    		tHelper.removeSelectedElement(tFonts);
		}
		else if("addFontSize".equals(evt.getActionCommand()))
		{
			tHelper.addElement(tSizes, sizesModel, (String)cboSizes.getSelectedItem());
		}
		else if("removeFontSize".equals(evt.getActionCommand()))
		{
			tHelper.removeSelectedElement(tSizes);
		}
		else if("filter".equals(evt.getActionCommand()))
		{
			new WordFilter();
		}
   	}//End of action performed method

   	//Test harness
   	public static void main(String [] args)
   	{
   		new DocumentOptions();
   	}
}//End of DocumentOptions class
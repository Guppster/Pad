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
		docName = "NewDocument.txt";
		numOfWords = 0;
		numOfSentences = 0;
		numOfCharacters = 0;
    }//End of Document default constructor

	//Initializes class fields and objects
    public Document(String docName)
	{
		readInFromFile(docName);
		countNumWords();
		countNumSentences();
		countNumCharacters();
	}//End of Document constructor method

	//Gets whatever is in the JTextArea on WritingMainBoard, and writes it to a file
	public void saveFile(String text, String filename) throws IOException
	{
		//Declare class fields and objects
		JFileChooser fileChooser = new JFileChooser();
		Writer output = null;
		File file = new File("write.txt");

		// let the user choose the destination file
		if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
		{
		    // indicates whether the user still wants to export the settings
		    boolean doExport = true;

		    // indicates whether to override an already existing file
		    boolean overrideExistingFile = false;

		    // get destination file
		    File destinationFile = new File(fileChooser.getSelectedFile().getAbsolutePath());

		    // check if file already exists
		    while (doExport && destinationFile.exists() && !overrideExistingFile) {
		        // let the user decide whether to override the existing file
		        overrideExistingFile = (JOptionPane.showConfirmDialog(this, "Replace file?", "Export Settings", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);

		        // let the user choose another file if the existing file shall not be overridden
		        if (!overrideExistingFile) {
		            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
		                // get new destination file
		                destinationFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
		            } else {
		                // seems like the user does not want to export the settings any longer
		                doExport = false;
		            }
		        }
		    }

		    // perform the actual export
		    if (doExport)
		    {
		        output = new BufferedWriter(new FileWriter(file));
				output.write(text);
				output.close();
		    }
		}
	}//End of saveFile method

	//Uploads the file to the Server
	//private void uploadToServer(File file)
	//{

	//}//End of uploadToServer method
}//End of Document Class
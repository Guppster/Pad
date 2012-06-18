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
	//Object Fields
	private String docName;
	private int numOfWords;
	private int numOfSentences;
	private int numOfCharacters;

	//Default Constructor
    public Document()
    {
		docName = "New Document";
		numOfWords = 0;
		numOfSentences = 0;
		numOfCharacters = 0;
    }//End of default constructor

    public Document(String docName)
	{
		readInFromFile(docName);
		countNumWords();
		countNumSentences();
		countNumCharacters();
	}//End of Object Constructor

}
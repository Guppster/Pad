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

}//End of Document Class
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Scanner;

public class CountWords {
	
	private ArrayList<String> aryList;
	private String inputFile;
	private String outputFile;
	
	/**
	 * Default Constructor
	 */
	public CountWords() {
		inputFile = null;
		outputFile = null;
		aryList = new ArrayList<String>();
	}
	
	//Setters
	/**
	 * Sets the input file for processing
	 * 
	 * @param file	Used for processing
	 */
	public void setInFile(String file) {
		inputFile = file;
	}
	
	/**
	 * Sets the output file for printing
	 * 
	 * @param file	Used to print results
	 */
	public void setOutFile(String file) {
		outputFile = file;
	}
	
	/**
	 * Set list to process
	 * 
	 * @param myAry	List for processing
	 */
	public void setAryList(ArrayList<String> myAry) {
		aryList = myAry;
	}
	
	//Getters
	/**
	 * Returns output file
	 * 
	 * @return	Output file
	 */
	public String getOutFile() {return outputFile;}
	
	/**
	 * Returns input file
	 * 
	 * @return	Input file
	 */
	public String getInFile() {return inputFile;}
	
	/**
	 * Returns array list
	 * 
	 * @return	Array List
	 */
	public ArrayList<String> getAryList() {return aryList;}
	
	/**
	 * Tests if the file exists
	 * 
	 * @return	True if file exists, false otherwise
	 */
	public boolean fileTester(String aFile) {
		
		try {
			
			FileInputStream someStream = new FileInputStream(aFile);
			someStream.close();
			return true;
			
		} catch(Exception e) {
			return false;
		}
		
	}
	
	/**
	 * Processes file and prints out results in columns of four
	 * 
	 * @throws FileNotFoundException	If the file does not exist 
	 * or a new file can not be created
	 */
	public void processFile() throws FileNotFoundException {
		
		aryListConvert();	//Convert file to array list
				
		printResults();	//Printing results
	
	}
	
	/**
	 * 
	 * @throws FileNotFoundException	If the file does not exist 
	 * or a new file can not be created
	 */
	private void aryListConvert() throws FileNotFoundException {
		
		FileInputStream myStream = null;	//Used to stream in words
		Scanner myScan = null;				//Used to get internal methods
		
		myStream = new FileInputStream(inputFile);	//Inputing words into program
		myScan = new Scanner(myStream);	//Setting myScan with myStream
		
		String aWord;
		
		while(myScan.hasNext()) {
			
			aWord = myScan.next();	//Set aWord to next word in list
			
			if (!isVerseNum(aWord)) { //Tests of aWord is a verse
				
				aWord = removePuct(aWord);	//Removes punctuation
				aWord = aWord.toUpperCase();	//Sets all letters in word to upper case
				
				if (!aWord.isEmpty() && !aryList.contains(aWord))
					aryList.add(aWord);	//Adding word to list
				
			} //Not VerseNum
		
		}//Scanner Loop
		
		aryList.sort(null);
		myScan.close();
		
		System.out.format("\nThe total word count is: %d", 
				aryList.size());	//Printing number of words in list
		
		System.out.println("\n\nDone processing Text File");
		
	}
	
	/**
	 * Tests if word is a verse reference
	 * 
	 * @param word	String used to test if it is a verse reference
	 * @return		Boolean if word is a verse reference or not
	 */
	private boolean isVerseNum(String word) {
		
		char firstChar = word.charAt(0);	// First character of word
		char lastChar = word.charAt(word.length() - 1);	// Last character of word
		
		// If word starts and ends with a number, and contains a colon
		// It is a verse reference
		if (isNum(firstChar) && word.contains(":") && isNum(lastChar)) {
			return true;
		}
		
		// Otherwise
		return false;
		
	} //isVerseNum
	
	/**
	 * Tests if symbol is a number
	 * 
	 * @param symbol	Character for testing
	 * @return	Boolean if symbol is a number or not
	 */
	private boolean isNum(char symbol) {
		
		/*
		 * ASCII Code of Numbers
		 * 0 to 9 -> 48 to 57
		 */
		
		// True if symbol is a number
		if (symbol >= '0' && symbol <= '9') {
			return true;
		}
		
		// Otherwise false
		return false;
		
	} //isNum
	
	/**
	 * Tests if symbol is a character
	 * 
	 * @param symbol	Character tested if a punctuation character
	 * @return	True if character is punctuation, false if otherwise
	 */
	private boolean isPunct(char symbol) {
		
		/*
		 * ASCII Code of Letters
		 * A to Z -> 65 to 90
		 * a to z -> 97 to 122
		 */
		
		// True if symbol is not a letter
		if (symbol < 'A' || (symbol > 'Z' && symbol < 'a') 
				|| symbol > 'z') {
			
			return true;
			
		}
		
		// Otherwise false
		return false;
		
	} //isPunct
	
	/**
	 * Removes all punctuation and possessives from words
	 * 
	 * @param word
	 * @return string with no punctuation
	 */
	private String removePuct(String word) {
		
		char lastChar;	// Last character of word
		char firstChar;	// First character of word
		
		// Sometimes a punctuation mark is by itself, when that happens
		// an error is thrown and word is returned as blank
		try {
			
			// Removing possessives
			if (word.endsWith("'s") || word.endsWith("s'")) {
				word = word.substring(0, word.length() - 2);
			}
			
			// Removing trailing punctuation
			lastChar = word.charAt(word.length() - 1);	
			while (isPunct(lastChar)) {
				
				// Removing punctuation at index {word.length() - 1}
				word = word.substring(0, word.length() - 1);
				lastChar = word.charAt(word.length() - 1);
				
			}
			
			// Removing leading punctuation
			firstChar = word.charAt(0);
			while (isPunct(firstChar)) {
				
				// Removing punctuation at index 0
				word = word.substring(1, word.length());
				firstChar = word.charAt(0);
				
			}
			
			return word;
			
		} catch(Exception e) {
			
			return "";	//Returning blank word
			
		}
		
	} //removePunct
	
	/**
	 * Finds longest word in myList
	 * 
	 * @param myList	ArrayList used to find longest word
	 * @return longestWords	returns longest word
	 */
	private String longestWords() {
		
		int i;	//loop index
		
		//Setting longest word to the first in list
		String longestWord = aryList.get(0);
		String longestWords = longestWord;
		
		for (i = 1; i < aryList.size(); i++) { //Loops through every word in list
			
			
			// If new word is just as long as then previous longest
			// word, append to new word to longest word separated
			// by a comma
			if (aryList.get(i).length() == longestWord.length())
					longestWords = longestWords.concat(", " 
							+ aryList.get(i));
			
			// If new word is longer than previous longest word,
			// set new word to longest word, and set longestWords to
			// longestWord
			if (aryList.get(i).length() > longestWord.length()) {
				
				longestWord = aryList.get(i);
				longestWords = longestWord;
				
			}
			
		}
		
		return longestWords;	//Returning longest word in list
		
	} //longestWords
	
	/**
	 * Prints results into four columns, with leftover words
	 * in columns of three
	 * 
	 * @param fileName	File to import words to
	 * @param wordList	Words to import to file
	 * @throws FileNotFoundException	Throws if file isn't found
	 */
	private void printResults() throws FileNotFoundException {
		
		//Variable Dictionary
		PrintWriter output = null;
		int i;	//loop index
		int j;	//loop index
		int currentWordPos = 0;	//Setting position to first in list
		double temp;	//Temporary double variable
		int totRows;	//Total rows for words
		int rowsOfFour;	//Total rows with four words in them
		int rowsOfThree;	//Total rows with three words in them
		
		
		if (outputFile.equals("console"))
			output = new PrintWriter(System.out);
		else
			output = new PrintWriter(outputFile);
		
		//Printing total number of words
		output.format("\n\tThe total word count is: %d\n", 
				aryList.size());	//Printing number of words in list
		
		//Printing longest word in aryList
		output.format("\tThe longest words are: %s\n\n", longestWords());
		
		/*
		 * Example of total rows per number of words:
		 * Total words | Rows of four | Rows of three
		 * 			10 | 			1 | 			2
		 * 			11 | 			2 |    			1
		 * 			12 | 			3 | 			0
		 * 			13 | 			1 | 			3
		 * 			14 | 			2 | 			2
		 * 			15 | 			3 | 			1
		 * 			16 | 			4 | 			0
		 * 			17 | 			2 | 			3
		 * 			18 | 			3 |	 			2
		 * 			19 | 			4 | 			1
		 * 			20 |			5 | 			0
		 * 			21 | 			3 | 			3
		 */
		
		// Total total number of rows equals the number of
		// words in list divided by four, always rounding up
		temp = (double) aryList.size() / 4.0;
		totRows = (int) Math.ceil(temp); // Rounding Up
		
		// Number of rows of three equals the number of words modulus
		// 4, with answers 1 and 3 swapped
		rowsOfThree = aryList.size() % 4;
		if (rowsOfThree == 1)
			rowsOfThree = 3;
		else if (rowsOfThree == 3)
			rowsOfThree = 1;
		
		// Number of rows of four equals the total number or rows
		// minus the number of rows of three
		rowsOfFour = totRows - rowsOfThree;
		
		// Printing words in rows of 4 to file
		for (i = 0; i < rowsOfFour; i++) {//Cycles through every row of 4
			
			for (j = 0; j < 4; j++) {  //Prints four words per line
				
				output.format("%18s ", aryList.get(currentWordPos));
				currentWordPos++;	//Go to next word
				
			} //j loop
			
			output.println();
			
		} //i loop
		
		// Printing words in rows of 3 to file
		for (i = 0; i < rowsOfThree; i++) {//Cycles through every row of 3
			
			for (j = 0; j < 3; j++) {  //Prints three words per line
				
				output.format("%18s ", aryList.get(currentWordPos));
				currentWordPos++;	//Go to next word
				
			} //j loop
			
			output.println();
			
		} //i loop
		
		// Pushing words to file and closing file
		output.close();
		
	} //printResults

} //class CountWords

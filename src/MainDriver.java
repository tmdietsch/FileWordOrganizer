/*
 * Name:	Timothy Dietsch
 * Class:	COMP141.B
 * Program:	UNIQUE WORDS
 */

import java.io.IOException;
import java.util.Scanner;

public class MainDriver {
	
	public static void main(String[] args) throws IOException {
		
		//Variable Dictionary
		CountWords cw = new CountWords();
		Scanner myScan = new Scanner(System.in);
		String userInput;
		
		// 1.0 Initialization		
		System.out.println("Timothy Dietsch ~ COMP141.B ~ "
				+ "UNIQUE WORDS");
		System.out.println("This program will print all unique "
				+ "words in a file to either another file or "
				+ "to the console");
		
		// 2.0 Setting Variables
		System.out.print("\nChoose a file to process (Example: file.txt), or type [stop] to quit: ");
		
		userInput = myScan.next();
		while(! (cw.fileTester(userInput) || userInput.equals("stop")) ) {
			
			System.out.print("File not found, please try again: ");
			userInput = myScan.next();
			
		}
		
		if (!userInput.equals("stop")) {
			
			cw.setInFile(userInput);
			
			System.out.print("\nName your text file to print your "
				+ "results (Example: file.txt),\n type [console] "
				+ "to print to the console: ");
		
			cw.setOutFile(myScan.next());
			
			cw.processFile();			
		}
		
		myScan.close();
		
		//Program successfully terminated
		System.out.println("\n\n\t<< Program Terminated >>");
		
	} //Main
	
} //MainDriver

/**
 * tasks a,b,c were done in 3 parts, this
 * makes some redundant code, program still runs as it should.
 */

// importing utility packs 
import java.util.Scanner;
import java.io.File;


// public class name
public class Innlesing {

    // main method, throws Exception
    public static void main(String[] args) throws Exception { 
	

	// declaring variables for input from file
	String fileName = "winnie.txt";
	File file = new File(fileName);
	Scanner fileIn = new Scanner(file);
	
	// counter for while loop
	int lineCounter = 0;
	
	// checks number of lines in file
	while(fileIn.hasNextLine()){
	    String line = fileIn.nextLine();
	    lineCounter+=1;
  
	}
	// prints out number of lines in file
	System.out.println("Lines in file: " + lineCounter);
	
	
	
	/**
	 * task 1b)
	 * reinitializes the scanner variable to start from the top
	 * of the file
	 */
	fileIn = new Scanner(file);
	lineCounter = 0;
	int countWord = 0;
	String searchWord = "Winnie-the-Pooh";
	
	

	/**
	 * counts instances of the searchWord, runs loop until end of file.
	 * includes only instances of word written exactly this way.
	 */
	 while (fileIn.hasNextLine()) {
	    String line = fileIn.nextLine();
	    if(line.equals(searchWord)) {
		countWord+=1;
	    }
	    
	    lineCounter +=1;
	}
	// prints times searchWord appears
	System.out.println("Found " + countWord + "instances of Winnie-the-Pooh");
	System.out.println("Search was case sensitive.");

	/**
	 * Task c)
	 * gives new calues to counters so they can be used in next while loop
	 * scanner variable is reinitialized to start from the top of the file
	 */
	
	countWord = 0;
	lineCounter = 0;
	fileIn = new Scanner(file);
	// new scanner for user input
	Scanner userIn = new Scanner(System.in);
	// output to user
	System.out.print("Type in word you want to search for: ");
	// input from user
	searchWord = userIn.nextLine();
	

	/**
	 * takes the input from user and searches for a match
	 * counts matches in file, ignores case.
	 */
	while(fileIn.hasNextLine()) {
	    String line = fileIn.nextLine();
	    if(line.equalsIgnoreCase(searchWord)) {
		countWord+=1;

		
	    }
	}
	// prints result to user
	System.out.println("Found " + countWord + " instances of the word you wanted to search for.");
    }
}




/**
 * Oppgavetekst til MinOppgave4:
 * Bruk oppgaven du lagde i MinOppgave2, Les inn filen secretQuestions.txt
 * og velg en tilfeldig linje fra filen som skal skrives ut til bruker.
 * print ut meny til brukeren til bruker velger exit
 */


/**
 * Oppgavetekst til MinOppgave2:
 * Lag et program hvor du kan ta i mot info fra bruker for registrering av en ny brukerkonto,
 * bruker skal ogsaa ha mulighet til aa endre sitt eget passord. Bruk metoder for aa gjoere koden lettere leselig,
 */

// import packages
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.util.Scanner;
import java.util.Random;

// class name
public class MinOppgave4 {


    static String fileName;
    static FileReader fReader; 
    static BufferedReader bReader; 
    static File file; 
    static Scanner fileIn; 
    static Random rand; 
    static Scanner input = new Scanner(System.in);
    static String password = "";
    static String secAnswer ="";
    static String secretCheck = "";
    static String username = "";
    static int menuChoice = 0;


    // main method thows Exception
    public static void main(String[]args)throws Exception {
	
	fileName = "secretQuestions.txt";
	// FileReader to read files of chars
	fReader = new FileReader(fileName);
	// buffers input from specified file
	bReader = new BufferedReader(fReader);
	file = new File(fileName);
	fileIn = new Scanner(file);
	// initialize a  random, rand
	rand = new Random();
	//print menu to user
	menu();
	//prints menu to user again

	
    }
	
    /**
     * Prints menu to user
     * and asks user for menuChoice to decide  next action
     * if user inputs 1 as menuChoice runs methods:
     * registerNewUser and secretAnswer.
     * if user inputs 2 as menuChoice runs method:
     * changePassword.
     * if user inputs 3 as menuChoice ends program.  
     */
    public static void menu()throws Exception {
	while(true){
	    System.out.println("1. Register new user.");
	    System.out.println("2. Change password for existing user.");
	    System.out.println("3. Exit");
	    
	    System.out.print("Please enter a number for desired action: ");
	    // user types menu choice
	    menuChoice = Integer.parseInt(input.nextLine());
	    
	    if (menuChoice == 1) {
		registerNewUser();
		secretAnswer();
	    	
	    } else if(menuChoice == 2){
		changePassword();
		
	    } else if (menuChoice == 3){
		return;

	    } else {
		System.out.println("ERROR! You have chosen an invalid command!");
	    }                  
	}

    }


    /**
     * Takes input from user and registers username and password
     */
    public static void registerNewUser() {
	System.out.print("Type in username, press enter: ");
	username = input.nextLine();
	System.out.print("Type in password, press enter: ");
	password = input.nextLine();

    }
    
    /** 
     * Asks user to register an answer to a random secret question 
     * by taking a random line from file secretQuestions.txt and 
     * printint out to user:
     */
    public static void secretAnswer() throws Exception {
	
	// counter for while loop
	int lineCounter = 0;
	
	// checks number of lines in file
	while(fileIn.hasNextLine()){
	     String line = fileIn.nextLine();
	    lineCounter+=1;
  
	}

	/**
	 * lineCounter+1 because random excludes last number
	 * and includes 0, whant line 1 to lineCounter. 
	 */
	int randomLineNumber = rand.nextInt(lineCounter + 1);
	String randLine = bReader.readLine();
	
	/**
	 * if the int i is less than randomLineNumber picks out 
	 * buffers line,   
	 */
	for (int i = 0; i < randomLineNumber ; i+=1) {
	    randLine = bReader.readLine();
	}
	// output to user
	System.out.println("Keep the answer to this question a secret, you can use this answer to reset your password");
	// prints out bufferd randLine as secret question
	System.out.println(randLine);
	// insert random secret question
	secAnswer = input.nextLine();
    }
    /**
     * asks user for the secret answer, if correct 
     * lets user change password.
     * If incorrect, displays error message.
     */
    
    public static void changePassword (){
	System.out.print("Please type the answer to your secret question: ");
        secretCheck = input.nextLine();
	if (secretCheck.equalsIgnoreCase(secAnswer)){
	    System.out.print("Enter a new password: ");
	    password = input.nextLine();
	}
	else {
	    System.out.println("The secret answer you have entered is incorrect.");
	}    
    } 
}

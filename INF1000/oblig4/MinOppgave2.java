/**
 * Oppgavetekst til MinOppgave2:
 * Lag et program hvor du kan ta i mot info fra bruker for registrering av en ny brukerkonto,
 * bruker skal ogsaa ha mulighet til aa endre sitt eget passord. Bruk metoder for aa gjoere koden lettere leselig,
 */

import java.io.File
import java.util.Scanner;
import java.util.Random;

public class MinOppgave4 {
    
    static String fileName = "secretQuestions.txt";
    static File file = new File(fileName);
    static Scanner fileIn = new Scanner;
    static Random rand = new Random();
    static Scanner input = new Scanner(System.in);
    static String password = "";
    static String secAnswer ="";
    static String secretCheck = "";
    static String username = "";
    static int menuChoice = 0;

    public static void main(String[]args)throws Exception {
	
	//print menu to user
	menu();
	// user types menu choice
	menuChoice = Integer.parseInt(input.nextLine());
	// runs menuUserChoice to determine next action
	menuUserChoice();
	//prints menu to user again
	menu();
	menuChoice = Integer.parseInt(input.nextLine());
	menuUserChoice();
    }
	
    /**
     * Prints menu to user
     */
        public static void menu() {
	System.out.println("1. Register new user.");
	System.out.println("2. Change password for existing user.");
	//	System.out.println("3. Delete user.");
    }

    /**
     * Takes input from user and registers username and password
     */
    public static void registerNewUser() {
	System.out.println("Type in username press enter, then enter your preffered password, then press enter.");
	username = input.nextLine();
	password = input.nextLine();

    }
    
    /** 
     * Asks user to register a secret answer
     */
    public static void secretAnswer() throws Exception {
	// counter for while loop
	int lineCounter = 0;
	
	// checks number of lines in file
	while(fileIn.hasNextLine()){
	    String line = fileIn.nextLine();
	    lineCounter+=1;
  
	}
	// lineCounter +1 because random excludes last number
	int radomLineNUmber = rand.nextInt(lineCounter + 1);
	String line = reader.readLine();
	for (int i = 0; i < randomLineNumber ; i+=1) {
	    line = reader.readLine();
}
	System.out.println("Keep the answer to this question a secret, you can use this answer to reset your password");
	// insert random secret question
	secAnswer = input.nextLine();
    }
    /**
     * asks user for the secret answer, if correct 
     * lets user change password.
     * If incorrect, displays error message.
     */

    public static void changePassword (){
	System.out.println("Please type the answer to your secret question.");
        secretCheck = input.nextLine();
	if (secretCheck.equals(secAnswer)){
	    System.out.print("Enter a new password: ");
	    password = input.nextLine();
	}
	else {
	    System.out.println("The secret answer you have entered is incorrect.");
	}    
    }
    /** if user inputs 1 as menuChoice runs methods:
     * registerNewUser and secretAnswer.
     * if user inputs 2 as menuChoice runs method:
     * changePassword.
     */

    public static void menuUserChoice(){
       
	if (menuChoice == 1) {
	    registerNewUser();
	    secretAnswer();
	    	    
	}
  
	if (menuChoice == 2){
	    changePassword();
	}
    }
}

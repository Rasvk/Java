/**
 * Oppgavetekst til MinOppgave2:
 * Lag et program hvor du kan ta i mot info fra bruker for registrering av en ny brukerkonto,
 * bruker skal ogsaa ha mulighet til aa endre sitt eget passord. Bruk metoder for aa gjoere koden lettere leselig,
 */


import java.util.Scanner;

public class MinOppgave2 {
    
    static Scanner input = new Scanner(System.in);
    static String password = "";
    static String secAnswer ="";
    static String secretCheck = "";
    static String username = "";
    static int menuChoice = 0;

    public static void main(String[]args) {
	
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
    public static void secretAnswer() {
	System.out.println("Keep the answer to this question a secret, you can use this answer to reset your password");
	System.out.print("What is your bestfriends name? ");
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

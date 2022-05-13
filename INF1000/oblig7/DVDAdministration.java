/**
 * Class to administrate objects used in Oblig 7
 * Part of Obligatorisk innlevering 7 INF1000 05.11.2014
 *
 * @autor Rasmus Vedholm Krog
 * @verison 1.04 2014-11-03
 */

// imports usefull functions for program to run 
import java.util.Scanner;

// public class name
public class DVDAdministration {
    
    static Scanner input;
    static Archive archive;
    static Person person;

    // main method
    public static void main(String[] args) { 
	// calls on method menu()
	menu();
    }

    /**
     * runs menu untill user exists
     * method menu calls on different methods, depending on input from user
     * prints error message if user enters a choice that does not exist
     */
    public static void menu(){
	
	
	String menuChoice;
	DVD newDVD;
        input = new Scanner(System.in);
	archive = new Archive();

	// runs until user exits
	while (true){
	    System.out.println("(O) to get an (O)verview of all the persons in the archive.");
	    System.out.println("(N) to register a (N)ew Person for the archive.");
	    System.out.println("(B) to register a newly (B)ought DVD by a given person.");
	    System.out.println("(L) to (L)oan a DVD from a given person.");
	    System.out.println("(R) to (R)eturn a borrowed DVD");
	    System.out.println("(S) to (S)how a persons DVD collection ");
	    System.out.println("(E) to (E)xit archive.");
	   
	    System.out.println("");
	    System.out.println("*--------------------------------*");
	    System.out.print("Please enter a letter for desired action ");
	    menuChoice = input.nextLine();  
	    System.out.println("*--------------------------------*");
	    System.out.println("");

	    if (menuChoice.equalsIgnoreCase("O") ) {
	        archive.printOverViewOfArchive();

	    } else if (menuChoice.equalsIgnoreCase("N")) {
		archive.addPersonToArchive();

	    } else if (menuChoice.equalsIgnoreCase("B")) {
		addDVDToPerson();

	    } else if (menuChoice.equalsIgnoreCase("L")) {
		administrateLoan();
		
	    } else if (menuChoice.equalsIgnoreCase("R")) {
		returnDVDToOwner();

	    } else if (menuChoice.equalsIgnoreCase("S")) {
		showListOfDVDsByPerson();

	    } else if (menuChoice.equalsIgnoreCase("E")) {
		exitProgram();
		return;
		
	    } else {
		System.out.println("");
		System.out.println("#########################################");
		System.out.println("ERROR! You have chosen an invalid command! Please enter a letter");
		System.out.println("#########################################");
		System.out.println("");
	    }
	}
    }

    /**
     * method to use when program is exiting
     * writes to file with filename
     * calls on method writeToFile of class Writer
     */
    public static void exitProgram(){
	String filename = "DVDarchive.txt";
	Writer write = new Writer(archive, filename);
	write.writeToFile();
    }
    
    /**
     * Shows the list of DVDs by person, also prints out a notice behind each title if it's allready
     * borrowed by someone. If user enter "*" prints out the list of DVDs of all persons, but users 
     * can specify a name and only see the DVDs of the given person. If user enters a name of a person 
     * that doesn't exist, prints out errormessage and returns user to menu.
     */
    public static void showListOfDVDsByPerson(){
	    
	input = new Scanner(System.in);	    
	System.out.print("Wich persons DVD collection do you want to show (type: * for all persons) ");
	String name = input.nextLine();

	if (name.equals("*")) {
	    archive.printOverView();

	} else {
	
	System.out.println("*--------------------------------------------------------*");
	person = archive.getPerson(name);
	
	if (archive.doesPersonExist(name)) {
	    System.out.println("Here is the overview of " + name + "'s DVDs " );
	    person.printDVDCollection();
	
	} else {
	    System.out.println("ERROR: Person Does not exist! Check your spelling!");
	}
	System.out.println("*--------------------------------------------------------*");

	}
    }

    /**
     * method for returning DVDs to owner, is used when user enters menuchoice "R"
     * asks for name of the person wanting to return a DVD, and the title of the movie
     * calls on method returnBorrowedDVD() from class Person
     */
    public static void returnDVDToOwner(){
	input = new Scanner(System.in);
	System.out.print("The name of the person wanting to return a DVD: ");
	String nameOfPersonLoaning = input.nextLine();
	System.out.print("The title of the movie: ");
	String title = input.nextLine();
	person = archive.getOverViewOfPersons().get(nameOfPersonLoaning);
	person.returnBorrowedDVD(title,person);
    }
    
    /**
     * Method adds DVD to a given owner, used when user enter menuchoice "B"
     * asks for name of the person who owns the DVD, and the title of the DVD
     * calls on method addDVDToCollection() from the Archive class
     */    
    public static void addDVDToPerson(){
	input = new Scanner(System.in);
	System.out.print("Who owns the DVD? ");
	String name = input.nextLine();
	System.out.print("What's the title of the DVD? ");
	String title = input.nextLine();
	archive.addDVDToCollection(name,title);
		
    }
   
    /**
     * this method admistrates the loan process and asks user for needed input
     * to check if DVD could be borrowed. Asks user for name of the person loaning the DVD
     * checks if a person with given name exists, then asks user for name of the owner of DVD
     * checks if owner exists, and prints out list of owners DVDs, asks for title of DVD.
     * prints error messages depending on wich check fails.
     * Example: Person Loaning allready owns DVD he/she wants to loan, ERROR: you allready own that DVD.
     */
    public static void administrateLoan(){
	DVD DVDToBorrow;
	Person owner;
	Person personLoaning;
	System.out.print("Who wants to borrow a DVD? ");
	String nameOfPersonLoaning = input.nextLine();
	// checks if person loaning exists
	if (archive.doesPersonExist(nameOfPersonLoaning)) {
	    
	    personLoaning = archive.getOverViewOfPersons().get(nameOfPersonLoaning);
	    System.out.print("Who do you want to loan a DVD from? ");
	    String nameOfOwner = input.nextLine();
	    
	    // checks if person Loaning and owner is the same person
	    if (!nameOfPersonLoaning.equals(nameOfOwner)) {
		
		// Checks if owener exists
		if (archive.doesPersonExist(nameOfOwner)) {
		    
		    owner = archive.getOverViewOfPersons().get(nameOfOwner);

		    // checks if owner actually owns any DVDs
		    if (owner.numberOfDVDsPersonOwns() > 0) {

		    System.out.println("");
		    System.out.println("Here is the overview of " + nameOfOwner + "'s DVDs " );
		    System.out.println("*--------------------------------*");
		    owner.printDVDCollection();
		    System.out.println("*--------------------------------*");
		    System.out.println("");
		    System.out.print("What's the title of the DVD? ");
		    String title = input.nextLine();
		    
		    // checks if person loaning owns DVD allready
		    if (!personLoaning.doesDVDExist(title, personLoaning)) {
			
			// checks if person has borrowed a DVD with the same title allready
			if (!personLoaning.isLoanedByPerson(title, personLoaning)){
			
			    // checks if owner owns given DVD
			    if (owner.doesDVDExist(title, owner)) {
				
				// initialise DVDToBorrow
				DVDToBorrow = owner.getDVD(title);
				
				// checks if owner allready has loaned out DVDToBorrow
				if (!DVDToBorrow.isDVDLoanedOut()) {
				    
				    // calls onn borrowDVD from class Person
				    personLoaning.borrowDVD(DVDToBorrow, personLoaning);

				} else {
				    System.out.println("The owner has allready loaned out the DVD " + title);
				} 
				
			    } else {
				System.out.println("ERROR: owner doesn't own " + title + "!");
			    } 
			    
			} else {
			    System.out.println("ERROR: You have allready borrowed this DVD: /"+ title + "/ from "
					       + personLoaning.getBorrowedDVD(title).getOwner().getName()  );
			}
			
		    } else {
			System.out.println("ERROR: You allready own this DVD: /" + title + "/");
		    }
		    
		    } else {
			System.out.println("ERROR: This person: /" + nameOfOwner + "/ owns no DVDs");
		    }   
		} else {
		    System.out.println("ERROR: Person" + nameOfOwner + " does not exist! Please check your spelling.");
		}
	    } else {
		System.out.println("ERROR: You can't borrow DVDs from yourself!");
	    }

	} else {
	    System.out.println("ERROR: You may have mistyped the name, no such person: /" + nameOfPersonLoaning + " exists!");
	}
    }

}

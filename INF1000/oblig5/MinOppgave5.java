/**
 * MinOppgave5 lag en oppgave som tar inn personalia til en person
 * skal ha mulighet til å printe dette ut igjen til bruker. Programmer
 * en klasse Personalia og gi den instansevariabler av passende type
 * for lagring av info. Opprett metoder som lagrer infoen, og metoder som kan hente
 * ut info igjen.
 */



// import Scanner
import java.util.Scanner;

// public class name
public class MinOppgave5{  
    
    static Scanner input = new Scanner(System.in);
    static int menuChoice;
    static Personalia person = new Personalia();
    static String maritalStatus;
    static String name;
    static String email;
    static String address;
    static String countryOfResidency;
    static int zipCode;
    static int age;
    
    //main method
    public static void main(String[] args) { 

	menu(); // call method menu()
    }
    /**
     * Metod prints out menu to user, calls on methods dependant
     * on users menuChoice, if user enters invalid command, displays
     * error message and prints menu until user chooses to exit.
     */
	public static void menu(){
	    // need menu to run until user exits
	    while(true){
		System.out.println("1. Register new personalia.");
		System.out.println("2. Print out personalia.");
		System.out.println("3. Exit");
		// output to user
		System.out.print("Please enter a number for desired action: ");
		// input from user
		menuChoice = Integer.parseInt(input.nextLine());
		// depending on input chooses action
		if (menuChoice == 1) {
		    registerNameAndAge(person);
		    registerAddress(person);
		    registerMaritalStatus(person);
		
		} else if(menuChoice == 2){
		    printOutPersonalia(person);
		    
		} else if (menuChoice == 3){
		    return;
		    // error message if command doesn't exist
		} else {
		    System.out.println("ERROR! You have chosen an invalid command!");
		}                  
	    }

	}
    /**
     * Method to register street address zip code and a persons
     * country of residency.
     * 
     * @param person
     * 
     */
	public static void registerAddress(Personalia person){
	    // output to user
	    System.out.print("Please enter country of residency: ");
	    // input from user
	    countryOfResidency = input.nextLine();
	    System.out.print("Please enter street address: ");
	    address = input.nextLine();
	    System.out.print("Please enter Zip code: ");
	    zipCode = Integer.parseInt(input.nextLine());
	    //info stored in object person
	    person.setCountryOfResidency(countryOfResidency);
	    person.setAddress(address,zipCode);
	    person.setEmail(email);
	}
	
    /**
     * Method registers name and age of person
     * @param person
     * 
     */
	public static void registerNameAndAge(Personalia person){
	    System.out.print("Please enter name: ");
	    name = input.nextLine();
	    System.out.print("Please enter age: ");
	    age = Integer.parseInt(input.nextLine());
	    person.setName(name);
	    person.setAge(age);
	}
    
    /**
     * Method registers a persons marital status.
     * prints out menu for user to choose status, if none fits
     * user can enter own status. Displays error message if number outside
     * of menu options is pressed, and displays menu again
     */
    public static void registerMaritalStatus(Personalia person){
	    
	    while(true){
		System.out.println("Choose marital status:");
		System.out.println("1. Married.");
		System.out.println("2. Single");
		System.out.println("3. Divorced");
		System.out.println("4. Widowed");
		System.out.println("5. Domestic partnership.");
		System.out.println("6. None of the above.");
		// description output to user
		System.out.print("Please enter a number for fitting status: ");
		// input from user
		menuChoice = Integer.parseInt(input.nextLine());
	    
		if (menuChoice == 1) {
		    maritalStatus = "Married";
		    person.setMaritalStatus(maritalStatus); 
		    return;

		} else if(menuChoice == 2){
		    maritalStatus = "Single";
		    person.setMaritalStatus(maritalStatus);
		    return;

		} else if (menuChoice == 3){
		    maritalStatus = "Divorced";
		    person.setMaritalStatus(maritalStatus);
		    return;
		    
		} else if(menuChoice == 4){
		    maritalStatus = "Widowed";
		    person.setMaritalStatus(maritalStatus);
		    return;

		} else if (menuChoice == 5){
		    maritalStatus = "Domestic partnership";
		    person.setMaritalStatus(maritalStatus);
		    return;
		
		} else if(menuChoice == 6){
		    maritalStatus = input.nextLine();
		    System.out.print("Please enter a more fitting description: ");
		    person.setMaritalStatus(maritalStatus); 
		    return;
		    
		} else {
		    System.out.println("ERROR! You have chosen an invalid command! Please try again.");
		    
		}
	    }
    }
    /**
     * prints out info stored in object Personalia person
     * @param person
     */
    public static void printOutPersonalia(Personalia person){
	System.out.println("Name: " + person.getName() + "\nAge: " + person.getAge());
	System.out.println("Address: " + person.getStreetAddress() + "\nZipcode: " + person.getZipCode());
	System.out.println("Country of residency: " + person.getCountryOfResidency());
	System.out.println("Marital status: " + person.getMaritalStatus());
    }
}


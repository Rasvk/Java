/**
 * Class to create and manipulate archives of persons and DVDs
 * Part of Obligatorisk innlevering 7 INF1000 05.11.2014
 *
 * @autor Rasmus Vedholm Krog
 * @verison 1.02 2014-11-03
 */

// imports necessary functions for class
import java.util.Set;
import java.util.HashMap;
import java.util.Scanner;

// public class name
public class Archive {
    
    
    private Scanner input = new Scanner(System.in);
    private HashMap<String,Person> overViewOfPersons = new HashMap<>();

    /**
     * Checks if person exists in HashMap<Person> returns false if Person does not exist.
     * 
     * @return true
     * @return false
     */
   public boolean doesPersonExist(String name){
	if(overViewOfPersons.containsKey(name)) {
	    return true;
	} else {
	    return false;
	}
    }

    /**
     * method to get overVieOfPerson
     *
     * @return HashMap<String, Person> overViewOfPersons
     */
    public HashMap<String,Person> getOverViewOfPersons(){
	return overViewOfPersons;
    }

    /**
     * method to add persons to archive
     * asks for name of person, checks if person allready exists
     * if person exists, display errormessage, if not creates person adds it to archive
     */
    public void addPersonToArchive(){

	System.out.print("Please enter the persons name: ");
	String name = input.nextLine();
	if (doesPersonExist(name)) {
	    System.out.println("Person allready exists, please enter another name.");
	} else {
	    Person person = new Person(name);
	    overViewOfPersons.put(name,person);
	}
    }
    
    /**
     * method to add a given DVD to a given person.
     * checks if person exists, if person exists checks if person owns the DVD allready
     * if person owns DVD displays error message, if not creates DVD and add to persons 
     * personalDVDCollection. Also displays errormesasge if person does not exist
     */
    public void addDVDToCollection (String name, String title){

	if (doesPersonExist(name)) {
	    Person person = overViewOfPersons.get(name);

	    if (person.doesDVDExist(title, person)) {
		System.out.println("You allready own the DVD, " + name);

	    } else {
		DVD newDVD = new DVD(title, overViewOfPersons.get(name));
		person.getDVDCollection().put(title, newDVD);
	    }

	} else {
	    System.out.println("ERROR: Person does not exist! Check your spelling or register new person!");
	}
       
    }

    /**
     * method to print out the DVD collection of all the people in the archive
     * uses enhanced for-loop to go through Set. 
     */
    public void printOverView(){
	Set<String> keySet = overViewOfPersons.keySet();
	System.out.println("*---------------------------------*");
	for (String key : keySet) {
	    System.out.println(key + " owns these DVDs: ");
	    
	    overViewOfPersons.get(key).printDVDCollection();
	}
   	System.out.println("*---------------------------------*");
    }

    /**
     * method to get a given person from archive
     *
     * @param name , name of person.
     * @return overViewOfPersons.get(name), given person
     */
    public Person getPerson(String name){
	return overViewOfPersons.get(name);
    }

    /**
     * Prints out the overview when user enters menuchoice "O" in class DVDAdministration
     * prints out name of owner, how many dvds owned, how many loaned out, and how many borrowed.
     */
   public void printOverViewOfArchive(){
	Set<String> keySet = overViewOfPersons.keySet();
		System.out.println("*---------------------------------*");
	for (String key : keySet) {
	    Person person = overViewOfPersons.get(key);
	    String printOut = " owns " + person.numberOfDVDsPersonOwns() + " has Loaned out " + person.numberOfDVDsPersonHasLoanedOut() + " and has borrowed " + person.numberOfDVDsBorrowedByPerson();
	    System.out.println(key + printOut + " DVDs" );
	    
	}
   	System.out.println("*---------------------------------*");
    }
}

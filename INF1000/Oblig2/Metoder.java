//Importing Scanner.
import java.util.Scanner;

//Name class
public class Metoder {

    /**
     *Define method nameResidence for future use.
     * Method takes input from user and prints out name and place of residence.
     */
    public static void nameResidence() {
	Scanner sc = new Scanner(System.in);
	System.out.print("Input name: ");
	String name = sc.nextLine();
	System.out.print("Input place of residence: ");
	String residence = sc.nextLine();
	System.out.println("Hello, " + name + " You are from " + residence + ".");
	}

    //Main method
    public static void main(String[]args) {
	// run nameResidence three times
	nameResidence();
	nameResidence();
	nameResidence();
    }
}

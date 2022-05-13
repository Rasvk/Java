// importing Scanner for input from user.
import java.util.Scanner;

// classname
public class SumTall {
  
    // main method
    public static void main(String[] args) {

	/**
	 * Declare variables Scanner sc is used when 
	 * user is entering input to program.
	 * input is stored in inputNumber, 
	 * sumNumber is stored as inputNumber so it stores the first
	 * value that the user inputs.
	 */
	Scanner sc = new Scanner(System.in);
	System.out.print("Please enter a number, then press enter. : ");
	int inputNumber = Integer.parseInt(sc.nextLine());
	int sumNumber = inputNumber; 

	/**
	 * Takes input from user, stores users input 
	 * in the variable sumNumberuntil user enters 0 
	 */
	while (inputNumber != 0 ) {
	    System.out.print("Please enter another number: ");
	    inputNumber = Integer.parseInt(sc.nextLine());
	    sumNumber += inputNumber;  
	    
	}
	// Prints out the sum of the numbers entered.
	System.out.println("The sum of the numbers you have entered is: " +sumNumber);
    }

}

// Import Scanner for taking input from user
import java.util.Scanner;

// public class
public class FirstArray {

    // main method
    public static void main(String[] args) { 
	
	/**
	 * Declares new scanner and initializes it for use in loops.
	 * Declares arrays arrayName and arrayNumber and give them length.
	 */
	Scanner sc = new Scanner(System.in);
	
	String[] arrayName = new String[5];
	
	int[] arrayNumber = new int[4];
	
	/**
	 * want to replace the 0 in array with numbers 0,1,2,3 use variable index to do so.
	 * increments variable index by 1 and runs untill all elements in array is checked.
	 * runs if loop to replace the first and last value of array with 1337.
	 */
	for(int index = 0; index < arrayNumber.length; index++){
		arrayNumber[index] = index; 
				
		if(index ==0 || index == arrayNumber.length-1){
		    arrayNumber[index] = 1337;
		}
		System.out.println(arrayNumber[index]);
	}
	/**
	 * asks user to enter a name stores it in the array increments index by one and asks 
	 * for another name, untill user has entered 5 names.
	 */

	for(int index = 0; index < arrayName.length; index++){
	    System.out.print("Enter a name: ");
	    arrayName[index] = sc.nextLine();
	     }
	// prints names entered to user.
	System.out.println("All the names entered are: ");
	for(int index = 0; index < arrayName.length; index++){
	    System.out.println(arrayName[index]);
	}
    }
}    
	

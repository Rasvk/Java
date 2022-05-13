// import Arrays utillity for printing arrays
import java.util.Arrays;

// public class
public class NegativeTall {
    
    // main method
    public static void main(String[] args) {
	
	/**
	 * declares array stated inn oppgave 3.4
	 * needs a counter to run through the loop and check for 
	 * negative numbers.
	 */
	int[] array  = {1, 4, 5, -2, -4, 6, 10, 3, -2};
	int count = 0;

	/**
	 * declares index = 0 as a variable to count wich element
	 * in the array we are checking. If loop checks for negative
	 * numbers.
	 */
	
	for(int index = 0; index < array.length; index++){
	    if(array[index]<0){
		count++;
	    }

	}

	// Prints out number of negative numbers in array.
	System.out.println("Amount of negative numbers in array: "+count);
	
	/**
	 * Declares new variable elementNumber, making it possible to
	 * declare a new variable index as int in the last for loop.
	 * set value of count back to 0, so that it does not skewer our result
	 */
	
	int elementNumber = 0;
	count = 0;

	/**
	 * Has the same function as the for loop above.
	 * goes through the array checks if element is < 0
	 * adds 1 to counter, and and then adds 1 to elementNumber.
	 * breaks when elementNumber is = or > array.length.
	 * Since we now have covered all elements of the array.
	 */

	while(elementNumber < array.length){
	    if(array[elementNumber]<0){
		count++;
	    }
	    elementNumber++;
	}
	
	// Prints out number of negative numbers inn array.
	System.out.println("Amount of negative numbers in array: "+count);
	
	// Prints old unchanged array
	System.out.println("The array is: " + Arrays.toString(array));
    
	/**
	 * goes through the array and checks if element is < 0
	 * if < 0 replaces number with the index number adds 1 to index
	 * breaks when index reaches array length.
	 */

	for(int index = 0; index < array.length; index++){
	    if(array[index]<0){
		array[index]=index;

	    }
	    
	}
	
	// Prints out formatted version of the array.
	System.out.println("The new array is: " + Arrays.toString(array));
	
    }


}

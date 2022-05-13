//class name
public class Forlokker {

    //main method
    public static void main(String[]args){   
	
	/**
	 * declare sumEven and sumOdd so it can be used out side of for loop.
	 */
	
	int sumEven = 0;
	int sumOdd = 0;
	
	// prints heading to terminal
	System.out.println("The following are all the even numbers between 0 and 10:");
	
	/**
	 * checks every number between 0 and 10 if it's an even or odd number.
	 */
	
	for (int number = 1; number < 10; number++){
	    
	    // check if rest.
	    int moduloNumber = number % 2;
	    
	    /**
	     * if no rest, even number else odd number.
	     * if even adds number to previously stored sumEven
	     * prints out the even number.
	     * If not a even number add the number to the 
	     * previously stored sumOdd 
	     */
	   
	    if(moduloNumber == 0){
		 sumEven += number;
		 System.out.println(number);
		}
	    
	    else{
		sumOdd += number;
	    }
	       
	}
	// prints out the sum of odd and even numbers
	System.out.println("The sum of the odd numbers is: " + sumOdd);
	System.out.println("The sum of the even numbers is: " + sumEven);
    }	
}  


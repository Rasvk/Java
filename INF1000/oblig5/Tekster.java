public class Tekster {

    public static void main(String[] args) { 

	/**
	 * task a)
	 * use a loop and print out the text variable containing the text "Agnes i senga"
	 * backwards.
	 */
	String backwardsString = ""; // declare a backwardsString
	String stringToPrintOutBackwards = "Agnes i senga"; // declare variable with given string
	int lengthOfString = stringToPrintOutBackwards.length(); // find length of string

	/**
	 * i = lengthOfString-1 as to not exceed string length while useing .charAt()
	 * as charAt() counts from zero. Hence i has to be greater or equal to 0 to get 
	 * all letters, then decrease i with 1
	 */
	for(int i = lengthOfString-1; i >= 0 ; i-- ){
	    backwardsString = backwardsString + stringToPrintOutBackwards.charAt(i);
	    
	}
	// prints out backwards string to user
	System.out.println("The string printed out backwards is: " + backwardsString);


	/**
	 * task b)
	 * starts here
	 */
	String capitalInf1000 = "INF1000";
	String lowerCaseInf1000 = "inf1000";
	if(capitalInf1000.equals(lowerCaseInf1000)){
	    System.out.println( "the strings are exactly equal");
	}
	else {
	    System.out.println("the strings are not equal");
	}

	/**
	 * task c)
	 * starts here
	 */
	// declare variabel subjects
	String subjects = "INF1100INF1000INF1010";
	// get INF1000 out of string useing substring
	String inf1000 = subjects.substring(7,14);
	// print to terminal
	System.out.println(inf1000);



    }


}

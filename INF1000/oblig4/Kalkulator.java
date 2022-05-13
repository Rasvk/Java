// public class name
public class Kalkulator {
    
    // main method
    public static void main(String[] args) { 
	
	// testing return value of method addition
	int addAnswer = addition(3,7);
	// printing out return value
	System.out.println("The sum of your numbers are " + addAnswer);
	
	// testing return value of method subtraction
	int subAnswer = subtraction(5,2);
	// prints out the return value
	System.out.println("The difference between your numbers are " + subAnswer);

	// testing return value of method integerDivision
	int intDivAnswer = integerDivision(10,3);
	// prints out the return value
	System.out.println("The quotient of your numbers are: " + intDivAnswer );

	// testing return value of method division
	double divAnswer = division(10,3);
	// prints out the return value
	System.out.println("The quotient of your numbers are: " + divAnswer);

    }
    
    /**
     * Method adds two ints, return int
     */
    public static int addition (int number1, int number2){
	int sum = number1 + number2;
	return sum;
    }
   
    /**
     * Method subtracts two ints, return int
     */  
    public static int subtraction (int number1 , int number2){
	int difference = number1 + number2;
	return difference;

    }
   
    /**
     * Method divides two numbers, return int
     */
    public static int integerDivision (int number1, int number2){
	int quotient = number1 / number2;
	return quotient;

    }
    
    /**
     *  Method divides two numbers, return double
     */
    public static double division(double number1, double number2){
	double quotient = number1 / number2;
	return quotient;

    }


}

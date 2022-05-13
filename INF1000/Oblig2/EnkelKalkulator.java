import java.util.Scanner;

public class EnkelKalkulator {  
    
    /**
     * Method calculating the sum of two integers
     */ 
    public static void sumInteger(int number1, int number2) {
	int sumNumbers = number1 + number2;
	System.out.println("The sum of the numbers are: " + sumNumbers);

    }
    /**
     * Method calculating the difference between two integers.
     */
    public static void differenceInteger(int number1, int number2) {
	int differenceNumbers = number1 - number2;
	System.out.println("The difference of the numbers are: " + differenceNumbers);
    }
    /**
     * Method for calculating the product of two integers
     */

    public static void productInteger(int number1, int number2){
	int productNumbers = number1 * number2;
	System.out.println("The product of the numbers are: " + productNumbers);
    }

    public static void main(String[] args) { 
	//declare variable sc for input from user
	Scanner sc = new Scanner(System.in);
	String writeInt = "Enter an Integer: ";
	System.out.print(writeInt);
	int inputNumber1 = Integer.parseInt(sc.nextLine());
	System.out.print(writeInt);
	int inputNumber2 = Integer.parseInt(sc.nextLine());
       	//methods with inputargument
	sumInteger(inputNumber1,inputNumber2);
	differenceInteger(inputNumber1,inputNumber2);
	productInteger(inputNumber1,inputNumber2);
    }

}

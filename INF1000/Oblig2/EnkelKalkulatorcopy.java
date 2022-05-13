import java.util.Scanner;
public class EnkelKalkulator {  
    
    static Scanner sc = new Scanner(System.in);
    static String writeInt = "Please enter an integer: ";
    static String inputInt1;
    static String inputInt2;
    static int number1;
    static int number2;
    static double dblnumber1;
    static double dblnumber2;
    static int sumNumbers;
    static int productNumbers;
    static double differenceNumbers;
    
    
    
    public static void sumInteger()) {
	sumNumbers = number1 + number2;
	System.out.println("The sum of the numbers are: " + sumNumbers);

    }

    public static void differenceInteger() {
	differenceNumbers = dblnumber1 / dblnumber2;
	System.out.println("The difference of the numbers are: " + differenceNumbers);
    }
  
    public static void productInteger(){
	productNumbers = number1 * number2;
	System.out.println("The product of the numbers are: " + productNumbers);
    }
    public static void main(String[] args) { 
	System.out.print(writeInt);
	inputInt1 = sc.nextLine();
	number1 = Integer.parseInt(inputInt1);
	dblnumber1 = (double)number1;
	System.out.print(writeInt);
	inputInt1 = sc.nextLine();
	number2 = Integer.parseInt(inputInt1);
	dblnumber2 = (double)number2;
	
	sumInteger();
	differenceInteger();
	productInteger();
}

}

import java.util.Scanner;
import java.util.Arrays;

public class MinOppgave3 {
    
    public static void main(String[]args){
	
	Scanner input = new Scanner(System.in);
	// alphabet length + space
	int alphabetLength = 27;
	int[] capitalLetters = new int[alphabetLength];
	int [] lowerCaseLetters = new int[alphabetLength];
	int asciiCapital = 65;
	int asciiLowerCase = 97;
	
	 
	// fills array with dec number for capital letters.
	for (int index = 0; index < capitalLetters.length; index++ ){
	    
	    capitalLetters[index]= asciiCapital;
	    asciiCapital++;
	}
	// fills array with dec number for lower case letters
	for (int index = 0; index < lowerCaseLetters.length; index++ ){
	    
	    lowerCaseLetters[index] = asciiLowerCase;
	    asciiLowerCase++;

	}
	
        
	// System.out.println(Arrays.toString(capitalLetters));
	// System.out.println(Arrays.toString(lowerCaseLetters));
	
	// user inputs string
	String userInput = input.nextLine();
	
	// convert string to char array
	char[] inputArray = userInput.toCharArray();
	System.out.println(Arrays.toString(inputArray));
	int[] castToInt = new int[inputArray.length];
	for(int i=0;i<inputArray.length;i++){
	    castToInt[i] =(int) inputArray[i];
	}
	System.out.println(Arrays.toString(castToInt));
	

	System.out.print("Enter the cryptation key: ");
	int cryptationKey = Integer.parseInt(input.nextLine());
	for(int i = 0; i<castToInt.length;i++){
	    castToInt[i] = castToInt[i]+ cryptationKey;
	}

	System.out.println("new cast to int:" +Arrays.toString(castToInt));
	char[] encryptedChar = new char[inputArray.length];
	for(int i = 0; i<castToInt.length; i++){
	    encryptedChar[i] = (char) castToInt[i];
	}
	System.out.println("encrypted array: "+Arrays.toString(encryptedChar));
	}	
} 
	
	
	

	    
    

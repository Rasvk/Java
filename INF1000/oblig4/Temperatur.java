// importing Scanner and File
import java.util.Scanner;
import java.io.File;

//public class name
public class Temperatur {

    static double averageTemperature = 0;
    static double tempSum = 0;

    /** 
     * Method to caluclate average temperature
     */
    public static void averageTemperature(int[] temp, int months) {
	// for loop for adding elements in temp array to tempSum
	for(int i = 0; i < temp.length; i+=1 ) {
	    tempSum+=temp[i];
	    // if temp is full calculate avg. Temperature
	    if(temp.length == months) {
		averageTemperature = tempSum / months;
	    
	    
	    }
	}
	System.out.println("The average temprature over the last: " + months + " months is " 
			   + averageTemperature + " degrees");
	
}

    
    // main method
    public static void main(String[] args) throws Exception {
	
	//declaring variables for input from file
	String fileName = "temperatur.txt";
	File file = new File(fileName);
	Scanner fileIn = new Scanner(file);
	// declarying variable month to use as length of array
	int months = 12; 
	// declares array temperature with length months
	int[] temperature = new int[months];

     
	/**
	 * fills array temperature with the numbervalue of the
	 * lines in the file 
	 */
	for(int i = 0; fileIn.hasNextLine(); i+=1) {
	    // converts the strings to int and stores them in corresponding index
	    temperature[i] = Integer.parseInt(fileIn.nextLine());

	}

	// calls method averageTemperature
	averageTemperature(temperature,months );
	
    }


}

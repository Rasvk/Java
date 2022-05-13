import java.util.Scanner;
import java.util.Random;

public class Oppgave51 {


    public static int phoneNumberGenerator(int min, int max) {
    
	Random random = new Random();
	int phoneNumber = random.nextInt((max - min) + 1) + min;
	
	return phoneNumber;
    }

    public static void main(String[] args) {
	
	Scanner input = new Scanner(System.in); // declare variable of type Scanner 
	Random rand = new Random(); // declare variable of type Random
	int start = 9000000; // used as start for phoneNumber
	int stop = 99999999; // used as end of phoneNumber
	Mobil newCP = new Mobil(); // create an object of type Mobil
	Person personData = new Person(); // create an object of type Person
	int cellPhoneNumber = phoneNumberGenerator(start, stop);
	
	// output to user
	System.out.print("Enter name of owner: ");
	// stores input from user
	String nameOfOwner = input.nextLine();
	// declare array with some phone brands
	String[] brandOfPhone = {"Apple","Nokia", "Samsung", "Doro", "Sony", "LG", "Huawei", "Motorola" };
	// output to user
	System.out.print("input age of new cellphone owner: ");
	// convert input to int and stores it
	int ageOfPerson = Integer.parseInt(input.nextLine());
	// output to user
	System.out.print("Enter place of recidency of owner: ");
	// stores input from user
	String placeOfResidencyOfPerson = input.nextLine();
	// declares random index withing length of array
	int index = rand.nextInt(brandOfPhone.length);
	
	/**
	 * store name age and place of residency in object of class
	 * Person.
	 *
	 * store name, brand, and phonenumber  in object of class 
	 * Mobil.
	 */
	personData.storeName(newCP.getOwnerName());
	personData.storeAge(ageOfPerson);
	personData.storePlaceOfResidency(placeOfResidencyOfPerson);
        newCP.storePhoneBrand(brandOfPhone[index]);
	newCP.storeOwnerName(nameOfOwner);
	newCP.storePhoneNumber(cellPhoneNumber);

        // print out name of owner, brand and phonenumber
	System.out.println("Owners name: " + newCP.getOwnerName() + " ,Brand of phone: "
			   + newCP.getPhoneBrand() + " ,Phonenumber " + newCP.getPhoneNumber());
    }

   
}

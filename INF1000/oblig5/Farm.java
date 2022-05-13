// public class
public class Farm {
    
    static Pig[] pigSti = new Pig[10];
    static Horse[] stable = new Horse[5];
    static Cow[] barn = new Cow[30];  
   
    // main method
    public static void main(String[] args) {
	int numberOfPigs = 5;
	int numberOfCows = 8;
	int numberOfHorses = 3;
	String typeToSell; 
	int numberOfAnimalsToSell;
	generatePig(numberOfPigs);
	generateCow(numberOfCows);
	generateHorse(numberOfHorses);
	// task e)
	typeToSell = "pigs";
	numberOfAnimalsToSell = 3;
	sellAnimals(typeToSell, numberOfAnimalsToSell);
	typeToSell = "cows";
	numberOfAnimalsToSell = 3;
	sellAnimals(typeToSell, numberOfAnimalsToSell);
    	typeToSell = "horses";
	numberOfAnimalsToSell = 3;
	sellAnimals(typeToSell, numberOfAnimalsToSell);
 
    }
    /**
     * Method sells numbers of animals of given type
     * if no such type exists display errormessage to user
     * if type exists sells animal/sets array[i] of given type
     * to null
     * @param type
     * @param numberOfAnimalsOfTypeToSell
     */
    public static void sellAnimals(String type, int numberOfAnimalsOfTypeToSell){
	
	if(type.equalsIgnoreCase("pigs")){
	    for(int i = 0; i < numberOfAnimalsOfTypeToSell; i++)
		if(pigSti[i]!= null){
		    pigSti[i] = null;
		} 
	}
	else if(type.equalsIgnoreCase("cows")){
	    for(int i = 0; i < numberOfAnimalsOfTypeToSell; i++)
		if(barn[i]!= null){
		    barn[i] = null;
		} 
	}
	else if(type.equalsIgnoreCase("Horses")){
	    for(int i = 0; i < numberOfAnimalsOfTypeToSell; i++)
		if(stable[i]!= null){
		    stable[i] = null;
		    	    
		} 
	}
	else{
	    System.out.println("Error! No such animal exists");
	}
    }

    

    
    /**
     * insert Pig with name into array of PigSti
     *
     * @param newPig
     */
     public static void insertPig(Pig newPig ){
	 
	 for(int i = 0; i < pigSti.length; i++){
	     if(pigSti[i] == null){
	       	 pigSti[i] = newPig;
		 return;
	     }	     
	 }
     }
    
    /**
     * inserts Cow newCow into array
     * @param newCow
     */
    public static void insertCow(Cow newCow ){
	 
	 for(int i = 0; i < barn.length; i++){
	     if(barn[i] == null){
	       	 barn[i] = newCow;
		 return;
	     }	     
	 }
     }
    
    /**
     * inserts Horse newHorse into array stable
     * @param newHorse
     */
    public static void insertHorse(Horse newHorse ){
	for(int i = 0; i < stable.length; i++){
	     if(stable[i] == null){
	       	 stable[i] = newHorse;
		 return;
	     } 
	}
    }
    
    /**
     * generates given number of pigs
     * names pigs "Pig" + indexnumber
     * @param howManyPigsToGenerate
     * calls on insertPig to insert newPig with
     * given name into array
     */
    public static void generatePig(int howManyPigsToGenerate){
	for(int i = 0; i < howManyPigsToGenerate; i++){
	    Pig newPig = new Pig();
	    newPig.givePigName("Pig"+i);
	    insertPig(newPig);
	}
    }
    
    /**
     * generates given number of cows
     * names cows "Cow" + indexnumber
     * @param howManyCowsToGenerate
     * calls on insert cwo to insert cow with given name
     * into array
     */
    public static void generateCow(int howManyCowsToGenerate){
	for(int i = 0; i < howManyCowsToGenerate; i++){
	    Cow newCow = new Cow();
	    newCow.giveCowName("Cow"+i);
	    insertCow(newCow);
	}
    }
    
    /**
     * generates given number of Horses
     * names Horses "Horse" + indexNumber
     * @param howManyHorsesToGenerate
     * calls on insertHorse to insert Horse with name
     * into array
     */
    public static void generateHorse(int howManyHorsesToGenerate){
	for(int i = 0; i < howManyHorsesToGenerate; i++){
	    Horse newHorse = new Horse();
	    newHorse.giveHorseName("Horse"+i);
	    insertHorse(newHorse);	    
	}
	
    }

}


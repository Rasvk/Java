public class Farm {
    
    Pig[] pigSti = new Pig[10];
    //Horse[] stable = new Horse[5];
    //Cow[] barn = new Cow[30];  
    //private String[] pigNames = {"Mr Wiggles", "Babe","Ms. Piggy", "Ruth","Snouter"};


    /**
     * insert Pig with name into array of PigSti
     *
     * @param newPig
     */

    public insertPig(Pig newPig){
	newPig = new Pig();
	
	
	
	for(int i = 0; i < pigSti.lenght; i++){
	    newPig = newpig.givePigName(pigNames[i]);
	    pigSti[i] = newPig;
	}
	
    
    }

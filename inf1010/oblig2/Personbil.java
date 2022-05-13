public class Personbil extends Fossilbil {
	int seter;
    /**
     * Konstruktor Personbil
     * 
     * @param regNr - naermeste super sin super
     * @param co2Utslipp - parameter i naermeste super 
     * @param seter - antall seter i bil, int
     * @see Fossilbil
     */
    public Personbil(String regNr, double co2Utslipp, int seter){
	super(regNr, co2Utslipp);
	this.seter = seter;
    }
    
    /**
     * skriver ut info om Personbil
     */
    public void skrivUtInfo(){
	System.out.println("Type motorvogn: PERSONBIL ");
	System.out.println("Reg.nr: " + regNr);
	System.out.println("CO2-utslipp: " + co2Utslipp);
	System.out.println("Seter: " + seter);

    }
}

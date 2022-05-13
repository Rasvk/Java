public class Lastebil extends Fossilbil{
    double nyttevekt;

    /**
     * Konstruktor av Lastebil
     * 
     * @param bilNr - naermeste super sin super
     * @param co2Utslipp - parameter i naermeste super 
     * @param nyttevekt - lastebils nyttevekt double
     * @see Fossilbil 
     */
    public Lastebil(String bilNr, double co2Utslipp, double nyttevekt){
	super(bilNr, co2Utslipp);
	this.nyttevekt = nyttevekt;
    }

    /**
     * Skriver ut info om Lastebil.
     */
    public void skrivUtInfo(){
	System.out.println("Type motorvogn: LASTEBIL ");
	System.out.println("Reg.nr: " + regNr);
	System.out.println("CO2-utslipp: " + co2Utslipp);
	System.out.println("Nyttevekt: " + nyttevekt);
    }
}

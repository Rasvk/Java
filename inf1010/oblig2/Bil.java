public class Bil {
    String regNr;		// registreringsnummer til en bil

    /**
     * Konstruktor Bil
     *
     * @param regNr - registreingsnummer bil, String
     */    
    public Bil(String regNr){
	this.regNr = regNr;
    }    
    
    /**
     * skriver ut info om Bil
     */
     public void skrivUtInfo(){
	System.out.print("Type motorvogn: BIL ");
	System.out.print("Registreringsnummer: " + regNr);
    }
}

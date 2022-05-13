public class Fossilbil extends Bil {
    double co2Utslipp; 			// hvor mye CO2 en fossilbil slipper ut

    /**
     * Kostruktor Fossilbil
     *
     * @param regNr - superklassens parameter
     * @param co2Utslipp, double utslipp fossilbil
     * @see Bil
     */
    public Fossilbil(String regNr, double co2Utslipp){
	super(regNr);
	this.co2Utslipp = co2Utslipp;
    }

    /**
     * skriver ut info om Fossilbil
     */
    public void skrivUtInfo(){
	System.out.println("Type motorvogn: FOSSIL ");
	System.out.println("Reg.nr: " + regNr);
	System.out.println("CO2-utslipp: " + co2Utslipp);
    }
}

public class Elbil extends Bil {
    double kW;			// Batteri kapasitet til el-bil

    /**
     * Konstruktor av Elbil kaller paa superklassens konstruktor med parameter
     *
     * @param bilNr - Superklassens parameter
     * @param kW - effekt batteri(kWh), double
     */
    public Elbil(String bilNr, double kW){
	super(bilNr);
	this.kW= kW;    
    }

    /**
     * skriver ut info om Elbil
     */
    public void skrivUtInfo(){
	System.out.println("Type motorvogn: ELBIL ");
	System.out.println("Reg.nr: " + regNr);
	System.out.println("Batterikapasitet (kWh): " + kW);
    }
}

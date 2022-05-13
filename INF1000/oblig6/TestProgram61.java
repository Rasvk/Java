// public class name
public class TestProgram61 {

    // main method
    public static void main(String[] args) { 
	// oppretter et objekt av type ord
	Ord soekeOrd = new Ord("hei");
	
	// tester toString() metoden
	System.out.println("ordet du soeker etter er: " + soekeOrd.toString());
	// tester metoden hentAntall()
	System.out.println("antall forekomster av ordet: " + soekeOrd.hentAntall() );
	// oeker antall av objekt
	soekeOrd.oekAntall();
	// henter verdi via metoden hentAntall()
	System.out.println("antall forekomster av ordet: " + soekeOrd.hentAntall() );
    }
}

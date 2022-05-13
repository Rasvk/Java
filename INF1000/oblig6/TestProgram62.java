// public class
public class TestProgram62 {

    // main method
    public static void main(String[] args)throws Exception { 

	
	String filnavn = "test.txt"; // filnavn
	Ordliste ol = new Ordliste(); 
	//leser fil
	ol.lesBok(filnavn);
	// printer ut antall unike ord
	System.out.println(ol.antallUnikeOrd());
	// sjekker om ord fins i listen
	ol.ordFunnet("the");
	//printer ut forekomster av forskjellige ord i listen
        System.out.println("antall deg: "+ol.antallForekomster("deg"));
        System.out.println("antall du: "+ol.antallForekomster("du"));
	System.out.println("antall meg: "+ol.antallForekomster("meg"));
	System.out.println("antall hei: "+ol.antallForekomster("hei"));
	// printer ut det vanligste ordet
	System.out.println(ol.vanligsteOrd().toString());

    }



}

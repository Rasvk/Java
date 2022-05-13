public class kopiTest {

        public static void main(String[] args)throws Exception { 

	String filnavn = "scarlet.txt";
	OrdlisteKopi ol = new OrdlisteKopi();
	ol.lesBok(filnavn);
	System.out.println(ol.antallOrd());
	ol.ordFunnet("deg");
        System.out.println("antall deg: "+ol.antallForekomster("deg"));
        System.out.println("antall du: "+ol.antallForekomster("du"));
	System.out.println("antall meg: "+ol.antallForekomster("meg"));
	System.out.println("antall hei: "+ol.antallForekomster("hei"));
	
	System.out.println(ol.vanligsteOrd().toString());
	//	ol.printArray();
    }




}

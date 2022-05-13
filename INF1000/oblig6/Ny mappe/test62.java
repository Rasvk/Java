import java.util.ArrayList;
public class test62 {

    public static void main(String[] args)throws Exception { 

	String filnavn = "scarlet.txt";
	Ordliste ol = new Ordliste();
	ol.lesBok(filnavn);
	ArrayList<String> ordliste = ol.getLesBok();
	System.out.println(ordliste.size());
	//String ord = "HE";
	//ol.leggTilOrd(ord);
	//ordliste = ol.getLesBok();
	//System.out.println(ordliste.size());
	ol.ordFunnet("Holmes",ol);
	

	int antallOrd = ol.antallOrd();
	System.out.println(antallOrd);
    }



}

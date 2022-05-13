import java.util.ArrayList;
public class TestProgram62 {

    public static void main(String[] args)throws Exception { 
	String filnavn = "test.txt";
	Ordliste ol = new Ordliste();
	ArrayList<String> ordliste = ol.lesBok(filnavn);
	ol.leggTilOrd("derp");
	System.out.println(ol.antallOrd(ordliste));
	
	
    }


}

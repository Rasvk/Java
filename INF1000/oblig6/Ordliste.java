import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

// public class name
public class Ordliste {

    
    private ArrayList<Ord> ordliste = new ArrayList<>(); 
   
    /**
     * leser fil , kaller paa leggTilOrd(String ord) for og fylle ArrayList<Ord>
     *
     *@param filnavn
     */
    public void lesBok(String filnavn)throws Exception{
	File fil = new File(filnavn);
	Scanner lestFil = new Scanner(fil);
	/**
	 * kaller leggTilOrd(String ord) saa lenge det er fler linjer i fil
	 */
	while(lestFil.hasNextLine()){
	    leggTilOrd(lestFil.nextLine());
	}
       
    }
    
    /**
     * returnerer ArrayList<Ord>
     *
     * @return ordliste
     */
    private ArrayList<Ord> getLesBok(){
	return ordliste;
    }

    /**
     * legger inn Ord i ordliste saa lenge Ord ikke finnes fra foer.
     * Hvis ord finnes, oeker antall til Ord.
     *
     * @param ord
     */
    private void leggTilOrd(String ord){
	Ord nyttOrd = new Ord(ord);
	int storrelse = ordliste.size();
	int i = 0;
	while (i < storrelse){
	    if(ord.equalsIgnoreCase(ordliste.get(i).toString())){
		if(nyttOrd.toString().equalsIgnoreCase(ordliste.get(i).toString())){
			ordliste.get(i).oekAntall();
		    }
		
		return;
	    }
	    else{
		i++;
	    }
	}

	ordliste.add(nyttOrd); 
	
    }
    
    /**
     * returnerer verdi for soekeOrd ettersom ordet det soekes etter finnes i ordlisten
     * eller ikke
     *
     * @param ord
     * @return soekeOrd
     */
    private Ord finnOrd(String ord){
	
	Ord soekeOrd = null;                     // setter soekeOrd til null
 	int storrelse = ordliste.size();         // definerer storrelse 
	int i = 0;
	/**
	 * gaar gjennom ArrayList<Ord> sjekker om @param ord er lik ord lagret i objekt
	 * i ordlisten, hvis likt returnerer String =  "", hvis ikke oeker i med en,
	 * og sjekker neste objekt.
	 */
	while (i < storrelse){
	    if(ord.equalsIgnoreCase(ordliste.get(i).toString())){
		soekeOrd = new Ord("");
		return soekeOrd;
	    }
	    else{
		i++;
	    }
	}
	
	return soekeOrd; // returnerer soekeOrd med verdi null
    }

    /**
     * printer tilbakemelding til bruker om ord finnes i listen eller ikke
     * 
     * @param ord
     */
    public void ordFunnet( String ord){
	
	if(finnOrd(ord) == null){
	    System.out.println("ordet finnes ikke i ordlisten");
	} else{
	    System.out.println("Ordet finnes i ordlisten");
	}
    }

    /**
     * finner antall unike ord i ordlisten
     * antall unike ord er lik antal objekter i ArrayList<Ord>
     *
     * @return unikeOrd
     */
    public int antallUnikeOrd(){
	int unikeOrd = ordliste.size();
	return unikeOrd;
    }
    
    /**
     * sjekker antall forekomster av et gitt ord i ArrayList<Ord>
     * sjekker ordet opp mot alle ord i listen store og smaa bokstaver spiller ingen rolle.
     * returnerer antall forekomster
     * 
     * @param ord
     * @return forekomster
     */
    public int antallForekomster(String ord){

	int forekomster = 0;
	int i = 0;
	/**
	 * gaar gjennom ordliste saa lenge i < storrelsen til ordliste,
	 * sjekker om string ord er lik string lagret i objekt på plass i i ordliste
	 * hvis like returner antall forekomster, hvis ikke oek i med 1
	 * finnes ikke ord i ordlisten returneres verdien 0
	 */
	while(i < ordliste.size()){
	    if(ord.equalsIgnoreCase(ordliste.get(i).toString())) {
		forekomster = ordliste.get(i).hentAntall();
		return forekomster;
	    }
	    else{
		i++;
		}
	    
	}
	return forekomster; 
    }

    /**
     * sjekker hvilket ord som er det vanligste ordet i ordlisten returnerer det vanligste ordet 
     *
     * @return vanligsteOrd;
     */
    public Ord vanligsteOrd(){
	int i = 0;
	// deklarerer vanligste ord som foerste ord i listen 
	Ord vanligsteOrd = new Ord(ordliste.get(i).toString()); 
	
	/**
	 * gaar gjennom ordlsite saa lenge i er mindre enn storrelsen paa ordlisten
	 * sjekker antallForekomster av ord opp mot de andre ordene i ordlisten.
	 * hvis ord(i) forekommer flere ganger enn vanligsteOrd, vanligsteOrd = ord(i)
	 */
	while(i < ordliste.size()){
	    // deklarerer vanligst som antall forekomster av det vanligste ordet
	    int vanligst = antallForekomster(vanligsteOrd.toString());
	    if(vanligst < antallForekomster(ordliste.get(i).toString())){
		vanligsteOrd = ordliste.get(i);
		
	    }
	    i++;
	}
	return vanligsteOrd;
}
    /**
     * printer ut alle elementene i ArrayList<Ord> ordliste
     * brukt for aa teste om logikk fungerer som den skal
     */
    private void printArray(){
	for(int i = 0; i < ordliste.size(); i++){
	    System.out.println(ordliste.get(i).toString());
	}
    }

}


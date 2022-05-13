import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Ordlistecopy {
    
    private static ArrayList<Ord> ordliste;
    Ord nyttOrd = new Ord("");
    File fil;
    
    public void lesBok(String filnavn)throws Exception{
	fil = new File(filnavn);
	Scanner lestFil = new Scanner(fil);
	ordliste = new ArrayList<>();
	
	while(lestFil.hasNextLine()){
	    String linje = lestFil.nextLine();
	    leggTilOrd(linje, lestFil);
	    
	}
	
    }

    ArrayList<Ord> getOrdliste(){
	return ordliste;

    }

    public void leggTilOrd(String ord,Scanner scan) {
       int i = 0;
       
       while(scan.hasNextLine()){
	   nyttOrd = new Ord(ord); 
	   if(ord.equalsIgnoreCase(ordliste.get(i).toString())) {
	       nyttOrd.oekAntall();
	   }
	   
	   else {
	       ordliste.add(nyttOrd);
	   }
       }   
       i++;
   } 
    
    Ord finnOrd(String ord){
	Ord soekeOrd = new Ord(ord);
	int storrelse = ordliste.size();
	int i = 0;
	while (i < storrelse){
	    if(soekeOrd == ordliste.get(i)){
		return soekeOrd;
	    }
	    else{
		i++;
	    }
	   
	}
	soekeOrd = null;
	return soekeOrd;

    }

    void inneHolderOrd(String ord){
	if(finnOrd(ord) == null){
	    System.out.println("ordet finnes ikke i ordlisten");
	} else{
	    System.out.println("Ordet finnes i ordlisten");
	}
    }

	public int antallOrd(){
	    int unikeOrd = ordliste.size();
	    return unikeOrd;
    }
    
	    
    
}

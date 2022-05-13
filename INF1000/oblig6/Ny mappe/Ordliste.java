import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Ordliste {

    static ArrayList<String> ordliste = new ArrayList<String>();
    ArrayList<Ord> ordArray = new ArrayList<>();
    File fil;
    Ord nyttOrd;
    
    public void lesBok(String filnavn)throws Exception{
	fil = new File(filnavn);
	Scanner lestFil = new Scanner(fil);

	while(lestFil.hasNextLine()){
	    leggTilOrd(lestFil.nextLine());
	}
       
    }
    public ArrayList<String> getLesBok(){
	return ordliste;
    }

    public void leggTilOrd(String ord){
	nyttOrd = new Ord(ord);
	int storrelse = ordliste.size();
	int i = 0;
	while (i < storrelse){
	    if(ord.equalsIgnoreCase(ordliste.get(i))){
		for(int j = 0;j < ordArray.size(); j++)
		    if(nyttOrd.toString().equalsIgnoreCase(ordArray.get(j).toString())){
			ordArray.get(j).oekAntall();
		    }
		
		return;
	    }
	    else{
		i++;
	    }
	}
	ordliste.add(ord); 
	ordArray.add(nyttOrd);
    }
    
    public Ord finnOrd(String ord){
	
	Ord soekeOrd = null;
 	int storrelse = ordliste.size();
	int i = 0;
	while (i < storrelse){
	    if(ord.equalsIgnoreCase(ordliste.get(i))){
		soekeOrd = new Ord("Exists");
		return soekeOrd;
	    }
	    else{
		i++;
	    }
	}
	
	return soekeOrd; 
    }

    public void ordFunnet( String ord, Ordliste ol){
	
	if(ol.finnOrd(ord) == null){
	    System.out.println("ordet finnes ikke i ordlisten");
	} else{
	    System.out.println("Ordet finnes i ordlisten");
	}
    }
    
    public int antallOrd(){
	int unikeOrd = ordliste.size();
	return unikeOrd;
    }
    
    public int antallForekomster(String ord){
	Ord someWord = new Ord(ord);
	int forekomster = 0;
	int i = 0;
	while(i < ordArray.size()){
	    //System.out.println(ordArray.get(i));
	    if(ord.equalsIgnoreCase(ordArray.get(i).toString())) {
		
		forekomster = ordArray.get(i).hentAntall();

		return forekomster;
	    }
	    else{
		i++;
		}
	    
	}
	return forekomster;
    }

    Ord vanligsteOrd(){
	int i = 0;
	Ord vanligsteOrd = new Ord(ordArray.get(i).toString());
	
	while(i < ordArray.size()){
	    int vanligst = antallForekomster(vanligsteOrd.toString());
	    if(vanligst < antallForekomster(ordArray.get(i).toString())){
		vanligsteOrd = ordArray.get(i);
		
	    }
	    i++;
	}
	return vanligsteOrd;
}
    void printArray(){
	for(int i = 0; i < ordArray.size(); i++){
	    System.out.println(ordArray.get(i).toString());
	}
    }


	    /*
    public int antallOrd(){
	ArrayList<String> ordlisteCopy = new ArrayList<String>(ordliste);
	int unikeOrd;
	int storrelse = ordlisteCopy.size() ;
	int i = 0;
	

	while( i < storrelse){
	    int j = i+1;
	    while (j < ordlisteCopy.size()){
		if(ordlisteCopy.get(i).equalsIgnoreCase(ordlisteCopy.get(j))){
		    ordlisteCopy.remove(j);
		    		    
		}
		j++;
		
	    }
	    i++;	
		    
	}
    
	unikeOrd = ordlisteCopy.size();
	return unikeOrd;   
    }
    */

    /*Ord vanligsteOrd(){
    

    }
    */
}


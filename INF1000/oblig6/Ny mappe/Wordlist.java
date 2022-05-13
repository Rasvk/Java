import java.util.ArrayList;
import java.util.Scanner; 
import java.io.File;

public class Wordlist {

   
    ArrayList<Ord> wordlist = new ArrayList<>();
    File fil;
    Ord nyttOrd;
    
    public void lesBok(String filename)throws Exception{
	fil = new File(filename);
	Scanner readFile = new Scanner(fil);

	while(readFile.hasNextLine()){
	    leggTilOrd(readFile.nextLine());
	}
       
    }
    public ArrayList<Ord> getLesBok(){
	return wordlist;
    }

    public void leggTilOrd(String ord){
	nyttOrd = new Ord(ord);
	int storrelse = wordlist.size();
	int i = 0;
	while (i < storrelse){
	    if(ord.equalsIgnoreCase(wordlist.get(i).toString())){
		if(nyttOrd.toString().equalsIgnoreCase(wordlist.get(i).toString())){
			wordlist.get(i).oekAntall();
		    }
		
		return;
	    }
	    else{
		i++;
	    }
	}

	wordlist.add(nyttOrd); 
	
    }
    
    public Ord finnOrd(String ord){
	
	Ord soekeOrd = null;
 	int storrelse = wordlist.size();
	int i = 0;
	while (i < storrelse){
	    if(ord.equalsIgnoreCase(wordlist.get(i).toString())){
		soekeOrd = new Ord("Exists");
		return soekeOrd;
	    }
	    else{
		i++;
	    }
	}
	
	return soekeOrd; 
    }

    public void ordFunnet( String ord){
	
	if(finnOrd(ord) == null){
	    System.out.println("ordet finnes ikke i wordlistn");
	} else{
	    System.out.println("Ordet finnes i wordlistn");
	}
    }
    
    public int antallOrd(){
	int unikeOrd = wordlist.size();
	return unikeOrd;
    }
    
    public int antallForekomster(String ord){
	Ord someWord = new Ord(ord);
	int forekomster = 0;
	int i = 0;
	while(i < wordlist.size()){
	    //System.out.println(wordlist.get(i));
	    if(ord.equalsIgnoreCase(wordlist.get(i).toString())) {
		
		forekomster = wordlist.get(i).hentAntall();

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
	Ord vanligsteOrd = new Ord(wordlist.get(i).toString());
	
	while(i < wordlist.size()){
	    int vanligst = antallForekomster(vanligsteOrd.toString());
	    if(vanligst < antallForekomster(wordlist.get(i).toString())){
		vanligsteOrd = wordlist.get(i);
		
	    }
	    i++;
	}
	return vanligsteOrd;
}
    void printArray(){
	for(int i = 0; i < wordlist.size(); i++){
	    System.out.println(wordlist.get(i).toString());
	}
    }

}


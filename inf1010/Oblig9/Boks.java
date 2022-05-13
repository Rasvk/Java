import java.lang.Character;
public class Boks{
    Rute[][] boksArray;
    static int unikTeller;
    int unikID;
    int n;
    int raderPerBoks;
    int kolonnerPerBoks;
    int[] verdiArray;

    public Boks(int rader, int kolonner){
	boksArray = new Rute[rader][kolonner];
	n = rader * kolonner;
	raderPerBoks = rader;
	kolonnerPerBoks = kolonner;
	unikID = unikTeller;
	unikTeller++;
	verdiArray = new int[n];
    }

    public int getUnikID(){
	return unikID;
    }

    public void setArray(int rad, int kolonne, Rute rute){
	boksArray[rad][kolonne] = rute;
    }

    int teller = 0;
    public int sokGjennom(int tall){
	for(int i = 0; i < raderPerBoks; i++){
	    for(int j = 0; j < kolonnerPerBoks; j++){
		if(boksArray[i][j].getTall() == ','){
		    verdiArray[teller] = 0;
		} else{
		verdiArray[teller] = Character.getNumericValue(boksArray[i][j].getTall());
		teller++;
		}
	    }
	}
	
	for(int i = 0; i < verdiArray.length; i++){
	    if(verdiArray[i] == tall){
		return verdiArray[i];
	    }
	}
	return 0;
    }

    public void skrivUtArray(){
	for(int i = 0; i < raderPerBoks; i++){
	    for(int j = 0; j < kolonnerPerBoks; j++){
		System.out.print(boksArray[i][j].getTall());
	    }
	}
    }
}

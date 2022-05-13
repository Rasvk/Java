import java.util.Arrays;
public class Kolonne{
    private Rute kolonneArray[];
    private static int unikTeller;
    private int unikID;

    public Kolonne(int n){
	kolonneArray = new Rute[n];
	unikID = unikTeller;
	unikTeller++;
    }

    public void setArray(int plass, Rute rute){
	kolonneArray[plass] = rute;
    }

    public int getUnikID(){
	return unikID;
    }

    public void skrivUtArray(){
	for(int i = 0; i < kolonneArray.length; i++){
	    System.out.print(kolonneArray[i].getTall() + " ");
	}
    }
}

import java.util.Arrays;
public class Rad{
    private Rute[] radArray;
    private static int unikTeller;
    private int unikID;

    public Rad(int n){
	radArray = new Rute[n];
	unikID = unikTeller;
	unikTeller++;
    }

    public int getUnikID(){
	return unikID;
    }

    public void setArray(int plass, Rute rute){
	radArray[plass] = rute;
    }

    public void skrivUtArray(){
	for(int i = 0; i < radArray.length; i++){
	    System.out.print(radArray[i].getTall() + " ");
	}
    }
}

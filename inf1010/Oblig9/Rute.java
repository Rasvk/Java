import java.util.ArrayList;
public class Rute{
    char tall;
    Rad rad;
    Kolonne kolonne;
    Boks boks;
    int n;
    ArrayList<Integer> svarListe = new ArrayList<Integer>();
    
    Rute(char tall, int n){
	this.tall = tall;
	this.n = n;
    }

    public char getTall(){
        return tall;
    }

    public void setInfo(Rad rad, Kolonne kolonne){
	this.rad = rad;
	this.kolonne = kolonne;
    }

    public void setBoks(Boks boks){
	this.boks = boks;
    }

    public void printInfo(){
	System.out.println("Verdi: " + tall + " Rad: " + rad.getUnikID() + " kolonne: " + kolonne.getUnikID() + " Boks " + boks.getUnikID());
    }

    public ArrayList<Integer> finnAlleMuligeTall(){
	for(int i = 0; i < n; i++){
	    svarListe.add(i);
	}

	for(int j = 0; j < svarListe.size(); j++){
	    int tempTall = boks.sokGjennom(svarListe.get(j));
	    if(tempTall != 0){
		svarListe.remove(j);
	    }
	}
	
	return svarListe;
	}
}

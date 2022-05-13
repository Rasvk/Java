import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Oblig9Main{  
    static int raderPerBoks = 0;
    static int kolonnerPerBoks = 0;
    static int n = 0;
    static Rad[] radListe;
    static Kolonne[] kolonneListe;
    static Brett brett;
    static Boks[] boksListe;

    public static void main(String[] args) {
	lesFil();
	delInnRuter();
	
    }

    public static void lesFil(){
  
	Scanner sc = new Scanner(System.in);
	System.out.println("Skriv inn navnet paa filen!");
	String filnavn = sc.nextLine();
	File fil = new File(filnavn);
	Scanner filScanner = null;

	try{
	    filScanner = new Scanner(fil);
	}
	catch(FileNotFoundException e){
	    System.out.println("Filen finnes ikke!");
	    e.printStackTrace();
	}

	raderPerBoks = Integer.parseInt(filScanner.nextLine());
	kolonnerPerBoks = Integer.parseInt(filScanner.nextLine());
	n = raderPerBoks * kolonnerPerBoks;

	brett = new Brett(raderPerBoks, kolonnerPerBoks, n);
	

	int rad = 0;
	int kolonne = 0;
	while(filScanner.hasNextLine()){
	    String linje = filScanner.nextLine();
	    Scanner linjeScanner = new Scanner(linje);

	    for(int i = 0; i < linje.length(); i++){
		Rute r = new Rute(linje.charAt(i), n);
		brett.settInnRute(rad, kolonne, r);
		kolonne++;
	    }
	    linjeScanner.close(); 
	    rad++;
	    kolonne = 0;
	}
	filScanner.close();
	brett.printArray();
	ArrayList<Integer> tempArrayList = brett.getArray()[0][0].finnAlleMuligeTall();

	for(Integer i : tempArrayList){
	    System.out.println(i);
	}
    }
	
    public static void delInnRuter(){
	radListe = new Rad[n];
	kolonneListe = new Kolonne[n];
	boksListe = new Boks[n];

	for(int i = 0; i < n; i++){
	    Rad r = new Rad(n);
	    radListe[i] = r;
	    Kolonne k = new Kolonne(n);
	    kolonneListe[i] = k;

	    Boks b = new Boks(raderPerBoks, kolonnerPerBoks);
	    boksListe[i] = b;
	}

	int r = 0;
	int k = 0;
	for(int j = 0; j < n; j++){
	    for(int h = 0; h < n; h++){
		brett.getArray()[j][h].setInfo(radListe[j], kolonneListe[h]);
		radListe[j].setArray(h, brett.getArray()[j][h]);
		kolonneListe[h].setArray(j, brett.getArray()[j][h]);

		brett.getArray()[j][h].setBoks(boksListe[boksIndex(j, h)]);

		if(r >= raderPerBoks)
		    r = 0;
		if(k >= kolonnerPerBoks)
		    k = 0;

		
		boksListe[boksIndex(j, h)].setArray(r, k, brett.getArray()[j][h]);
		
		brett.getArray()[j][h].printInfo();
		k++;
	    }
	    r++;
	}
	
	for(int i = 0; i < n; i++){
	    System.out.print("Rad " + i + ":     ");
	    radListe[i].skrivUtArray();
	    System.out.println("");
	    System.out.print("Kolonne " + i + ":     ");
	    kolonneListe[i].skrivUtArray();
	    System.out.println("");
	    System.out.print("Boks " + i + ": ");
	    boksListe[i].skrivUtArray();
	    System.out.println("");
	}

    }

    public static int boksIndex(int rad, int kolonne){
	return raderPerBoks * (rad/raderPerBoks) + (kolonne/kolonnerPerBoks);
    }
}

//package com.rasmusvk;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.lang.ArrayIndexOutOfBoundsException;
import java.io.PrintStream;
import java.io.OutputStream;
/**
 * Created by Rasmusvk on 30.03.2017.
 */
public class Labyrint {
    private Rute labyrint[][];
    private int rader, kolonner;
    private Stabel<String> utveiListe = new Stabel<String>();
    private Labyrint(Rute[][] labyrint, int rader, int kolonner) {
        this.labyrint = labyrint;
        this.rader = rader;
        this.kolonner = kolonner;
    }

    public static Labyrint lesFraFil(File fil) throws FileNotFoundException {
        int teller = 0;
        Rute[][] lab;
        Scanner sc = new Scanner(fil);
        int rad = 0, kolonne = 0, r = 0;

        String[] t = sc.nextLine().split(" ");
        kolonne = Integer.parseInt(t[1]);
        rad = Integer.parseInt(t[0]);
        //System.out.println(rad + " " + kolonne);
        lab = new Rute[rad][kolonne];


        while (sc.hasNextLine()) {
            String[] tokens = sc.nextLine().split("");
            for (int i = 0; i < tokens.length; i++) {
                if (tokens[i].equals(".")) {
                    if (erAapning(r, i, rad, kolonne)) {
                        lab[r][i] = new Aapning(r, i);
                    } else {
                        lab[r][i] = new HvitRute(r, i);
                    }
                } else {
                    lab[r][i] = new SortRute(r, i);
                    //System.out.println(r);
                }
            }
            r++;
        }
        Labyrint l = new Labyrint(lab, rad, kolonne);
        l.setRuteInstanseVariabler(l);
        sc.close();
        return l;
    }

    public Rute[][] getLabyrint() {
        return labyrint;
    }

    private static boolean erAapning(int rad, int kolonne, int maxRad, int maxKolonne) {
        return ((rad == 0) || (rad == maxRad - 1) || (kolonne == maxKolonne - 1) || (kolonne == 0));
    }

    public int getRader() {
        return rader;
    }

    public int getKolonner() {
        return kolonner;
    }
    
    /**
     * printer ut labyrint ved hjelp av lokke og tilTegn()
     */
    public void printLab() {
        System.out.println("Rader: " + rader);
        System.out.println("Kolonner: " + kolonner);
        for (int i = 0; i < rader; i++) {
            for (int j = 0; j < kolonner; j++) {
                System.out.print(labyrint[i][j].utskrift());
            }
            System.out.println("");
        }
    }
    
    /**
     * override toString()
     */
    public String toString() {
        String lab = "";
        for (int i = 0; i < rader; i++) {
            for (int j = 0; j < kolonner; j++) {
                lab += labyrint[i][j].tilTegn();
            }
            lab += '\n';
        }
        return lab;
    }
    
    /**
     * setter instansvariablene til en rute
     * @param l labyrint
     */
    private void setRuteInstanseVariabler(Labyrint l) {
        Rute[][] lab = l.getLabyrint();
        for (int rad = 0; rad < rader; rad++) {
            for (int kolonne = 0; kolonne < kolonner; kolonne++) {
                lab[rad][kolonne].setLabyrint(this);
                setNaboer(lab, lab[rad][kolonne]);
            }
        }

    }

    /**
     * setter alle naboer til en rute i labyrinten
     * @param l - labyrinten
     * @param r - gitt rute
     */
    private void setNaboer(Rute[][] l, Rute r) {
        int rad = r.getRad();
        int kolonne = r.getKolonne();
        if (rad > 0) {
            r.setNord(l[rad - 1][kolonne]);
        }
        if (kolonne > 0) {
            r.setVest(l[rad][kolonne - 1]);
        }
        if (rad < rader-1) {
            r.setSyd(l[rad + 1][kolonne]);
        }
        if (kolonne < kolonner-1) {
            r.setOst(l[rad][kolonne + 1]);
        }
    }

    public void settMinimalUtskrift(){
	// sette en annen printstream???
	// System.setOut(new PrintStream(new OutputStream() {		
	// 	public void write(int i){
	// 	}
	//     }));
    }

    
    /**
     * Finner alle utveier fra gitt kolonne, rad
     * @param kol
     * @param rad
     */
    public Liste<String> finnUtveiFra(int kol, int rad) {
	for(String s : utveiListe){
	    utveiListe.fjern();
	}
	try {
	    if(!(labyrint[rad-1][kol-1] instanceof SortRute))
		labyrint[rad-1][kol-1].finnUtvei();
	} catch(ArrayIndexOutOfBoundsException e){
	    System.out.printf("FEIL: gitt kolonne '%d', rad '%d' max kolonne og max rad er '%d', '%d' \n", kol, rad, getKolonner(), getRader());
	    System.exit(1);
	}
        return utveiListe;
    }
    
    /**
     * Setter in String s i utveiListe
     * @param s 
     */
    public void settInn(String s){
	utveiListe.settInn(s);
    }
    
}

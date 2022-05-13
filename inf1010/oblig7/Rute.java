//package com.rasmusvk;
import java.util.ArrayList;
/**
 * Created by Rasmusvk on 30.03.2017.
 * Usefull information
 */
abstract class Rute {
    protected int rad, kolonne;
    protected Labyrint labyrint;
    protected Rute nord, syd, ost, vest;
    protected boolean komFra;
    protected ArrayList<int[][]> losning;
    public Rute(int rad, int kolonne) {
        this.rad = rad;
        this.kolonne = kolonne;
	komFra = false;
    }

    public void setLabyrint(Labyrint labyrint) {
        this.labyrint = labyrint;
    }

    public void skrivNaboer(){
        char n = (nord != null)? nord.tilTegn(): '0';
        char s = (syd != null)? syd.tilTegn(): '0';
        char o = (ost != null)? ost.tilTegn(): '0';
        char v = (vest != null)? vest.tilTegn(): '0';
        System.out.println("Nord: " + n + " Syd: " + s + " Ost : " + o + " Vest: " + v);
    }
    abstract char tilTegn();
    abstract char utskrift();

    private boolean gaa(Rute r, String utvei) {
    	utvei += "(" + (kolonne+1) + ", " + (rad+1) + ")";
	Rute komFra = this;
	//System.out.println("rad: "+rad+" kolonne: " +kolonne);
	if(this instanceof Aapning) {
	    this.labyrint.settInn(utvei);
	    return true;
	}
	if(nord != null && !komFraNord(r) && !(nord instanceof SortRute)){
	    utvei += " --> ";
	    utvei = stringFix(utvei);
	    nord.gaa(komFra, utvei);
	   
	}
	
	if(syd != null && !komFraSyd(r) && !(syd instanceof SortRute)){
	    utvei += " --> ";
	    utvei = stringFix(utvei);
	    syd.gaa(komFra, utvei);
	   
	}
	
	if(ost != null && !komFraOst(r) && !(ost instanceof SortRute) ) {
	    utvei += " --> ";
	    utvei = stringFix(utvei);
	    ost.gaa(komFra, utvei);
	   
	}
	
	if(vest != null && !komFraVest(r) && !(vest instanceof SortRute)) {
	    utvei += " --> ";
	    utvei = stringFix(utvei);
	    vest.gaa(komFra, utvei);
	  
	}	

	return false;
    }
    
    public void finnUtvei(){
	gaa(this, "");
    }
    
    
    private String stringFix(String s) {
	if(s != null && s.length() >= 10) {
	    String temp = s.substring(s.length() - 10);
	    if(temp.equals(" -->  --> ")) {
		s = s.substring(0, s.length() - 5);
	    }
	}
	return s;
    }

    protected boolean komFraNord(Rute r) {
	boolean komfra = false;
	if(r.kolonne == nord.kolonne && r.rad == nord.rad)
	    komfra = true;
	return komfra;
    }

    protected boolean komFraSyd(Rute r) {
	boolean komfra = false;
	if(r.kolonne == syd.kolonne && r.rad == syd.rad)
	    komfra = true;
	return komfra;
    }
    
    protected boolean komFraOst(Rute r) {
	boolean komfra = false;
	if(r.kolonne == ost.kolonne && r.rad == ost.rad)
	    komfra = true;
	return komfra;
    }
    
    protected boolean komFraVest(Rute r) {
	boolean komfra = false;
	if(r.kolonne == vest.kolonne && r.rad == vest.rad)
	    komfra = true;
	return komfra;
    }
    

     public int getRad() {
        return rad;
    }

    public int getKolonne() {
        return kolonne;
    }

    public Labyrint getLabyrint() {
        return labyrint;
    }

    public Rute getNord() {
        return nord;
    }

    public void setNord(Rute nord) {
        this.nord = nord;
    }

    public Rute getSyd() {
        return syd;
    }

    public void setSyd(Rute syd) {
        this.syd = syd;
    }

    public Rute getOst() {
        return ost;
    }

    public void setOst(Rute ost) {
        this.ost = ost;
    }

    public Rute getVest() {
        return vest;
    }

    public void setVest(Rute vest) {
        this.vest = vest;
    }
}

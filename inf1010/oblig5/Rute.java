package com.rasmusvk;

/**
 * Created by Rasmusvk on 30.03.2017.
 */
abstract class Rute {
    protected int rad, kolonne;
    protected Labyrint labyrint;

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

    protected Rute nord, syd, ost, vest;

    public Rute(int rad, int kolonne) {
        this.rad = rad;
        this.kolonne = kolonne;
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
}

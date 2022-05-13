/**
 * abstrakt klasse legemiddel, inneholder kode som alle legemidler bruker.
 * @author Rasmusvk
 * @version 1.0
 * @since 24.03.2017
 */
abstract class Legemiddel {
    protected String navn;
    protected double pris;
    protected double virkestoff;
    private  final int UNIKID;
    private static int unikTeller;

    /**
     * Konstruktor for legemiddel, oker unikID med 1 pr. legemiddel opprettet
     * @param navn
     * @param pris
     * @param virkestoff
     */
    Legemiddel(String navn, double pris, double virkestoff){
	this.navn = navn;
	this.pris = pris;
	this.virkestoff = virkestoff;
	UNIKID = unikTeller++;
    }

    /**
     * hent unikid
     * @return unikid
     */
    public int hentId() {
	return UNIKID;
    }

    /**
     * hent navn
     * @return navn
     */
    public String hentNavn() {
	return navn;
    }

    /**
     * hent pris
     * @return pris
     */
    public double hentPris() {
	return pris;
    }

    /**
     * hent virkestoff
     * @return virkestoff
     */
    public double hentVirkestoff() {
	return virkestoff;
    }

    /**
     * override av toString metode
     * @return
     */
    public String toString(){
	    String string = "Navn: " + navn + " pris: " + pris + " total mengde virkestoff: " + virkestoff;
	    return string;
	}
}

/**
 * subklasse av Legemiddel hvor styrke er den narkotiske styrken til legemiddelet
 */
class LegemiddelA extends Legemiddel {
    private int styrke;

    /**
     * Konstruktor av LegemiddelA kaller paa super sin konstruktor og fyller inn info.
     * @param navn
     * @param pris
     * @param virkestoff
     * @param styrke
     */
    LegemiddelA(String navn, double pris, double virkestoff, int styrke) {
	super(navn, pris, virkestoff);
	this.styrke = styrke;
    }

    /**
     * hent metode
     * @return narkotiskstyrke
     */
    public int hentNarkotiskStyrke(){
	return styrke;
    }

    /**
     * override av toString metode
     * @return
     */
    public String toString(){
	    String string = "Navn: " + navn + " pris: " + pris + " total mengde virkestoff: " + virkestoff + " narkotisk styrke: " + styrke;
	    return string;
    }
}

/**
 * subklasse av Legemiddel hvor styrke er den vanedannende styrken til legemiddelet
 */
class LegemiddelB extends Legemiddel{
     private int styrke;

    /**
     * Konstruktor av LegemiddelB kaller paa super sin konstruktor og fyller inn info
     * @param navn
     * @param pris
     * @param virkestoff
     * @param styrke
     */
    LegemiddelB(String navn, double pris, double virkestoff, int styrke) {
	super(navn, pris, virkestoff);
	this.styrke = styrke;
    }

    /**
     * hent metode
     * @return vanedannendestyrke
     */
    public int hentVanedannendeStyrke(){
	return styrke;
    }

    /**
     * override av toString metode
     * @return
     */
    public String toString(){
	    String string = "Navn: " + navn + " pris: " + pris + " total mengde virkestoff: " + virkestoff + " narkotisk styrke: " + styrke;
	    return string;
    }

}

/**
 * LegemiddelC maa ha egen konstruktor siden superklassen er en abstract class
 */
class LegemiddelC extends Legemiddel {

    LegemiddelC(String navn, double pris, double virkestoff){
	super(navn, pris, virkestoff);
    }
}




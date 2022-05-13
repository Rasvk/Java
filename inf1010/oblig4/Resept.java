/**
 * Klasse respet
 * @author  Rasmusvk
 * @version 1.0
 * @since   24.03.2017
 */
abstract class Resept {
    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege;
    protected int pasientID;
    protected int reit;
    private  final int UNIKID;
    private static int unikTeller;
    protected double pris;

    /**
     * Konstruktor Resept
     * @param legemiddel
     * @param utskrivendeLege
     * @param pasientID
     * @param reit
     */
    Resept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientID, int reit){
	this.legemiddel = legemiddel;
	this.utskrivendeLege = utskrivendeLege;
	this.pasientID = pasientID;
	this.reit = reit;
	pris = legemiddel.hentPris();
	UNIKID = unikTeller++;
    }

    /**
     * hent metode
     * @return unikid
     */
    public int hentId(){
	return UNIKID;
    }

    /**
     * hent metode
     * @return Legemiddel
     */
    public Legemiddel hentLegemiddel() {
	return legemiddel;
    }

    /**
     * hent metode
     * @return Lege
     */
    public Lege hentLege(){
	return utskrivendeLege;
    }

    /**
     * hent metode
     * @return pasientID
     */
    public int hentPasientId() {
	return pasientID;
    }

    /**
     * hent metode
     * @return reit
     */
    public int hentReit(){
	return reit;
    }

    /**
     * sjekker om resept er gyldig trekker fra 1 paa reit hvis gyldig
     * @return
     */
    public boolean bruk() {
	if(reit > 0) {
	    reit--;
	    return true;
	} else {
	    return false;
	}
    }

    /**
     * override av toString metode
     * @return
     */
    public String toString(){
	    String string ="Legemiddel: " + legemiddel.hentNavn() + ", utskriver: " + utskrivendeLege.hentNavn() + ", pasient: "
            + pasientID + ", reit: " + reit;
	    return string;
    }

    /**
     * returnerer reseptens farge
     * @return
     */
    abstract public String farge();

    /**
     * returnerer pris som betales for legemiddel paa resept
     * @return
     */
    abstract public double prisAaBetale();
}

/**
 * subklasse av Resept
 */
class BlaaResept extends Resept {

    /**
     * Kostruktor blaaResept
     * @param lege
     * @param utskrivendeLege
     * @param pasientID
     * @param reit
     */
    BlaaResept(Legemiddel lege, Lege utskrivendeLege, int pasientID, int reit){
	super(lege, utskrivendeLege, pasientID, reit);
    }

    /**
     * returnerer blaa
     * @return
     */
    public String farge(){
	return "blaa";
    }

    /**
     * returnerer pris for blaa resept
     * @return
     */
    public double prisAaBetale(){
	return (super.pris * 0.75);
    }

    /**
     * override av toString metode
     * @return
     */
    public String toString(){
	    String string ="Legemiddel: " + legemiddel.hentNavn() + ", utskriver: " + utskrivendeLege.hentNavn() + ", pasient: "
		+ pasientID + ", reit: " + reit + ", farge: " + farge() + ", pris: " + prisAaBetale();
	    return string;
	}
}

/**
 * subklasse av Resept
 */
class HvitResept extends Resept {

    /**
     * Konstruktor HvitResept
     * @param lege
     * @param utskrivendeLege
     * @param pasientID
     * @param reit
     */
    HvitResept(Legemiddel lege, Lege utskrivendeLege, int pasientID, int reit){
	super(lege, utskrivendeLege, pasientID, reit);
    }

    /**
     * returnerer hvit
     * @return
     */
    public String farge(){
	return "hvit";
    }

    /**
     * returnerer pris for hvitresept
     * @return
     */
    public double prisAaBetale(){
	return super.pris;
    }

    /**
     * override av toString metode
     * @return
     */
    public String toString(){
	    String string ="Legemiddel: " + legemiddel.hentNavn() + ", utskriver: " + utskrivendeLege.hentNavn() + ", pasient: "
	        + pasientID + ", reit: " + reit + ", farge: " + farge() + ", pris: " + prisAaBetale();
	    return string;
    }
}

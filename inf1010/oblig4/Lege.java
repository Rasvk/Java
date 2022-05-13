/**
 * Lege
 * @author  Rasmusvk
 * @since   24.03.17
 * @version 1.0
 */
public class Lege implements Comparable<Lege> {
    private String navn;
    private Koe<Resept> rl;

    /**
     * Kostruktor Lege
     * @param navn
     */
    Lege(String navn){
	this.navn = navn;
	rl = new Koe<Resept>();
    }

    /**
     * sjekker om lege finnes fra for med samme navn.
     * @param lege
     * @return
     */
    public int compareTo(Lege lege){
	return navn.compareTo(lege.hentNavn());
    }

    /**
     * override av toString metode i object
     * @return
     */
    public String toString(){
	    String string = ("Navn: " + navn);
	    return string;
    }

    /**
     * hent metode
     * @return  navn
     */
    public String hentNavn() {
	return navn;
    }

    /**
     * legger til resept i legens reseptliste
     * @param resept
     */
    public void leggTilResept(Resept resept) { 
	rl.settInn(resept);
    }

    /**
     * hent metode
     * @return rl
     */
    public Koe<Resept> hentReseptliste() {
	return rl;
    }

    /**
     * vurdert som erstattning til compareTo kode
     * @param navn
     * @return
     */
    public boolean samme(String navn) {
	return (this.navn.equals(navn));
    }
}

/**
 * subklasse av lege implementerer grensesnittet Kommuneavtale
 */
class Fastlege extends Lege implements Kommuneavtale{
    private int avtalenummer;

    /**
     * Konstruktor Fastlege
     * @param navn
     * @param avtalenummer
     */
    Fastlege(String navn, int avtalenummer) {
	super(navn);
	this.avtalenummer = avtalenummer;
    }

    /**
     * override toString i object
     * @return
     */
    public String toString(){
	    String string = ("Navn: " + super.hentNavn() + " avtalenummer: " + avtalenummer);
	    return string;
    }

    /**
     * hent metode
     * @return avtalenummer
     */
    public int hentAvtalenummer() {
	return avtalenummer;
    }
}

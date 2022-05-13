/**
 * pasient
 * @autor   Rasmus v. Krog
 * @version 1.0
 * @since   24.03.2017
 */
public class Pasient {
    private String navn, gateadresse;
    private long fodselsnummer;
    private int postnummer;
    private static int unikTeller;
    private final int UNIKID;
    private Stabel<Resept> rl;

    /**
     * Kostruktor pasient
     * @param navn
     * @param fodselsnummer
     * @param gateadresse
     * @param postnummer
     */
    Pasient(String navn, long fodselsnummer, String gateadresse, int postnummer){
	this.navn = navn;
	this. fodselsnummer = fodselsnummer;
	this.gateadresse = gateadresse;
	this.postnummer = postnummer;
	rl = new<Resept>Stabel();
	UNIKID = unikTeller++;
    }

    /**
     * hent metode
     * @return unikid
     */
    public int hentId() {
	return UNIKID;
    }

    /**
     * hent metode
     * @return navn
     */
    public String hentNavn() {
	return navn;
    }

    /**
     * hent metode
     * @return fodselsnummer
     */
    public long hentFodselsnummer() {
	return fodselsnummer;
    }

    /**
     * hent metode
     * @return gateadresse
     */
    public String hentGateadresse() {
	return gateadresse;
    }

    /**
     * hent metode
     * @return postnummer
     */
    public int hentPostnummer() {
	return postnummer;
    }

    /**
     * legger inn en resept i reseptlisten rl.
     * @param resept
     */
    public void leggTilResept(Resept resept) {
	rl.settInn(resept);
    }

    /**
     * hent metode
     * @return rl
     */
    public Stabel<Resept> hentReseptliste() {
	return rl;
    }

    /**
     * override av toString metode
     * @return
     */
    public String toString() {
	    String string = "Navn: " + navn + " Fodselsnummer:" + fodselsnummer;
    }

}

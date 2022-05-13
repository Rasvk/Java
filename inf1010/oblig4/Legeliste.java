class Legeliste extends OrdnetLenkeliste<Lege> {
    /**
     * Soeker gjennom listen etter en lege med samme navn som `navn`
     * og returnerer legen (uten aa fjerne den fra listen).
     * Hvis ingen slik lege finnes, returneres `null`.
     * @param   navn    navnet til legen
     * @return  legen
     */
    public Lege finnLege(String navn) {

        for(Lege l : this) {
	    if(l.samme(navn)) {
		return l;	     
	    } 
	}
	return null;
    }

    /**
     * Returnerer et String[] med navnene til alle legene i listen
     * i samme rekkefoelge som de staar i listen.
     * @return array med navn til alle legene
     */
    public String[] stringArrayMedNavn() {
	String[] legeNavn = new String[storrelse()];
	int index = 0;
	for(Lege l : this) {
	    legeNavn[index++] = l.hentNavn();
	}
	return legeNavn;
    }
}

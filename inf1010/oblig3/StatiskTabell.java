import java.util.Iterator;
/**
 * Klasse Statisk tabell med generisk typeparameter.
 * 
 * @author Rasmus Vedholm Krog
 * @version 1.0
 * @since 13/03/2017
 * @param <T> beskriver typeparameter
 * @see java.util.Iterator
 */
public class StatiskTabell<T> implements Tabell<T> {
    
    private T[] tabell;
    
    /**
     * Konstruktor StatiskTabell
     *
     * @param kapasitet kapasitet til statisk tabell(lengden til array)
     */
    public StatiskTabell(int kapasitet) {
	@SuppressWarnings("unchecked")
	T[] tabell = (T[])new Object[kapasitet];
	this.tabell = tabell;
    }
    /**
     * Konstruktor StatiskTabell
     * Setter kapasitet til 100 som default hvis ikke kapasitet oppgis
     * 
     */
    public StatiskTabell() {
	this(100);
    }
    
    /**
     * Setter inn et element av type T inn i tabellen, hvis tabellen er full
     * kastes et FullTabellUnntak.
     *
     * @param element element som skal settes inn i tabell av type T
     * @throws FulltabellUnntak hvis tabell er full
     */
    public void settInn(T element) throws FullTabellUnntak {
	if(erFull()) {
	    throw new FullTabellUnntak(storrelse());   
	} else {
	    tabell[storrelse()] = element;
	} 
    }
    
    /**
     * Henter et element T fra en gitt plass i tabellen, kaster unntak hvis gitt plass
     * er ugyldig
     *
     * @param plass plass i tabell et element skal hentes fra
     * @throws UgyldigPlassUnntak hvis det ikke finnes en element paa en plass ie. outOfBounds
     * @return element element som hentes
     */
    public T hentFraPlass(int plass) throws UgyldigPlassUnntak {
	T element = null;
	if(plass < tabell.length && plass >= 0) {
	    if(storrelse() >= plass){
		element = tabell[plass];
		return element;
	    }
	} else {
	    throw new UgyldigPlassUnntak(plass, storrelse());
	}
	return element;
    }
    
    /**
     * beregner storrelse paa en tabell, antall faktiske elementer i tabellen
     *
     * @return storrelse storrelse paa tabell.
     */
    public int storrelse() {
	int storrelse = 0;
	for(int i = 0; i < tabell.length; i++){
	    if(tabell[i] != null){
		storrelse++;
	    }
	}
	return storrelse;
    }
    
    /**
     * Sjekker om tabellen er tom
     *
     *@return om tabellen er tom
     */
    public boolean erTom() {
	return (storrelse() == 0);
    }
    
     /**
     * Sjekker om tabellen er full
     *
     *@return om tabellen er full
     */
    public boolean erFull() {
	return(storrelse() == tabell.length);
    }
    
    public Iterator<T> iterator() {
	return new TabellIterator();
    }
    
    /**
     * Impolementerer grensesnittet Iterator<T>
     */
    private class TabellIterator implements Iterator<T> {
	private int teller = 0;
	
	/**
	 * saa lenge teller er mindre enn lengden til tabellen minus 1 og element paa gitt plass er null oeker teller med 1.
	 *
	 *@return om det finnes en neste  
	 */
	public boolean hasNext() {
	    while(teller < tabell.length-1 && tabell[teller] == null) {
		teller++;
	    }
	    return(teller < tabell.length && tabell[teller] != null);
	}
	/**
	 * finner neste for iterator
	 *
	 * returnerer neste element.
	 */
	public T next(){
	    return tabell[teller++];
	}
	
	public void remove(){}
    }
}


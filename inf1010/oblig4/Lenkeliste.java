import java.util.Iterator;
/**
 * Abstrakt klasse Lenkeliste som implementerer grensesnittet Liste
 * brukes for Stabel Koe og OrdnetLenkeliste klassene senere i oppgaven.
 *
 *@autor Rasmus Vedholm Krog
 *@version 1.0
 *@since
 *@param T beskriver typeparameter
 *@see java.util.Iterator
 */
abstract class Lenkeliste<T> implements Liste<T>  {
    
    /**
     * indre klasse Nodemed typeparameter T
     *
     * @param T typeparameter
     */
    protected class Node<T> {
	protected Node<T> neste;
	protected T element;
	
	/**
	 * Konstruktor Node
	 *
	 *@param element element i node.
	 */
	public Node(T element) {
	    this.element = element;
	}

	
    }

    protected Node<T> forst, sist;
    protected int antallNoder;
    
    /**
     * Konstruktor lenkeliste initialiserer forst sist og antallNoder
     */
    public Lenkeliste() {
	forst = null;
	sist = null;
	antallNoder = 0;
    }

    /**
     * Setter inn et element i lenkelisten , legges inn foran.
     *
     * @param element elementet som skal legges inn i lenkelisten
     */
    public void settInn(T element) {
	Node<T> ny = new Node<T>(element);
	if(erTom()) {
	    ny.neste = forst;
	    forst = ny;
	    sist = ny;
	    antallNoder++;
	} else {
	    ny.neste = forst;
	    forst = ny;
	    antallNoder++;
	}
    }
    
    /**
     * Fjerner forste node i listen flytter foran ett hak lenger bak i listen og returnerer forste node.
     *
     *@return returnerer forste element i listen hvis tom liste returnerer null.
     */
    public  T fjern() {
	Node<T> n = forst;
	if(n != null) {
	    forst = n.neste;
	    antallNoder--;
	    return n.element;
	} else {
	    return null;
	}
    }

    /**
     * Sjekker om lenkelisten er tom
     *
     *@return om lenkelisten er tom
     */
    public boolean erTom() {
	return (forst == null);
    }
    /**
     * returnerer storrelse paa lenkeliste
     *
     *@return antallNoder
     */
    public int storrelse() {
	return antallNoder;
    }
    
    public Iterator<T> iterator() {
	return new LenkeListeIterator();
    }  
        
    class LenkeListeIterator implements Iterator<T> {
	private Node<T> neste = forst;

	public boolean hasNext() {
	    return (neste != null);
	}
	
	public T next() {
	    T nesteElement = neste.element;
	    neste = neste.neste;
	    return nesteElement;
	}
	
	public void remove() {}
    }
}

/**
 * Siden Lenkeliste allerede er implementert som en Stabel trengs ingen endringer gjoeres
 * saa klassen er tom
 *
 * @author Rasmus Vedholm Krog
 * @version 1.0
 * @since 13/03/2017
 * @param <T> beskriver typeparameter
 * @see java.util.Iterator
 */
class Stabel<T> extends Lenkeliste<T>{}

/**
 * Forst inn forst ut, maa kun gjoere endringer paa settInn metoden
 *
 * @author Rasmus Vedholm Krog
 * @version 1.0
 * @since 13/03/2017
 * @param <T> beskriver typeparameter
 * @see java.util.Iterator
 */
class Koe<T> extends Lenkeliste<T>{
    
    /**
     * Konstruktor Koe kaller paa super()
     */
    public Koe() {
	super();
    }

    /**
     * setter inn noder sist i listen.
     *
     *@param element element som skal settes inn i listen
     */
    public void settInn(T element){
	Node<T> ny = new Node<T>(element);
	if(erTom()){
	    ny.neste = forst;
	    forst = ny;
	    sist = ny;
	    antallNoder++;
	} else {
	    ny.neste = null;
	    sist.neste = ny;
	    sist = ny;
	    antallNoder++;
	}
    }
}

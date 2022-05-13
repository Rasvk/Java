/**
 * Klasse OrdnetLenkeliste med generisk typeparameter. lager en ordnet lenkeliste ved hjelp av comparable 
 * utvider abstrakt klassen Lenkeliste
 * 
 * @author Rasmus Vedholm Krog
 * @version 1.0
 * @since 13/03/2017
 * @param <T> beskriver typeparameter
 * @see java.util.Iterator
 */
public class OrdnetLenkeliste<T extends Comparable<T>> extends Lenkeliste<T> {
    
    /**
     * Setter inn element T i en ordnet lenkeliste, sjekker forst om listen er tom, hvis tom settes inn i liste.
     * om ikke tom sjekker om element er mindre enn det som ligger forst i listen om mindre enn settes inn for det forste elementet
     * hvis ikke looper gjennom listen til den finner et element den er stoore enn og settes saa inn bak dette elementet.
     *
     * @param element element som skal settes inn i listen.
     */
    // public void settInn(T element) {
    // 	Node<T> ny = new Node<T>(element);
    // 	if(erTom()) {
    // 	    ny.neste = forst;
    // 	    forst = ny;
    // 	    sist = ny;
    // 	    antallNoder++;
	    
    // 	} else if(ny.element.compareTo(forst.element) < 0) {
    // 	    ny.neste = forst;
    // 	    forst = ny;
    // 	    antallNoder++;
	  	   
    // 	} else {
    // 	    Node<T> n = forst;
    // 	    boolean lagtTil = false;
    // 	    while(n.neste != null) {
    // 		if(n.neste.element.compareTo(ny.element) > 0) {
    // 		    ny.neste = n.neste;
    // 		    n.neste = ny;
    // 		    antallNoder++;
    // 		    lagtTil = true;
    // 		    return;
    // 		}
    // 		n = n.neste;
    // 	    }
    // 	    if(!lagtTil) {
    // 		sist = ny;
    // 		n.neste = ny;
    // 		antallNoder++;
    // 	    }
    // 	}
    // } 
    public boolean settInnElement(T element) {
	Node<T> ny = new Node<T>(element);
      
	if(erTom()) {  
	    ny.neste = forst;
	    forst = ny;
	    sist = ny;
	    antallNoder++;
	    return true;
	}
 
	if(ny.element.compareTo(forst.element) < 0){
	    ny.neste = forst;
	    forst = ny;
	    antallNoder++;
	    return true;
	} 
	Node<T> n = forst;
	boolean bleLagtTil = false;
	while(n.neste != null){
	    if(n.neste.element.compareTo(ny.element) > 0){
		ny.neste = n.neste;
		n.neste = ny;
		bleLagtTil = true;
		antallNoder++;
		return true; 
         
	    }
	    n = n.neste;
         
	}
	if(!bleLagtTil)
	    sist = ny;
	n.neste = ny;
	antallNoder++;
	return true;
      
    }
}
    


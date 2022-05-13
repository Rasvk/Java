public class Ord {
    // declaring private variables
    private String ord;
    private int forekomst;
    
    /**
     * konstruktoer
     * @param ord
     */
    public Ord(String ord){
	this.ord = ord;
	forekomst = 1;

    }
    /**
     * returnerer en String representasjon av et Ord
     */
    public String toString(){
       	return ord;
    }
    
    /**
     * returnerer antall forekomster av Ord
     */
    public int hentAntall(){
	return forekomst;
    }
    /**
     * oeker antall forekomster av Ord
     */
    public void oekAntall(){
	forekomst++;
	   
    }


}

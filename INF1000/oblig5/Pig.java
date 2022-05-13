public class Pig{
     
    private String name;
    
    /**
     * stores name of the pig
     *
     * @param name 
     */
    void givePigName(String name){
	this.name = name;
    }
    
    /**
     * returns name of the pig
     *
     * @return name
     */
    String getPigName(){
	return name;
    }
}

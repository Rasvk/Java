public class Cow{
     
    private String name;
    
    /**
     * stores name of the Cow
     *
     * @param name 
     */
    void giveCowName(String name){
	this.name = name;
    }
    
    /**
     * returns name of the pig
     *
     * @return name
     */
    String getCowName(){
	return name;
    }
}

public class Horse{
     
    private String name;
    
    /**
     * stores name of the Horse
     *
     * @param name 
     */
    void giveHorseName(String name){
	this.name = name;
    }
    
    /**
     * returns name of the Horse
     *
     * @return name
     */
    String getHorseName(){
	return name;
    }
}

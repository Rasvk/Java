public class Person {

    private String name;
    private String placeOfResidency;
    private int age;
    
    /**
     * stores age of person
     *
     * @param int age
     */
    void storeAge(int age){
	this.age = age;
    }
    
    /**
     * stores Place of residency of person
     *
     * @param String placeOfResidency
     */
    void storePlaceOfResidency(String placeOfResidency){
	this.placeOfResidency = placeOfResidency;
    }
    
    /**
     * stores name of person
     *
     * @param String name
     */
    void storeName(String name){
	this.name = name;
    }
    
    /**
     * returns age of person
     *
     * @return age
     */
    int getAge(){
	return age;
    }
    
    /**
     * returns place of residency of person
     *
     * @return palceofResidency
     */
    String getPlaceOfResidency(){
	return placeOfResidency;
    }
    /**
     * returns name of person
     *
     * @return name
     */
    String getName(){
	return name;
    }
}

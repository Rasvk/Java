// public class name
public class Personalia {
    
    private String name;
    private String countryOfResidency;
    private String address;
    private String maritalStatus;
    private int zipCode;
    private int age;
    private String email;
    

    

    // class interface

    /**
     * instance method to set name
     * @param name
     */
    public void setName(String name){
	this.name = name;
    }
    
    /**
     * instance method to set age
     * @param age
     */
    public void setAge(int age){
	this.age = age;
    }
    
    /**
     * instance method to set country of residency
     * @param countryOfResidency
     */
    public void setCountryOfResidency(String countryOfResidency){
	this.countryOfResidency = countryOfResidency;
    }
    
     /**
     * instance method to set address and zip code
     * @param address
     * @param zipCode
     */
    public void setAddress(String address, int zipCode){
	this.address = address;
	this.zipCode = zipCode;
    }

     /**
     * instance method to set marital status
     * @param maritalStatus
     */
    public void setMaritalStatus(String maritalStatus){
	this.maritalStatus = maritalStatus;
    }
    
     /**
     * instance method to get name
     * @return name
     */
    public String getName(){
	return name;
    }
    
     /**
     * instance method to get country of residency
     * @return countryOfResidency
     */
    public String getCountryOfResidency(){
	return countryOfResidency;
    }

    /**
     * instance method to get the street address
     * @return address
     */
    public String getStreetAddress(){
	return address;
    }

    /**
     * instance method to get marital status
     * @return maritalStatus
     */
    public String getMaritalStatus(){
	return maritalStatus;
    }

     /**
     * instance method to get zipCode
     * @return zipCode
     */
    public int getZipCode(){
	return zipCode;
    }

     /**
     * instance method to get age
     * @return zipCode
     */
    public int getAge(){
	return age;
    }
}

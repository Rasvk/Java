public class Mobil {

    private String phoneBrand; // phone brand
    private String ownerName;  // owners name
    private int phoneNumber;   // phoneNumber


    /**
     * stores brand of phone
     *
     * @param String phoneBrand
     */
    void storePhoneBrand(String phoneBrand) {
	
	this.phoneBrand = phoneBrand; 
    }
    
    /**
     * stores owner name
     *
     * @param String ownerName
     */
    void storeOwnerName(String ownerName) {
	
	this.ownerName = ownerName;
    }
    
    /**
     * stores phonenumber of owner
     *
     * @param int phoneNumber
     */
    void storePhoneNumber(int phoneNumber) {

	this.phoneNumber = phoneNumber;
    }
    
    /**
     * returns brand of phone
     *
     * @return phoneBrand
     */
    String getPhoneBrand() {
	return phoneBrand;
    }
    
    /**
     * returns the name of the owner
     *
     * @return ownerName
     */
    String getOwnerName() {
	return ownerName;
    }
    
    /**
     * returns the phones phonenumber
     *
     * @return phoneNumber
     */
    int getPhoneNumber(){
	return phoneNumber;
    }



}

/**
 * Class to create and manipulate objects of type DVD
 * Part of Obligatorisk innlevering 7 INF1000 05.11.2014
 *
 * @autor Rasmus Vedholm Krog
 * @verison 1.03 2014-11-03
 */

// public class name
public class DVD {
   
    private String title; 
    private Person owner;
    private Person personLoaning;


    /**
     * Constructor of object of type DVD
     * Every DVD needs a title, and an owner
     * also all DVDs starts with personLoaning set to null
     * 
     * @param title
     * @param owner
     */    
    public DVD(String title, Person owner){
	this.title = title;
	this.owner = owner;
	this.personLoaning = null;

    }
    
    /**
     * returns Person owner.
     *
     * @return owner
     */
    public Person getOwner(){
	return owner;
    }
    
    /**
     * returns person loaning dvd
     *
     * @return personLoaning
     */
    public Person getPersonLoaning(){
	return personLoaning;
    }
    
    /**
     * returns title of DVD
     *
     * @return title
.     */
    public String getTitle(){
	return title;
    }
    
    /**
     * sets personl loaning dvd, increase counter of DVDs owner has loaned out.
     * Also increases personLoanings counter of number of DVDs borrowed.
     */
    public void borrowDVD(Person personLoaning){
	this.personLoaning = personLoaning;
	owner.increaseDVDsLoanedOut();
    }

    /**
     * sets personLoaning to null, decreases counter of DVDs owner has loaned out.
     *
     */
   public void returnDVD(){
	personLoaning = null;
	owner.decreaseDVDsLoanedOut();
    }

    /**
     * checks if DVD is loaned out, if not return false.
     *
     * @return false if not loaned out
     * @return true if loaned out
     */
    public boolean isDVDLoanedOut(){
	if (personLoaning == null) {
	    return false;
	} else {
	    return true;
	}
    }    
    
    /**
     * toString method of type String returns a string representation of a DVD,
     * representation changes depending on if DVD is loaned out.
     *
     * @return stringRepresentation
     */
    public String toString(){

	    String stringRepresentation = title;
	
	    if (personLoaning != null) {
		stringRepresentation = stringRepresentation + "(Borrowed by " + personLoaning.getName() + ")";
		}
		
	    return stringRepresentation;
	}
    
}

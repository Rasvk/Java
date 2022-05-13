//package com.rasmusvk;

/**
 * Created by Rasmusvk on 30.03.2017.
 */
public class HvitRute extends Rute {
    public HvitRute(int rad, int kolonne) {
        super(rad, kolonne);
    }

    public char tilTegn(){
        return '.';
    }

    public char utskrift() {
        return ' ';
    }
    
    public String toString(){
	return " ";
    }
  
 
}

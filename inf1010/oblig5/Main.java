package com.rasmusvk;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Labyrint l = null;
        File fil = new File("1.in.txt");
        try {
            l = Labyrint.lesFraFil(fil);
        } catch (FileNotFoundException e){
            System.out.println("ERROR");
        }
        System.out.println(l.toString());
        Rute[][] lab = l.getLabyrint();
        lab[1][2].skrivNaboer();
    }
}

// class name
public class Oblig6{

    //main method
    public static void main(String[] args)throws Exception {
	Ordliste ol = new Ordliste();
	String filnavn = "scarlet.txt";
	//reads file and stores in ArrayList<Ord>
	ol.lesBok(filnavn);
	// prints out number of uniqe words
	System.out.println("Number of unique words: " +ol.antallUnikeOrd());
	// prints out number of occurances of word Holmes
	System.out.println("Number of times the word Holmes occurs in the text: "+ol.antallForekomster("Holmes"));
	// prints out number of occurances of word elementary.
	System.out.println("Number of times the word elementary occurs in the text: "+ol.antallForekomster("elementary"));
	// prints out most common word and number of occurances of said word
	System.out.println("Most the common word is: " +ol.vanligsteOrd() +
	" it occurs " + ol.antallForekomster(ol.vanligsteOrd().toString()) + " times.");
    }

}


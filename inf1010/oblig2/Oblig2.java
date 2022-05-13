import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Oblig2  {

    public static void main(String[] args) {
	ArrayList<Bil> bilRegister = new ArrayList<>();

	
	if(args.length != 0) { 
	    String filNavn = args[0];
	    File fil = new File(filNavn);
	    if(fil.exists()){
		lesInnFraFil(fil, bilRegister, filNavn);
		utskrift(bilRegister, args);
	    } else {
		System.out.println("ERROR! Filen som ble spesifisert: " + filNavn + " ,finnes ikke i denne mappen.");
	    }
	} else {
	    System.out.println("ERROR! For aa kjoere programmet gjoer foelgende:");
	    System.out.println("java Oblig2 <inputfil> [filter]");
	    System.out.println("filter kan vaere EL eler FOSSIL");
	    System.out.println("Hvis filter ikke spesifiseres vil info om alle biler bli printet ut");
	}	
    }
	
    /**
     * Leser inn fra gitt fil, leser hver linje i filen bruker splitt("\\s") for aa separe ord paa whitespaces disse lagres i en String array, sjekker saa om Stringene
     * etter hva slags type bil med equalsIgnoreCase. Oppretter saa objekt av riktig type, legger objektet inn i en ArrayList<Bil> oeaker antallBiler.
     * Printer ut totalt antall registrerte biler til slutt.
     * Hvis det ikke eksisterer en fil med gitt filnavn vil en feilmelding vises for bruker, og programmet maa startes paa nytt
     *
     * @param filSomSkalLeses - filens som skal leses gjennom
     * @param bilRegister - ArrayList av type Bil
     * @param filNavn - String navnet paa filen
     * @see java.io.File
     * @see java.util.ArrayList
     * @see java.io.FileNotFoundException
     */
    public static void lesInnFraFil(File filSomSkalLeses, ArrayList<Bil> bilRegister, String filNavn) {
	try {
	   
	    Scanner filScanner = new Scanner(filSomSkalLeses);
	    int antallBiler = 0;
	    int i = 0;
	    
	    while(filScanner.hasNextLine()) {
		
		String[] tokens = filScanner.nextLine().split("\\s");
		if(tokens[i].equalsIgnoreCase("BIL")) {
		    Bil bil = new Bil(tokens[i+1]);
		    leggTilIArrayList(bil, bilRegister);
		    antallBiler++;
		} else if(tokens[i].equalsIgnoreCase("EL")) {
		    Elbil elBil = new Elbil(tokens[i+1], Double.parseDouble(tokens[i+2]));
		    leggTilIArrayList(elBil, bilRegister);
		    antallBiler++;
		} else if(tokens[i].equalsIgnoreCase("FOSSIL")) {
		    Fossilbil fBil = new Fossilbil(tokens[i+1], Double.parseDouble(tokens[i+2]));
		    leggTilIArrayList(fBil, bilRegister);
		    antallBiler++;
		} else if(tokens[i].equalsIgnoreCase("LASTEBIL")) {
		    Lastebil lBil = new Lastebil(tokens[i+1], Double.parseDouble(tokens[i+2]),
						 Double.parseDouble(tokens[i+3]));			    
		    leggTilIArrayList(lBil, bilRegister);
		    antallBiler++;
		} else if(tokens[i].equalsIgnoreCase("Personbil")) {
		    Personbil pBil = new Personbil(tokens[i+1], Double.parseDouble(tokens[i+2]),
						   Integer.parseInt(tokens[i+3]));
		    leggTilIArrayList(pBil, bilRegister);
		    antallBiler++;
		}     		
	    }
	    
	    filScanner.close();
	    System.out.println("Antall biler registrert: " + antallBiler);

	} catch (FileNotFoundException e) {

	    System.out.println("ERROR! Det finnes ingen fil med filnavnet som ble spesifisert: " + filNavn);
	}
    }

    /**
     * Tar inn en bil som argument og legger inn i en Array List
     *
     * @param bil bil som skal legges inn i ArrayList
     * @see java.util.ArrayList
     */
    public static boolean leggTilIArrayList(Bil bil, ArrayList<Bil> bilRegister) {
	return bilRegister.add(bil);
    }

    /**
     * Sjekker om bil i bilregister er Personbil hvis den er det printer ut info om bil
     *
     * @param bilRegister
     * @see ArrayList
     */
    public static void skrivUtInfoFossilbil(ArrayList<Bil> bilRegister) {
	for(Bil bil : bilRegister) {
	    if(bil instanceof Fossilbil) {
		bil.skrivUtInfo();
	    }
	}
    }

    /**
     * Sjekker om bil i bilregister er Elbil hvis den er det printer ut info om bil
     *
     * @param bilRegister
     * @see ArrayList
     */
    public static void skrivUtInfoElbil(ArrayList<Bil> bilRegister) {
	for(Bil bil : bilRegister) {
	    if(bil instanceof Elbil) {
		bil.skrivUtInfo();
	    }
	}
    }

    /**
     * Sjekker type bil, skriver ut info om riktig type. Bruker objekt sin instanceof metode til aa sjekke objekt type.
     *
     * @param bilRegister
     * @see java.util.ArrayList
     */
    public static void skrivUtInfoAlleBiler(ArrayList<Bil> bilRegister) {
	for(Bil bil : bilRegister) {
	    bil.skrivUtInfo();

	}
    }
    
    /**
     * Sjekker om filter finnes hvis ikke printer ut info om alle registrerte biler,
     * hvis filter finnes, sjekker om filter er enten [EL] eller [FOSSIL], hvis ingen
     * av delene printer ut en feilmelding og eksempel paa hvordan programmet brukes.
     *
     * @param bilRegister - ArrayList<Bil>, listen med biler.
     * @param filter - String[], en liste som inneholder filter på plass 2(filter[1]).
     */
    public static void utskrift(ArrayList<Bil> bilRegister, String[] filter){
	if(filter.length == 1){
	    skrivUtInfoAlleBiler(bilRegister);
	} else if (filter.length > 2){
	    System.out.println("ERROR! For mange argumenter gitt, kjoer program slik:");
	    System.out.println("java Oblig2 <inputfil> [filter]");
	    System.out.println("filter kan vaere EL eler FOSSIL");
	    System.out.println("Hvis filter ikke spesifiseres vil info om alle biler bli printet ut");
	} else if(filter[1].equalsIgnoreCase("EL")) {
	    skrivUtInfoElbil(bilRegister);
	} else if(filter[1].equalsIgnoreCase("FOSSIL")) {
	    skrivUtInfoFossilbil(bilRegister);
	} else {
	    System.out.println("ERROR! filter er ugyldig, filter gitt: " + filter[1]);
	    System.out.println("For aa kjoere programmet gjoer foelgende:");
	    System.out.println("java Oblig2 <inputfil> [filter]");
	    System.out.println("filter kan vaere EL eler FOSSIL");
	    System.out.println("Hvis filter ikke spesifiseres vil info om alle biler bli printet ut");
	}
    }
    
}

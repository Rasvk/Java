import java.util.Scanner;
public class Beslutninger {
    public static void main(String[] args) { 
	Scanner sc = new Scanner(System.in);
	System.out.print("Hvor gammel er du? ");
	String gammel = sc.nextLine();
	int alder = Integer.parseInt(gammel);
	if(alder < 18){
	    System.out.println("Du er ikke myndig.");
	}
	else{
	    System.out.println("Du er myndig.");
	}
    }
}
 

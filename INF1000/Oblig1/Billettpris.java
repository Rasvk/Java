import java.util.Scanner;
public class Billettpris{
    public static void main(String[] args) { 
	Scanner sc = new Scanner(System.in);
	int voksen = 50 ;
	int honnor = voksen/2;
	System.out.print("Hvor gammel er du? ");
	String gammel = sc.nextLine();
	int alder = Integer.parseInt(gammel);
	
	if(alder < 12 || alder > 67){
	    System.out.println("Pris for din billett: " + honnor + "kroner");
	}
	else{
	    System.out.println("Pris for din billett " + voksen + "kroner");
	}
    }
}

import java.util.Scanner;
public class HeiStudent{
    public static void main(String[] args) { 
	/* String navn = "Rasmus V. Krog";
    int en = 1;
    int to = 2;
    System.out.println("Summen av tallene er: " + en + to);
    
    System.out.println("Hei " + navn );
    System.out.println("Hei Student!"); */
	Scanner sc = new Scanner(System.in);
	System.out.print("Skriv inn tall en: ");
         String i = sc.nextLine();
         int s = Integer.parseInt(i);
     System.out.print("Skriv inn tall to: ");
         String j = sc.nextLine();
         int t = Integer.parseInt(j);
         System.out.println("Summen av tallene er :");
         System.out.println(s+t);
  }
}

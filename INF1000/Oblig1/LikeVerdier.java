import java.util.Scanner;
    public class LikeVerdier {
    public static void main(String[] args) { 
	Scanner sc = new Scanner(System.in);
	System.out.print("Skriv inn et tall til variabelen c: ");
	String a = sc.nextLine();
	int c = Integer.parseInt(a);
	System.out.print("Skriv inn et tall til variabelen d ");
	String b = sc.nextLine();
	int d = Integer.parseInt(b);
      if(c == d) {
	  System.out.println("c og d er like.");
      }
      else {
	  System.out.println("c er ikke lik d.");
      }
    }
    }
    
    
    

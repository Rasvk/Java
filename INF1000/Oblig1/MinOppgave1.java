
/*      Oppgavetekst MinOppgave1

Lag en Matematikk prøve med ti spørsmål. Programmet skal ta input fra brukeren og sjekke opp mot fasit for
saa aa gi brukeren tilbakemelding om svaret er riktig eller galt. Hvert riktige svar skal gi bruker 1 poeng.
Etter bruker er ferdig med prøven skal han/hun få tilbakemelding i form av karakter. Karakterskalaen er som følger representert ved antall riktige:
A >= 9
B >= 7
C >= 5
D >= 4
E >= 3
F <  3
 */

import java.util.Scanner;
public class MinOppgave1 {
    public static void main(String[] args) { 
	Scanner sc = new Scanner(System.in);
        
	// deklarerer nå verdier som skal helpe til aa gi en karakter til bruker.
	int poeng = 0;
        int A = 9;
	int B = 7;
	int C = 5;
	int D = 4;
	int E = 3;
	
	System.out.println("Denne prÃ¸ven vil sjekke hvor mange riktige svar du har gitt for saa aa  gi deg en karakter etter hvor godt du gjorde det. Lykke til!");
	
	System.out.print("Oppgave 1: Hva er verdien til x? x+2=8 ");
	int fasit = 8-2;
	String inputSvar = sc.nextLine();
	int svar = Integer.parseInt(inputSvar);
	if(svar == fasit){
	    System.out.println("Gratulerer svaret er riktig");
	    poeng++;
	    }
	else{
	    System.out.println("Dessverre svaret er feil");
		}
	/*ønsker ikke beholde verdien i variabelen fasit inputSvar eller svar bruker derfor samme siden oppgave ikke stiller krav til å gi bruker tilbakemelding på hva de svarte og hva fasit sier i til slutt*/ 
	System.out.print("Oppgave 2: 60+2/2 ");
	fasit = 60+2/2;
	inputSvar = sc.nextLine();
	svar = Integer.parseInt(inputSvar);
	if(svar == fasit){
	System.out.println("Gratulerer svaret er riktig");
	poeng++;
	    }
	else{
	    System.out.println("Dessverre svaret er feil");
		}

	System.out.print("Oppgave 3: 43-77 ");
	fasit = 43-77;
	inputSvar = sc.nextLine();
	svar = Integer.parseInt(inputSvar); 
	if(svar == fasit){
	System.out.println("Gratulerer svaret er riktig");
	poeng++;
	    }
	else{
	    System.out.println("Dessverre svaret er feil");
		}
	System.out.print("Oppgave 4: (26-22)*5 ");
	fasit = (26-22)*5;
	inputSvar = sc.nextLine();
	svar = Integer.parseInt(inputSvar);
	if(svar == fasit){
	System.out.println("Gratulerer svaret er riktig");
	poeng++;
	    }
	else{
	    System.out.println("Dessverre svaret er feil");
		}

	System.out.print("Oppgave 5: 120*30 ");
	fasit = 120*30;
	inputSvar = sc.nextLine();
	svar = Integer.parseInt(inputSvar);
	if(svar == fasit){
	System.out.println("Gratulerer svaret er riktig");
	poeng++;
	    }
	else{
	    System.out.println("Dessverre svaret er feil");
		}
	
	System.out.print("Oppgave 6: 2+2-2*0 ");
	fasit = 2+2-2*0;
	inputSvar = sc.nextLine();
	svar = Integer.parseInt(inputSvar);
	if(svar == fasit){
	System.out.println("Gratulerer svaret er riktig");
	poeng++;
	    }
	else{
	    System.out.println("Dessverre svaret er feil");
		}
	// Deklarerer ny variabel av typen double for at regnestykke skal ha løsning
	System.out.print("Oppgave 7: 40 delt paa 0.5 pluss 20 hva blir svaret? ");
	double fasitDouble = 40/0.5+20;
	/* Gjør double variablen om til en int, denne metoden er grei siden utregningene ikke gir tall etter komma og taper ikke presisjon fordi det ikke blir avrundet*/
	fasit = (int)fasitDouble;
	inputSvar = sc.nextLine();
	double svarDouble = Double.parseDouble(inputSvar);
	svar = (int)svarDouble;
	/*oensker at bruker kan svare uten desimaler, men bruker faar ikke feil ved bruk av
	  et gjeldene siffer etter komma*/
	if(svar == fasit || svarDouble == fasitDouble){
	System.out.println("Gratulerer svaret er riktig");
	poeng++;
	    }
	else{
	    System.out.println("Dessverre svaret er feil det riktige svaret er: " );
		}

	System.out.print("Oppgave 8: (10+2)*2 ");
	fasit = (10+2)*2;
	inputSvar = sc.nextLine();
	svar = Integer.parseInt(inputSvar);
	if(svar == fasit){
	System.out.println("Gratulerer svaret er riktig");
	poeng++;
	    }
	else{
	    System.out.println("Dessverre svaret er feil");
		}

	System.out.print("Oppgave 9: (88-100)*(-2) ");
	fasit = (88-100)*(-2);
	inputSvar = sc.nextLine();
	svar = Integer.parseInt(inputSvar);
	if(svar == fasit){
	System.out.println("Gratulerer svaret er riktig");
	poeng++;
	    }
	else{
	    System.out.println("Dessverre svaret er feil");
		}

	System.out.print("Oppgave 10:hva er verdien til x? 4+4x=-4 ");
	fasit =((-4 - 4)/4);
	inputSvar = sc.nextLine();
	svar = Integer.parseInt(inputSvar);
	if(svar == fasit){
	System.out.println("Gratulerer svaret er riktig");
	poeng++;
	    }
	else{
	    System.out.println("Dessverre svaret er feil" );
	}
        
	// Sjekker hvilken karakter bruker faar ut fra hvor mange spQrsmaal som var besvartriktig
        if(poeng < E) {
	    System.out.println("Du har desverre fått karakteren F, og du hadde " + poeng + " riktige");
	} 
	else if(poeng >= E && poeng < D) {
	    System.out.println("Du kunne nok Qvd litt mer, du fikk karakter E, og du hadde " + poeng + " riktige");
	}
	else if(poeng >=D && poeng < C) {
	    System.out.println("Du har nok fortsatt litt aa gaa paa, du fikk karakter D, og hadde " + poeng + " riktige");
	}
	else if(poeng >= C && poeng < B) {
	    System.out.println("Ikke vaerst du fikk karakter C, og du hadde " + poeng + " ritkige");
	}
	else if(poeng >= B && poeng < A){
	    System.out.println("Bra! Du fikk karakter B, og du hadde " + poeng + " riktige");
	}
	else{
	    System.out.println("Imponerende! Du har nok forberedet deg godt. Din karater er: A, du hadde " + poeng + " riktige");
	}
    }
}

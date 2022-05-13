import java.util.Scanner;
public class PersonRegister {
public static void main(String[] args) {
Scanner innleser = new Scanner(System.in);
String navn = "Ikke satt";
String bosted = "Ikke satt";
int valg = 0;
printMeny();
valg = Integer.parseInt(innleser.nextLine());
if (valg == 1) {
registrerPerson();
navn = innleser.nextLine();
bosted = innleser.nextLine();
}
if (valg == 2) {
printPerson(navn, bosted);
}
printMeny();
valg = Integer.parseInt(innleser.nextLine());
if (valg == 1) {
registrerPerson();
navn = innleser.nextLine();
bosted = innleser.nextLine();
}
if (valg == 2) {
printPerson(navn, bosted);
}
}
// metode for aa gjore valg fra brukeren
static void printMeny() {
System.out.println("1. Registrer en person");
System.out.println("2. Print personen");
}
// metode for aa skrive ut beskjed om aa registrere en person
static void registrerPerson() {
System.out.println("Skriv inn navnet, trykk enter, og skriv saa inn bostedet og trykk enter");
}
// metode for å printe en person
static void printPerson(String navnForUskrift, String bostedForUskrift) {
    System.out.println("Du har registert personen: " + navnForUskrift + " fra " + bostedForUskrift);
}

}

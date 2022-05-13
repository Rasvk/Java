import java.util.*;
import java.util.concurrent.*;

public class Oblig2 {
    private double[][] a, b;

    /**
     * konstruktor for klasse Oblig2 initialiserer a og b 2d arrayene
     * @param n dimensjoner paa matriser
     */
    Oblig2(int n) {
        a = new double[n][n];
        b = new double[n][n];
    }

    /**
     * maint metode sjekker om argument lengde er 1 hvis ikke viser feilmelding, burde vaert en sjekk om argumentet er en int
     * og kanskje hvor mange kjoringer man vil gjore.
     * @param args
     */
    public static void main(String[] args) {

        double[][] a, b; // matriser som skal multipliseres
        int n;
        if (args.length != 1) {
            System.out.println("Kjor med > java Oblig2 [lengde og bredde n]");
        } else {

            n = Integer.parseInt(args[0]);
            Oblig2 o2 = new Oblig2(n);
            o2.startProgramm(n);
        }
    }

    /**
     * starter sekvensiell og parallell matrise multiplisering lagrer resultat tider i sRes og pRes og kaller paa test()
     * for aa skrive ut mediantidene etter 7 kjoringer.
     *
     * @param n
     */
    public void startProgramm(int n) {
        double[] sRes = new double[7];
        double[] pRes = new double[7];

        for(int i = 0; i < sRes.length; i++) {
            sRes[i] = sekvensiellMM(n);
            pRes[i] = parallellMM(n);
        }
        test(sRes, pRes);
    }

    /**
     * setter/resetter verdiene i matrisene, transponerer matrise b lager og initialiserer matrise c multipliserer saa
     * matrisene sekvensielt, returnerer tid brukt i ms.
     *
     * @param n dimensjonene til matrisene
     * @return tid brukt paa sekvensiell multiplisering av matriser i ms.
     */
    public double sekvensiellMM(int n) {
        resetMatriser();
        long start = System.nanoTime();
        transponerMatrise(b,n);
        double[][] c = new double[n][n];
        multipliserMatriser(a,b,c,n,0,n);
        return(System.nanoTime() - start) / 1000000.0;
    }

    /**
     * setter/resetter verdiene i matrisene bruker samme seed hver gang, faar samme tall, transponerer matrise b,
     * lager og initialiserer matrise c som er resultatmatrisen finner antTraader = antall kjerner som maskinen har ledig
     * deler inn antall elementer til hver traad, setter opp en cyclicBarrier siden det leses og skrives fra samme objekt,
     * kan ingen traader gjore det samtidig, bruker derfor en cyclicBarrier slik at det kun blir et kall pr traad setter
     * venstre og hoyre bound for hver traads "ansvarsfelt" av matrisen hvis i == antTraader-1 skal hoyre bound være max dimensjon
     * loser problem hvor antTraader kan ha blitt rundet ned pga heltallsdiv. lager nye traader og starter disse venter til disse er ferdig
     * printer ut test og returnerer tid i ms.
     *
     * @param n dimensjon paa matrisene
     * @return tid i ms for sekvensiell losning av oppgaven.
     */
    public double parallellMM(int n) {
        resetMatriser();
        long start = System.nanoTime();
        double[][] c = new double[n][n];
        transponerMatrise(b,n);
        int antTraader = Runtime.getRuntime().availableProcessors();
        int elementerPrTraad = n / antTraader;
        CyclicBarrier ferdig = new CyclicBarrier(antTraader + 1);

        for(int i = 0; i < antTraader; i++) {
            int venstre = i * elementerPrTraad;
            int hoyre = (i+1) * elementerPrTraad;
            //sjekk om siste traad
            if(i == antTraader-1){
                hoyre = n;
            }
            new Thread(new Para(a,b,c,n,venstre,hoyre,ferdig)).start();
        }
        try {
            ferdig.await();
        } catch(Exception e) {
            System.exit(1);
        }
        testMM(a, b, c);
        return(System.nanoTime() - start) / 1000000.0;
    }

    /**
     * multipliserer sammen to matriser, antar at matrise b er transponert allerede. Saa paa piazza at vi ikke skulle
     * gjore noe for ikke transponerte matriser saa droppet det fra min losning.
     *
     * param a matrise som skal multipliseres med b array
     * @param b matrise som skal multipliseres med a
     * @param c resultat matrise
     * @param n maal for dimensjoner av matrisene
     * @param venstre venstre bound for traadens arbeidsomraade
     * @param hoyre hoyre bound for traadens arbeidsomraade
     * @param ferdig CyclicBarrier
     */
    public void multipliserMatriser(double[][] a, double[][] b, double[][] c, int n, int venstre, int hoyre) {
        for (int i=0;i < n ;i++) {
            // for each row i C
            for (int j= venstre;j < hoyre ;j++) {
                // for each collumn in C
                for (int k=0;k < n ;k++){
                    c[i][j] += a[i][k]*b[j][k];
                }

            } // end j
        } // end i
    }



    /**
     *  Setter random verdier inn i a og b mellom 0.0 og 1.0, brukes i starten av hvert kall paa sekvensiellMM() og
     *  parallellMM().
     */
    public void resetMatriser() {
        Random r = new Random(6734);
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                a[i][j] = r.nextDouble(); // fix with next random
            }
        }

        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                b[i][j] = r.nextDouble(); // same as above
            }
        }

    }

    /**
     * printer ut en 2d array, brukt for debug.
     * @param c
     */
    public void printC(double[][] c) {
        for(int i = 0; i < c.length; i++) {
            for(int j = 0; j < c[0].length; j++){
                System.out.print(c[i][j] + ", ");
            }
            System.out.println("");
        }
    }

    /**
     * printer ut mediantiden i ms. for sekvensiell og parallell losning
     * @param sRes
     * @param pRes
     */
    public void test(double[] sRes, double[] pRes) {
        Arrays.sort(sRes);
        Arrays.sort(pRes);
        System.out.println("Mediantiden for sekvensielle multiplisering er:" + (sRes[sRes.length/2]) + " ms");
        System.out.println("Mediantiden for parallell multiplisering er:" + (pRes[pRes.length/2]) + " ms");

    }

    /**
     * metode brukes for aa transponere 2d arrayen a
     *
     * @param a, array som skal transponeres.
     */
    public void transponerMatrise(double[][] a, int n) {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < i; j++) {
                double tmp = a[i][j];
                a[i][j] = a[j][i];
                a[j][i] = tmp;
            }
        }
    }

    /**
     * Tester om matrisemultiplikasjonen har gaatt som forventet, kan ikke sammenlikne double med 0 pga unoyaktighet ved datatypen
     * double kan vaere veldig naerme 0.0, men ikke helt og vil gi utslag paa testen, satt akseptabel feilmargin til 0.001.
     *
     * @param a array a skal multipliseres med b
     * @param b array b skal multipliseres med a
     * @param c array c resultat
     */
    public void testMM(double[][] a, double[][] b, double[][] c) {
        int n = c.length;
        double[][] d = new double[n][n];
        multipliserMatriser(a, b, d,0, n, n);
        for(int i = 0; i < n; i++) {
            for(int j = 0; i < n; i++) {
                d[i][j] -= c[i][j];
                if(d[i][j] >= 0.001) {
                    System.out.println("Feil i multiplisering av matrise paa posisjon: " + i + " ," + j + " resultat er: " + d[i][j] );
                }
            }
        }
    }

    /**
     * implementerer Runnable og overrider run()
     */
    class Para implements Runnable {
        int n, venstre, hoyre;
        double[][] a, b, c;
        CyclicBarrier ferdig;

        /**
         *
         * @param a matrise som skal multipliseres med b array
         * @param b matrise som skal multipliseres med a
         * @param c resultat matrise
         * @param n maal for dimensjoner av matrisene
         * @param venstre venstre bound for traadens arbeidsomraade
         * @param hoyre hoyre bound for traadens arbeidsomraade
         * @param ferdig CyclicBarrier
         */
        Para(double[][] a, double[][] b, double[][] c, int n, int venstre, int hoyre, CyclicBarrier ferdig) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.n = n;
            this.venstre = venstre;
            this.hoyre = hoyre;
            this.ferdig = ferdig;
        }

        /**
         * kaller paa multipliserMatriser() metoden og venter til traader er ferdig avslutter hvis exception
         * @override run
         */
        public void run(){
            multipliserMatriser(a, b, c, venstre, hoyre, n);
            try {
                ferdig.await();
            } catch(Exception e) {
                System.exit(1);
            }
        }
    }
}

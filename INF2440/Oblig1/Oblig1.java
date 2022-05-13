import java.util.*;

/**
 *
 */
public class Oblig1 {

    /**
     * Main metode for Oblig1 printer en hjelpemelding hvis bruker gir med feil mengde parametere til programmet
     * @param args
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Kjor med > java Oblig1Parallell [k storste tall] [antall elementer]");
        } else {
            int k = Integer.parseInt(args[0]);
            int n = Integer.parseInt(args[1]);

            Random r = new Random(7361);
            int[] arr = new int[n];
            int[] sortCheck = new int[n];

            long[] sort = new long[7];
            long[] innstikk = new long[7];


            for (int i = 0; i < n; i++) {
                int tmp = r.nextInt(n);
                arr[i] = tmp;
                sortCheck[i] = tmp;
            }

            // fyller sort med kjoretider og sorterer array
            for (int i = 0; i < sort.length; i++) {
                long startTid = System.nanoTime();
                Arrays.sort(arr);
                long sluttTid = System.nanoTime();
                sort[i] = (sluttTid - startTid);
                Random r1 = new Random(7361);
                for (int j = 0; j < n; j++) {
                    arr[j] = r1.nextInt(n);
                }
            }

            // fyller innstikk med kjoretider og sorterer aray
            for (int i = 0; i < sort.length; i++) {
                long startTid = System.nanoTime();
                sekvensielSort(arr, n, k);
                long sluttTid = System.nanoTime();
                innstikk[i] = (sluttTid - startTid);
                test(arr, sortCheck, k);
                Random r1 = new Random(7361);
                for (int j = 0; j < n; j++) {
                    arr[j] = r1.nextInt(n);
                }
            }

            // skriver ut mediantid
            medianTid(innstikk, sort);
        }
    } // end main

    /**
     * starter algoritmen for sekvensiell sortering beskrevet i oppgaveteksten
     *
     * @param arr
     * @param n
     * @param k
     */
    public static void sekvensielSort(int[] arr, int n, int k) {
        arr = innstikk(arr, 0, k - 1);
        arr = rest(arr, n, k);
    }

    /**
     * standard innstikk sort som sorterer i synkende rekkefolge
     *
     * @param a[] array som skal sorteres
     * @param v   sorter fra
     * @param h   sorter til
     */
    public static int[] innstikk(int[] arr, int v, int h) {
        int i, t;
        for (int k = v; k < h; k++) {
            t = arr[k + 1];
            i = k;
            while (i >= v && arr[i] < t) {
                arr[i + 1] = arr[i];
                i--;
            }
            arr[i + 1] = t;
        }
        return arr;
    }

    /**
     * Sammenlikner det minste tallet i de k storste med resten av arrayen finner den et tall som er storre
     * bytter disse to plass, kaller så ettElementInnstikkSort for aa finne elementets plass i de k storste.
     * @param arr
     * @param n
     * @param k
     * @return
     */
    public static int[] rest(int[] arr, int n, int k) {
        for (int j = k; j < n; j++) {
            if (arr[j] > arr[k - 1]) {
                int tmp = arr[k - 1];
                arr[k - 1] = arr[j];
                arr[j] = tmp;
                arr = ettElementInnstikkSort(arr, 0, k - 1);
            }
        }
        return arr;
    }

    /**
     * Sorterer i stigende rekkefølge, finner ut om siste element i de k storste staar paa riktig plass i arrayen
     * @param arr
     * @param v
     * @param h
     * @return
     */
    public static int[] ettElementInnstikkSort(int[] arr, int v, int h) {
        for (int i = h - 1; i >= 0; i--) {
            if (arr[h] > arr[i]) {
                int tmp = arr[i];
                arr[i] = arr[h];
                arr[h] = tmp;
                h--;
            }
        }
        return arr;
    }

    /**
     * Finner og printer ut mediankjoretid til innstikk sorteringen og Arrays.sort
     * @param innstikk array med tider for innstikk sort
     * @param sort array med tider for Arrays.sort
     */
    public static void medianTid(long[] innstikk, long[] sort) {
        Arrays.sort(innstikk);
        Arrays.sort(sort);
        System.out.println("Mediantiden for sekvensiell instikksortering er:" + (innstikk[(innstikk.length) / 2]) / 1000000.0 + " ms");
        System.out.println("Mediantiden for Arrays.sort er:" + (sort[(sort.length) / 2]) / 1000000.0 + " ms");
    }

    /**
     * Enkel test for aa sjekke om arrayet har blitt sortert riktig.
     * @param arr
     * @param sortCheck
     * @param k
     */
    public static void test(int[] arr, int sortCheck[], int k) {
        Arrays.sort(sortCheck);
        for (int i = 0; i < k; i++) {
            if (arr[i] != sortCheck[(sortCheck.length - 1) - i]) {
                System.out.println("FEIL! Arrayen har ikke blitt sortert korrekt");
                return;
            }
        }
        System.out.println("RIKTIG!");
    }

} //end Oblig1

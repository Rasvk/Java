import java.util.*;
import java.util.concurrent.*;

public class Oblig1Parallell extends Oblig1 {
    int[] arr;
    int antKjerner, antTraader;
    static int n, k;
    CyclicBarrier vent, ferdig;
    volatile boolean stop = false;
    int elementerPrTraad;

    /**
     * Finner antall kjerner og antall traader, deklarer CyclicBarrier(antTraader +1) for aa faa med main ogsaa
     * regner ut hvor mange elementer hver traad skal behandle, starter traadene.
     *
     * @param param
     */
    void initTraader(int[] param) {
        antKjerner = Runtime.getRuntime().availableProcessors();
        antTraader = antKjerner;
        vent = new CyclicBarrier(antTraader + 1);
        ferdig = new CyclicBarrier(antTraader + 1);
        elementerPrTraad = n / antTraader;

        for (int i = 0; i < antTraader; i++) {
            new Thread(new Para(i)).start();
        }
    } //end initTraader

    /**
     * kaller initTraader hvis cyclicBarrier ikke er satt opp, venter til alle traader er ferdig  for mergeSort kalles
     * for aa slaa sammen arbeidsomraadet til alle traadene
     *
     * @param arr
     */
    void paraSort(int[] arr) {
        if (vent == null) {
            initTraader(arr);
        }

        try {
            vent.await();
        } catch (Exception e) {

        }

        try {
            ferdig.await();
        } catch (Exception e) {

        }
        mergeSort(arr);
    }

    /**
     * Slaar sammen de sorterte omraadene fra traadene og sorterer de slik at de storste elementene havner forst i
     * arrayen
     *
     * @param arr
     */
    void mergeSort(int[] arr) {
        int t, j;
        for (int l = elementerPrTraad; (l + k - 1) < n; l += elementerPrTraad) {
            for (int i = l + k - 1; i >= l; i--) {
                if (arr[i] > arr[k - 1]) {
                    t = arr[i];
                    arr[i] = arr[k - 1];
                    j = k - 2;
                    while (j >= 0 && t > arr[j]) {
                        arr[j + 1] = arr[j];
                        j--;
                    }
                    arr[j + 1] = t;
                }
            }
        }
    }

    /**
     * starter den parallelle sorteringen i oppgaven internt i traadene
     * @param arr
     * @param left
     * @param right
     */
    void parallellSort(int[] arr, int left, int right) {
        parallellInstikk(arr, left, right);
        parallellRest(arr, left, right);
    }

    /**
     * innstikk sortering for traader slik at hver traad kun jobber paa sitt felt.
     *
     * @param arr
     * @param left
     * @param right
     */
    void parallellInstikk(int[] arr, int left, int right) {
        int j, t;
        for (int i = left + k - 1; i >= left; i--) {
            j = i;
            t = arr[i];
            while (j < left + k - 1 && t < arr[j + 1]) {
                arr[j] = arr[j + 1];
                j++;
            }
            arr[j] = t;
        }
    }

    /**
     * sorterer alle elementer utenfor de k storste for hver traad.
     *
     * @param arr
     * @param left
     * @param right
     */
    void parallellRest(int[] arr, int left, int right) {
        int t, j, s = 49;
        for (int i = left + k; i <= right; i++) {
            if (arr[i] > arr[left + k - 1]) {
                t = arr[i];
                arr[i] = arr[left + s];
                j = left + s-1;
                while (j >= left && t > arr[j]) {
                    arr[j + 1] = arr[j];
                    j--;
                }
                arr[j + 1] = t;
            }
        }
    }

    /**
     * lager en array som brukes for aa sjekke om sortering blir gjort riktig, finner mediantiden for 7 kjoringer
     * av parallell sortering skriver saa ut mediantiden setter stop til true og venter paa at alle traader skal bli ferdig
     * @param n
     */
    void parallellTest(int n) {
        arr = new int[n];
        int[] sortCheck = new int[n];
        Random r = new Random(7361);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(arr.length);
            sortCheck[i] = arr[i];
        }

        long[] parallell = new long[7];
        for (int i = 0; i < 7; i++) {
            long startTid = System.nanoTime();
            paraSort(arr);
            long sluttTid = System.nanoTime();
            parallell[i] = (sluttTid - startTid);
            test(arr, sortCheck, k);
            Random r2 = new Random(7361);
            for (int j = 0; j < n; j++) {
                arr[j] = r2.nextInt(n);
            }
        }
        System.out.println("Mediantiden fir parallell innstikk er: " + (parallell[(parallell.length) / 2]) / 1000000.0 + " ms");

        stop = true;
        try {
            vent.await();
        } catch (Exception e) {

        }

        try {
            ferdig.await();
        } catch (Exception e) {

        }
    }

    /**
     * implementerer Runnable of overrider run().
     */
    class Para implements Runnable {
        int id;

        Para(int id) {
            this.id = id;
        }

        /**
         * starter sorteringen for traaden kjorer til stop blir satt til true.
         */
        public void run() {
            while (!stop) {
                try {
                    vent.await();
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                    return;
                }

                sorterLokalt(id);

                try {
                    ferdig.await();
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }

        /**
         * starter sorteringen til traadene med riktig hoyre venstre bounds
         * @param id
         */
        void sorterLokalt(int id) {
            if (id != antTraader - 1) {
                parallellSort(arr, elementerPrTraad * id, (elementerPrTraad * (id + 1)) - 1);
            } else {
                parallellSort(arr, elementerPrTraad * id, n - 1);
            }
        }
    } //end para

    /**
     * main metode sjekker om bruker har gitt med riktig parametere, hvis ikke print feilmelding, kjorer saa sorteringen
     * @param args
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Kjor med > java Oblig1Parallell [k storste tall] [antall elementer]");
        } else {
            k = Integer.parseInt(args[0]);
            n = Integer.parseInt(args[1]);
            new Oblig1Parallell().parallellTest(n);
        }
    }
} //end Oblig1Parallell

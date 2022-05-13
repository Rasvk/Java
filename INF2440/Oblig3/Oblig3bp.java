import java.util.*;
import java.util.concurrent.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Oblig3bp {
    boolean[] primes;
    int[] sqrtPrimes;
    int n;
    int k; // antall traader
    int[] thePrimes;
    long[] theNumbers;
    final boolean DEBUG = true;
    CyclicBarrier done, waiting;

    /**
     *  Constructor for parallell part
     * @param n
     * @param k
     */
    Oblig3bp(int n, int k) {
        this.n = n;
        this.k = k;
        primes = new boolean[n];
    }

    public static void main(String[] args) {
        int n = 0, k = 0;
        if (args.length != 2) {
            System.out.println("Use error: please start program with java Oblig3bp n k");
            System.out.println("N is number to search for primes less than n");
            System.out.println("K is number of threads to use, if zero all availible threads will be used");
        } else {
            try {
                n = Integer.parseInt(args[0]);
                k = Integer.parseInt(args[1]);
            } catch (Exception e) {
                System.out.println("Error! something went wrong, possibly non int as argument, or argument too large");
            }

            if (n == 0) {
                System.out.println("N not properly set exiting program");
                System.exit(1);
            }

            Oblig3bp o3p = new Oblig3bp(n,k);
            o3p.setPrimes();
            o3p.findSqrtNPrimes((int)Math.sqrt(n));
            o3p.sqrtPrimes = o3p.booleanToIntArray(o3p.nrFoundSqrtN());
            double timep = o3p.initiatePrimes(o3p);
            System.out.println("Time prime search: " +timep);
            System.out.println("Primes found: " + o3p.nrFound());
            o3p.thePrimes = o3p.booleanToIntArray(o3p.nrFound());
            o3p.theNumbers = o3p.qubed();
            o3p.paraFactStart(o3p);
           // o3p.printPrime();
        }
    }

    /**
     * computes threads needed
     * @param k
     * @return
     */
    public int nrThreads(int k){
        int nrThreads;
        if(k == 0) {
            nrThreads = Runtime.getRuntime().availableProcessors();
        } else {
            nrThreads = k;
        }
        return nrThreads;
    }

    /**
     * sets the boolean array to corresponding values true = possible prime, false = even number wich we disregard
     */
    public void setPrimes(){
        for(int i = 1; i < n; i++) {
            primes[i] = true;
            if(i % 2 == 0) {
                primes[i-1] = false;
            }
        }
        primes[n-1] = false;
        primes[1] = true;

    }

    /**
     * Finds all primes < square root of n
     * @param r
     */
    public void findSqrtNPrimes(int r) {

        for(long i = 2; i < r; i++) {
            // check if crossed out
            if(primes[(int)i-1]) {

                for(long j = i*i; j < r; j += i) {
                    //sqrtPrimes[(int)j-1] = false;
                    primes[(int)j-1] = false;
                }
            }
        }
    }

  /**
     * returns total number of primes found
     * @return i nr of primes found
     */
    public int nrFound() {
        int i = 0;
        for(boolean b: primes) {
            if(b) {
                i++;
            }
        }
        return i;
    }

    /**
     * finds all primes in a given interval
     * @param left bound of interval
     * @param right bound of interval
     */
    public void findPrimesInInterval(int left, int right) {
        System.out.println("Searching interval (" + left + ", " + right +")");
        for(int j = 0; j < sqrtPrimes.length; j++) {
            int currentPrime = sqrtPrimes[j];

            for (int i = left; i < right; i+=2) {

                if(i % currentPrime == 0) {
                    primes[(int)i-1] = false;
                }
            }
        }
        System.out.println("Found all primes in interval");
    }

    /**
     * turns boolean array into int array with the correct values
     * @param nrOfPrimes
     * @return int array with primes
     */
    public int[] booleanToIntArray(int nrOfPrimes) {
        int i = 1, j = 0;
        int[] primesArr = new int[nrOfPrimes];
        for(boolean b: primes) {
            if(b && j < primesArr.length) {
                primesArr[j++] = i;
            }
            i++;
        }
        return primesArr;
    }

    /**
     * returns number of primes found less than square root of n
     * @return i nr of primes found
     */
    public int nrFoundSqrtN() {
        int i = 0, j = 1;
        for(boolean b: primes) {
            if(b && j < Math.sqrt(n)) {
                i++;
            }
            j++;
        }
        return i;
    }

    /**
     *  prints the primes found with nr in front.
     */
    public void printPrime() {
        int i = 1, k = 1;
        for(boolean b: primes) {

            if(b && i < n) {
                System.out.printf("%02d %02d\n",k++,i);
            }
            i++;
        }
    }


    /**
     * initiates the threads searching for prime numbers with sieve.
     * @param o3p
     * @return time task takes
     */
    public double initiatePrimes(Oblig3bp o3p) {
        long start = System.nanoTime();
        int numThreads = o3p.nrThreads(k);
        done = new CyclicBarrier(numThreads + 1);
        int sqrtN = (int)Math.sqrt(n);
        int nrElements = n - sqrtN;
        int elementsPrThread = nrElements/numThreads;
        //System.out.println("Number of threads: " + numThreads + " elements: " + (n-nrElements));
        //System.out.println("elements per thread: " + elementsPrThread);
        for(int i = 0; i < numThreads; i++) {
            int left = sqrtN + i * elementsPrThread;
            int right = sqrtN +(i+1) * elementsPrThread;

            if(i == numThreads-1) {
                right = n;
            }

            if(left % 2 == 0) {
                //System.out.println("Left Before: " + left);
                primes[left-1] = false;
                left+= 1;
                //System.out.println("Left after: " + left);
            }

            System.out.println("Left after: " + left);
            new Thread(new ParaPrime(left, right, o3p)).start();
        }
        try {
            done.await();
        } catch(Exception e){
            System.exit(1);
        }
        return(System.nanoTime() - start) / 1000000.0;
    }

    /**
     * starts up threads and waits for them to finnish to find tand return all factors of a number
     * @param o3p
     * @return
     */
    public ArrayList<Long> initiateFactorization(Oblig3bp o3p, long nr) {

        int numThreads = o3p.nrThreads(k);
        done = new CyclicBarrier(numThreads + 1);
        waiting = new CyclicBarrier(numThreads + 1);
        int elementsPrThread = o3p.thePrimes.length/numThreads;
        ArrayList<Long> facts = new ArrayList<Long>();
        ArrayList<ArrayList<Long>> allFacts = new ArrayList<ArrayList<Long>>();
        //System.out.println("Number of elements: " + elementsPrThread);
        ParaFact[] pf = new ParaFact[numThreads];

        //start the threads
        for(int i = 0; i < numThreads; i++) {
            long left = i * elementsPrThread;
            long right = (i + 1) * elementsPrThread;

            if (i == (numThreads - 1)) {
                right = thePrimes.length-1;
            }
            new Thread(pf[i] = new ParaFact(nr, left, right, o3p)).start();
        }
        // wait and calculate results
        try {
            waiting.await();
            for(ParaFact parFa: pf) {
                allFacts.add(parFa.getFacts());
            }
            done.await();
        } catch(Exception e){
            System.exit(1);
        }
        long prod = 1;
        for(ArrayList<Long> list : allFacts) {
            for(long num : list) {
                facts.add(num);
                prod *= num;
            }
        }

        if(prod != nr) { // I believe this is the root of problems with non prime factors
            facts.add(nr / prod);
            prod *= nr/ prod;
        }
        return facts;
    }

    /**
     * Starts the parallell prime factorization and finds time task takes stores factors in a HashMap
     * Writes to file if DEBUG variable is set to true
     * @param o3p
     */
    public void paraFactStart(Oblig3bp o3p) {
        HashMap<Long, ArrayList<Long>> factors = new HashMap<Long, ArrayList<Long>>();
        double start = System.nanoTime();
        for(long i = 0; i < theNumbers.length; i++) {
            factors.put(theNumbers[(int)i],initiateFactorization(o3p, theNumbers[(int)i])); // might lose factors here?
        }
        double stop = (System.nanoTime() - start) / 1000000.0;

        if (o3p.DEBUG) {
            FactorPrintOut out = new FactorPrintOut("rasmusvk", n);
            for (Map.Entry<Long, ArrayList<Long>> entry : factors.entrySet()) {
                Long key = entry.getKey();
                ArrayList<Long> value = entry.getValue();
                for (Long l : value) {
                    out.addFactor(key, l);
                }
            }
            out.writeFactors();

        }
    }

    /**
     * Tests all primes given to a thread to see if they are factors in the number
     * @param left left bound of given primes
     * @param nr number to check if primes are factors in.
     * @param right right bound of given primes
     */
    public ArrayList<Long> paraFactorize(long nr ,long left, long right) {
        ArrayList<Long> factors = new ArrayList<Long>();
        //System.out.println("left and right: " + left + ", " + right);
        for(long i = left; i < right; i++) {
            long possibleFactor = (long)thePrimes[(int)i];
            if(nr % possibleFactor == 0) {
                nr /= possibleFactor;
                factors.add(possibleFactor);
            }
        }
        return factors;
    }
    /**
     * finds and stores n*n-100 -> n*n in an array
     * @return factor 100 largest values from n*n
     */
    public long[] qubed() {
        long qubed = (long)n*n;
        long[] factor = new long[(int)qubed];
        int j = 0;
        for(long i = qubed-100; i < qubed; i++, j++ ) {
            factor[j] = i;
        }
        return factor;
    }

    /**
     * class wich implements the runnable interface and overwrites the run() method class for parallell search for
     * primes
     */
    class ParaPrime implements Runnable {
        int left, right;
        Oblig3bp o3p;

        /**
         *  Constructor for paraPrime
         * @param left
         * @param right
         * @param o3p
         */
        ParaPrime(int left, int right, Oblig3bp o3p) {
            this.left = left;
            this.right = right;
            this.o3p = o3p;
        }

        /**
         * overwrites run calls findPrimesInInteval method to find this threads primes.
         */
        public void run() {
            findPrimesInInterval(left, right);
            try {
                done.await();
            } catch(Exception e){
                System.exit(1);
            }
        }
    }

    /**
     * class wich implements the runnable interface and overwrites the run() method, class for parallell prime
     * factorization.
     */
    class ParaFact implements Runnable {
        long left,  right, nr;
        Oblig3bp o3p;
        ArrayList<Long> facts;

        /**
         * Constructor ParaFact
         * @param left
         * @param right
         * @param o3p
         */
        ParaFact(long nr, long left, long right, Oblig3bp o3p) {
            this.left = left;
            this.right = right;
            this.nr = nr;
            this.o3p = o3p;
        }

        ArrayList<Long> getFacts() {
            return facts;
        }

        /**
         * calls the paraFactorize method
         */
        public void run() {
                facts = paraFactorize(nr, left, right);

            try {
                waiting.await();
                done.await();
            } catch(Exception e){
                System.exit(1);
            }
        }

    }
}
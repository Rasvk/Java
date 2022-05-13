import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Oblig3bool {
    int n;
    boolean [] primes;

    final boolean DEBUG = false;
    public Oblig3bool(int n) {
        this.n = n;

        primes = new boolean[n];
        //primes[1] = true;
    }

    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("Error! Run program with int N(find all primes less than N)");
            System.out.println("Example: java Oblig3bool 20");
        } else {
            int n = 0;
            try {
                n = Integer.parseInt(args[0]);

            } catch (Exception e) {
                System.out.println("Error! Something went wrong, non int as argument or argument too large");
                System.exit(1);
            }

            if (n == 0) {
                System.out.println("Error! N was not set properly exiting program.");
                System.exit(1);
            }


            Oblig3bool o3b = new Oblig3bool(n);
            HashMap<Long, ArrayList<Long>> primeFactors = new HashMap<Long,ArrayList<Long>>();

            o3b.setPrimes();
           primeFactors = o3b.initSequential(o3b, primeFactors);

            // need to set DEBUG to true if you want to write to file.
            if (o3b.DEBUG) {
            FactorPrintOut out = new FactorPrintOut("rasmusvk", n);
            for (Map.Entry<Long, ArrayList<Long>> entry : primeFactors.entrySet()) {
                Long key = entry.getKey();
                ArrayList<Long> value = entry.getValue();
                for (Long l : value) {
                    out.addFactor(key, l);
                }
            }
            out.writeFactors();

        }
    }


    }

    /**
     *  Finds all primes less than n by
     * @return
     */
    public double findAllPrimesLessThanN() {
        double start = System.nanoTime();
        int nrOfPrimes = 0;
        for(long i = 3; i < n; i+=2) {
            // check if crossed out
            if(primes[(int)i-1]) {
                nrOfPrimes++;
                for(long j = i*i; j < n; j += i) {
                       primes[(int)j-1] = false;
                }
            }
        }


        //printPrime();
        return (System.nanoTime() - start) / 1000000.0;
        //System.out.println(nrOfPrimes);

    }

    /**
     * prints primes found
     */
    public void printPrime() {
        int i = 1, k = 1;
        for(boolean b: primes) {

            if(b) {
                System.out.printf("%02d %02d\n",k++,i);
            }
            i++;
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
     * turns boolean array into int array with the correct values
     * @param nrOfPrimes
     * @return int array with primes
     */
    public int[] booleanToIntArray(int nrOfPrimes) {
        int i = 1, j = 0;
        int[] primesArr = new int[nrOfPrimes];
        for(boolean b: primes) {
            if(b) {
                primesArr[j++] = i;
            }
            i++;
        }
        return primesArr;
    }

    /**
     * sets the boolean array to corresponding values true = possible prime, false = even number wich we disregard
     */
    public void setPrimes(){
        for(int i = 1; i < n; i++) {
            if(!((i-1) % 2 == 0)) {
                primes[i] = true;
            }
        }
        primes[1] = true; // 2
        primes[n-1] = false;
    }

    /**
     * Method to do prime factorization loops first untill 2 is not a factor then checks if any odd/prime numbers are a factor
     * if not add rest as factor.
     * @param nr
     * @return all prime factors for a number
     */
    public ArrayList<Long> factorize(long nr) {
        ArrayList<Long> factors = new ArrayList<Long>();
        while(nr % 2 == 0) {
            nr /= 2;
            factors.add(2l);
        }

        for(long i = 3; i <= Math.sqrt(nr); i+= 2) {
            while(nr % i == 0) {
                nr /= i;
                factors.add(i);
            }
        }

        if(nr > 2) {
            factors.add(nr);
        }

        return factors;
    }

    /**
     * Factorizes all numbers less than n*n to n*n-100
     * @return HashMap with base number and all its  factors
     */
    public HashMap<Long, ArrayList<Long>> faktorizeAll(){
        long qubed = (long)n*n;
        //System.out.println(primesArr.length);


        HashMap<Long,ArrayList<Long>> factors = new HashMap<Long,ArrayList<Long>>();
        for(long i = qubed-100; i < qubed; i++) {
            factors.put(i,factorize(i));
        }
        return factors;
    }

    /**
     * Initiates the sequential algorithm and factorization and prints wanted data returns the HashMap with the factors and base numbers in
     * used for writing to file in the main method.
     * @param o3b
     * @param primeFactors
     * @return
     */
    public HashMap<Long, ArrayList<Long>> initSequential(Oblig3bool o3b, HashMap<Long, ArrayList<Long>> primeFactors) {

        double[] primeTimes = new double[7];
        double[] factorTime = new double[7];
        for(int i = 0; i < primeTimes.length; i++) {

            primeTimes[i]= o3b.findAllPrimesLessThanN();
            double start = System.nanoTime();
            primeFactors = o3b.faktorizeAll();
            factorTime[i] = (System.nanoTime() - start) / 1000000.0;
        }
        //o3b.printPrime(); // comment this out if not wanted
        System.out.println("********************************SEQUENTIAL SIEVE AND FACTORING********************************");
        System.out.println("Mediantime to find all primes less than N(" + n + "): " + primeTimes[3]);
        System.out.println("Mediantime to find prime factors of 100 largest numbers less than n*n(" +((long)n*n)+"): " + factorTime[3]);
        System.out.println("Primes found: " + o3b.nrFound());
        System.out.println("**********************************************************************************************");
        return primeFactors;
    }
}
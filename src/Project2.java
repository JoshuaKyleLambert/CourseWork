/**
 * Created by Joshua Lambert on 8/24/2017.
 * <p>
 * Java Implementation of Classical sort Algorithms Merge & Insertion sort
 * as found in Introduction to Algorithms 3rd ed.
 * <p>
 * This program will also output mean run times for performance testing.
 * <p>
 * An easy to view Histogram is also available.
 * <p>
 * Running the program makes usage clear.
 * <p>
 * Using the default Settings run the program and show the point of
 * inflection where merge sort becomes more efficient than insertion sort.
 * On my setup Merge sort appears to become more efficient N=~420.
 * <p>
 * <p>
 * DEFAULT VALUES:
 * iterations = 10000;
 * maxSize = 450;
 * stepSize = 1, markSize = 100, startSize = 400, offset = 15000;
 * <p>
 * <p>
 * SAMPLE PROGRAM OUTPUT  :
 * <p>
 * MERGE SORT:===================================================    N=418  MEANTIME: 20022ns
 * INSERTION SORT:###############################################  MEANTIME: 19655ns
 * MERGE SORT:==================================================    N=419  MEANTIME: 19982ns
 * INSERTION SORT:################################################  MEANTIME: 19712ns
 * MERGE SORT:======================================================    N=420  MEANTIME: 20319ns
 * INSERTION SORT:################################################  MEANTIME: 19733ns
 * MERGE SORT:====================================================    N=421  MEANTIME: 20169ns
 */
public class Project2 {

    public static void main(String[] args) {

        promptUser();

    }

    /*
    *   Sort Implementations
    */

    public static void insertionSort(int[] a) {
        int i;
        int key;
        for (int j = 1; j < a.length; j++) {
            key = a[j];
            i = j - 1;
            while (i >= 0 && a[i] > key) {
                a[i + 1] = a[i];
                i = i - 1;
            }
            a[i + 1] = key;
        }
    }

    public static void mergeSort(int[] a) {
        int p = 0;
        int r = a.length;
        mergeSort(a, p, r);

    }


    public static void merge(int[] A, int p, int q, int r) {

        int n1 = q - p;
        int n2 = r - q;
        int i, j;

        int[] L = new int[n1 + 1];
        int[] R = new int[n2 + 1];

        for (i = 0; i < n1; i++) {
            L[i] = A[p + i];
        }
        for (j = 0; j < n2; j++) {
            R[j] = A[q + j];
        }

        L[n1] = Integer.MAX_VALUE;
        R[n2] = Integer.MAX_VALUE;

        i = 0;
        j = 0;

        for (int k = p; k < r; k++) {
            if (L[i] <= R[j]) {
                A[k] = L[i];
                i = i + 1;
            } else {
                A[k] = R[j];
                j = j + 1;
            }
        }
    }

    public static void mergeSort(int[] A, int p, int r) {
        int q;

        if (p < r) {
            q = (p + r) / 2;
            // Floor function omitted since integer ignores decimal portion of number
            mergeSort(A, p, q);
            mergeSort(A, q + 1, r);
            merge(A, p, q, r);
        }
    }

    /*

    runTests() Calls the sort functions and prints results.

    size = N  = set size
    iterations
    1 mark = MEANTIME/marksize
    shorten the histogram by the offset amount
     */

    private static void runTests(int size, int iterations, int markSize, int offset) {

        long totalTime = 0;
        long beginTime, endTime;
        int[] sampleSet = new int[size];
        java.util.Random generator = new java.util.Random(2);

        for (int i = 0; i < size; i++) {
            sampleSet[i] = generator.nextInt();
        }

        int[] setCopy = sampleSet.clone();

        for (int i = 0; i < iterations; i++) {
            beginTime = System.nanoTime();
            mergeSort(sampleSet);
            endTime = System.nanoTime();
            totalTime += endTime - beginTime;
            sampleSet = setCopy.clone();
        }


        System.out.print("\n    MERGE SORT:");
        for (int i = offset; i < totalTime / iterations; i += markSize) {
            System.out.print("=");
        }
        System.out.print("    N=" + size);
        System.out.print("  MEANTIME: " + totalTime / iterations + "ns");

        sampleSet = setCopy.clone();
        totalTime = 0;

        for (int i = 0; i < iterations; i++) {
            beginTime = System.nanoTime();
            insertionSort(sampleSet);
            endTime = System.nanoTime();
            totalTime += endTime - beginTime;
            sampleSet = setCopy.clone();
        }

        //System.out.println("INSERTION SORT: N=" + size + " Runs=" + iterations +" Mean RunTime =  " + totalTime / iterations + "ns.");
        System.out.print("\nINSERTION SORT:");
        for (int i = offset; i < totalTime / iterations; i += markSize) {
            System.out.print("#");
        }
        System.out.print("  MEANTIME: " + totalTime / iterations + "ns");
    }

    private static void promptUser() {
        java.util.Scanner input = new java.util.Scanner(System.in);


        //Defaults
        int iterations = 10000;
        int maxSize = 450;
        int stepSize = 1, markSize = 100, startSize = 400, offset = 15000;

        int choice;


        System.out.print(" 1. Run with default values. \n 2. Enter custom parameters. ");
        choice = input.nextInt();

        while (choice != 1 && choice != 2) {
            System.out.println("Pick 1. or 2. ");
            choice = input.nextInt();
        }

        if (choice == 1) {
            for (int sampleSize = startSize; sampleSize <= maxSize; sampleSize += stepSize) {
                runTests(sampleSize, iterations, markSize, offset);
            }

        } else {


            System.out.print("Enter set size, integer value between 2 - 1,000,000: ");
            maxSize = input.nextInt();
            while (maxSize < 2 || maxSize > 1000000) {
                System.out.println("Enter set size, integer value between 2 - 1,000,000: ");
                maxSize = input.nextInt();
            }

            System.out.print("Enter starting set, blank for 1: ");
            startSize = input.nextInt();
            while (startSize < 0 || startSize > maxSize - 1) {

                System.out.print("Enter a valid starting number: ");
                startSize = input.nextInt();
            }


            System.out.print("Enter number of times to run sorts: ");
            iterations = input.nextInt();
            while (iterations > 100000 || iterations < 1) {
                System.out.print("Be a bit more conservative, : ");
                iterations = input.nextInt();
            }


            System.out.print("Enter Step Size for Tests:");
            stepSize = input.nextInt();
            while (stepSize < 1 || stepSize > maxSize) {
                System.out.print("Input Proper step size");
            }


            System.out.print("Enter scalar value for tick marks 1 - 1000 nano seconds: ");
            markSize = input.nextInt();
            while (markSize < 1 || markSize > 1000) {
                System.out.print("Choose proper value:");
                markSize = input.nextInt();
            }


            for (int sampleSize = startSize; sampleSize <= maxSize; sampleSize += stepSize) {
                runTests(sampleSize, iterations, markSize, offset);
            }


        }


    }

}


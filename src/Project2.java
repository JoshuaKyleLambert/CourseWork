import java.util.Arrays;

/**
 * Created by Joshua Lambert on 8/24/2017.
 *
 * Implementation of Merge & Insertion sort Classic Algorithms as found in
 * the Corman Text.
 *
 * This program will also output runtimes for performance testing.
 *
 * Any easy to view Histogram is also available.
 *
 * Running the program makes usage clear.
 *
 */
public class Project2 {



    public static void main(String[] args){

        java.util.Scanner input = new java.util.Scanner(System.in);

        int iterations;
        int maxSize;
        int stepSize, markSize;

        System.out.print("Enter set size, integer value between 2 - 1,000,000:");
        maxSize = input.nextInt();

        while(maxSize < 2 || maxSize > 1000000){
            System.out.println("Enter set size, integer value between 2 - 1,000,000:");
            maxSize = input.nextInt();
        }
        System.out.print("Enter number of times to run sorts: ");

        iterations = input.nextInt();

        while(iterations > 100000 || iterations < 1){
            System.out.print("Be a bit more conservative, : ");
            iterations = input.nextInt();
        }

        System.out.print("Enter Step Size for Tests:");
        stepSize = input.nextInt();
        while(stepSize < 1 || stepSize > maxSize){
            System.out.print("Input Proper step size");
        }

        System.out.print("Enter value for tick marks 1 - 1000 nano seconds: ");
        markSize = input.nextInt();
        while ( markSize < 1 || markSize > 1000){
            System.out.print("Choose proper value:");
            markSize = input.nextInt();
        }

        for (int sampleSize = 1; sampleSize <= maxSize; sampleSize += stepSize){
            runTests(sampleSize, iterations, markSize);
        }


    }

    /*
    *   Sort Implementations
    */

    public static void insertionSort(int[] a) {
        int i;
        int key;
        for(int j = 1;j < a.length; j++){
            key = a[j];
            i = j - 1;
            while(i >= 0 && a[i] > key){
                a[i + 1] = a[i];
                i = i - 1;
            }
            a[i + 1] = key;
        }
    }

    public static void mergeSort(int[] a){
        int p = 0;
        int r = a.length;
        mergeSort(a, p, r);

    }


    public static void merge(int[] A, int p, int q , int r){

        int n1 = q - p ;
        int n2 = r - q;
        int i, j;

        int[] L = new int[n1 + 1];
        int[] R = new int[n2 + 1];

        for( i = 0; i < n1; i++){
            L[i] = A[p + i ];
        }
        for ( j = 0; j < n2; j++){
            R[j] = A[q + j];
        }

        L[n1] = Integer.MAX_VALUE;
        R[n2] = Integer.MAX_VALUE;

        i = 0;
        j = 0;

        for (int k = p; k < r; k++){
            if (L[i] <= R[j]) {
                A[k] = L[i];
                i = i + 1;
            } else {
                A[k] = R[j];
                j = j + 1;
            }
        }
    }

    public static void mergeSort(int[] A, int p, int r){
        int q;
        if (p < r){
            q = (p + r ) / 2;
            mergeSort(A, p, q);
            mergeSort(A,q + 1, r);
            merge(A, p, q, r);
        }
    }
    private static void runTests(int size, int iterations, int markSize){

        long totalTime = 0;
        long beginTime, endTime;


        int[] sampleSet = new int[size];

        java.util.Random generator = new java.util.Random(2);

        for(int i = 0; i < size; i++ ){
            sampleSet[i] = generator.nextInt();
        }
        int[] setCopy = sampleSet.clone();





        //int[] test = { 3, 4 , 6 , 6 , 6 , 8 , 8, 25,  67,};
        //System.out.println(Arrays.toString(test));

        for( int i = 0; i < iterations; i++) {
            beginTime = System.nanoTime();
            mergeSort(sampleSet);
            endTime = System.nanoTime();

            totalTime += endTime - beginTime;
            sampleSet = setCopy.clone();

        }

        //System.out.println(Arrays.toString(test));

        //System.out.print("\nMERGE SORT:     N=" + size + " Runs=" + iterations +" Mean RunTime =  " + totalTime / iterations + "ns.");
        System.out.print("\n    MERGE SORT:");
        for( int i = 0; i < totalTime / iterations; i += markSize) {
            System.out.print("-");
        }
        System.out.print("    N=" + size);
        System.out.print("  MEANTIME: " + totalTime / iterations + "ns");

        sampleSet = setCopy.clone();
        totalTime = 0;
        for( int i = 0; i < iterations; i++) {
            beginTime = System.nanoTime();
            insertionSort(sampleSet);
            endTime = System.nanoTime();

            totalTime += endTime - beginTime;
            sampleSet = setCopy.clone();

        }

        //System.out.println("INSERTION SORT: N=" + size + " Runs=" + iterations +" Mean RunTime =  " + totalTime / iterations + "ns.");
        System.out.print("\nINSERTION SORT:");
        for( int i = 0; i < totalTime / iterations; i += markSize) {
            System.out.print("#");
        }
        System.out.print("  MEANTIME: " + totalTime / iterations + "ns");
    }
    private static void promptUser(){

    }

}

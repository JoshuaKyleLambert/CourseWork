import java.util.Arrays;

/**
 * Created by Joshua Lambert on 8/24/2017.
 *
 * Implementation of Merge & Insertion sort Classic Algorithms as found in
 * the Corman Text.
 *
 * This program will also output runtime for performance testing.
 *
 */
public class Project2 {



    public static void main(String[] args){

        java.util.Scanner input = new java.util.Scanner(System.in);
        System.out.print("Enter set size, integer value between 2 - 1,000,000:");

        int size = input.nextInt();
        long totalTime = 0;
        long beginTime, endTime;
        int iterations;

        while(size < 2 || size > 1000000){
            System.out.println("Enter set size, integer value between 2 - 1,000,000:");
            size = input.nextInt();
        }
        System.out.print("Enter number of times to run test: ");

        iterations = input.nextInt();

        while(iterations > 100000 || iterations < 1){
            System.out.print("Be a bit more conservative, : ");
            iterations = input.nextInt();
        }


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
        System.out.println("\n\nMERGE SORT:\n\nN =  " + size + "\nRuns = " + iterations +"\nMean RunTime =  " + totalTime / iterations + "ns.");
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

    private static void promptUser(){

    }

}

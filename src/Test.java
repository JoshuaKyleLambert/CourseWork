import java.util.*;
/**
 * Created by Joshu on 8/25/2017.
 */
public class Test {

        java.util.Scanner input = new java.util.Scanner(System.in);

        int iterations = 50;
        int maxSize = 800;
        int stepSize = 10, markSize = 500, startSize = 1, offset = 15000;

        public void prompt() {
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
                //runTests(sampleSize, iterations, markSize, offset);
            }


        }

         /*   public void quickRun(int sampleSize, int iterations, int markSize, int offset){
                for (int sampleSize = startSize; sampleSize <= maxSize; sampleSize += stepSize) {
                  //  runTests(sampleSize, iterations, markSize, offset);
                }
            }*/
}

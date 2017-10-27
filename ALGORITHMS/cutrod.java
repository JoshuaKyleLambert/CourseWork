import java.util.Arrays;

public class cutrod {

    // a test program
    public static void main(String[] args) {
        int n = 20;
        int[] l = {2, 8, 10};
        cutrod.bestBreakSequence(n, l);
    }


    // print the lowest cost and a best sequence of breaks
    public static void bestBreakSequence(int n, int[] l){
         int[] memo = new int[n];
         int[] cuts = new int[n];


       memo[0] = 0;

       for (int j = 0; j < n-1; j++)
       {
           int q = Integer.MIN_VALUE;

           for ( int i = 0; i < j;i++){
               if (q < l[i] + memo[j - i])
               {
                q = l[i] + memo[j - i];
                cuts[j] =  i;
               }
           }

           memo[j] = q;
       }

       System.out.println("Lowest Cost: " + Arrays.toString(memo));
       System.out.println("Best Sequence: " + Arrays.toString(cuts));

    }

}

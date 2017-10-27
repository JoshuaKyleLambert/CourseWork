/*
Joshua Lambert
Project 5

I have failed to grasp DP.  I haven't given up though.
I never fully understood how this process is supposed to work
What a next best cut is, or what the rules of the cuts are.
what possible cut lists might look like or what optimal final set
of broken string resembles and as such I struggled with this
problem for many days and never came up with a working
solution.   here's my last attempt.

While researching DP and problems similar to the assignment to
try and better understand the topic it seems as though problems like
these can be solved in n^2 time the text book mentions an algorithm
like this runs at n^2 as well.

So I guess this is too.  if it worked.
 */



import java.util.Arrays;

public class Project5 {
static int q;


    public static void main(String[] args) {
        int n = 20;
        int[] l = {2, 8, 10};
        Project5.bestBreakSequence(n, l);
    }


    public static void bestBreakSequence(int n, int[] l) {
        int[] r;

        r = new int[n + 1];

        memoizedBestBreak(n, l, r);
        System.out.println(Arrays.toString(r));
    }


    public static int memoizedBestBreak(int n, int[] l,int[] r) {
        int q;
        for (int i = 0; i < n; i++) {
            r[i] = Integer.MIN_VALUE;
        }
        return memoizedBestBreakAux(n, l, r);
    }


    private static int memoizedBestBreakAux(int n, int[] l,int[] r) {
        System.out.println(q);
       // if (n - 1 == 0)
           // return 0;
       // if (r[n] >= 0)
          //  return r[n];
        if (n == 0) {
            q = 0;
        } else {
            q = Integer.MIN_VALUE;
            for (int i = 0; i < l.length - 1; i++) {
                q = Math.min(q,  memoizedBestBreakAux(n - (i + 1), l, r) - l[i]);
            }
        }

        r[n] = q;


        return q;
    }

}



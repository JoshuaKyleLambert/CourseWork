import java.util.*;
/**
 * Created by Joshu on 8/25/2017.
 */
public class Test {

    public static void main(String[] arg0){
        int[] b = {1, 4, 2, 3, 9, 7, 8, 10, 14, 16};
        System.out.println(Arrays.toString(b));
        MaxHeap myHeap = new MaxHeap(b);
        System.out.println(Arrays.toString(myHeap.a));
        System.out.println(Arrays.toString(b));
    }

}

import java.util.*;
/**
 * Created by Joshu on 8/25/2017.
 */
public class Test {

    public static void main(String[] arg0){
        int[] b = {5, 13, 2, 25, 7 , 17, 20, 8 , 4};
        System.out.println("Unsorted , unheaped" + Arrays.toString(b));
        MaxHeap myHeap = new MaxHeap(b);
        //System.out.println("Heaped             " + Arrays.toString(myHeap.a));
        System.out.println("Heaped             " + Arrays.toString(b));
        myHeap.sort();
        System.out.println("Sorted             " + Arrays.toString(b));
        System.out.println(myHeap.extractMax());



    }

}




public class MaxHeap {


    // reference to the array
    public int[] a;

    // heapSize may be different from a.length
    public int heapSize;

    // construct a MaxHeap object
    // reference (do not copy) the user supplied array
    public MaxHeap(int[] a)

    public void heapify(int i) {
    int largest = 0;
    int swap;
    int l = left(i);
    int r = right(i);

    if (l <= a.length && a[l] > a[i]){
        largest = l;
    }
    else if ( r <= a.length && a[r] > a[largest])
        largest = r;
    if (largest != i){
        swap = a[largest];
        a[largest] = a[i];
        a[i] = swap;
        heapify(largest);
    }

}

    public void buildHeap()
    // heapsort on a
    public void sort()

    // priority queue operations
    public int maximum()
    public int extractMax()
    public void increaseKey(int i, int key)
    public void insert(int key)

    private int parent(int i){
        return i/2;
    }
    private int left(int i){
        return i << 1 ;
    }
    private int right(int i){
        return (i << 1) + 1;
    }



}
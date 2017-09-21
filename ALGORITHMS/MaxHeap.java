


public class MaxHeap {


    // reference to the array
    public int[] a;
    //swap space
    int swap;
    int heapSize;

    // heapSize may be different from a.length
    public int heapSize;

    // construct a MaxHeap object
    // reference (do not copy) the user supplied array
    public MaxHeap(int[] a){
        this.a = a;
        heapSize = a.length - 1;
    }

    public void heapify(int i) {
        int largest;

        int l = left(i);
        int r = right(i);

        if (l <= heapSize && a[l] > a[i]) largest = l;
        else largest = i;
        if (r <= heapSize && a[r] > a[largest]) largest = r;
        if (largest != i) {
            swap = a[largest];
            a[largest] = a[i];
            a[i] = swap;
            heapify(largest);
        }

    }

    public void buildHeap(){
        heapSize = a.length - 1;
        for (int i = (a.length-1)/2; i >= 0; i--){
            heapify(i);
        }
    }
    // heapsort on a
    public void sort(){
        buildHeap();
        for(int i = a.length-1; i >= 1; i--){
            swap = a[i];
            a[i] = a[0];
            heapSize = heapSize - 1;
            heapify(1);
        }
    }

    // priority queue operations
    public int maximum(){
        return a[0];
    }
    public int extractMax(){
        if ( a.length-1 < 1){
            System.out.print("Heap underflow");
        }
    }
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
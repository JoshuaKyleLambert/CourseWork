/**
 * Joshua lambert
 * 09/23/2017
 *
 * Heap
 *
 * Sort
 *
 * Priority Queue
 *
 * 0 Based
 *
 * turn your integer array into a Heap by supplying
 * it as the argument to the default constructor.
 *
 * Be aware the array is referenced and not copied.
 */


public class MaxHeap {


    // reference to the array
    public int[] a;
    //swap space
    private int swap;

    // heapSize may be different from a.length
    private int heapSize;

    // construct a MaxHeap object
    // reference (do not copy) the user supplied array
    public MaxHeap(int[] a) {
        this.a = a;
        heapSize = a.length - 1;
        buildHeap();
    }

    public void heapify(int i) {

        int largest;

        int l = left(i);
        int r = right(i);

        if (l <= heapSize && a[l] > a[i]) {
            largest = l;
        } else largest = i;

        if (r <= heapSize && a[r] > a[largest]) {
            largest = r;
        }

        if (largest != i) {
            swap = a[largest];
            a[largest] = a[i];
            a[i] = swap;
            heapify(largest);
        }

    }

    public void buildHeap() {
        heapSize = a.length - 1;
        for (int i = (a.length - 1) / 2; i >= 0; i--) {
            heapify(i);
        }
    }

    // heapsort on a
    public void sort() {
        buildHeap();
        for (int i = a.length - 1; i >= 1; i--) {
            swap = a[i];
            a[i] = a[0];
            a[0] = swap;
            heapSize--;
            heapify(0);
        }
    }

    // priority queue operations
    public int maximum() {
        return a[0];
    }

    public int extractMax() {
        // if sort was run, heap property is not maintained
        //buildHeap();
        int max;
        if (heapSize < 0) {
            System.out.print("Heap underflow");
            //return Integer.MIN_VALUE;
        }
        max = a[0];
        a[0] = a[heapSize];
        heapSize = heapSize--;
        heapify(0);
        return max;
    }

    public void increaseKey(int i, int key) {
        if (key < a[i])
            System.out.print("new Key is smaller than current key");
        else
            a[i] = key;
        while (i > 0 && a[parent(i)] < a[i]) {
            swap = a[parent(i)];
            a[parent(i)] = a[i];
            a[i] = swap;
            i = parent(i);
        }
    }

    public void insert(int key) {
        heapSize = heapSize + 1;
        a[heapSize] = Integer.MIN_VALUE;
        increaseKey(heapSize, key);
    }

    private int parent(int i) {
        return i / 2;
    }

    private int left(int i) {
        return (i * 2) + 1;
    }

    private int right(int i) {
        return (i * 2) + 2;
    }


}
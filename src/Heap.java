public class Heap {

    String[] d;
    int n;
    int maxSize;

    // Empty heap constructor
    public Heap(int maxSize) {
        this.maxSize = maxSize;
        d = new String[maxSize + 1];  // d[0] unused
        n = 0;
    }
    // Push d[i] down until heap order is restored
// Min-heap: parent must be <= both children (alphabetical)
    public void siftDown(int i) {
        String temp = d[i];
        int child;
        while (2 * i <= n) {
            child = 2 * i;
            // pick the smaller of the two children
            if (child < n && d[child + 1].compareTo(d[child]) < 0)
                child++;
            // if parent already <= smaller child, stop
            if (temp.compareTo(d[child]) <= 0)
                break;
            d[i] = d[child];           // pull child up
            i    = child;              // move down
        }
        d[i] = temp;
    }
    // Push d[i] up until heap order is restored
    public void siftUp(int i) {
        String temp = d[i];
        while (i > 1 && temp.compareTo(d[i / 2]) < 0) {
            d[i] = d[i / 2];  // pull parent down
            i    = i / 2;     // move up
        }
        d[i] = temp;
    }
    // Add a new word to the heap, then siftUp to fix order
    public void insert(String word) {
        if (n >= maxSize) throw new RuntimeException("Heap is full");
        n++;
        d[n] = word;
        siftUp(n);
    }
}
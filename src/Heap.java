//consulted claud ai free version.

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
    }// Remove and return the smallest word (root)
    // Replace root with last element, then siftDown
    public String deleteMin() {
        if (n == 0) return null;
        String min = d[1];
        d[1] = d[n];
        n--;
        if (n > 0) siftDown(1);
        return min;
    }
    // BOTTOM-UP: fill array then siftDown from n/2 down to 1  O(n)
    public void buildBottomUp(String[] words, int len) {
        n = len;
        for (int i = 1; i <= n; i++)
            d[i] = words[i - 1];
        for (int i = n / 2; i >= 1; i--)
            siftDown(i);
    }

    // TOP-DOWN: insert one word at a time  O(n log n)
    public void buildTopDown(String[] words, int len) {
        n = 0;
        for (int i = 0; i < len; i++)
            insert(words[i]);
    }
}
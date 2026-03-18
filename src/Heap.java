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
}
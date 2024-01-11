public class HeapSort<E extends Comparable<? super E>> {
    void sort(E[] a) {
        for(int i = a.length / 2 - 1; i >= 0; i--) 
            percolateDown(a, i, a.length);
        
            for(int i = a.length - 1; i > 0; i--) {
                swap(a, 0, i);
                percolateDown(a, 0, i);
            }
    }

    private int leftChild(int idx) {
        return 2 * idx + 1;
    }

    void percolateDown(E[] a, int hole, int size) {
        E tmp;
        int child;

        for(tmp = a[hole]; leftChild(hole) < size; hole = child) {
            child = leftChild(hole);
            if(child != size - 1 && a[child].compareTo(a[child + 1]) > 0) 
                child++;
            if(tmp.compareTo(a[child]) > 0)
                a[hole] = a[child];
            else 
                break;
        }

        a[hole] = tmp;
    }

    void swap(E[] a, int i, int j) {
        E tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}

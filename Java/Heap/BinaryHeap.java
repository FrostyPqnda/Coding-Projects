@SuppressWarnings("unchecked")
public class BinaryHeap<E extends Comparable<? super E>> implements Heap<E> {
    private static final int DEFAULT_SIZE = 11;
    private E[] heap;
    private int numItems;

    public BinaryHeap() {
        this(DEFAULT_SIZE);
    }

    public BinaryHeap(int capacity) {
        heap = (E[]) new Comparable[capacity];
        numItems = 0;
    }

    public BinaryHeap(E[] items) {
        numItems = items.length;
        heap = (E[]) new Comparable[(numItems + 2) * 11 / 10];
        int i = 1;
        for(E item : items) 
            heap[i++] = item;
        buildHeap();
    }

    @Override
    public void insert(E x) {
        expand();
        int hole = ++numItems;
        for(heap[0] = x; x.compareTo(heap[hole / 2]) < 0; hole /= 2) 
            heap[hole] = heap[hole / 2];
        heap[hole] = x;
    }

    @Override
    public E findMin() {
        return heap[1];
    }

    @Override
    public E deleteMin() {
        if(isEmpty()) 
            throw new IllegalStateException();
        E minItem = findMin();
        heap[1] = heap[numItems--];
        percolateDown(1);
        return minItem;
    }

    @Override
    public boolean isEmpty() {
        return numItems == 0;
    }

    @Override
    public void makeEmpty() {
        heap = (E[]) new Comparable[heap.length];
        numItems = 0;
    }

    public String toString() {
        if(isEmpty()) 
            return "[]";
        String res = "";
        for(E item : heap) 
            if(item != null)
                res += (item + " ");
        return '[' + res.trim() + ']';
    }
    
    private void percolateDown(int hole) {
        int child;
        E tmp = heap[hole];
        for(; hole * 2 <= numItems; hole = child) {
            child = hole * 2;
            if(child != numItems && heap[child + 1].compareTo(heap[child]) < 0) 
                child++;
            if(heap[child].compareTo(tmp) < 0)
                heap[hole] = heap[child];
            else 
                break;
        }
        heap[hole] = tmp;
    }

    private void expand() {
        if(numItems < heap.length - 1)
            return;
        E[] copy = heap;
        heap = (E[]) new Comparable[copy.length * 2 + 1];
        int index = 1;
        for(E item : copy)
            heap[index++] = item;
        return;
    }

    private void buildHeap() {
        for(int i = numItems / 2; i > 0; i--)
            percolateDown(i);
    }

    @Override
    public void print() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'print'");
    }
}

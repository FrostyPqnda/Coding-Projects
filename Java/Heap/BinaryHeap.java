@SuppressWarnings("unchecked")
public class BinaryHeap<E extends Comparable<? super E>> implements Heap<E> {
    private static final int DEFAULT_SIZE = 10; // Default heap size
    private E[] heap; // Underlying array
    private int numItems; // Number of items

    /**
     * BinaryHeap()
     * 
     * Default constructor that sets the underyling 
     * heap array to size of DEFAULT_SIZE.
     */
    public BinaryHeap() {
        this(DEFAULT_SIZE);
    }

    /**
     * BinaryHeap(int capacity)
     * 
     * @param capacity Size of the underlying heap array
     * 
     * Parameterized constructor that sets the underyling
     * heap array to a given capacity.
     */
    public BinaryHeap(int capacity) {
        heap = (E[]) new Comparable[capacity + 1];
        numItems = 0;
    }

    /**
     * BinaryHeap(E[] items)
     * 
     * @param items An array of data to be inserted into the heap
     * 
     * Parameterized constructor that takes an array of data to build
     * the priority queue.
     */
    public BinaryHeap(E[] items) {
        numItems = items.length;
        heap = (E[]) new Comparable[(numItems + 2) * 11 / 10];
        int i = 1;
        for(E item : items) 
            heap[i++] = item;
        buildHeap();
    }

    /**
     * insert(E x)
     * 
     * @param x Item to be inserted into the priority queue
     */
    @Override
    public void insert(E x) {
        expand(); // Enlarge the array if full
        // Percolate up
        int hole = ++numItems;
        for(; hole > 1 && x.compareTo(heap[hole / 2]) < 0; hole /= 2) 
            heap[hole] = heap[hole / 2];
        heap[hole] = x;
    }

    /**
     * findMin()
     * 
     * @return Smallest item in the priority queue
     */
    @Override
    public E findMin() {
        return isEmpty() ? null : heap[1];
    }

    /**
     * deleteMin()
     * 
     * @return Smallest item removed from the priority queue
     * @throws IllegalStateException if queue is empty
     */
    @Override
    public E deleteMin() {
        if(isEmpty()) 
            throw new IllegalStateException();
        E minItem = findMin(); // Get the root of the binary heap
        heap[1] = heap[numItems--]; // Set heap[1] (root) to the last item inserted
        percolateDown(1); // Percolate down to preserve heap order
        return minItem; // Return the previous root value
    }

    /**
     * isEmpty()
     * 
     * @return True if priority queue is empty
     */
    @Override
    public boolean isEmpty() {
        return numItems == 0;
    }

    /**
     * makeEmpty()
     * 
     * Remove all items in the priority queue
     */
    @Override
    public void makeEmpty() {
        heap = (E[]) new Comparable[heap.length];
        numItems = 0;
    }

    /**
     * print()
     * 
     * Display the binary heap
     */
    @Override
    public void print() {
        for(int i = 0; i < heap.length; i++) {
            System.out.print(heap[i] + " ");
        }
    }

    /**
     * toString()
     * 
     * @return String representation of the BinaryHeap class
     */
    public String toString() {
        if(isEmpty()) 
            return "[]";
        String res = "";
        for(E item : heap) 
            if(item != null)
                res += (item + " ");
        return '[' + res.trim() + ']';
    }

    /**
     * percolateDown(int hole)
     * 
     * @param hole Starting index to percolate down in the heap
     *
     * Internal method to percolate down in the heap.
     */
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

    /**
     * expand()
     * 
     * Expands the size of the underlying array.
     */
    private void expand() {
        if(numItems < heap.length - 1)
            return;
        E[] copy = heap;
        heap = (E[]) new Comparable[copy.length * 2 + 1];
        for(int i = 0; i < copy.length; i++)    
            heap[i] = copy[i];
        return;
    }

    /**
     * buildHeap()
     * 
     * Establish heap order propert from an arbitrary
     * arrangement of items.
     */
    private void buildHeap() {
        for(int i = numItems / 2; i > 0; i--)
            percolateDown(i);
    }
}

/**
 * My implementation of the Queue data structure
 * using a circular array implementation
 */

interface MyIterator<E> {
    boolean hasNext();
    E next();
    E remove();
}

public class Queue<E> {
    static final int CAPACITY = 100; // Default capacity
    int front, back, size; // front, back, and size of queue
    int modCount = 0;
    E[] arr; // Underlying array

    /**
     * Default Queue 
     */
    public Queue() {
        this(CAPACITY);
    }

    /**
     * @param initialCapacity Starting capacity of Queue
     * Parameterized Queue
     */
    @SuppressWarnings("unchecked")
    public Queue(int initialCapacity) {
        if(initialCapacity < 0) 
            throw new IllegalArgumentException("initialCapacity < 0");
        arr = (E[]) new Object[initialCapacity];
        front = back = -1;
        size = 0;
    }

    /**
     * @param item Data to be inserted
     * @return item
     * 
     * Adds an item to the back of the queue.
     */
    public E enqueue(E item) {
        if(isFull()) expand();
        if(front == -1) front = 0;
        back = (back + 1) % arr.length;
        arr[back] = item;
        size++;
        modCount++;
        return item;
    }
    
    /**
     * @return frontmost item in the queue
     * 
     * Removes the frontmost item in the queue
     */
    public E dequeue() {
        if(isEmpty()) throw new IllegalStateException("Empty Queue");
        E del = arr[front];
        arr[front] = null;
        if(front == back) front = back = -1;
        else front = (front + 1) % arr.length;
        size--;
        modCount++;
        return del;
    }

    /**
     * @return true if queue is full
     */
    boolean isFull() {
        return (front == 0 && back == arr.length - 1) || front == back + 1;
    }

    /**
     * @return true if queue is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * @return size of queue
     */
    public int size() {
        return size;
    }

    /**
     * Doubles the size of underlying array
     */
    @SuppressWarnings("unchecked")
    void expand() {
        E[] alloc = (E[]) new Object[arr.length * 2];
        for(int i = 0; i < size; i++) {
            alloc[i] = arr[front];
            front = (front + 1) % arr.length;
        }
        front = 0;
        back = size;
        arr = alloc;
    }

    /**
     * @return String representation of Queue
     */
    public String toString() {
        if(size == 0) return "[]";
        String res = "";
        for(int i = 0; i < arr.length; i++)
            if(arr[i] != null) res += (arr[i].toString() + " ");
        return '[' + res.trim() + ']';
    }

    public QueueIterator iterator() {
        return new QueueIterator();
    }

    class QueueIterator implements MyIterator<E> {
        int expectedModCount = modCount;
        int head = 0;

        @Override
        public boolean hasNext() {
            return head != size();
        }

        @Override
        public E next() {
            if(!hasNext()) throw new java.util.NoSuchElementException(); 
            if(modCount != expectedModCount) throw new java.util.ConcurrentModificationException();
            E value = arr[head++];
            return value;
        }

        @Override
        public E remove() {
            return dequeue();
        }
        
    }
}

class Main {
    public static void main(String[] args) {
        Queue<Integer> q = new Queue<>();

        for(int i = 0; i < 10; i++) q.enqueue(i + 1);

        Queue<Integer>.QueueIterator it = q.iterator();
        while(it.hasNext()) System.out.println(it.next());
    }
}
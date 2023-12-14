package MyImplementations;

@SuppressWarnings("unchecked")
public class Queue<E> {
    final int CAPACITY = 100; // Initial capacity of queue
    int head, tail;
    int numItems;
    E[] queue;

    public Queue() {
        queue = (E[]) new Object[CAPACITY];
        numItems = 0;
        head = tail = -1;
    }

    /**
     * enqueue(E x)
     * 
     * @param x Item to be inserted at the rear
     * @return Item inserted
     */
    public E enqueue(E x) {
        numItems++; // Increment numItems;
        resize(); // Resize the array if it is full
        // Set head to 0 if the queue is empty
        if(isEmpty()) {
            head = 0;
        } 
        tail = (tail + 1) % queue.length; // Get the index based on the current array size
        queue[tail] = x; // Append x to the rear of the array
        return x;
    }

    /**
     * dequeue()
     * 
     * @return Frontmost item to be deleted
     * @throws NoSuchElementException if Queue is empty
     */
    public E dequeue() {
        // Throw an error if we try to remove an element from
        // an empty queue
        if(isEmpty()) {
            throw new java.util.NoSuchElementException("Empty queue");
        }
        E frontItem = queue[head]; // Frontmost item to be removed
        // If the head reaches the tail, then reset the head and tail
        // value to -1.
        // Otherwise, set head to the next index based on the queue's
        // current size.
        if(head == tail) {
            head = tail = -1;
        } else {
            head = (head + 1) % queue.length;
        }
        numItems--; // Decrement numItems
        return frontItem;
    }

    /**
     * peek()
     * 
     * @return Frontmost item in the queue
     * @throws NoSuchElementException if queue is empty
     */
    public E peek() {
        if(isEmpty()) {
            throw new java.util.NoSuchElementException();
        }   
        return queue[head];
    }

    /**
     * isEmpty()
     * 
     * @return true if Queue is empty
     */
    public boolean isEmpty() {
        return (head == -1 && tail == -1) || numItems <= 0;
    }

    /**
     * size()
     * 
     * @return Number of items in the queue
     */
    public int size() {
        return numItems;
    }

    /**
     * isFull()
     * 
     * @return true if the tail has reached the head
     */
    boolean isFull() {
        return (tail + 1) % queue.length == head;
    }

    /**
     * resize()
     * 
     * Double the capacity of the underlying Queue array
     */
    void resize() {
        if(!isFull()) 
            return;
        
        int i = 0, j = head;
        E[] alloc = (E[]) new Object[queue.length * 2];
        do {
            alloc[i++] = queue[j];
            j = (j + 1) % queue.length;
        } while(j != head);

        head = 0; // Set head to 0
        tail = queue.length - 1; // Set tail to last index of queue
        queue = alloc; // Set queue to alloc
        return;
    }
}

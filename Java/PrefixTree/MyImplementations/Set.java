package MyImplementations;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class Set<E> implements Iterable<E> {
    class SetNode {
        E data; // Value stored in the SetNode
        SetNode next; // Next node referenced by the SetNode

        /**
         * SetNode(E data)
         * 
         * @param data Value to be stored in the SetNode
         */
        public SetNode(E data) {
            this.data = data;
            next = null;
        }
    }

    SetNode head; // Head of SetNode
    int numItems;
    int modCount = 0;

    /**
     * Set()
     * 
     * Constructor for the Set class
     */
    public Set() {
        clear();
    }

    /**
     * add(E x)
     * 
     * @param x Value to inserted into the set.
     * @return True if x was successfully inserted.
     */
    public boolean add(E x) {
        if(contains(x)) return false;
        SetNode newNode = new SetNode(x);
        numItems++;
        modCount++;

        if(isEmpty()) {
            head = newNode; // Set head to newNode
        } else {
            SetNode curr = head; // Get a reference to the head
            while(curr.next != null) 
                curr = curr.next;
            curr.next = newNode; // Set last node to newNode
        }
        
        return true;
    }

    /**
     * clear()
     * 
     * Remove all items in the set
     */
    public void clear() {
        head = null;
        numItems = 0;
        modCount++;
    }

    /**
     * contains(E x)
     * 
     * @param x Item to be checked
     * @return True if x exists in the set
     */
    public boolean contains(E x) {
        if(isEmpty()) return false;
        SetNode tmp = head;
        while(tmp != null) {
            if(tmp.data.equals(x)) return true;
            tmp = tmp.next;
        }
        return false;
    }

    /**
     * isEmpty()
     * 
     * @return True if head is null or numItems is 0
     */
    public boolean isEmpty() {
        return head == null | numItems == 0;
    }

    public boolean remove(E x) {
        if(!contains(x)) return false;
        
        numItems--;
        modCount++;

        // Set head to the next node to remove x from the linked list
        // if it is stored at the head.
        if(head.data.equals(x)) {
            head = head.next;
            return true;
        }

        SetNode del = head; // Get a reference to the head
        // Loop until we hit the node containing x
        while(!del.next.data.equals(x))
            del = del.next;
        del.next = del.next.next; // Link the previous node to the next node
        return true;
    }

    /**
     * size()
     * 
     * @return No. of items in the set
     */
    public int size() {
        return numItems;
    }

    /**
     * toArray() 
     * 
     * @return An array representation of the Set class
     */
    public E[] toArray() {
        @SuppressWarnings("unchecked")
        E[] arr = (E[]) new Object[numItems];
        int idx = 0;
        SetNode ptr = head; 
        
        while(ptr != null) {
            arr[idx++] = ptr.data;
            ptr = ptr.next;
        }
        
        return arr;
    }

    public String toString() {
        if(isEmpty()) return "[]";
        String str = "";
        Iterator<E> it = iterator();
        while(it.hasNext()) 
            str += (it.next() + " ");
        return '[' + str.trim().replaceAll(" ", ", ") + ']';
    }

    /**
     * iterator()
     * 
     * @return A SetIterator object
     */
    @Override
    public Iterator<E> iterator() {
        return new SetIterator();
    }

    class SetIterator implements Iterator<E> {
        SetNode ptr = head; // Reference to the head;
        int expectedModCount = modCount; 
        E removed = null;
        boolean canRemove = false;

        /**
         * hasNext()
         * 
         * @return True if ptr is not null
         */
        @Override
        public boolean hasNext() {
            return ptr != null;
        }

        /**
         * next()
         * 
         * @return The current item in the iteration
         * @throws ConcurrentModificationException If modCount != expectedModCount
         * @throws NoSuchElementException If hasNext() returns false
         */
        @Override
        public E next() {
            if(modCount != expectedModCount) 
                throw new java.util.ConcurrentModificationException();
            
            if(!hasNext())
                throw new java.util.NoSuchElementException();

            E value = removed = ptr.data; // Current item in the iteration
            ptr = ptr.next;
            canRemove = true;
            return value;
        }

        /**
         * remove()
         * 
         * @return The current item in the iteration that was removed
         * @throws ConcurrentModificationException If modCount != expectedModCount
         * @throws IllegalStateException If canRemove is false
         */
        @Override
        public void remove() {
            if(modCount != expectedModCount) 
                throw new java.util.ConcurrentModificationException();
        
            if(!canRemove)
                throw new IllegalStateException();

            Set.this.remove(removed);
            expectedModCount++;
            canRemove = false;
            return;
        }
    }
}
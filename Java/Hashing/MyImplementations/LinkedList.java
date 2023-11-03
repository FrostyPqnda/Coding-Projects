package MyImplementations;

public class LinkedList<E> implements MyIterable<E> {
    /**
     * Private inner class that will be the node contained in the
     * SinglyLinkedList class
     */
    class Node {
        E data; // Data stored in the node
        Node next; // Reference to the next node

        /**
         * @param data A value that will be stored in the node
         * 
         * Constructor for the Node. When initialized, 
         * next will be set null.
         */
        public Node(E data) {
            this.data = data;
            next = null;
        }
    }   
 
    Node head; // Header node of the linked list
    int numItems; // Number of items in the linked list
    int modCount = 0; // Number of modifications made

    /**
     * LinkedList()
     * 
     * Constructor
     */
    public LinkedList() {
        head = null;
        numItems = 0;
    }

    /**
     * size()
     * 
     * @return current number of items in linked list
     */
    public int size() {
        return numItems;
    }

    /**
     * print()
     * 
     * Prints the current items in the linked list
     */
    public void print() {
        if(numItems <= 0) return;
        Node temp = head;
        while(temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
        return;
    }

    /**
     * contains(E x)
     * 
     * @param x Item to be searched
     * @return true if x was found and false otherwise
     */
    public boolean contains(E x) {
        if(head == null) return false;
        Node temp = head;
        while(temp != null) {
            if(temp.data.equals(x)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    /**
     * insert(E x)
     * 
     * @param x Item to be inserted at the end of the list
     * @return true if item was successfully added, false otherwise
     */
    public boolean insert(E x) {
        // Item already exists, return false
        if(contains(x)) return false;
        
        Node newNode = new Node(x); // Create a new node containing value x
        numItems++; // Increment numItems
        modCount++; // Increment modCount
        
        // If head is null, then set head to newNode.
        // Otherwise, go to the end of the linked list
        // and insert the newNode at the end
        if(head == null) {
            head = newNode;
        } else {
            Node curr = head;
            while(curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
        }
        return true;
    }   

    /**
     * remove(E x)
     * 
     * @param x The item to be removed from the linked list
     */
    public boolean remove(E x) {
        // Return if [x] does not exist in the linked list
        if(!contains(x)) return false;

        // If the head of linked list contains x, then 
        // set head to the next node
        if(head.data.equals(x)) {
            head = head.next;
            return true;
        } 

        Node del = head; // Get temporary reference to the head;
        // Loop through until we find the node containing x
        while(!del.next.data.equals(x)) {
            del = del.next; 
        } 
        del.next = del.next.next; // Unlink node containing x by linking to the node after it
        numItems--; // Decrement numItems
        modCount++; // Increment modCount
        return true;
    }

    /**
     * clear()
     * 
     * Reset the linked list
     */
    public void clear() {
        head = null; // Unlink all the nodes
        numItems = 0; // Reset numItems back to 0
    }

    /**
     * toString()
     * 
     * @return A string representation of MyLinkedList
     */
    public String toString() {
        if(numItems <= 0) return "[]";

        MyIterator<E> itr = new ListIterator();
        String res = "";
        while(itr.hasNext()) {
            res += (itr.next() + " ");
        }
        res = '[' + res.trim().replaceAll(" ", " -> ") + ']';
        return res;
    }

    /**
     * iterator()
     * 
     * @return ListIterator object
     */
    @Override
    public MyIterator<E> iterator() {
        return new ListIterator();
    }

    /**
     * Inner class that implements the MyIterator interface.
     */
    class ListIterator implements MyIterator<E> {
        Node ptr = head; // Reference to the head node
        int expectedModCount = modCount; // The expected number of modifications made to the list
        
        @Override
        /**
         * hasNext() 
         * 
         * @return true if the List can still be iterated over.
         */
        public boolean hasNext() {
            return ptr != null;
        }
        
        @Override
        /**
         * next()
         * 
         * @return The value at the current iteration of the list.
         * @throws NoSuchElementException if there are no more item in the list.
         * @throws ConcurrentModificationException if the modCount is not equal to expectedModCount.
         */
        public E next() {
            if(!hasNext()) {
                throw new java.util.NoSuchElementException();
            }

            if(modCount != expectedModCount) {
                throw new java.util.ConcurrentModificationException();
            }

            E value = ptr.data; // Retrieve the value at the current pointer
            ptr = ptr.next; // Shift the pointer to the next node
            return value; // Return the retrieved value
        }  
    }
}

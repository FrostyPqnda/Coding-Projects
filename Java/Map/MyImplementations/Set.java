package MyImplementations;

/**
 * MyIterator interface
 */
interface MyIterator<E> {
    /**
     * hasNext()
     * 
     * @return True if there are more items to iterate over.
     */
    boolean hasNext();

    /**
     * next()
     * 
     * @return The current item in the iterator.
     */
    E next();

    /**
     * remove()
     * 
     * @return Removed item in the iterator.
     */ 
    E remove();
}

/**
 * MyIterable interface
 */
interface MyIterable<E> {
    /**
     * iterator()
     * 
     * @return A MyIterator object.
     */
    MyIterator<E> iterator();
}

/**
 * Set
 * 
 * This implementation uses a singly Linked List implementation to perform
 * Set operation and uses the MyIterable interface.
 * 
 * A set is a collection of items that does not allow duplicates.
 */
@SuppressWarnings("unchecked")
public class Set<E> implements MyIterable<E> {
    /**
     * Private inner class to store the node contains in the Set class
     */
    private class Node {
        E data; // The value stored in the node
        Node next; // The next node referenced by a node

        /**
         * Node(E data)
         * 
         * @param data Value to be stored in the node
         */
        public Node(E data) {
            this.data = data; // Set the instance data to data
            next = null; // Set next reference to null
        }
    }

    Node head; // Head of the linked list used by the Set class
    int numItems; // Number of items in the set
    int modCount = 0; // Number of modifications made to the Set

    /**
     * Set()
     * 
     * Constructor for the Set class.
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
        if(contains(x)) return false; // Return false if a duplicate value is inserted
        Node newNode = new Node(x); // Create a new node to store x
        numItems++; // Increment numItems
        modCount++; // Increment modCount

        // If Set is empty, store newNode in the head.
        // Otherwise, store newNode at the end of the
        // linked list.
        if(isEmpty()) {
            head = newNode; // Set head to newNode
        } else {
            Node curr = head; // Get a temporary reference to the head
            while(curr.next != null) curr = curr.next; // Go to the last node
            curr.next = newNode; // Set the last node to newNode
        }
        return true; // Item was successfully added to the set.
    }

    /**
     * clear()
     * 
     * Remove all items in the set
     */
    public void clear() {
        head = null; // Set head to null to unlink all nodes in the linked list
        numItems = 0; // Set numItems back to 0
        modCount++; // Increment modCount
    }

    /**
     * contains(E x)
     * 
     * @param x Item to be checked
     * @return True if x exists in the
     */
    public boolean contains(E x) {
        if(isEmpty()) return false; // Return false if set is empty
        Node temp = head; // Get a temporary reference to the head
        // Loop until we reach the end of the linked list
        while(temp != null) {
            if(temp.data.equals(x)) return true; // End if x was found in the set
            temp = temp.next; // Go to the next node
        }
        return false; // Item was not found
    }

    /**
     * isEmpty()
     * 
     * @return True if head is null or numItems is 0
     */
    public boolean isEmpty() {
        return head == null || numItems == 0;
    }
    
    /**
     * iterator()
     * 
     * @return A SetIterator object
     */
    @Override
    public MyIterator<E> iterator() {
        return new SetIterator();
    }

    /**
     * remove(E x)
     * 
     * @param x Item to be removed from the set
     * @return True if x was successfully removed
     */
    public boolean remove(E x) {
        if(!contains(x)) return false; // Item does not exist in the set.
        // Set head to the next node to remove x from the linked list
        // if it is stored at the head.
        if(head.data.equals(x)) {
            head = head.next;
            return true;
        }
        Node del = head; // Get a temporary reference to the head
        // Loop until we hit the next node containing x
        while(!del.next.data.equals(x)) 
            del = del.next;
        del.next = del.next.next; // Unlink node storing x by linking previous node to the next node after
        numItems--; // Decrement numItems
        modCount++; // Increment modCount
        return true; // Item was successfully removed from the set
    } 

    /**
     * size()
     * 
     * @return Number of items in the set
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
        E[] arr = (E[]) new Object[numItems]; // Create an array of size numItems
        int idx = 0; // Index counter
        Node curr = head; // Get a temporary reference to the head
        // Iterate and insert all items in the set into the array
        while(curr != null) {
            arr[idx++] = curr.data; // arr[idx] = curr.data
            curr = curr.next; // Go to the next node
        }
        return arr; // Return the array
    }

    /**
     * toString()
     * 
     * @return A String representation of the Set class
     */
    public String toString() {
        if(isEmpty()) return "[]";
        String str = "";
        MyIterator<E> it = iterator();
        while(it.hasNext()) str += (it.next() + " ");
        return '[' + str.trim().replaceAll(" ", ", ") + ']';
    }

    /**
     * SetIterator
     * 
     * Private inner class that implements the MyIterator interface
     */
    private class SetIterator implements MyIterator<E> {
        Node ptr = head; // Get a reference to the head
        int expectedModCount = modCount; // Expected number of modification made to the set
        boolean canRemove = false; // Flag checking if an item can be removed by the SetIterator
        E removed = null; // Item set in next() and to be used by remove()

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
            ptr = ptr.next; // Go the next node in the iteration
            canRemove = true; // Set canRemove to true
            return value; // Return the current item in the iteration
        }
        
        /**
         * remove()
         * 
         * @return The current item in the iteration that was removed
         * @throws ConcurrentModificationException If modCount != expectedModCount
         * @throws IllegalStateException If canRemove is false
         */
        @Override 
        public E remove() {
            System.out.printf("Mod count = %d, Expected mod count = %d\n", modCount, expectedModCount);
            if(modCount != expectedModCount) 
                throw new java.util.ConcurrentModificationException();
            
            if(!canRemove)
                throw new IllegalStateException();

            Set.this.remove(removed); // Call Set.remove() to remove the current item
            //expectedModCount++;
            System.out.printf("Updated mod count = %d, Updated expected mod count = %d\n", modCount, expectedModCount);
            canRemove = false; // Set canRemove to false
            return removed; // Return the item that was removed
        }
    }
}

class Main {
    public static void main(String[] args) {
        Set<Integer> set = new Set<>();

        java.util.Scanner scan = new java.util.Scanner(System.in);

        System.out.print("Enter size: ");
        int size = scan.nextInt();

        for(int i = 0; i < size; i++) {
            System.out.print("Enter value: ");
            set.add(scan.nextInt());
        }

        System.out.println(set);

        MyIterator<Integer> it = set.iterator();
        while(it.hasNext()) 
            if(it.next() % 2 == 0)
                it.remove();
        //System.out.println(it.next());
        scan.close();
        System.out.println(set);
    }
}
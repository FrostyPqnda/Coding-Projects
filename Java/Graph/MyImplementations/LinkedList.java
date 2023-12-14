package MyImplementations;

public class LinkedList<E> implements List<E> {
    /**
     * Private inner class to store the node contains in the LinkedList class
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

    Node head; // Head of the linked list
    int numItems; // Number of items in the ;list
    int modCount = 0; // Number of modifications made to the list

    public LinkedList() {
        clear();
    }

    /**
     * add(E x)
     * 
     * @param x Value to inserted into the set.
     * @return True if x was successfully inserted.
     */
    @Override
    public boolean add(E x) {
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
            // Go to the last node
            while(curr.next != null)  
                curr = curr.next; 
            curr.next = newNode; // Set the last node to newNode
        }
        return true; // Item was successfully added to the set.
    }

    /**
     * clear()
     * 
     * Remove all items in the set
     */
    @Override
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
    @Override
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

    @Override
    public E get(int index) {
        if(index < 0 || index >= numItems)
            throw new ArrayIndexOutOfBoundsException();
        Node tmp = head;
        int count = 0;
        while(tmp != null) {
            if(count == index)
                return tmp.data;
            tmp = tmp.next;
            count++;
        }
        return null;
    }

    @Override
    public int indexOf(E x) {
        Node tmp = head;
        int idx = -1;
        int count = 0;
        while(tmp != null) {
            if(tmp.data.equals(x)) {
                idx = count;
                break;
            } else {
                count++;
            }
            tmp = tmp.next;
        }
        return idx;
    }

    /**
     * isEmpty()
     * 
     * @return True if head is null or numItems is 0
     */
    @Override
    public boolean isEmpty() {
        return head == null || numItems == 0;
    }

    /**
     * remove(E x)
     * 
     * @param x Item to be removed from the set
     * @return True if x was successfully removed
     */
    @Override
    public boolean remove(E x) {
        if(!contains(x)) return false; // Item does not exist in the set.
       
        numItems--; // Decrement numItems
        modCount++; // Increment modCount

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
        return true; // Item was successfully removed from the set
    } 

    @Override
    public E set(int index, E x) {
        if(index < 0 || index >= numItems)
            throw new ArrayIndexOutOfBoundsException();
        E old = null;
        Node tmp = head;
        int count = 0;
        while(tmp != null) {
            if(count == index) {
                old = tmp.data;
                tmp.data = x;
                break;
            } else {    
                count++;
            }
            tmp = tmp.next;
        }
        return old;
    }

    /**
     * size()
     * 
     * @return Number of items in the set
     */
    @Override
    public int size() {
        return numItems;
    }

    public String toString() {
        if(isEmpty())
            return "[]";
        String str = "";
        Node tmp = head;
        while(tmp != null) {
            str += (tmp.data + " ");
            tmp = tmp.next;
        }
        return '[' + str.trim().replaceAll(" ", ", ") + ']'; 
    }
}
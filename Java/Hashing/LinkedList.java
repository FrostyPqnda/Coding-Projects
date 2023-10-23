interface MyIterator<E> {
    boolean hasNext();
    E next();
}

interface MyIterable<E> {
    MyIterator<E> iterator();
}

public class LinkedList<E> implements MyIterable<E> {
    class Node {
        E data;
        Node next;

        public Node(E data) {
            this.data = data;
            next = null;
        }
    }

    Node head;
    int numItems = 0;
    int modCount = 0;

    public LinkedList() {
        clear();
    }

    public boolean insert(E x) {
        if(contains(x)) return false;

        Node newNode = new Node(x);
        numItems++;
        modCount++;

        if(head == null) {
            head = newNode;
        } else {
            Node curr = head;
            while(curr.next != null)
                curr = curr.next;
            curr.next = newNode;
        }

        return true;
    }

    public boolean remove(E x) {
        if(!contains(x)) return false;

        if(head.data.equals(x)) 
            head = head.next;

        Node temp = head;
        while(!temp.next.data.equals(x))
            temp = temp.next;
        temp.next = temp.next.next;
        
        numItems--;
        modCount++;
        return true;
    }

    public boolean contains(E x) {
        Node temp = head;
        while(temp != null) {
            if(temp.data.equals(x)) 
                return true;
            temp = temp.next;
        }
        return false;
    }

    public int size() {
        return numItems;
    }
    
    public void clear() {
        head = null;
        numItems = 0;
    }

    public String toString() {
        if(numItems <= 0) return "[]";

        String res = "";
        Node temp = head;

        while(temp != null) {
            res += temp.data + " ";
            temp = temp.next;
        }

        return '[' + res.trim().replaceAll(" ", " -> ") + ']';
    }

    @Override
    public MyIterator<E> iterator() {
        return new ListIterator();
    }

    class ListIterator implements MyIterator<E> {
        Node ptr = head;
        int expectedModCount = modCount;
        boolean canRemove = false;

        @Override
        public boolean hasNext() {
            return ptr != null;
        }

        @Override
        public E next() {
            if(!hasNext())
                throw new java.util.NoSuchElementException();
            
            if(modCount != expectedModCount)
                throw new java.util.ConcurrentModificationException();
            
            canRemove = true;
            E data = ptr.data;  
            ptr = ptr.next;
            return data;
        }
    }
}

/**
 * Command:
 * javac LinkedList.java
 * java LinkedListMain
 */
class LinkedListMain {
    public static void main(String[] args) {
        LinkedList<Character> ll = new LinkedList<>();
        for(char c = 65; c <= 90; c++)
            ll.insert(c);
        
        
        System.out.println(ll);
        ll.remove('H');
        System.out.println(ll);
    } 
}

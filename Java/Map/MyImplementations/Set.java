package MyImplementations;

interface MyIterator<E> {
    boolean hasNext();
    E next();
    default void remove() {
        throw new UnsupportedOperationException("remove");
    }
}

interface MyIterable<E> {
    MyIterator<E> iterator();
}

@SuppressWarnings("unchecked")
public class Set<E> implements MyIterable<E> {
    private class Node {
        E data;
        Node next;

        public Node(E data) {
            this.data = data;
            next = null;
        }
    }

    Node head;
    int numItems;
    int modCount = 0;

    public Set() {
        clear();
    }

    public boolean add(E x) {
        if(contains(x)) return false;
        
        Node newNode = new Node(x);
        numItems++;
        modCount++;

        if(isEmpty()) {
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

    public void clear() {
        head = null;
        numItems = 0;
        modCount++;
    }

    public boolean contains(E x) {
        if(head == null) return false;
        Node temp = head;
        while(temp != null) {
            if(temp.data.equals(x))
                return true;
            temp = temp.next;
        }
        return false;
    }

    public boolean isEmpty() {
        return head == null || numItems == 0;
    }

    @Override
    public MyIterator<E> iterator() {
        return new SetIterator();
    }

    public boolean remove(E x) {
        if(!contains(x)) return false;
        
        if(head.data.equals(x)) {
            head = head.next;
            return true;
        }

        Node del = head;
        while(!del.next.data.equals(x)) 
            del = del.next;
        del.next = del.next.next;
        numItems--;
        modCount++;
        return true;
    } 

    public int size() {
        return numItems;
    }

    public E[] toArray() {
        E[] arr = (E[]) new Object[numItems];
        int idx = 0;
        Node curr = head;
        while(curr != null) {
            arr[idx++] = curr.data;
            curr = curr.next;
        }
        return arr;
    }

    public String toString() {
        if(isEmpty()) return "[]";
        String str = "";
        Node curr = head;
        while(curr != null) {
            str += (curr.data + " ");
            curr = curr.next;
        }
        return '[' + str.trim().replaceAll(" ", ", ") + ']';
    }

    private class SetIterator implements MyIterator<E> {
        Node ptr = head;
        int expectedModCount = modCount;
        boolean canRemove = false;
        E removed = null;

        @Override
        public boolean hasNext() {
            return ptr != null;
        }

        @Override
        public E next() {
            if(modCount != expectedModCount) 
                throw new java.util.ConcurrentModificationException();

            if(!hasNext())
                throw new java.util.NoSuchElementException();
                
            E value = removed = ptr.data;
            removed = value;
            ptr = ptr.next;
            canRemove = true;
            return value;
        }
        
        @Override 
        public void remove() {
            if(modCount != expectedModCount) 
                throw new java.util.ConcurrentModificationException();
            
            if(!canRemove)
                throw new IllegalStateException();

            Set.this.remove(removed);
            expectedModCount++;
            canRemove = false;
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
        
        scan.close();
        System.out.println(set);
    }
}
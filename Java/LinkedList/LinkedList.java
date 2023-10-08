interface Iterator<E> {
    boolean hasNext();
    E next();
    void remove();
}

class LinkedList<E> {
    static class Node<E> {
        E data;
        Node<E> prev;
        Node<E> next;

        public Node(E data, Node<E> prev, Node<E> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    Node<E> start, end;
    int size;
    int modCount = 0;

    public LinkedList() {
        clear();
    }

    public boolean add(E data) {
        add(size(), data);
        return true;
    }

    public void add(int idx, E data) {
        if(idx < 0 || idx > size) throw new IndexOutOfBoundsException();
        addBefore(getNode(idx, 0, size()), data);
    }

    void addBefore(Node<E> p, E data) {
        Node<E> newNode = new Node<>(data, p.prev, p);
        newNode.prev.next = newNode;
        p.prev = newNode;
        size++;
        modCount++;
    }

    public E get(int idx) {
        return getNode(idx).data;
    }

    Node<E> getNode(int idx) {
        return getNode(idx, 0, size() - 1);
    }

    Node<E> getNode(int idx, int low, int high) {
        if(idx < low || idx > high) throw new IndexOutOfBoundsException();

        Node<E> p;

        if(idx < size() / 2) {
            p = start.next;
            for(int i = 0; i < idx; i++) p = p.next;
        } else {
            p = end;
            for(int i = size(); i > 0; i--) p = p.prev;
        }

        return p;
    }

    public E set(int idx, E data) {
        Node<E> p = getNode(idx);
        E old = p.data;
        p.data = data;
        return old;
    }

    public E remove(int idx) {
        return remove(getNode(idx));
    }

    E remove(Node<E> p) {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        size--;
        modCount++;
        return p.data;
    }

    public void clear() {
        size = 0;
        start = new Node<E>(null, null, null);
        end = new Node<E>(null, start, null);
        modCount++;
    }

    public boolean isEmpty() {
        return size <= 0;
    }

    public int size() {
        return size;
    }

    public String toString() {
        if(isEmpty()) return "[]";
        String res = "";
        ListIterator it = iterator();
        while(it.hasNext()) 
            res += (it.next().toString() + " ");
        return res;
    }

    public ListIterator iterator() {
        return new ListIterator();
    }

    class ListIterator implements Iterator<E> {
        int expectedModCount = modCount;
        Node<E> current = start.next;
        boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current != end;
        }

        @Override
        public E next() {
            if(!hasNext()) throw new java.util.NoSuchElementException();
            if(modCount != expectedModCount) throw new java.util.ConcurrentModificationException();
            E item = current.data;
            current = current.next;
            okToRemove = true;
            return item;
        }

        @Override
        public void remove() {
            if(modCount != expectedModCount) throw new java.util.ConcurrentModificationException();
            if(!okToRemove) throw new IllegalStateException();
            
            LinkedList.this.remove(current.prev);
            expectedModCount++;
            okToRemove = false;
        }
    }    
}

class Main {
    public static void main(String[] args) {
        LinkedList<Character> ll = new LinkedList<>();

        for(char i = 65; i <= 90; i++) {
            ll.add(i);
        }
        System.out.println(ll);
    }
}
public interface Heap<E> {
    void insert(E x);
    E findMin();
    E deleteMin();
    boolean isEmpty();
    void makeEmpty();
    void print();
}
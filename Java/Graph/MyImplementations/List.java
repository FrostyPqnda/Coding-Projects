package MyImplementations;

public interface List<E> {
    boolean add(E x);
    void clear();
    boolean contains(E x);
    E get(int index);
    int indexOf(E x);
    boolean isEmpty();
    boolean remove(E x);
    E set(int index, E x);
    int size();
}

interface HashTable<E> {
    void insert(E x);
    void remove(E x);
    boolean contains(E x);
    int count();
    void clear();
    void display();
}
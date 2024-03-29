public interface MyGraph<E> {
    void addEdge(E src, E dest);
    void addVertex(E data);
    boolean hasVertex(E data);
    boolean hasEdge(E src, E dest);
    void print();
}

package MyImplementations;

/**
 * Iterator interface
 */
public interface Iterator<E> {
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
    void remove();
}
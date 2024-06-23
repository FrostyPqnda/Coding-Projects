package MyImplementations;

/**
 * Iterable interface
 */
public interface Iterable<E> {
    /**
     * iterator()
     * 
     * @return A MyIterator object.
     */
    Iterator<E> iterator();
}
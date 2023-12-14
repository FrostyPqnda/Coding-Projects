package MyImplementations;

/**
 * MyIterable interface
 */
public interface MyIterable<E> {
    /**
     * iterator()
     * 
     * @return A MyIterator object.
     */
    MyIterator<E> iterator();
}
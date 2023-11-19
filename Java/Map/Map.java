import MyImplementations.*;

/**
 * Map interface
 */
public interface Map<K, V> {
    /**
     * Entry interface
     */
    interface Entry<K, V> {
        /**
         * getKey()
         * 
         * @return Key associated with a map entry
         */
        K getKey();

        /**
         * getValue()
         * 
         * @return Value associated with a key in the map entry
         */
        V getValue();

        /**
         * equals(Entry<K, V> o)
         * 
         * @param o Entry to be compared with
         * @return True if the entry matches the compared entry
         */
        boolean equals(Entry<K, V> o);
    }
    
    /**
     * clear()
     * 
     * Removes all entries in the map
     */
    void clear();

    /**
     * containsKey(K key) 
     * 
     * @param key Key to be searched
     * @return True if the key exists in the map
     */
    boolean containsKey(K key);

    /**
     * containsValue(V value)
     *  
     * @param value Value to be searched
     * @return True if the value exists in the map
     */
    boolean containsValue(V value);

    /**
     * entrySet()
     * 
     * @return A set of map entries
     */
    Set<Entry<K, V>> entrySet();

    /**
     * get(K key)
     * 
     * @param key Key associated with the value to be returned
     * @return The value stored in the key
     */
    V get(K key);

    /**
     * isEmpty()
     * 
     * @return True if map is empty
     */
    boolean isEmpty();

    /**
     * keySet()
     * 
     * @return A set of the map's keys
     */
    Set<K> keySet();

    /**
     * put(K key, V value)
     * 
     * @param key Key to be inserted into the map
     * @param value Value to be inserted into the map
     * @return Value inserted into the map
     */
    V put(K key, V value);

    /**
     * remove(K key)
     * 
     * @param key Key associated with the value to be removed
     * @return Value removed from the map
     */
    V remove(K key);

    /**
     * size()
     * 
     * @return Number of entries in the map
     */
    int size();

    /**
     * equals(Map<K, V> o)
     * 
     * @param o Map to be compared to
     * @return True if all entries of a map matches the entries of o
     */
    boolean equals(Map<K, V> o);
}
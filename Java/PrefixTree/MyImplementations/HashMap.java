package MyImplementations;

@SuppressWarnings("unchecked")
public class HashMap<K, V> implements Map<K, V>{
    /**
     * Inner class used by the HashMap class
     * 
     * Implements the Entry interface defined in the
     * Map class
     */
    class HashEntry implements Map.Entry<K, V>{
        K key; // Key stored in the entry
        V value; // Value stored in the entry
        HashEntry next; // Next entry

        public HashEntry(K key, V value) {
            this.key = key; // Set instance's key to key
            this.value = value; // Set instance's value to value
            next = null;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public boolean equals(Entry<K, V> o) {
            return key.equals(o.getKey()) && value.equals(o.getValue());
        }
        
        public String toString() {
            return String.format("%s=%s", key, value);
        }
    }

    HashEntry[] map; // Underlying array for the HashMap class
    int numItems; // No. of items in the HashMap
    static final int DEFAULT_SIZE = 101; // Default size for the HashMap

    /**
     * HashMap()
     * 
     * Default constructor for the HashMap class
     * Initialize map to be of size DEFAULT_SIZE
     */
    public HashMap() {
        this(DEFAULT_SIZE);
    }

    /**
     * HashMap(int initCapacity)
     * 
     * @param initCapacity Initial size of the underlying map array
     */
    public HashMap(int initCapacity) {
        numItems = 0; // Set numItems to 0
        map = new HashMap.HashEntry[nextPrime(initCapacity)];
    }

    @Override   
    public void clear() {
        numItems = 0;
        for(int i = 0; i < map.length; i++)
            map[i] = null;
    }

    @Override
    public boolean containsKey(K key) {
        for(int i = 0; i < map.length; i++) 
            if(map[i] != null)
                if(map[i].getKey().equals(key))
                    return true;
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        for(int i = 0; i < map.length; i++) 
            if(map[i] != null)
                if(map[i].getValue().equals(value))
                    return true;
        return false;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new Set<>();
        for(HashEntry me : map) 
            if(me != null)
                set.add(me);
        return set;
    }

    @Override
    public V get(K key) {
        int idx = find(key);
        return map[idx] != null ? map[idx].getValue() : null;
    }

    @Override
    public boolean isEmpty() {
        return numItems == 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new Set<>();
        for(HashEntry me : map)
            if(me != null)
                keys.add(me.getKey());
        return keys;
    }

    @Override
    public Set<V> values() {
        Set<V> values = new Set<>();
        for(HashEntry me : map)
            if(me != null)
            values.add(me.getValue());
        return values;
    }

    @Override
    public V put(K key, V value) {
        rehash(); // Expand the underlying array if it is full
        HashEntry e = new HashEntry(key, value);
        int idx = find(key); // Find a spot to insert the HashEntry object using double hashing
        V val = map[idx] != null ? map[idx].getValue() : null; // Set the return value to either the previous value stored by the key or null
        e.next = map[idx]; // Set the next HashEntry to map[idx]
        map[idx] = e; // Set map[idx] to the HashEntry object
        numItems++;
        return val;
    }

    @Override
    public V remove(K key) {
        if(!containsKey(key)) return null; // If key does not exist, return null
        int idx = find(key); // Find the position of where the key is stored
        V old = map[idx].getValue(); // Get the old value stored in the key 
        map[idx] = null; // Set map[idx] to null to remove the HashEntry from the HashMap
        return old;
    }

    @Override
    public int size() {
        return numItems;
    }

    @Override
    public boolean equals(Map<K, V> o) {
        HashMap<K, V> cmp = (HashMap<K, V>)o;
        for(int i = 0; i < map.length; i++) 
            if(map[i] != null && !map[i].equals(cmp.map[i]))
                return false;
        return true;
    }

    /**
     * toString()
     * 
     * @return A String representation of the HashMap class
     */
    public final String toString() {
        if(isEmpty()) return "{}";
        String str = "";
        for(HashEntry me : map)
            if(me != null)
                str += (me + ", ");
        return '{' + str.substring(0, str.length() - 2) + '}';
    }
    
    /**
     * find(K key)
     * 
     * @param key Key to be inserted into the HashMap
     * @return A possible location to insert key into the HashMap using double hashing
     */
    private final int find(K key) {
        int h1 = hash1(key); // Get the first hash value of key
        int h2 = hash2(key); // Get the second hash value of key
        // Loop until we find a possible index to insert key into the HashMap
        while(map[h1] != null && !map[h1].getKey().equals(key)) {
            h1 += h2;
            h1 %= map.length;
        }
        return h1;
    }

    /**
     * hash1(K key)
     * 
     * @param key Key to be hashed
     * @return A possible index to insert into the HashMap
     */
    private final int hash1(K key) {
        int hashCode = key.hashCode(); // Get the hash code of key
        hashCode %= map.length; // Set hashCode to the mod of itself and the map size
        if(hashCode < 0) hashCode += map.length; // Increment hashCode by the map size if it is less than 0
        return hashCode; // Return the hash code
    }

    /**
     * hash2(K key)
     * 
     * @param key Key to be hashed
     * @return A second possible index to insert into the HashMap
     */
    private final int hash2(K key) {
        int prime = nextPrime(map.length); // Get a prime number >= map size
        return prime - hash1(key) % prime; // Return the second hash value of key
    } 

    /**
     * nextPrime(int n)
     * 
     * @param n Starting value
     * @return A prime number >= n
     */
    private final int nextPrime(int n) {
        if(n < 2) return 2; // Return the lowest prime number
        while(!isPrime(n)) n++; // Increment until n is a prime number
        return n; // Return the prime number
    }

    /**
     * isPrime(int n)
     * 
     * @param n Value to be checked
     * @return True if n is a prime number
     */
    private final boolean isPrime(int n) {
        if(n < 2) return false; // End if n is less than the lowest prime number
        // Iterate until we reach the end or n is divisible by i
        for(int i = 2; i <= n / 2; i++)
            if(n % i == 0) return false;
        return true; // n is a prime number
    }

    /**
     * rehash()
     * 
     * Expands the underlying map array used by the HashMap class
     */
    private final void rehash() {
        if(numItems < map.length) return; // End if numItems is less than the map size
        HashEntry[] old = map; // Create a copy of the old map
        map = new HashMap.HashEntry[nextPrime(2 * old.length)]; // Double the size of map array to a size of nextPrime(2 * old map size)
        numItems = 0; // Reset numItems back to 0
        // Copy back into the map array
        for(HashEntry me : old)
            if(me != null)
                put(me.getKey(), me.getValue());
        return;
    }
}

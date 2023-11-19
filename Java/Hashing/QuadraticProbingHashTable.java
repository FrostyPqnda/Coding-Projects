import MyImplementations.HashingUtility;

/**
 * class QuadraticProbingHashTable
 * 
 * Implements the HashTable interface and uses quadratic probing
 * to perform its operations.
 */
@SuppressWarnings("unchecked")
public class QuadraticProbingHashTable<E> implements HashTable<E> {
    /**
     * class QuadraticProbingHashingUtility
     * 
     * Private inner class that implements the HashingUtility interface 
     * and all of its operation to be used by its parent class.
     */
    private class QuadraticProbingHashingUtility implements HashingUtility<E> {
        /**
         * find(E x)
         * 
         * @param x Item to be hashed into the table
         * @return Index to insert x into the table
         */
        @Override
        public int find(E x) {
            int idx = hash(x); // Get hash value of x
            int offset = 1; // Offset for quadratic probing
            // Iterate over the table until an available spot to insert x is found
            while(table[idx] != null && !table[idx].equals(x)) {
                idx += offset; // Increment idx by offset -> idx += f(i)
                offset += 2; // Increment offset by 2 -> f(i) = i * i
                // Decrement idx by table size if its greater than
                // or equal to the table size
                if(idx >= table.length)
                    idx -= table.length;
            }
            return idx; // Available position to insert x
        }

        /**
         * isActive(int idx)
         * 
         * @param idx Index in the table
         * @return True if table[idx] has an element
         */
        @Override
        public boolean isActive(int idx) {
            return table[idx] != null;
        }

        /**
         * hash(E x)
         * 
         * @param x Item to be hashed
         * @return Hash value of x
         */
        @Override
        public int hash(E x) {
            int hashCode = x.hashCode(); // Hash code of x
            hashCode %= table.length; // Mod hashCode by the table size
            // Increment hashCode if less than 0
            if(hashCode < 0)    
                hashCode += table.length;
            return hashCode; // Return hash of x
        }

        /**
         * void rehash()
         * 
         * Copies all data to a bigger array while maintaining 
         * the relative positioning of each element.
         */
        @Override
        public void rehash() {
            // End if table is not half full
            if(numItems < table.length / 2) return;
            
            E[] old = table; // Copy of the original table
            table = (E[]) new Object[nextPrime(2 * old.length)]; // Set table to a new size of nextPrime(2 * table size)
            numItems = 0; // Reset numItems back to 0
            
            // Iterate over the original table and copy it into
            // a bigger table
            for(E item : old) 
                if(item != null) 
                    insert(item);
        }

        /**
         * nextPrime(int n)
         * 
         * @param n Starting number
         * @return Next prime number after n
         */
        @Override
        public int nextPrime(int n) {
            // Return 2 if n is less than or equal to 2
            if(n <= 2) return 2; 
            // Loop until n is a prime number
            while(!isPrime(n))
                n++;
            return n; // Return n as a prime number
        }

        /**
         * isPrime(int n)
         * 
         * @param n Integer value to check
         * @return True if n is prime
         */
        @Override
        public boolean isPrime(int n) {
            // Return false because smallest possible prime is 2
            if(n < 2) return false;
            // Iterate from i = 2 up to i = n / 2
            for(int i = 2; i <= n / 2; i++)
                // Return false if n is divisible by i
                if(n % i == 0) 
                    return false;
            return true; // n is a prime number
        }  
    }
    static final int DEFAULT_SIZE = 101; // Default table size
    int numItems; // No. of items in the table
    E[] table; // Underlying table
    HashingUtility<E> hu; // HashingUtility object

    /**
     * LinearProbingHashTable()
     * 
     * Default constructor for the LinearProbingHashTable class
     * Initialize table to be of size DEFAULT_SIZE
     */
    public QuadraticProbingHashTable() {
        this(DEFAULT_SIZE);
    }

    /**
     * QuadraticProbingHashTable(int capacity)
     * 
     * @param capacity Size of the table
     * 
     * Parameterized constructor for the QuadraticProbingHashTable class
     * Instantiate hu to be a QuadraticProbingHashingUtility object
     * Set numItems to 0
     * Assign table to be an array of size nextPrime(capacity);
     */
    public QuadraticProbingHashTable(int capacity) {
        hu = new QuadraticProbingHashingUtility();
        numItems = 0;
        table = (E[]) new Object[hu.nextPrime(capacity)];
    }

    /**
     * insert(E x)
     * 
     * @param x Item to be inserted into the table
     */
    @Override
    public void insert(E x) {
        hu.rehash(); // Enlarge table if it is full
        int idx = hu.find(x); // Find available spot to insert x
        if(hu.isActive(idx)) return; // End if spot is not aviable
        table[idx] = x; // Set table[idx] to x
        numItems++; // Increment nummItems
        return;
    }

    /**
     * remove(E x)
     * 
     * @param x Item to be removed from the table
     */
    @Override
    public void remove(E x) {
        int idx = hu.find(x); // Find the index containing x
        if(!hu.isActive(idx)) return; // End if table[idx] is not active
        table[idx] = null; // Set table[x] to null
        numItems--; // Decrement numItems
    }

    /**
     * count()
     * 
     * @return No. of items in the table
     */
    @Override
    public int count() {
        return numItems;
    }

    /**
     * clear()
     * 
     * Removes all items in the table
     */
    @Override
    public void clear() {
        table = (E[]) new Object[table.length];
        numItems = 0;
    }

    /**
     * contains(E x)
     * 
     * @param x Item to be searched
     * @return True if x exists
     */
    @Override
    public boolean contains(E x) {
        return hu.isActive(hu.find(x));
    }

    /**
     * display()
     * 
     * Print the table
     */
    @Override
    public void display() {
        for(int i = 0; i < table.length; i++)
            System.out.printf("%d: %s\n", i, table[i]);
    }
}

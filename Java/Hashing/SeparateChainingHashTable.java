/**
 * class SeparateChainingHashTable
 * 
 * Implements the HashTable interface and uses separate chaining
 * (linked lists) to perform its operations.
 */
public class SeparateChainingHashTable<E> implements HashTable<E> {
    /**
     * class SeparateChainingHashingUtility
     * 
     * Private inner class that implements the HashingUtility interface 
     * and all of its operation to be used by its parent class.
     */
    private class SeparateChainingHashingUtility implements HashingUtility<E> {
        @Override
        public int find(E x) {
            throw new UnsupportedOperationException("Unimplemented method 'find'");
        }

        @Override
        public boolean isActive(int idx) {
            throw new UnsupportedOperationException("Unimplemented method 'isActive'");
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
         * rehash()
         * 
         * Enlarge the table
         */
        @Override
        @SuppressWarnings("unchecked")
        public void rehash() {
            // End if table is not full
            if(numItems < table.length) return;

            LinkedList<E>[] old = table; // Make a copy of the table
            table = new LinkedList[nextPrime(2 * old.length)]; // Set table to a new size of nextPrime(2 * table size)
            numItems = 0; // Reset numItems back to 0
            
            // Instantiate each LinkedList in the table
            for(int i = 0; i < table.length; i++) 
                table[i] = new LinkedList<>();

            // Iterate over the original table and copy into a
            // bigger table
            for(int i = 0; i < old.length; i++) {
                MyIterator<E> it = old[i].iterator();
                while(it.hasNext())
                    insert(it.next());
            }
        }

        /**
         * nextPrime(int n)
         * 
         * @param n Starting number
         * @return Next prime number after n
         */
        public int nextPrime(int n) {
            // If n < 2, next prime will be 2
            if(n < 2) return 2;
            // Loop until n is a prime number
            while(!isPrime(n)) {
                n++;
            }
            return n; // Return n
        }

        /**
         * isPrime(int n)
         * 
         * @param n Integer
         * @return true if n is prime
         */
        public boolean isPrime(int n) {
            // Return false because the smallest possible prime is 2
            if(n < 2) return false;
            
            // Iterate starting from i = 2 all the way up to 
            // i = n / 2.
            for(int i = 2; i <= n / 2; i++) {
                // Return false if n % i == 0
                if(n % i == 0) {
                    return false;
                }
            }
            return true; // n is a prime number
        }
    }
    static final int DEFAULT_SIZE = 101; // Default table size
    int numItems; // No. of items in the table
    LinkedList<E>[] table; // Underlying table
    HashingUtility<E> hu; // HashingUtility object

    /**
     * SeparateChainingHashTable()
     * 
     * Default constructor for the SeparateChainingHashTable class
     * Initialize table to be of size DEFAULT_SIZE
     */
    public SeparateChainingHashTable() {
        this(DEFAULT_SIZE);
    }

    /**
     * SeparateChainingHashTable(int capacity)
     * 
     * @param capacity Size of the table
     * 
     * Parameterized constructor for the SeparateChainingHashTable class
     * Instantiate hu to be a SeparateChainingHashingUtility object
     * Set numItems to 0
     * Assign table to be an array of size nextPrime(capacity)
     * Instantiate each LinkedList in the table
     */
    @SuppressWarnings("unchecked")
    public SeparateChainingHashTable(int capacity) {
        hu = new SeparateChainingHashingUtility();
        numItems = 0;
        table = new LinkedList[hu.nextPrime(capacity)];
        for(int i = 0; i < table.length; i++) 
            table[i] = new LinkedList<>();
    }

    /**
     * insert(E x)
     * 
     * @param x Item to be inserted into the table
     */
    @Override
    public void insert(E x) {
        // Call LinkedList.insert to insert x
        if(table[hu.hash(x)].insert(x)) 
            numItems++; // Increment numItems
        hu.rehash(); // Enlarge table if it is full
        return;
    }

    /**
     * remove(E x)
     * 
     * @param x Item to be removed from the table
     */
    @Override
    public void remove(E x) {
        // Call the LinkedList.remove to remove x
        if(table[hu.hash(x)].remove(x)) 
            numItems--; // Decrement numItems
        return;
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
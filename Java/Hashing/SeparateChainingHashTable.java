public class SeparateChainingHashTable<E> implements HashTable<E> {
    private class SeparateChainingHashingUtility implements HashingUtility<E> {
        @Override
        public int find(E x) {
            return hash(x);
        }

        @Override
        public boolean isActive(int idx) {
            return table[idx] != null;
        }

        @Override
        public int hash(E x) {
            int hashCode = x.hashCode();
            hashCode %= table.length;
            if(hashCode < 0) 
                hashCode += table.length;
            return hashCode;
        }

        @Override
        @SuppressWarnings("unchecked")
        public void rehash() {
            if(numItems < table.length) return;

            LinkedList<E>[] old = table;
            table = new LinkedList[nextPrime(2 * old.length)];
            numItems = 0;
            
            for(int i = 0; i < table.length; i++) 
                table[i] = new LinkedList<>();

            for(int i = 0; i < old.length; i++) {
                MyIterator<E> it = old[i].iterator();
                while(it.hasNext())
                    insert(it.next());
            }
        }

        @Override
        public int nextPrime(int n) {
            if(n <= 2) return 2;
            while(!isPrime(n))
                n++;
            return n;
        }

        @Override
        public boolean isPrime(int n) {
            if(n < 2) return false;

            for(int i = 2; i <= n / 2; i++)
                if(n % i == 0) 
                    return false;

            return true;
        }
    }
    static final int DEFAULT_SIZE = 101;
    int numItems;
    LinkedList<E>[] table;
    HashingUtility<E> schu;

    public SeparateChainingHashTable() {
        this(DEFAULT_SIZE);
    }

    @SuppressWarnings("unchecked")
    public SeparateChainingHashTable(int capacity) {
        schu = new SeparateChainingHashingUtility();
        numItems = 0;
        table = new LinkedList[schu.nextPrime(capacity)];
        for(int i = 0; i < table.length; i++) 
            table[i] = new LinkedList<>();
    }

    @Override
    public void insert(E x) {
        schu.rehash();
        table[schu.hash(x)].insert(x);
        numItems++; 
        return;
    }

    @Override
    public void remove(E x) {
        table[schu.hash(x)].remove(x);
        numItems--;
        return;
    }

    @Override
    public void display() {
        for(int i = 0; i < table.length; i++)
            System.out.printf("%d: %s\n", i, table[i]);
    }
}